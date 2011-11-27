/*
 * Copyright (c) 2011, Julio Jiménez, René Toro, José Vargas. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * This file is part of JTaxi.
 * 
 * JTaxi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JTaxi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JTaxi.  If not, see <http://www.gnu.org/licenses/>.
 */
package cl.pucv.eii.jtaxi.utilidades.listas;

import static org.junit.Assert.*;


import org.junit.Test;
@SuppressWarnings({"rawtypes", "unchecked"})
public class ListaSimpleTest {

	@Test
	public void testAgregarK() {
		ListaSimple lista = new ListaSimple();
		Object k = new Object();
		lista.agregar(k);
		assertEquals(1, lista.tamaño());
	}

	@Test
	public void testAgregarIntK() {
		ListaSimple lista = new ListaSimple();
		Object k = new Object();
		for (int i = 0; i < 5; i++)
			lista.agregar(new Object());
		assertEquals(5, lista.tamaño());
		lista.agregar(4, k);
		assertEquals(4, lista.getIndice(k));
		lista.eliminar(4);
		lista.agregar(0, k);
		assertEquals(0, lista.getIndice(k));
		lista.eliminar(0);
		lista.agregar(2, k);
		assertEquals(2, lista.getIndice(k));
		lista.eliminar(2);
	}

	@Test
	public void testAgregarAlInicio() {
		ListaSimple<Object> lista = obtenerListaConVarios();
		Object item = new Object();
		lista.agregarAlInicio(item);
		assertEquals(0, lista.getIndice(item));
	}

	@Test
	public void testEliminarK() {
		ListaSimple<Object> lista = obtenerListaVacia();
		Object item = new Object();
		assertTrue(!lista.eliminar(item));
		lista.agregar(item);
		assertTrue(lista.eliminar(item));
		assertEquals(0, lista.tamaño());

		lista = obtenerListaConUnDato();
		Object item2 = new Object();
		lista.agregarAlInicio(item);
		lista.agregar(item2);
		Object aux = lista.getObject(1);
		lista.eliminar(item);
		assertEquals(0, lista.getIndice(aux));

		assertTrue(!lista.eliminar(null));
	}

	@Test
	public void testEliminarInt() {
		ListaSimple<Object> lista = obtenerListaVacia();
		Object item = new Object();

		boolean flag = true;
		try {
			lista.eliminar(0);
			flag = false;
		} catch (IndexOutOfBoundsException e) {
			assertTrue(flag);
		}
		
		lista.agregar(item);
		assertTrue(item == lista.eliminar(0));
		assertTrue(lista.estaVacia());
	}

	@Test
	public void testGetPos() {
		ListaSimple<Object> lista = obtenerListaConVarios();
		Object k = new Object();
		lista.agregar(k);
		assertEquals(10, lista.getIndice(k));

		lista = obtenerListaVacia();
		assertEquals(-1, lista.getIndice(k));
		lista.agregar(k);
		assertEquals(0, lista.getIndice(k));

		lista = obtenerListaConUnDato();
		lista.agregar(k);
		assertEquals(1, lista.getIndice(k));

	}

	@Test
	public void testGetObject() {
		ListaSimple<Object> lista = obtenerListaVacia();
		Object i = new Object();
		boolean flag = true;
		try {
			lista.getObject(0);
			flag = false;
		} catch (IndexOutOfBoundsException e) {
			assertTrue(flag);
		}

		lista.agregar(i);
		lista.agregar("asdf");
		lista.agregar("asdf2");
		lista.agregar("asd1f");
		assertTrue(lista.getObject(0) == i);
		assertEquals(0, lista.getIndice(lista.getObject(0)));
		assertTrue(lista.getObject(2).equals("asdf2"));
	}

	@Test
	public void testTamaño() {
		ListaSimple lista = new ListaSimple();
		for (int i = 0; i < 3; i++)
			lista.agregar(new Object());
		assertEquals(3, lista.tamaño());
	}

	@Test
	public void testEstaVacia() {
		ListaSimple lista = new ListaSimple();
		assertTrue(lista.estaVacia());
		lista.agregar("");
		assertTrue(!lista.estaVacia());
	}

	private ListaSimple<Object> obtenerListaConVarios() {
		ListaSimple<Object> lista = new ListaSimple<>();
		for (int i = 0; i < 10; i++)
			lista.agregar(new Object());
		return lista;
	}

	private ListaSimple<Object> obtenerListaVacia() {
		return new ListaSimple<Object>();
	}

	private ListaSimple<Object> obtenerListaConUnDato() {
		ListaSimple<Object> lista = new ListaSimple<>();
		lista.agregar(new Object());
		return lista;
	}

}
