package com.heroe.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.heroe.model.Heroe;
import com.heroe.response.HeroeResponseRest;

public interface IHeroeService {

	public ResponseEntity<HeroeResponseRest> search();

	public ResponseEntity<HeroeResponseRest> searchById(Long id);

	public ResponseEntity<HeroeResponseRest> findByNombre(String nombre);

	public ResponseEntity<HeroeResponseRest> findByLugarOperacion(String lugarOperacion);

	public List<Heroe> consultaSqlParametros(String nombre, String lugarOperacion) throws Exception;

	public ResponseEntity<HeroeResponseRest> save(Heroe category);

	public ResponseEntity<HeroeResponseRest> update(Heroe category, Long id);

	public ResponseEntity<HeroeResponseRest> delete(Long id);

}
