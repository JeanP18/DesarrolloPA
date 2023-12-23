package com.lozano.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lozano.productos.entity.Categoria;




public interface CategoriaService {
	public List<Categoria> findAll(Pageable page);
	public List<Categoria> findByNombre(String nombre,Pageable page);
	public Categoria findById(int id);
	public Categoria save(Categoria categoria);
	public Categoria update(Categoria categoria);
	public void delete(int id);
}
