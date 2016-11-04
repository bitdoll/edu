package com.spc.jpa.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author lKJ
 */
@MappedSuperclass
public abstract class AbstractHistoryEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "version")
	@Version
	long version;

	@Column(name = "created_by")
	String createdBy;

	@Column(name = "created_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	LocalDateTime createdTime;

	@Column(name = "modified_by")
	String modifiedBy;

	@Column(name = "modified_time")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	LocalDateTime modifiedTime;

	public AbstractHistoryEntity() {
		// empty constructor
	}

	public AbstractHistoryEntity(String createdBy) {
		this.createdBy = createdBy;
	}

	@PrePersist
	public void prePersist() {
		this.createdTime = LocalDateTime.now();
		this.modifiedTime = this.createdTime;
	}

	@PreUpdate
	public void preUpdate() {
		modifiedTime = LocalDateTime.now();
	}

	@ApiModelProperty(hidden = true)
	@JsonIgnore
	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@ApiModelProperty(readOnly = true)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@ApiModelProperty(readOnly = true)
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ApiModelProperty(readOnly = true)
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	@ApiModelProperty(readOnly = true)
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
}
