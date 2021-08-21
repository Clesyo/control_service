package com.app.control.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.control.api.exception.EntityNotExist;
import com.app.control.api.models.User;
import com.app.control.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		// TODO Auto-generated method stub
		return findOrFail(id);
	}

	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new EntityNotExist("Usuário não encontrado para email informado"));
	}
	public User create(User user) {
		return userRepository.save(user);
	}
	
	public User edit(Long id, User user) {
		User u = findOrFail(id);
		BeanUtils.copyProperties(user, u);
		return userRepository.save(user);
	}
	
	public void delete(Long id) {
		User user = findOrFail(id);
		userRepository.delete(user);
	}

	private User findOrFail(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotExist("Usuário não encontrado para ID informado."));
	}
}
