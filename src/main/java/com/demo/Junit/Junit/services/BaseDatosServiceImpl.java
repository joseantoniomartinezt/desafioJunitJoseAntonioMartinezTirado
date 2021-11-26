package com.demo.Junit.Junit.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.Junit.Junit.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI{

	private Map<Integer, Articulo> storage = new HashMap<>();
	
	@Override
	public void initiBBDD() {
		storage.put(1, new Articulo("Pantalon", 15D));
		storage.put(2, new Articulo("Camiseta", 14D));
		storage.put(3, new Articulo("Zapatos", 60D));
		storage.put(4, new Articulo("Camisa", 30D));
		
	}

	@Override
	public Articulo findById(Integer id) {
		
		return storage.get(id);
	}

	@Override
	public Integer insertarArticulo(Articulo articulo) {
		storage.put(storage.size() + 1, articulo);
		
		return storage.size();
	}

}
