package com.hxchassi.ms.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Entidade genérica de exemplo
 */
@Entity
public class GenericEntity {

	@JsonIgnore
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 50)
	private String description;
	
	@Column(nullable = false, length = 100)
	@Email
	private String email;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "GenericEntity [id=" + id + ", description=" + description + ", email=" + email + "]";
	}
	
}
