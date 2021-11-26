package com.demo.Junit.Junit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.Junit.Junit.model.Articulo;

@ExtendWith(MockitoExtension.class)
public class CarritoCompraServiceImplTest {

	@InjectMocks
	private CarritoCompraServiceI carritoService = new CarritoCompraServiceImpl();;
	
	@Mock
	private BaseDatosServiceI baseDatos;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Inicializamos el servicio");
		
		
	}
	@Test
	public void testLimpiarCesta() {
		System.out.println("Probando LimpiarCesta");
		carritoService.addArticulo(new Articulo("Pantalon", 35.90));
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testAddArticulo() {
		System.out.println("Probando AddArticulo");
		assertTrue(carritoService.getArticulos().isEmpty());
		carritoService.addArticulo(new Articulo("Pantalon", 35.90));
		assertFalse(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testGetNumArticulo() {
		System.out.println("testGetNumArticulo");
		assertTrue(carritoService.getArticulos().isEmpty());
	}

	@Test
	public void testGetArticulos() {
		System.out.println("testGetArticulos");
		
		carritoService.addArticulo(new Articulo("Pantalon", 15D));
		carritoService.addArticulo(new Articulo("Camiseta", 14D));
		
		List<Articulo> listaArticulos = new ArrayList<>();
		listaArticulos.add(new Articulo("Pantalon", 15D));
		listaArticulos.add(new Articulo("Camiseta", 14D));
		
		for(int ind=0;ind<carritoService.getArticulos().size();ind++) {
			assertTrue(listaArticulos.get(ind).getNombre() == carritoService.getArticulos().get(ind).getNombre() && listaArticulos.get(ind).getPrecio() == carritoService.getArticulos().get(ind).getPrecio());
		}
		
	}

	@Test
	public void testTotalPrice() {
		System.out.println("testTotalPrice");
	
		carritoService.addArticulo(new Articulo("Pantalon", 35.90));
		assertEquals(carritoService.totalPrice(), 35.90);
	}

	@Test
	public void testCalculadorDescuento() {
		System.out.println("testCalculadorDescuento");
		assertEquals(carritoService.calculadorDescuento(100, 20),80d);
	}
	
	@Test
	public void aplicarDescuentoTest() {
		Articulo articulo =  new Articulo("Zapatos", 60D);
		when(baseDatos.findById(3)).thenReturn(articulo);
		when(baseDatos.findById(2)).thenReturn(articulo);
		Double resultado = carritoService.aplicarDescuento(10D, 3);
		assertEquals(resultado, 54);
		verify(baseDatos,atLeast(1)).findById(eq(3));
	}
	
	@Test
	public void insertarArticuloTest() {
		Articulo articulo = new Articulo("Camiseta",10D);
		when(baseDatos.insertarArticulo(articulo)).thenReturn(5);
		Integer id = carritoService.insertarArticulo(articulo);
		assertEquals(id, 5);
		assertTrue(carritoService.getArticulos().contains(articulo));
		verify(baseDatos,atLeast(1)).insertarArticulo(articulo);
		
	}

}
