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

import java.util.ListIterator;

import cl.pucv.eii.jtaxi.utilidades.listas.Lista;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaCircular;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaDoble;
import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

public class Flota {

	private String nombre;
	private ListaDoble<Taxista> taxistas = new ListaDoble<>();
	private ListaCircular<Paradero> paraderos = new ListaCircular<>();
	private ListaDoble<Taxi> taxis = new ListaDoble<>();

	public Flota(String nombre) {
		this.nombre = nombre;
	}

	public boolean agregarParadero(Paradero p) {
		if (p == null)
			return false;
		if (buscarParadero(p.getNombre()) == null) {
			paraderos.agregar(p);
			return true;
		}
		return false;
	}
	
	public Taxi buscarTaxiCapacidad(int capacidad){
		for(Taxi t: taxis)
			if (t.getCapacidad() >= capacidad)
				return t;
		return null;
	}
	
	public void vaciarTaxi(Taxi t){
		if(t != null)
			t.vaciar();
	}

	public boolean agregarPasajeroTaxi(Pasajero pasajero, String patente){
		if (pasajero == null || patente == null || buscarTaxiPasajero(pasajero.getRut()) != null)
			return false;
		Taxi t = buscarTaxi(patente);
		if (t.getNPasajeros() == t.getCapacidad()) return false;
		
		t.agregarPasajero(pasajero);
		return true;
	}

	public boolean agregarTaxi(Taxi t) {
		if (t == null)
			return false;
		if (buscarTaxi(t.getPatente()) == null) {
			taxis.agregar(t);
			return true;
		}
		return false;
	}
	
	public boolean agregarTaxista(Taxista nuevo) {
		if (nuevo == null)
			return false;
		if (buscarTaxista(nuevo.getRut()) == null) {
			taxistas.agregar(nuevo);
			return true;
		}
		return false;
	}

	public Paradero buscarParadero(String nombre) {
		Paradero p;
		for (ListIterator<Paradero> itr = paraderos.iteradorDoble(); itr
				.hasNext();) {
			p = itr.next();
			if (p.getNombre().equals(nombre))
				return p;
		}
		return null;
	}
	
	public Taxi buscarTaxi(String patente) {
		for (Taxi t : taxis)
			if (t.getPatente().equals(patente))
				return t;
		return null; // en caso de que no exista el taxi
	}

	public Taxi buscarTaxiPasajero(Rut rut) {
		for (Taxi t : taxis)
			if (t.estaOcupado() && t.contienePasajero(rut))
				return t;
		return null;
	}

	public Taxista buscarTaxista(Rut rut) {
		for (Taxista t : taxistas)
			if (t.getRut().equals(rut))
				return t;
		return null;
	}

	public Taxi buscarTaxiTaxista(Rut rut) {
		if (rut == null)
			return null;
		for (Taxi t : taxis) {
			if (t.getTaxista() != null && t.getTaxista().getRut().equals(rut))
				return t;
		}
		return null;
	}

	public boolean eliminarParadero(String nombre) {
		Paradero p = buscarParadero(nombre);
		if (p != null) {
			paraderos.eliminar(p);
			return true;
		}
		return false; // en caso de que no exista el paradero
	}

	public boolean eliminarPasajero(Rut rut) {
		Taxi t = buscarTaxiPasajero(rut);
		return (t == null) ? false : t.eliminarPasajero(rut);
		
	}

	public boolean eliminarTaxi(String patente) {
		Taxi t = buscarTaxi(patente);
		if (t != null) {
			taxis.eliminar(t);
			return true;
		}
		return false;
	}

	public boolean eliminarTaxista(Rut r) {
		Taxista t = buscarTaxista(r);
		if (t != null) {
			Taxi taxi = buscarTaxiTaxista(r);
			if (taxi != null) taxi.setTaxista(null);
			taxistas.eliminar(t);
			return true;
		}
		return false; // en caso de que no exista el taxista
	}

	public String getNombre() {
		return nombre;
	}

	public void listarParaderos(Lista<Paradero> lista) {
		for (ListIterator<Paradero> itr = paraderos.iteradorDoble(); itr
				.hasNext();)
			lista.agregar(itr.next());
	}

	public void listarTaxis(Lista<Taxi> lista) {
		for (Taxi t : taxis)
			lista.agregar(t);
	}

	public void listarTaxista(Lista<Taxista> lista) {
		for (Taxista t : taxistas)
			lista.agregar(t);
	}

	public void modificarSueldo(int nuevoSueldo, Rut rut) {
		Taxista t = buscarTaxista(rut);
		if (t != null)
			t.setSueldo(nuevoSueldo);

	}

	public boolean setTaxistaTaxi(Rut rut, String patente) {
		Taxista nuevo = buscarTaxista(rut);
		Taxi t = buscarTaxi(patente);
		if (t == null)
			return false;

		t.setTaxista(nuevo);
		return true;

	}

	@Override
	public String toString() {
		return nombre;
	}
	

}
