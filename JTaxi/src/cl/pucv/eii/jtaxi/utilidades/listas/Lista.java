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

/**
 * 
 * @author Julio
 * 
 */
public interface Lista<K> {

	/**
	 * Agrega item en la última posición de la lista
	 * 
	 * @param item
	 *            objeto a agregar
	 */
	public void agregar(K item);

	/**
	 * Agrega item en la posición pos
	 * 
	 * @param indice
	 *            índice donde se ubicará item
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
	 * 
	 * @param item
	 *            objeto a eliminar
	 * @return true si el objeto se encontraba en la lista y fue eliminado
	 */
	public boolean eliminar(K item);

	/**
	 * Elimina el objeto ubicado en la posición indicada.
	 * 
	 * @param indice
	 *            índice del objeto a eliminar
	 * @return el objeto eliminado
	 */
	public K eliminar(int indice);

	/**
	 * Retorna la posición de la primera entrada de item en la lista
	 * 
	 * @param item
	 *            objeto a buscar
	 * @return el índice de la primera entrada de item en la lista o -1 si item
	 */
	public int getPos(K item);

}
