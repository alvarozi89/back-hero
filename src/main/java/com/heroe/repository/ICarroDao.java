package com.heroe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heroe.model.Carro;

public interface ICarroDao extends JpaRepository<Carro, Long> {

}
