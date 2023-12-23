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

import com.lozano.productos.converter.CategoriaConverter;
import com.lozano.productos.dto.CategoriaDTO;
import com.lozano.productos.entity.Categoria;
import com.lozano.productos.service.CategoriaService;
import com.lozano.productos.utils.WrapperResponse;


@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@Autowired
	private CategoriaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<CategoriaDTO>>findAll(
				@RequestParam(value = "nombre",required = false,defaultValue = "")String nombre,
				@RequestParam(value = "offset",required = false,defaultValue = "0")int pageNumber,
				@RequestParam(value = "limit",required = false,defaultValue = "5")int pageSize
				){
			Pageable page= PageRequest.of(pageNumber, pageSize);
			List<Categoria> categorias;
			if(nombre==null) {
				categorias=service.findAll(page);
			}else {
				categorias=service.findByNombre(nombre, page);
			}/*
			if (categorias.isEmpty()) {
				return ResponseEntity.noContent().build();
			}*/
			List<CategoriaDTO> categoriasDTO=converter.fromEntity(categorias);
			return new WrapperResponse(true,"success",categoriasDTO).createRespose(HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<CategoriaDTO>> findById(@PathVariable("id")int id){
		Categoria categoria = service.findById(id);
		/*if (categoria==null) {
			return ResponseEntity.notFound().build();
		}*/
		CategoriaDTO caetgoriaDTO=converter.fromEntity(categoria);
		return new WrapperResponse<CategoriaDTO>(true,"success",caetgoriaDTO).createRespose(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> create(@RequestBody CategoriaDTO categoriaDTO){
		Categoria registro= service.save(converter.fromDTO(categoriaDTO));
		CategoriaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createRespose(HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable("id")int id,@RequestBody CategoriaDTO categoriaDTO){
		Categoria registro = service.update(converter.fromDTO(categoriaDTO));
		/*if (registro==null) {
			return ResponseEntity.notFound().build();
		}*/
		CategoriaDTO registroDTO=converter.fromEntity(registro);
		return new WrapperResponse(true,"success",registroDTO).createRespose(HttpStatus.OK);
	}
	
	@DeleteMapping(value= "/{id}")
	public ResponseEntity<CategoriaDTO> delete(@PathVariable("id")int id){
		service.delete(id);
		return new WrapperResponse(true,"success",null).createRespose(HttpStatus.OK);
	}
}
