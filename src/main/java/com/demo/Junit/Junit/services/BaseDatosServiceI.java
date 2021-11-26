package com.demo.Junit.Junit.services;

import com.demo.Junit.Junit.model.Articulo;

public interface BaseDatosServiceI {

	public void initiBBDD();
	
	public Articulo findById(Integer id);
	
	public Integer insertarArticulo(Articulo articulo);
	
	
}
