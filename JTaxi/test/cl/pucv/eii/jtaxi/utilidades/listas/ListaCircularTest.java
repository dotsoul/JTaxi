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

import java.util.Iterator;

import org.junit.Test;

public class ListaCircularTest {

	@Test
	public void testAgregarK() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregarIntK() {
		fail("Not yet implemented");
	}

	@Test
	public void testAgregarAlInicio() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarK() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testTamaño() {
		ListaCircular<Object> lista = obtenerListaVacia();
		assertEquals(0, lista.tamaño());
		
		Object item = new Object();
		lista.agregar(item);
		assertEquals(1, lista.tamaño());
		lista.eliminar(item);
		assertEquals(0, lista.tamaño());
	}

	@Test
	public void testEstaVacia() {
		ListaCircular<Object> lista = obtenerListaVacia();
		assertTrue(lista.estaVacia());
		
		Object item = new Object();
		lista.agregar(item);
		assertTrue(!lista.estaVacia());
		lista.eliminar(item);
		assertTrue(lista.estaVacia());
	}

	@Test
	public void testIterator() {
		assertTrue(obtenerListaVacia().iterator() instanceof Iterator);
	}
	
	private ListaCircular<Object> obtenerListaConVarios() {
		ListaCircular<Object> lista = new ListaCircular<>();
		for (int i = 0; i < 10; i++)
			lista.agregar(new Object());
		return lista;
	}

	private ListaCircular<Object> obtenerListaVacia() {
		return new ListaCircular<Object>();
	}

	private ListaCircular<Object> obtenerListaConUnDato() {
		ListaCircular<Object> lista = new ListaCircular<>();
		lista.agregar(new Object());
		return lista;
	}

}
