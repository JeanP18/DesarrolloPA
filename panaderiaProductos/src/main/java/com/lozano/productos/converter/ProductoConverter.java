package com.lozano.productos.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lozano.productos.dto.ProductoDTO;
import com.lozano.productos.entity.Producto;




@Component
public class ProductoConverter extends AbstractConvert<Producto,ProductoDTO>{
	@Autowired
	   private CategoriaConverter categoriaConverter;
	 
	@Override
	public ProductoDTO fromEntity(Producto entity) {
		if (entity==null)return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.descripcion(entity.getDescripcion())
				.precio(entity.getPrecio())
				.categoria(categoriaConverter.fromEntity(entity.getCategoria()))
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		if (dto==null)return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.descripcion(dto.getDescripcion())
				.precio(dto.getPrecio())
				.categoria(categoriaConverter.fromDTO(dto.getCategoria()))
				.build();
	}
	
}
