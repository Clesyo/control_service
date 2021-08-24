package com.app.control.api.models.dto;

import com.app.control.api.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String email;
	private String name;
	private Integer limited;
	
	public UserDTO(Long id, String email, String name, Integer limited) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.limited = limited;
	}
	
	
	public static UserDTO convertToDto(User user) {
		return new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getLimited());	
	}



	
}
