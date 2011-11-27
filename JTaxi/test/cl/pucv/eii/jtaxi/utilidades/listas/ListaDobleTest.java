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
public class ListaDobleTest {

	@Test
	public void testAgregarK() {
		ListaDoble lista = new ListaDoble();
		Object k = new Object();
		lista.agregar(k);
		assertEquals(1, lista.tamaño());
	}

	@Test
	public void testAgregarIntK() {
		ListaDoble lista = new ListaDoble();
		Object k = new Object();
		for (int i = 0; i < 5; i++)
			lista.agregar(new Object());
		assertEquals(5, lista.tamaño());
		lista.agregar(2, k);
		assertEquals(2, lista.getPos(k));
	}

	@Test
	public void testAgregarAlInicio() {
		ListaDoble<Object> lista = obtenerListaConVarios();
		Object item = new Object();
		lista.agregarAlInicio(item);
		assertEquals(0, lista.getPos(item));
	}

	@Test
	public void testEliminarK() {
		ListaDoble<Object> lista = obtenerListaVacia();
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
		assertEquals(0, lista.getPos(aux));

		assertTrue(!lista.eliminar(null));
	}

	@Test
	public void testEliminarInt() {
		ListaDoble<Object> lista = obtenerListaVacia();
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
		ListaDoble<Object> lista = obtenerListaConVarios();
		Object k = new Object();
		lista.agregar(k);
		assertEquals(10, lista.getPos(k));

		lista = obtenerListaVacia();
		assertEquals(-1, lista.getPos(k));
		lista.agregar(k);
		assertEquals(0, lista.getPos(k));

		lista = obtenerListaConUnDato();
		lista.agregar(k);
		assertEquals(1, lista.getPos(k));

	}

	@Test
	public void testGetObject() {
		ListaDoble<Object> lista = obtenerListaVacia();
		Object i = new Object();
		boolean flag = true;
		try {
			lista.getObject(0);
			flag = false;
		} catch (IndexOutOfBoundsException e) {
			assertTrue(flag);
		}

		lista.agregar(i);
		assertTrue(lista.getObject(0) == i);
		assertEquals(0, lista.getPos(lista.getObject(0)));
	}

	@Test
	public void testTamaño() {
		ListaDoble lista = new ListaDoble();
		for (int i = 0; i < 3; i++)
			lista.agregar(new Object());
		assertEquals(3, lista.tamaño());
	}

	@Test
	public void testEstaVacia() {
		ListaDoble lista = new ListaDoble();
		assertTrue(lista.estaVacia());
	}

	private ListaDoble<Object> obtenerListaConVarios() {
		ListaDoble<Object> lista = new ListaDoble<>();
		for (int i = 0; i < 10; i++)
			lista.agregar(new Object());
		return lista;
	}

	private ListaDoble<Object> obtenerListaVacia() {
		return new ListaDoble<Object>();
	}

	private ListaDoble<Object> obtenerListaConUnDato() {
		ListaDoble<Object> lista = new ListaDoble<>();
		lista.agregar(new Object());
		return lista;
	}

}
