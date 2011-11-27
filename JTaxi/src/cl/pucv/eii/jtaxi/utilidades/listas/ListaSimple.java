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

public class ListaSimple<K> implements Lista<K>, Iterable<K> {

	private NodoSimple<K> head;
	private NodoSimple<K> tail;
	private int tamaño = 0;

	protected NodoSimple<K> getHead() {
		return head;
	}

	protected NodoSimple<K> getTail() {
		return tail;
	}

	@Override
	public Iterator<K> iterator() {
		return iteradorSimple();
	}

	private IteradorSimple<K> iteradorSimple() {
		return new IteradorSimple<K>(this);
	}

	@Override
	public void agregar(K item) {
		NodoSimple<K> nuevo = new NodoSimple<>(item);
		if (head == null) {
			head = nuevo;
			tail = head;
		} else {
			tail.setSiguiente(nuevo);
			tail = nuevo;
		}
		tamaño++;
	}

	@Override
	public void agregar(int indice, K item) {
		if (indice < 0 || indice >= tamaño())
			throw new IndexOutOfBoundsException();

		if (indice == 0) {
			agregarAlInicio(item);
		} else {
			NodoSimple<K> prev = getNodoAnterior(indice);
			NodoSimple<K> nuevo = new NodoSimple<>(item, prev.getSiguiente());
			prev.setSiguiente(nuevo);
		}

		tamaño++;
	}

	@Override
	public void agregarAlInicio(K item) {
		NodoSimple<K> nuevo = new NodoSimple<>(item);
		if (estaVacia()) {
			head = nuevo;
			tail = head;
		} else {
			nuevo.setSiguiente(head);
			head = nuevo;
		}
		tamaño++;
	}

	@Override
	public boolean eliminar(K item) {
		if (estaVacia() || item == null)
			return false;

		NodoSimple<K> nodoAnterior = getNodoAnterior(item);

		boolean esHead = item.equals(head.getItem());

		removerSiguiente(nodoAnterior);

		return (nodoAnterior != null || esHead);
	}

	@Override
	public K eliminar(int indice) {
		if (indice < 0 || indice >= tamaño)
			throw new IndexOutOfBoundsException();

		return removerSiguiente(getNodoAnterior(indice));
	}

	@Override
	public int getIndice(K item) {
		for (IteradorSimple<K> itr = iteradorSimple(); itr.hasNext();) {
			if (itr.next().equals(item)) {
				return itr.nextIndex() - 1;
			}
		}
		return -1;
	}

	@Override
	public K getObject(int pos) {
		if (pos < 0 || pos >= tamaño())
			throw new IndexOutOfBoundsException();

		for (IteradorSimple<K> itr = iteradorSimple(); itr.hasNext();) {
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

	private K removerSiguiente(NodoSimple<K> nodo) {
		NodoSimple<K> aRemover = (nodo == null) ? head : nodo.getSiguiente();
		NodoSimple<K> siguiente = aRemover.getSiguiente();

		if (aRemover == head) {
			head = siguiente;
		} else {
			nodo.setSiguiente(siguiente);
		}

		if (aRemover == tail) {
			tail = nodo;
		}

		aRemover.setSiguiente(null);
		tamaño--;
		return aRemover.getItem();
	}

	private NodoSimple<K> getNodoAnterior(K item) {
		if (item == null)
			throw new IllegalArgumentException("Item no puede ser nulo.");

		if (item.equals(head))
			return null;

		NodoSimple<K> nodo = head;
		while (nodo.getSiguiente() != null) {
			if (nodo.getSiguiente().equals(item))
				return nodo;
			else
				nodo = nodo.getSiguiente();
		}
		return null;
	}

	private NodoSimple<K> getNodoAnterior(int indice) {
		if (indice < 0 || indice >= tamaño)
			throw new IndexOutOfBoundsException();

		if (indice == 0)
			return null;

		NodoSimple<K> nodo = head;
		for (int i = 0; i < tamaño; i++) {
			if (i == indice - 1) {
				return nodo;
			}
			nodo = nodo.getSiguiente();
		}
		return nodo;
	}

	/*
	 * Implementación de la interface Iterator, fuértemente influenciada por el
	 * Iterator que utiliza la clase java.util.LinkedList Implementada
	 * principalmente con fines de aprendizaje.
	 */
	private class IteradorSimple<T> implements Iterator<T> {
		private int indiceSiguiente;
		private ListaSimple<T> lista;
		private NodoSimple<T> siguiente;
		private NodoSimple<T> ultimoRetornado = null;

		public IteradorSimple(ListaSimple<T> lista) {
			this.lista = lista;
			siguiente = lista.getHead();
			indiceSiguiente = 0;
		}

		@Override
		public boolean hasNext() {
			return (indiceSiguiente < lista.tamaño());
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
		public void remove() {
		}

	}

}
