
package br.com.schoolapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "Level")
@JsonIgnoreProperties(value = { "school" })
public class Level {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Code")
	private String code;

	@Column(name = "Status")
	private String status;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name="school_id", nullable=false) private School school;
	 */

	@Column(name = "CreatedAt")
	private String createdAt;

	@Column(name = "UpdatedAt")
	private String updatedAt;

	@Column(name = "DeletedAt")
	private String deletedAt;
}
