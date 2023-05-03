package com.heroe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heroe.model.Carro;
import com.heroe.model.Heroe;
import com.heroe.response.HeroeResponseRest;
import com.heroe.services.ICarroService;
import com.heroe.services.IHeroeService;

//@CrossOrigin(origins = { "http://locaslhost:4200" })
@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class HeroeRestController {

	@Autowired
	private IHeroeService service;

	@Autowired
	private ICarroService carroService;

	@GetMapping("/listar/heroes")
	public ResponseEntity<HeroeResponseRest> searchCategories() {
		ResponseEntity<HeroeResponseRest> response = service.search();
		return response;

	}

	@GetMapping("/listar/heroes/{id}")
	public ResponseEntity<HeroeResponseRest> searchCategoriesById(@PathVariable Long id) {
		ResponseEntity<HeroeResponseRest> response = service.searchById(id);
		return response;

	}

	@GetMapping("/listar/heroes/nombre/{nombre}")
	public ResponseEntity<HeroeResponseRest> searchCategoriesByName(@PathVariable String nombre) {
		ResponseEntity<HeroeResponseRest> response = service.findByNombre(nombre);
		return response;

	}

	@GetMapping("/listar/heroes/lugar/{lugarOperacion}")
	public ResponseEntity<HeroeResponseRest> searchCategoriesByLugar(@PathVariable String lugarOperacion) {
		ResponseEntity<HeroeResponseRest> response = service.findByLugarOperacion(lugarOperacion);
		return response;

	}

	@GetMapping("/listar/heroes/parametros/{nombre}/{lugarOperacion}")
	public ResponseEntity<List<Heroe>> consultaSqlParametros(@PathVariable String nombre,
			@PathVariable String lugarOperacion) throws Exception {
		return (ResponseEntity<List<Heroe>>) service.consultaSqlParametros(nombre, lugarOperacion);
	}

	@PostMapping("/crear/heroe")
	public ResponseEntity<HeroeResponseRest> save(@RequestBody Heroe category) {
		ResponseEntity<HeroeResponseRest> response = service.save(category);
		return response;

	}

	@PutMapping("/actualizar/heroe/{id}")
	public ResponseEntity<HeroeResponseRest> update(@RequestBody Heroe category, @PathVariable Long id) {
		ResponseEntity<HeroeResponseRest> response = service.update(category, id);
		return response;

	}

	@DeleteMapping("/eliminar/heroe/{id}")
	public ResponseEntity<HeroeResponseRest> delete(@PathVariable Long id) {
		ResponseEntity<HeroeResponseRest> response = service.delete(id);
		return response;

	}

	@GetMapping("/carros")
	public ResponseEntity<List<Carro>> listarCategorias() {
		return new ResponseEntity<>(carroService.findAll(), HttpStatus.OK);
	}
}
