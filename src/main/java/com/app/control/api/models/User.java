package com.app.control.api.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Nome deve ser preenchido.")
	@ApiModelProperty("Nome do usuário")
	private String name;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Email deve ser preenchido.")
	@ApiModelProperty("Emial do usuário")
	private String email;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Senha deve ser preenchido.")
	@ApiModelProperty("Senha do usuário")
	private String password;
	@Column(columnDefinition = "int(2) default 1")
	@NotNull(message = "O campo Limite deve ser informado.")
	@ApiModelProperty("Limite de divulgador por usuário")
	private Integer limited;
	
	@OneToMany(mappedBy = "user")
	private List<Disseminator> disseminators;
}
