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

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Implementación de la interface Iterator, fuértemente influenciada por el Iterator
 * que utiliza la clase java.util.LinkedList
 * Implementada principalmente con fines de aprendizaje.
 */
public class IteradorSimple<T> implements 	Iterator<T>{
	private int indiceSiguiente;
	private ListaSimple<T> lista;
	private NodoSimple<T> siguiente;
	private NodoSimple<T> ultimoRetornado = null;
	
	public IteradorSimple(ListaSimple<T> lista){
		this.lista = lista;
		siguiente = lista.getHead();
		indiceSiguiente = 0;
	}

	@Override
	public boolean hasNext() {
		return ( indiceSiguiente < lista.tamaño() );
	}

	@Override
	public T next() {
		if (!hasNext())
			throw new NoSuchElementException();
		ultimoRetornado = siguiente;
		siguiente = siguiente.getSiguiente();
		indiceSiguiente++;
		return ultimoRetornado.getItem();
	}

	public int nextIndex() {
		return indiceSiguiente;
	}


	@Override
	public void remove() {}

	

}
