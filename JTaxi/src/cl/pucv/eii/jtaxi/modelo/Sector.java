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
package cl.pucv.eii.jtaxi.modelo;

import cl.pucv.eii.jtaxi.utilidades.listas.Lista;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaDoble;

/**
 * La clase Sector representa una cierta área dentro de una ciudad.
 * @author René, José
 *
 */
public class Sector {

	private String nombre;
	private ListaDoble<Paradero> paraderos = new ListaDoble<Paradero>();

	public Sector(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void listarParaderos(Lista<Paradero> lista) {
		for (Paradero p: paraderos){
			lista.agregar(p);
		}
	}

	public boolean agregarParadero(Paradero p) {
		if (p == null)
			return false;
		
		if(buscarParadero(p.getDireccion()) != null)
			return false;

		if (buscarParadero(p.getNombre()) == null) {
			paraderos.agregar(p);
			return true;
		}
		
		return false; // Si el paradero existe.
	}

	public Paradero buscarParadero(String nombre) {
		if (nombre == null)
			return null;
		
		for (Paradero p : paraderos)
			if (p.getNombre().equals(nombre))
				return p;
		return null;

	}

	public boolean eliminarParadero(String nombre) {
		return paraderos.eliminar( buscarParadero(nombre) );
	}
	
	public boolean eliminarParaderoDirección(String dirección){
		return paraderos.eliminar(buscarParadero(dirección));
	}
	
	public Paradero buscarParaderoDirección(String dirección){
		if (dirección == null)
			return null;
		
		for(Paradero p : paraderos){
			if(p.getDireccion().equals(dirección))
				return p;
		}
		return null;
	}

}
