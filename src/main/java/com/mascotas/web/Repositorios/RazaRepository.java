package com.mascotas.web.Repositorios;


import org.springframework.data.jpa.repository.JpaRepository;

import com.mascotas.web.Entidades.Raza;

public interface RazaRepository extends JpaRepository<Raza, Long> { }