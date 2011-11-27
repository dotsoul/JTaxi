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
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListaDoble<K> implements Lista<K>, Iterable<K> {

	private NodoDoble<K> head;
	private NodoDoble<K> tail;
	private int tamaño = 0;

	protected NodoDoble<K> getHead() {
		return head;
	}

	protected NodoDoble<K> getTail() {
		return tail;
	}

	@Override
	public Iterator<K> iterator() {
		return listIterator();
	}

	public ListIterator<K> listIterator() {
		return new IteradorDoble<K>(this);
	}

	@Override
	public void agregar(K item) {
		NodoDoble<K> nuevo = new NodoDoble<>(item);
		if (head == null) {
			head = nuevo;
			tail = head;
		} else {
			tail.setSiguiente(nuevo);
			nuevo.setAnterior(tail);
			tail = nuevo;
		}
		tamaño++;
	}

	@Override
	public void agregar(int indice, K item) {
		if(indice < 0 || indice >= tamaño())
			throw new IndexOutOfBoundsException();
		
		if(indice == 0){
			agregarAlInicio(item);
		} else {
			NodoDoble<K> prev = getNodo(indice-1);
			NodoDoble<K> nuevo = new NodoDoble<>(item,prev,prev.getSiguiente());
			prev.getSiguiente().setAnterior(nuevo);
			prev.setSiguiente(nuevo);			
		}
		
		tamaño++;
	}

	@Override
	public void agregarAlInicio(K item) {
		NodoDoble<K> nuevo = new NodoDoble<>(item);
		if (estaVacia()) {
			head = nuevo;
			tail = head;
		} else {
			head.setAnterior(nuevo);
			nuevo.setSiguiente(head);
			head = nuevo;
		}
		tamaño++;
	}

	@Override
	public boolean eliminar(K item) {
		if (estaVacia() || item == null)
			return false;

		NodoDoble<K> nodo = getNodo(item);

		return (nodo == null) ? false : remover(nodo);
	}

	@Override
	public K eliminar(int indice) {
		if(indice < 0 || indice >= tamaño)
			throw new IndexOutOfBoundsException();
		
		NodoDoble<K> nodo = getNodo(indice);
		remover(nodo);
		return nodo.getItem();
	}

	@Override
	public int getIndice(K item) {
		for (ListIterator<K> itr = listIterator(); itr.hasNext();) {
			if (((K)itr.next()).equals(item)) {
				return itr.previousIndex();
			}
		}
		return -1;
	}

	@Override
	public K getObject(int pos) {
		if (pos < 0 || pos >= tamaño())
			throw new IndexOutOfBoundsException();

		for (ListIterator<K> itr = listIterator(); itr.hasNext();) {
			if (itr.nextIndex() == pos)
				return itr.next();
			itr.next();
		}

		return null;
	}

	@Override
	public int tamaño() {
		return this.tamaño;
	}

	@Override
	public boolean estaVacia() {
		return (tamaño == 0);
	}

	private boolean remover(NodoDoble<K> nodo) {
		if (nodo == null)
			throw new IllegalArgumentException("Nodo no puede ser nulo.");

		NodoDoble<K> anterior = nodo.getAnterior();
		NodoDoble<K> siguiente = nodo.getSiguiente();

		/* Si el nodo es el primero, se cambia el head. */
		if (nodo == head) {
			head = siguiente;
		} else {
			anterior.setSiguiente(siguiente);
			nodo.setAnterior(null);
		}
		
		if (nodo == tail){
			tail = anterior;
		} else {
			siguiente.setAnterior(anterior);
			nodo.setSiguiente(null);
		}
		
		tamaño--;
		return true;
	}

	private NodoDoble<K> getNodo(K item) {
		if (item == null)
			throw new IllegalArgumentException("Item no puede ser nulo.");

		NodoDoble<K> nodo = head;
		while (nodo != null) {
			if (nodo.getItem().equals(item))
				return nodo;
			else
				nodo = nodo.getSiguiente();
		}
		return null;
	}
	
	private NodoDoble<K> getNodo(int indice){
		if (indice < 0 || indice >= tamaño)
			throw new IndexOutOfBoundsException();
		
		NodoDoble<K> nodo = head;
		for(int i = 0;i<tamaño;i++){
			if(i == indice){
				return nodo;
			}
			 nodo = nodo.getSiguiente();
		}
		return nodo;
	}

	/*
	 * Implementación de la interface ListIterator, fuértemente influenciada por el Iterator
	 * que utiliza la clase java.util.LinkedList
	 * Implementada principalmente con fines de aprendizaje.
	 */
	protected class IteradorDoble<T> implements ListIterator<T>{
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
}
