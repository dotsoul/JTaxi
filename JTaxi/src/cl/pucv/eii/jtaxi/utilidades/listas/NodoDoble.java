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

class NodoDoble<K> {
	private K item;
	private NodoDoble<K> anterior;
	private NodoDoble<K> siguiente;
	
	NodoDoble(K item){
		this.item = item;
		this.anterior = null;
		this.siguiente = null;
	}
	
	NodoDoble(K item, NodoDoble<K> anterior, NodoDoble<K> siguiente){
		this.anterior = anterior;
		this.siguiente = siguiente;
		this.item = item;
	}

	NodoDoble<K> getAnterior() {
		return anterior;
	}

	void setAnterior(NodoDoble<K> anterior) {
		this.anterior = anterior;
	}

	NodoDoble<K> getSiguiente() {
		return siguiente;
	}

	void setSiguiente(NodoDoble<K> siguiente) {
		this.siguiente = siguiente;
	}

	K getItem() {
		return item;
	}
	
}
