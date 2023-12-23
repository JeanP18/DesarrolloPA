package com.lozano.productos.validator;

import com.lozano.productos.entity.Categoria;
import com.lozano.productos.exceptions.ValidateServiceException;

public class CategoriaValidator {
	public static void save(Categoria categoria) {
		if (categoria.getNombre()==null || categoria.getNombre().isEmpty()) {
			throw new ValidateServiceException("El nombre es Requerido");
		}
		if (categoria.getNombre().length()>50) {
			throw new ValidateServiceException("El nombre es muy largo");
		}
	}
}
