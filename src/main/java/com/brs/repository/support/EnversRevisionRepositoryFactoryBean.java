package com.brs.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.envers.DefaultRevisionEntity;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.history.support.RevisionEntityInformation;

public class EnversRevisionRepositoryFactoryBean
		extends
		JpaRepositoryFactoryBean<EnversRevisionRepository<Object, Serializable, Long>, Object, Serializable> {

	private Class<?> revisionEntityClass;

	/**
	 * Configures the revision entity class. Will default to
	 * {@link DefaultRevisionEntity}.
	 * 
	 * @param revisionEntityClass
	 */
	public void setRevisionEntityClass(Class<?> revisionEntityClass) {
		this.revisionEntityClass = revisionEntityClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean
	 * #createRepositoryFactory(javax.persistence.EntityManager)
	 */
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		return new RevisionRepositoryFactory(entityManager, revisionEntityClass);
	}

	/**
	 * Repository factory creating {@link RevisionRepository} instances.
	 * 
	 * @author Oliver Gierke
	 */
	private static class RevisionRepositoryFactory extends JpaRepositoryFactory {

		private final RevisionEntityInformation revisionEntityInformation;

		/**
		 * Creates a new {@link RevisionRepositoryFactory} using the given
		 * {@link EntityManager} and revision entity class.
		 * 
		 * @param entityManager
		 *            must not be {@literal null}.
		 * @param revisionEntityClass
		 *            can be {@literal null}, will default to
		 *            {@link DefaultRevisionEntity}.
		 */
		public RevisionRepositoryFactory(EntityManager entityManager,
				Class<?> revisionEntityClass) {

			super(entityManager);
			Class<?> revisionEntityClazz = revisionEntityClass == null ? DefaultRevisionEntity.class
					: revisionEntityClass;
			this.revisionEntityInformation = DefaultRevisionEntity.class
					.equals(revisionEntityClazz) ? new DefaultRevisionEntityInformation()
					: new ReflectionRevisionEntityInformation(
							revisionEntityClazz);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.jpa.repository.support.JpaRepositoryFactory
		 * #getTargetRepository
		 * (org.springframework.data.repository.core.RepositoryMetadata,
		 * javax.persistence.EntityManager)
		 */
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		protected <T, ID extends Serializable> JpaRepository<?, ?> getTargetRepository(
				RepositoryMetadata metadata, EntityManager entityManager) {

			JpaEntityInformation<T, Serializable> entityInformation = (JpaEntityInformation<T, Serializable>) getEntityInformation(metadata
					.getDomainType());
			return new EnversRevisionRepositoryImpl(entityInformation,
					revisionEntityInformation, entityManager);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.jpa.repository.support.JpaRepositoryFactory
		 * #getRepositoryBaseClass
		 * (org.springframework.data.repository.core.RepositoryMetadata)
		 */
		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return EnversRevisionRepositoryImpl.class;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.springframework.data.repository.core.support.RepositoryFactorySupport
		 * #getRepository(java.lang.Class, java.lang.Object)
		 */
		@Override
		public <T> T getRepository(Class<T> repositoryInterface,
				Object customImplementation) {

			if (RevisionRepository.class.isAssignableFrom(repositoryInterface)) {

				Class<?>[] typeArguments = GenericTypeResolver
						.resolveTypeArguments(repositoryInterface,
								RevisionRepository.class);
				Class<?> revisionNumberType = typeArguments[2];

				if (!revisionEntityInformation.getRevisionNumberType().equals(
						revisionNumberType)) {
					throw new IllegalStateException(
							String.format(
									"Configured a revision entity type of %s with a revision type of %s "
											+ "but the repository interface is typed to a revision type of %s!",
									repositoryInterface,
									revisionEntityInformation
											.getRevisionNumberType(),
									revisionNumberType));
				}
			}

			return super.getRepository(repositoryInterface,
					customImplementation);
		}
	}
}
