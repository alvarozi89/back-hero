package com.heroe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.heroe.model.Heroe;

public interface IHeroeDao extends JpaRepository<Heroe, Long> {

	public Optional<Heroe> findByNombre(String nombre);

	public Optional<Heroe> findByLugarOperacion(String lugarOperacion);

	@Query("SELECT H FROM Heroe H WHERE H.nombre LIKE %:nombre% or H.lugarOperacion LIKE %:lugarOperacion% ")
	public List<Heroe> consultaSqlParametros(@Param(value = "nombre") String nombre,
			@Param(value = "lugarOperacion") String lugarOperacion);

}
