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

/*
 * Para que hacer otra clase completa si la diferencia esta en como se recorre.
 */
public class ListaCircular<K> extends ListaDoble<K>{

	@Override
	public Iterator<K> iterator() {
		return listIterator();
	}
	
	@Override
	public ListIterator<K> listIterator() {
		return new IteradorCircular(this);
	}
	//Para poder listar de principio a fin.
	public ListIterator<K> iteradorDoble(){
		return new IteradorDoble(this);
	}

	/*
	 * IteradorDoble tuneado para lista circular.
	 */
	private class IteradorCircular implements ListIterator<K>{
		private int indiceSiguiente;
		private ListaCircular<K> lista;
		private NodoDoble<K> siguiente;
		private NodoDoble<K> ultimoRetornado = null;
		
		public IteradorCircular(ListaCircular<K> lista){
			this.lista = lista;
			siguiente = lista.getHead();
			indiceSiguiente = 0;
		}

		@Override
		public boolean hasNext() {
			return ( lista.tamaño() > 0 );
		}

		@Override
		public boolean hasPrevious() {
			return ( lista.tamaño() > 0 );
		}

		@Override
		public K next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			ultimoRetornado = siguiente;
			
			siguiente = siguiente.getSiguiente();
			indiceSiguiente++;
			if (siguiente == null){
				indiceSiguiente = 0;
				siguiente = lista.getHead();
			}
			return ultimoRetornado.getItem();
		}

		@Override
		public int nextIndex() {
			return indiceSiguiente;
		}

		@Override
		public K previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			
			ultimoRetornado = siguiente = (siguiente == lista.getHead()) ? lista.getTail() : siguiente.getAnterior();
			indiceSiguiente--;
			if(indiceSiguiente == -1){
				indiceSiguiente = lista.tamaño()-1;
				siguiente = lista.getTail();
			}
			return ultimoRetornado.getItem();
		}

		@Override
		public int previousIndex() {
			return (indiceSiguiente == 0) ? lista.tamaño()-1 : indiceSiguiente-1;
		}

		@Override
		public void remove() {}

		@Override
		public void set(K e) {}
		
		@Override
		public void add(K e) {}
		
	}
}
