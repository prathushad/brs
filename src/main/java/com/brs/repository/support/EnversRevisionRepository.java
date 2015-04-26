package com.brs.repository.support;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.history.Revision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.history.RevisionRepository;



/**
 * Convenience interface to allow pulling in {@link JpaRepository} and {@link RevisionRepository} functionality in one
 * go.
 */
@NoRepositoryBean
public interface EnversRevisionRepository<T, ID extends Serializable, N extends Number & Comparable<N>> extends
		RevisionRepository<T, ID, N>, JpaRepository<T, ID> {
	
	public Revision<N, T> findRevisionByDate(ID id, Date date, String dateFieldName);
	
	public Revision<N, T> findRevisionByEvent(ID id, Integer intValue, String intFieldName);
	
	public Integer findLastRaceRevision(Integer enrolleeID, Integer revisionID);
	
	public List<Object> findRacesCreatedForEnrollee(Integer enrolleeID, Integer revisionID);
	
	public Integer findLastRelationRevision(Integer sourceID, Integer targetID, Integer revisionID);
	
	public Revision<N, T> findLastSubscriberChangeRevision(ID id, Integer revValue);
	
	public Revision<N, T> findLastLocationRevision(ID id, Integer revValue);
}
