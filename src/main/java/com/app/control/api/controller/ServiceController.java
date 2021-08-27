package com.app.control.api.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.control.api.models.Disseminator;
import com.app.control.api.models.Services;
import com.app.control.api.models.dto.ServiceDTO;
import com.app.control.api.services.DisseminatorService;
import com.app.control.api.services.ServiceService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/servico", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ServiceController {

	private final ServiceService service;
	private final DisseminatorService disseminatorService;

	@GetMapping
	@ApiOperation("Busca todos os serviços")
	public List<ServiceDTO> all() {

		return service.findAll().stream().map(s -> {
			return ServiceDTO.convertToDto(s);
		}).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	@ApiOperation("Busca um serviço pelo seu codigo ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Serviço encontrado com sucesso"),
			@ApiResponse(code = 404, message = "Serviços não encontrado") })
	public ServiceDTO findById(@PathVariable Long id) {
		return ServiceDTO.convertToDto(service.findById(id));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Salva um serviço")
	@ApiResponses({ @ApiResponse(code = 201, message = "Serviço salvo com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação") })
	public ServiceDTO create(@RequestBody @Valid ServiceDTO dto) {
		Disseminator disseminator = disseminatorService.findById(dto.getDisseminator());
		Services services = Services.builder().description(dto.getDescription()).details(dto.getDetails())
				.amount(dto.getAmount()).disseminator(disseminator).build();
		return ServiceDTO.convertToDto(service.create(services));
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Altera um serviço")
	@ApiResponses({ @ApiResponse(code = 204, message = "Serviço alterado com sucesso"),
		@ApiResponse(code = 400, message = "Erro de validação"),
		@ApiResponse(code = 404, message = "Serviços não encontrado") })
	public ServiceDTO update(@PathVariable Long id, @RequestBody @Valid ServiceDTO dto) {
		Disseminator disseminator = disseminatorService.findById(dto.getDisseminator());
		Services services = Services.builder().description(dto.getDescription()).details(dto.getDetails())
				.amount(dto.getAmount()).disseminator(disseminator).build();
		return ServiceDTO.convertToDto(service.edit(id, services));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@ApiOperation("Deleta um serviço")
	@ApiResponses({ @ApiResponse(code = 204, message = "Serviço deletado com sucesso"),
		@ApiResponse(code = 404, message = "Serviços não encontrado") })
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
