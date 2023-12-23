package com.lozano.productos.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.lozano.productos.entity.Categoria;
import com.lozano.productos.entity.Producto;


public interface ProductoService {
	public List<Producto>findAll(Pageable page);
	public List<Producto>findByNombre(String nombre,Pageable page);
	public Producto findById(int id);
	public Producto create (Producto producto);
	public Producto update(Producto producto);
	public void delete(int id);
	public List<Categoria> selectCategoria();
}
