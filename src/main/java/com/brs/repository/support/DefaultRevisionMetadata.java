package com.brs.repository.support;
import org.joda.time.DateTime;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.util.Assert;


public class DefaultRevisionMetadata implements RevisionMetadata<Integer> {

	private final CustomRevisionEntity entity;

	/**
	 * Creates a new {@link DefaultRevisionMetadata}.
	 * 
	 * @param entity must not be {@literal null}.
	 */
	public DefaultRevisionMetadata(CustomRevisionEntity entity) {

		Assert.notNull(entity);
		this.entity = entity;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.history.RevisionMetadata#getRevisionNumber()
	 */
	public Integer getRevisionNumber() {
		return entity.getId();
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.history.RevisionMetadata#getRevisionDate()
	 */
	public DateTime getRevisionDate() {
		return new DateTime(entity.getTimestamp());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.history.RevisionMetadata#getDelegate()
	 */
	@SuppressWarnings("unchecked")
	public <T> T getDelegate() {
		return (T) entity;
	}
}
