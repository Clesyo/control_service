package com.app.control.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.control.api.models.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

}
