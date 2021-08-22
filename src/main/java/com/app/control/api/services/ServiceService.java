package com.app.control.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Services;
import com.app.control.api.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceService {

	private final ServiceRepository repository;
	
	public List<Services> findAll() {
		return repository.findAll();
	}
	
	public Services findById(Long id) {
		return findOrFail(id);
	}
	
	public Services create(Services services) {
		return repository.save(services);
	}
	
	public Services edit(Long id, Services services) {
		Services s = findOrFail(id);
		BeanUtils.copyProperties(services, s);
		return repository.save(s);
	}
	
	public void delete(Long id) {
		Services service = findOrFail(id);
		repository.delete(service);
	}
	
	private Services findOrFail(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotExist("Serviço não encontrado."));
	}
}
