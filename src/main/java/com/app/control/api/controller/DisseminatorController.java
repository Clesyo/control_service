package com.app.control.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.models.Disseminator;
import com.app.control.api.models.User;
import com.app.control.api.models.dto.DisseminatorDTO;
import com.app.control.api.models.query.dto.DisseminatorQueryDTO;
import com.app.control.api.services.DisseminatorService;
import com.app.control.api.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/divulgador", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class DisseminatorController {

	private final DisseminatorService service;
	private final UserService userService;

	@GetMapping("/all")
	@ApiOperation("Lista todos os divulgadores")
	public List<DisseminatorQueryDTO> all() {
		return service.findAll().stream().map(d -> {
			return DisseminatorQueryDTO.convertQueryDTO(d);
		}).collect(Collectors.toList());
	}

	@GetMapping
	@ApiOperation("Busca todos os divulgadores por seu ID de Usuário")
	public List<DisseminatorQueryDTO> allByUserId(@RequestParam(value = "id") @ApiParam(value = "ID de usuário") Long id) {
		return service.findByUserId(id).stream().map(d -> {
			return DisseminatorQueryDTO.convertQueryDTO(d);
		}).collect(Collectors.toList());
	}
	
	@GetMapping("/query/name/{name}")
	@ApiOperation("Busca todos os divulgadores por Nome")
	public List<DisseminatorQueryDTO> findByName(@PathVariable @ApiParam(value = "Nome do divulgador") String name) {
		return service.findByName(name).stream().map(d -> {
			return DisseminatorQueryDTO.convertQueryDTO(d);
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um divulgador pelo seu código ID")
	@ApiResponses({ @ApiResponse(code = 201, message = "Divulgador encontrado com sucesso."),
			@ApiResponse(code = 404, message = "Divulgador não encotrado") })
	public DisseminatorDTO findById(@PathVariable Long id) {
		return DisseminatorDTO.convertToDto(service.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um divulgador")
	@ApiResponses({ @ApiResponse(code = 201, message = "Divulgador salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public DisseminatorDTO create(@RequestBody @Valid DisseminatorDTO divulgador) {
		User user = userService.findById(divulgador.getUser());
		String token = UUID.randomUUID().toString();
		Disseminator disseminator = Disseminator.builder().name(divulgador.getName())
				.telephone(divulgador.getTelephone()).cellphone(divulgador.getCellphone()).email(divulgador.getEmail())
				.zipeCode(divulgador.getZipeCode()).adress(divulgador.getAdress())
				.numberHouse(divulgador.getNumberHouse()).complement(divulgador.getComplement())
				.district(divulgador.getDistrict()).city(divulgador.getCity()).uf(divulgador.getUf()).token(token)
				.observation(divulgador.getObservation()).user(user).build();
		return DisseminatorDTO.convertToDto(service.create(disseminator));
	}

	@PutMapping("/{id}")
	@ApiOperation("Altere um divulgador")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 204, message = "Divulgador salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação"),
			@ApiResponse(code = 404, message = "Divulgador não encontrado") })
	public DisseminatorDTO update(@PathVariable Long id, @RequestBody @Valid DisseminatorDTO divulgador) {
		User user = userService.findById(divulgador.getUser());
		Disseminator disseminator = Disseminator.builder().name(divulgador.getName())
				.telephone(divulgador.getTelephone()).cellphone(divulgador.getCellphone()).email(divulgador.getEmail())
				.zipeCode(divulgador.getZipeCode()).adress(divulgador.getAdress())
				.numberHouse(divulgador.getNumberHouse()).complement(divulgador.getComplement())
				.district(divulgador.getDistrict()).city(divulgador.getCity()).uf(divulgador.getUf())
				.token(divulgador.getToken()).observation(divulgador.getObservation()).user(user).build();
		return DisseminatorDTO.convertToDto(service.edit(id, disseminator));
	}

	@DeleteMapping("/{id}")
	@ApiOperation("Deleta um divulgador")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiResponses({ @ApiResponse(code = 204, message = "Divulgador deletado com sucesso"),
			@ApiResponse(code = 404, message = "Divulgador não encontrado") })
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
