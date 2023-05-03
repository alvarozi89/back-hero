package com.heroe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heroe.model.Carro;
import com.heroe.repository.ICarroDao;

@Service
public class CArroServiceImpl implements ICarroService {

	@Autowired
	private ICarroDao carroDao;

	@Override
	public List<Carro> findAll() {
		// TODO Auto-generated method stub
		return carroDao.findAll();
	}

}
