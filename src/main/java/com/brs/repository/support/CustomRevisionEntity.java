package com.brs.repository.support;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@Table(name = "revision_info")
@RevisionEntity(CustomEnversListener.class)
public class CustomRevisionEntity
{
    private int id;
    private long timestamp;
    private Integer userid;
    private String name;
    
    @Id
    @RevisionNumber
    @Column(name = "revision_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVISION_INFO_SEQ")
	@SequenceGenerator(name = "REVISION_INFO_SEQ", sequenceName = "REVISION_INFO_SEQ", allocationSize = 1)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @RevisionTimestamp
    @Column(name = "revision_timestamp")
    public long getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(long timestamp)
    {
        this.timestamp = timestamp;
    }

    @Column(name = "user_id")
    public Integer getUserid()
    {
        return userid;
    }

    public void setUserid(Integer userid)
    {
        this.userid = userid;
    }
    
    @Column(name = "user_name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}