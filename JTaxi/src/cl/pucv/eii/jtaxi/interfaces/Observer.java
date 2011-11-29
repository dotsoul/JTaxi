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
 * Implementación sencilla del patrón Observer
 * Al implementar esta interfaz, la clase puede ser notificada de cambios
 * en objetos observables.
 */
public interface Observer {

	/**
	 * El objeto observado llama a este método para notificar una modificación en
	 * la estructura recibida por parámetro.
	 * @param estructura nombre de la estructura modificada
	 */
	public void actualizar(String estructura);
	
}
