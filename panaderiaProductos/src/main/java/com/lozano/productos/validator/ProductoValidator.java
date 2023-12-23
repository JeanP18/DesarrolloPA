package com.lozano.productos.validator;

import com.lozano.productos.entity.Producto;
import com.lozano.productos.exceptions.ValidateServiceException;

public class ProductoValidator {
	public static void save(Producto producto) {
		if (producto.getNombre()==null || producto.getNombre().isEmpty()) {
			throw new ValidateServiceException("El nombre es Requerido");
		}
		if (producto.getNombre().length()>50) {
			throw new ValidateServiceException("El nombre es muy largo");
		}
		if (producto.getDescripcion()==null || producto.getDescripcion().isEmpty()) {
			throw new ValidateServiceException("La descripcion es Requerido");
		}
		if (producto.getDescripcion().length()>50) {
			throw new ValidateServiceException("La descripcion es muy largo");
		}
		if (producto.getPrecio()==null) {
			throw new ValidateServiceException("El precio es Requerido");
		}
		if (producto.getPrecio()<0) {
			throw new ValidateServiceException("El precio tiene un valor menor que 0");
		}
		if (producto.getCategoria() == null) {
            throw new ValidateServiceException("La categoría es requerida");
        }
		
	}
}
