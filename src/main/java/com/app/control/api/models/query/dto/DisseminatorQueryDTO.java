package com.app.control.api.models.query.dto;

import com.app.control.api.models.Disseminator;
import com.app.control.api.models.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DisseminatorQueryDTO {

	private Long id;
	private String token;
	private String name;
	private String email;
	private String telephone;
	private String cellphone;
	private String zipeCode;
	private String adress;
	private Integer numberHouse;
	private String complement;
	private String district;
	private String city;
	private String uf;
	private String observation;

	private UserDTO user;

	public static DisseminatorQueryDTO convertQueryDTO(Disseminator d) {
		return new DisseminatorQueryDTO(d.getId(), d.getToken(), d.getName(), d.getEmail(), d.getTelephone(),
				d.getCellphone(), d.getZipeCode(), d.getAdress(), d.getNumberHouse(), d.getComplement(),
				d.getDistrict(), d.getCity(), d.getUf(), d.getObservation(), UserDTO.convertToDto(d.getUser()));
	}
}
