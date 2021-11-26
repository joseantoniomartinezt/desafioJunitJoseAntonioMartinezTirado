package com.demo.Junit.Junit.services;

import java.util.List;

import com.demo.Junit.Junit.model.Articulo;

public interface CarritoCompraServiceI {

	public void limpiarCesta();
	
	public void addArticulo(Articulo articulo);
	
	public int getNumArticulo();
	
	public List<Articulo> getArticulos();
	
	public Double totalPrice();
	
	public Double calculadorDescuento(double precio,double porcentajeDescuento);
	
	public Double aplicarDescuento(Double descuento,Integer idArticulo); 
	
	public Integer insertarArticulo(Articulo articulo);
	
	
}
