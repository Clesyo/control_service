package com.app.control.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
	
	private String login;
	private String token;

}
