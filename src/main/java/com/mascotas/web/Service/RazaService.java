package com.mascotas.web.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mascotas.web.Entidades.Raza;
import com.mascotas.web.Repositorios.RazaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RazaService {
    private final RazaRepository repo;
    public Raza crear(Raza r){ return repo.save(r); }
    public List<Raza> listar(){ return repo.findAll(); }
}

