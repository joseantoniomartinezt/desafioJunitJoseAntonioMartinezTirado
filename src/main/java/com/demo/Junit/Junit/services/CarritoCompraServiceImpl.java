package com.demo.Junit.Junit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Junit.Junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraServiceI{

	@Autowired
	private BaseDatosServiceI baseDatos;
	
	private List<Articulo> listaArticulos = new ArrayList<>();
	
	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
		
	}

	@Override
	public void addArticulo(Articulo articulo) {
		listaArticulos.add(articulo);
		
	}

	@Override
	public int getNumArticulo() {
		
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		
		double precioTotal = 0;
		
		for(Articulo articulo: listaArticulos) {
			
			precioTotal += articulo.getPrecio();
			
		}
		
		return precioTotal;
	}

	@Override
	public Double calculadorDescuento(double precio, double porcentajeDescuento) {
		
		/*double descuento = 1 - (porcentajeDescuento / 100);
		
		return precio * descuento;*/
		
		return precio - (precio * (porcentajeDescuento/100));
	}

	@Override
	public Double aplicarDescuento(Double descuento, Integer idArticulo) {
		
		Articulo articulo = baseDatos.findById(idArticulo);
		Articulo articulo1 = baseDatos.findById(2);
		if(articulo != null) {
			return calculadorDescuento(articulo.getPrecio(), descuento);
		}else {
			System.out.println("No se ha encontrado el articulo con id: " + idArticulo);
		}
		return null;
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		Integer id = baseDatos.insertarArticulo(articulo);
		addArticulo(articulo);
		return id;
	}

}
