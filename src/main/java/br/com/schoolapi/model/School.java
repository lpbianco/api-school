package br.com.schoolapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.schoolapi.enun.Status;
import lombok.Data;

@Data
@Entity
@Table(name = "School")
public class School{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "Inep")
	private Integer inep;

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	@Column(name = "Number")
	private String number;
	
	@Column(name = "Status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Level> levels;

	@Column(name = "CreatedAt")
	private String createdAt;

	@Column(name = "UpdatedAt")
	private String updatedAt;

	@Column(name = "DeletedAt")
	private String deletedAt;
}
