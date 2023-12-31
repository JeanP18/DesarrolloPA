package com.lozano.productos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lozano.productos.converter.ProductoConverter;
import com.lozano.productos.dto.ProductoDTO;
import com.lozano.productos.entity.Producto;
import com.lozano.productos.service.ProductoService;
import com.lozano.productos.utils.WrapperResponse;



@RestController
@RequestMapping("/productos")
public class ProductoController {
	@Autowired
	private ProductoService service;
	
	@Autowired
	private ProductoConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<ProductoDTO>>findAll(
				@RequestParam(value = "nombre",required = false,defaultValue = "")String nombre,
				@RequestParam(value = "offset",required = false,defaultValue = "0")int pageNumber,
				@RequestParam(value = "limit",required = false,defaultValue = "5")int pageSize
				){
			Pageable page= PageRequest.of(pageNumber, pageSize);
			List<Producto> productos;
			if(nombre==null) {
				productos=service.findAll(page);
			}else {
				productos=service.findByNombre(nombre, page);
			}/*
			if (gerentes.isEmpty()) {
				return ResponseEntity.noContent().build();
			}*/
			List<ProductoDTO> productosDTO=converter.fromEntity(productos);
			return new WrapperResponse(true,"success",productosDTO).createRespose(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<ProductoDTO>> findById(@PathVariable("id")int id){
		Producto producto = service.findById(id);
		/*if (gerente==null) {
			return ResponseEntity.notFound().build();
		}*/
		ProductoDTO productoDTO=converter.fromEntity(producto);
		return new WrapperResponse<ProductoDTO>(true,"success",productoDTO).createRespose(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO){
		Producto registro= service.create(converter.fromDTO(productoDTO));
		ProductoDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createRespose(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ProductoDTO> update(@PathVariable("id")int id,@RequestBody ProductoDTO productoDTO){
		Producto registro = service.update(converter.fromDTO(productoDTO));
		/*if (registro==null) {
			return ResponseEntity.notFound().build();
		}*/
		ProductoDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createRespose(HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<ProductoDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createRespose(HttpStatus.OK);
	}
}
