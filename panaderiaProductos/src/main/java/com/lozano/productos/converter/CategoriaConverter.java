package com.lozano.productos.converter;

import org.springframework.stereotype.Component;

import com.lozano.productos.dto.CategoriaDTO;
import com.lozano.productos.entity.Categoria;



@Component
public class CategoriaConverter extends AbstractConvert<Categoria, CategoriaDTO>{
	@Override
	public CategoriaDTO fromEntity(Categoria entity) {
		if (entity==null)return null;
		return CategoriaDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.build();
	}

	@Override
	public Categoria fromDTO(CategoriaDTO dto) {
		if (dto==null)return null;
		return Categoria.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.build();
	}
}	
