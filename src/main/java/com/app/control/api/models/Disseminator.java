package com.app.control.api.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "disseminators")
public class Disseminator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private String name;
	private String telephone;
	@Column(nullable = false)
	private String cellphone;
	@Column(name = "zipe_code", nullable = false)
	private String zipeCode;
	@Column(nullable = false)
	private String adress;
	@Column(nullable = false)
	private Integer numberHouse;
	private String complement;
	@Column(nullable = false)
	private String district;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String uf;
	private String observation;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "disseminator")
	private List<Service> services;
}
