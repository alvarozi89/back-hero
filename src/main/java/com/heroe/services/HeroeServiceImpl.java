package com.heroe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.heroe.model.Heroe;
import com.heroe.repository.IHeroeDao;
import com.heroe.response.HeroeResponseRest;

@Service
public class HeroeServiceImpl implements IHeroeService {

	@Autowired
	private IHeroeDao heroeDao;

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ResponseEntity<HeroeResponseRest> search() {
		HeroeResponseRest response = new HeroeResponseRest();
		try {
			List<Heroe> Heroe = heroeDao.findAll();
			response.getHeroeResponse().setHeroe(Heroe);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ResponseEntity<HeroeResponseRest> searchById(Long id) {
		HeroeResponseRest response = new HeroeResponseRest();
		List<Heroe> list = new ArrayList<>();
		try {
			Optional<Heroe> Heroe = heroeDao.findById(id);
			if (Heroe.isPresent()) {
				list.add(Heroe.get());
				response.getHeroeResponse().setHeroe(list);
				response.setMetadata("Respuesta ok", "00", "Heroe encontrado");
			}

			else {
				response.setMetadata("Respuesta MAL", "-1", "Heroe no encontrado");
				return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ResponseEntity<HeroeResponseRest> findByNombre(String nombre) {
		HeroeResponseRest response = new HeroeResponseRest();
		List<Heroe> list = new ArrayList<>();
		try {
			Optional<Heroe> Heroe = heroeDao.findByNombre(nombre);
			if (Heroe.isPresent()) {
				list.add(Heroe.get());
				response.getHeroeResponse().setHeroe(list);
				response.setMetadata("Respuesta ok", "00", "Heroe encontrado");
			}

			else {
				response.setMetadata("Respuesta MAL", "-1", "Heroe no encontrado");
				return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ResponseEntity<HeroeResponseRest> findByLugarOperacion(String lugarOperacion) {
		HeroeResponseRest response = new HeroeResponseRest();
		List<Heroe> list = new ArrayList<>();
		try {
			Optional<Heroe> Heroe = heroeDao.findByLugarOperacion(lugarOperacion);
			if (Heroe.isPresent()) {
				list.add(Heroe.get());
				response.getHeroeResponse().setHeroe(list);
				response.setMetadata("Respuesta ok", "00", "Heroe encontrado");
			}

			else {
				response.setMetadata("Respuesta MAL", "-1", "Heroe no encontrado");
				return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public List<Heroe> consultaSqlParametros(String nombre, String lugarOperacion) throws Exception {
		try {
			List<Heroe> heroe = heroeDao.consultaSqlParametros(nombre, lugarOperacion);
			return heroe;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	@org.springframework.transaction.annotation.Transactional()
	public ResponseEntity<HeroeResponseRest> save(Heroe Heroe) {
		HeroeResponseRest response = new HeroeResponseRest();
		List<Heroe> list = new ArrayList<>();
		try {

			Heroe HeroeSaved = heroeDao.save(Heroe);
			if (HeroeSaved != null) {

				list.add(HeroeSaved);
				response.getHeroeResponse().setHeroe(list);
				response.setMetadata("Respuesta ok", "00", "Heroe guardada");
			}

			else {
				response.setMetadata("Respuesta MAL", "-1", "Heroe no guardada");
				return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.BAD_REQUEST);

			}

		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional()
	public ResponseEntity<HeroeResponseRest> update(Heroe Heroe, Long id) {
		HeroeResponseRest response = new HeroeResponseRest();
		List<Heroe> list = new ArrayList<>();
		try {
			Optional<Heroe> HeroeSearch = heroeDao.findById(id);
			if (HeroeSearch.isPresent()) {
				HeroeSearch.get().setNombre(Heroe.getNombre());
				HeroeSearch.get().setNombre(Heroe.getNombre());

				Heroe HeroeToUpdate = heroeDao.save(HeroeSearch.get());

				if (HeroeToUpdate != null) {
					list.add(HeroeToUpdate);
					response.getHeroeResponse().setHeroe(list);
					response.setMetadata("Respuesta ok", "00", "Heroe guardado");

				} else {
					response.setMetadata("Respuesta MAL", "-1", "Heroe no actualizada");
					return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.NOT_FOUND);
				}

			}

			else {
				response.setMetadata("Respuesta MAL", "-1", "Heroe no encontrado");
				return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.NOT_FOUND);

			}

		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@org.springframework.transaction.annotation.Transactional()
	public ResponseEntity<HeroeResponseRest> delete(Long id) {
		HeroeResponseRest response = new HeroeResponseRest();
		try {
			heroeDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Registro eliminado");
		} catch (Exception e) {
			response.setMetadata("Respuesta MAL", "-1", "Respuesta ERROR");
			e.getStackTrace();
			return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<HeroeResponseRest>(response, HttpStatus.OK);
	}

}
