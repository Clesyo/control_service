package com.app.control.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.Disseminator;
import com.app.control.api.repository.DisseminatorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisseminatorService {

	private final DisseminatorRepository repository;
	
	public List<Disseminator> findAll() {
		return repository.findAll();
	}
	
	public Disseminator findById(Long id) {
		return findOrFail(id);
	}
	
	public Disseminator create(Disseminator disseminator) {
		return repository.save(disseminator);
	}
	
	public Disseminator edit(Long id, Disseminator disseminator) {
		Disseminator d = findOrFail(id);
		BeanUtils.copyProperties(disseminator, d, "id");
		return repository.save(d);
	}
	
	public void delete(Long id) {
		Disseminator d = findOrFail(id);
		repository.delete(d);
	}
	
	private Disseminator findOrFail(Long id) {
		return repository.findById(id).orElseThrow(() -> new EntityNotExist("Divuldador não encontrado"));
	}
}

