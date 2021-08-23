package com.app.control.api.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@Table(name = "services")
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@ApiModelProperty("Titulo do serviço")
	@NotEmpty(message = "Campo Nome deve ser preenchido.")
	private String name;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Descrição do serviço deve ser preenchido.")
	@ApiModelProperty("Descrição do serviço - tudo sobre o serviço")
	private String description;
	@Column(nullable = false)
	@NotEmpty(message = "Campo Datalhes do serviço deve ser preenchido.")
	@ApiModelProperty("Detalha como, quando e onde o serviço pode ser realizado.")
	private String details;
	@Column(nullable = false)
	@NotNull(message = "Campo Valor do serviço deve ser preenchido.")
	@ApiModelProperty("Valor do serviço de modo geral")
	private BigDecimal amount;
	
	@ManyToOne
	@JoinColumn(name = "disseminator_id", nullable = false)
	private Disseminator disseminator;
}
