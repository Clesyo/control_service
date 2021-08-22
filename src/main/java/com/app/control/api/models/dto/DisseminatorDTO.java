package com.app.control.api.models.dto;

import com.app.control.api.models.Disseminator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisseminatorDTO {

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

	private Long user;

	public static DisseminatorDTO convertToDto(Disseminator d) {
		return new DisseminatorDTO(d.getId(), d.getToken(), d.getName(), d.getEmail(), d.getTelephone(),
				d.getCellphone(), d.getZipeCode(), d.getAdress(), d.getNumberHouse(), d.getComplement(),
				d.getDistrict(), d.getCity(), d.getUf(), d.getObservation(), d.getUser().getId());
	}
}
