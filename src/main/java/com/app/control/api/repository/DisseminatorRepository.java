package com.app.control.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.control.api.models.Disseminator;

public interface DisseminatorRepository extends JpaRepository<Disseminator, Long> {

	@Query(value = "select * from disseminators where user_id = :user_id", nativeQuery = true)
	List<Disseminator> findByUserId(@Param("user_id") Long id);

	@Query(value = "select * from disseminators where name like '%:name%'", nativeQuery = true)
	List<Disseminator> findByNameLike(String name);
}
