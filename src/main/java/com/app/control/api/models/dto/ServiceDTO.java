package com.app.control.api.models.dto;

import java.math.BigDecimal;

import com.app.control.api.models.Services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {

	private Long id;
	private String name;
	private String description;
	private String details;
	private BigDecimal amount;

	private Long disseminator;

	public static ServiceDTO convertToDto(Services dto) {
		return new ServiceDTO(dto.getId(), dto.getName(), dto.getDescription(), dto.getDetails(), dto.getAmount(),
				dto.getDisseminator().getId());
	}
}
