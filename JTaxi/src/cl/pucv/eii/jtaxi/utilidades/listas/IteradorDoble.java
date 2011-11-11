/*
 * Copyright (c) 2011, Julio Jiménez, Rene Toro, José Vargas. All rights reserved.
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

import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * Implementación de la interface ListIterator, fuértemente influenciada por el Iterator
 * que utiliza la clase java.util.LinkedList
 * Implementada principalmente con fines de aprendizaje.
 */
public class IteradorDoble<T> implements ListIterator<T>{
	private int indiceSiguiente;
	private ListaDoble<T> lista;
	private NodoDoble<T> siguiente;
	private NodoDoble<T> ultimoRetornado = null;
	
	public IteradorDoble(ListaDoble<T> lista){
		this.lista = lista;
		siguiente = lista.getHead();
		indiceSiguiente = 0;
	}

	@Override
	public boolean hasNext() {
		return ( indiceSiguiente < lista.tamaño() );
	}

	@Override
	public boolean hasPrevious() {
		return ( indiceSiguiente > 0 );
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

	@Override
	public int nextIndex() {
		return indiceSiguiente;
	}

	@Override
	public T previous() {
		if (!hasPrevious())
			throw new NoSuchElementException();
		/* Pucha que cuesta imaginarse los iteradores como pide la definición de la Interface
		 * (como un cursor que se encuentra entre dos elementos).
		 * La siguiente linea esta copiada de la implementacion de java.util.LinkedList, pero la
		 * entiendo. Como el iterador esta entre los nodos, existe el caso en que el iterador
		 * se encuentre pasado a la lista, en ese caso siguiente seria null y por lo tanto
		 * lo que corresponde devolver es el tail de la lista, si siguiente no es null, y estamos
		 * entre dos nodos no nulos, basta pedirle a siguiente la referencia al anterior.
		 * -- Julio */
		ultimoRetornado = siguiente = (siguiente == null) ? lista.getTail() : siguiente.getAnterior();
		indiceSiguiente--;
		return ultimoRetornado.getItem();
	}

	@Override
	public int previousIndex() {
		return indiceSiguiente-1;
	}

	@Override
	public void remove() {}

	@Override
	public void set(T e) {}
	
	@Override
	public void add(T e) {}
	

}
