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

public class ListaCircular<K> implements Lista<K>, Iterable<K> {

	@Override
	public void agregar(K item) {
		System.out.println( "Test" );
		
	}

	@Override
	public void agregar(int indice, K item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarAlInicio(K item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean eliminar(K item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public K eliminar(int indice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPos(K item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public K getObject(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int tamaño() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean estaVacia() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
