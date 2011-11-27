/*
 * Copyright (c) 2011, Julio Jim�nez, Ren� Toro, Jos� Vargas. All rights reserved.
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

/**
 * 
 * @author Julio
 * 
 */
public interface Lista<K> {

	/**
	 * Agrega item en la �ltima posici�n de la lista
	 * 
	 * @param item
	 *            objeto a agregar
	 */
	public void agregar(K item);

	/**
	 * Agrega item en la posici�n pos
	 * 
	 * @param indice
	 *            �ndice donde se ubicar� item
	 * @param item
	 *            objeto a agregar
	 */
	public void agregar(int indice, K item);

	/**
	 * Agrega item en el indice 0
	 * 
	 * @param item
	 *            objeto a agregar
	 */
	public void agregarAlInicio(K item);

	/**
	 * Elimina la primera entrada de item de la lista.
	 * Devuelve false si item es null.
	 * @param item
	 *            objeto a eliminar
	 * @return true si el objeto se encontraba en la lista y fue eliminado,
	 *         false si no se encontraba o era null.
	 */
	public boolean eliminar(K item);

	/**
	 * Elimina el objeto ubicado en la posici�n indicada.
	 * 
	 * @param indice
	 *            �ndice del objeto a eliminar
	 * @return el objeto eliminado
	 */
	public K eliminar(int indice);

	/**
	 * Retorna la posici�n de la primera entrada de item en la lista
	 * 
	 * @param item
	 *            objeto a buscar
	 * @return el �ndice de la primera entrada de item en la lista o -1 si item
	 */
	public int getPos(K item);

	/**
	 * Retorna el objeto almacenado en el indice i.
	 * 
	 * 
	 * @param indice
	 *            posicion del objeto
	 * @return objeto almacenado en la posicion i
	 * 
	 */
	public K getObject(int i);

	/**
	 * Retorna el numero de items en la lista.
	 * 
	 * @return un int con la cantidad de items en la lista.
	 */
	public int tama�o();

	/**
	 * Devuelve true si la lista no tiene elementos.
	 * 
	 * @return true si la lista no tiene elementos.
	 */
	public boolean estaVacia();

}
