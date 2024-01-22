package com.desafio.cubonetwork.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "creation_date", nullable = false)
	@JsonProperty("createdAt")
	private LocalDateTime creationDate = LocalDateTime.now();

	public BaseEntity() {
		super();
		this.creationDate = LocalDateTime.now();
	}

	public BaseEntity(Long id, LocalDateTime creationDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		if (creationDate == null) {
			this.creationDate = LocalDateTime.now();
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		return Objects.equals(this.id, other.id);
	}
}
