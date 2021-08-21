package com.app.control.api.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.models.User;
import com.app.control.api.models.dto.UserDTO;
import com.app.control.api.services.UserService;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping
	@ApiOperation("Busca todos os usuários")
	public List<User> all() {
		return userService.findAll();
	}

	@GetMapping("/id")
	@ApiOperation("Busca um usuário pelo seu código ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário encontrado"),
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	public UserDTO findById(Long id) {
		return UserDTO.convertToDto(userService.findById(id));
	}

	@GetMapping("/email")
	@ApiOperation("Busca um usuário pelo seu email")
	@ApiResponses({ @ApiResponse(code = 200, message = "Usuário encontrado"),
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	public UserDTO findByEmail(String email) {
		return UserDTO.convertToDto(userService.findByEmail(email));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um usuário")
	@ApiResponses({ @ApiResponse(code = 201, message = "Usuário salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public UserDTO create(@RequestBody @Valid User usuario) {
		return UserDTO.convertToDto(userService.create(usuario));
	}

	@PutMapping("/id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um usuário")
	@ApiResponses({ @ApiResponse(code = 204, message = "Usuário alterado com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	public UserDTO update(@PathVariable @ApiParam("ID do usuário") Long id, @RequestBody @Valid User usuario) {
		return UserDTO.convertToDto(userService.edit(id, usuario));
	}

	@DeleteMapping("/id")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um usuário")
	@ApiResponses({ @ApiResponse(code = 204, message = "Usuário deletado com sucesso"),
			@ApiResponse(code = 404, message = "Usuário não encontrado") })
	public void delete(@PathVariable @PathParam("ID do usuário") Long id) {
		userService.delete(id);
	}
}
