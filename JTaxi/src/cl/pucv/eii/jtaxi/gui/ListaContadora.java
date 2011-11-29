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
package cl.pucv.eii.jtaxi.gui;

import java.util.Iterator;

import cl.pucv.eii.jtaxi.utilidades.listas.Lista;

/*
 * Lista que solo mantiene cuantas veces se le han agregado elementos.
 */
public class ListaContadora<K> implements Lista<K>{
	
	private int contador;

	public Iterator<K> iterator() {return null;}
	public void agregar(Object item) {contador++;}
	public void agregar(int indice, Object item) {contador++;}
	public void agregarAlInicio(Object item) {contador++;}
	public int getIndice(Object item) {	return -1;}
	public K getObject(int i) {return null;}
	public int tamaño() {return contador;}
	public boolean estaVacia() {return contador == 0;}
	public boolean eliminar(Object item) {return true;}
	public K eliminar(int indice) {return null;}

}
