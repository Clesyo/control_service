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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "disseminators")
public class Disseminator {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@ApiModelProperty("Token será usado para identificar a empresa em caso especificos")
	private String token;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Nome/Razão deve ser preenchido")
	private String name;
	@Column(nullable = false, unique = true)
	@NotEmpty(message = "Campo Email deve ser preenchido")
	private String email;
	@ApiModelProperty("Telefone ou celular")
	private String telephone;
	@Column(nullable = false)
	@ApiModelProperty("Celular ou Whatsapp")
	@NotEmpty(message = "Campo Celular deve ser preenchido")
	private String cellphone;
	@Column(name = "zipe_code", nullable = false)
	@ApiModelProperty("CEP do estabelecimento")
	@NotEmpty(message = "Campo CEP deve ser preenchido.")
	private String zipeCode;
	@Column(nullable = false)
	@ApiModelProperty("Logradouro do endereço")
	@NotEmpty(message = "Campo Endereço deve ser preenchido.")
	private String adress;
	@Column(nullable = false)
	@NotNull(message = "Campo Número deve ser preenchido.")
	private Integer numberHouse;
	private String complement;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Bairro deve ser preenchido.")
	private String district;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Cidade deve ser preenchido.")
	private String city;
	@Column(nullable = false)
	@NotEmpty(message = "Campo UF deve ser preenchido.")
	private String uf;
	private String observation;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "disseminator")
	private List<Services> services;
}
