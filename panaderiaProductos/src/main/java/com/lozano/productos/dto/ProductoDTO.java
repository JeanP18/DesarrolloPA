package com.lozano.productos.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private CategoriaDTO categoria;
}
