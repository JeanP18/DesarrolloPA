package com.lozano.productos.repository; 

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lozano.productos.entity.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	List<Producto> findByNombreContaining(String texto,Pageable page);
	Producto findByNombre(String texto);
}
