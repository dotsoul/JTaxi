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
package cl.pucv.eii.jtaxi.interfaces;

/**
 * 
 * Implementación sencilla del patrón Observer.
 * Esta interfaz representa la capacidad que tiene un objeto de ser observado
 * por una lista de Observers previamente registrados.
 * En este casoespecífico al problema que resuelve el implementar este patrón
 * en el proyecto, notificarObservers además entregar un string que representa
 * que clase de estructura se modificó.
 *
 */
public interface Observable {

	/**
	 * Agregar al Observer o a la lista de observadores para ser notificado de cambios.
	 * @param o Observer a agregar.
	 */
	public void agregarObserver(Observer o);
	/**
	 * Elimina el Observer o de la lista de observadores, ya no será notificado de cambios.
	 * @param o Observer a eliminar.
	 */
	public void eliminarObserver(Observer o);
	/**
	 * Envía a todos los Observer en la lista de observadores de un cambio en la estructura.
	 * @param estructura nombre de la estructura que fué modificada.
	 */
	public void notificarObservers(String estructura);
	
}
