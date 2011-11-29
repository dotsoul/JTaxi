/*
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

import java.util.ArrayList;
import java.util.Iterator;

import cl.pucv.eii.jtaxi.interfaces.Observable;
import cl.pucv.eii.jtaxi.interfaces.Observer;
import cl.pucv.eii.jtaxi.utilidades.listas.Lista;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaDoble;
import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

public class Central implements Observable {

	private String nombre;
	private ListaDoble<Flota> flotas;
	private ListaDoble<Sector> sectores;
	private ArrayList<Observer> observers = new ArrayList<>();

	public Central(String nombre) {
		this.nombre = nombre;
		flotas = new ListaDoble<Flota>();
		sectores = new ListaDoble<Sector>();
	}

	public boolean agregarFlota(Flota f) {
		if (f != null && buscarFlota(f.getNombre().toLowerCase()) == null) {
			flotas.agregar(f);
			notificarObservers("Flota");
			return true;
		}
		return false;
	}

	public boolean agregarParaderoSector(Paradero p, String sector) {
		if (p == null)
			return false;

		for (Sector s : sectores)
			if (s.buscarParadero(p.getNombre()) != null)
				return false;

		buscarSector(sector).agregarParadero(p);
		notificarObservers("Paradero");
		notificarObservers("Sector");
		return true;
	}

	public boolean agregarPasajeroTaxi(Pasajero pasajero, String patente){
		if(pasajero == null || patente == null)
			return false;
		
		for(Flota f: flotas)
			if(f.buscarTaxiPasajero(pasajero.getRut()) != null)
				return false;
		
		Flota f = buscarFlotaTaxi(patente);
		if (f == null)
			return false;
		if(f.agregarPasajeroTaxi(pasajero, patente)){
			notificarObservers("Pasajero");
			notificarObservers("Taxi");
			return true;
		}
		return false;
	}

	public boolean agregarSector(Sector s) {
		if (s != null && buscarSector(s.getNombre().toLowerCase()) == null) {
			sectores.agregar(s);
			notificarObservers("Sector");
			return true;
		}
		return false;
	}

	public boolean agregarTaxiFlota(Taxi taxi, Flota flota){
		if(flota == null) return false;
		if( agregarTaxiFlota(taxi, flota.getNombre())){
			notificarObservers("Taxi");
			notificarObservers("Flota");
			return true;
		}
		return false;
	}

	public boolean agregarTaxiFlota(Taxi taxi, String flota) {
		if (taxi == null)
			return false;
		Flota f = buscarFlota(flota);
		if (f == null)
			return false;
		
		if (buscarFlotaTaxi(taxi.getPatente()) == null){
			f.agregarTaxi(taxi);
			notificarObservers("Taxi");
			notificarObservers("Flota");
			return true;
		}
		
		return false;

	}

	public boolean agregarTaxistaFlota(Taxista nuevo, Flota flota) {
		if (nuevo == null)
			return false;
		Flota f = buscarFlotaTaxista(nuevo.getRut());

		if (f != null)
			return false;

		if( flota.agregarTaxista(nuevo) ){
			notificarObservers("Taxista");
			notificarObservers("Flota");
		}
		
		return false;
	}

	public boolean agregarTaxistaTaxi(String patente, Rut rut) {
		if (patente == null || rut == null)
			return false;
		Flota f = buscarFlotaTaxista(rut);
		Flota y = buscarFlotaTaxi(patente);

		if (f == null || y == null || f != y)
			return false;
		
		if (f.setTaxistaTaxi(rut, patente)){
			notificarObservers("Taxi");
		}

		return false;
	}

	/**
	 * Retorna la flota con nombre equivalente a nombre.
	 * 
	 * @param nombre
	 * @return
	 */
	public Flota buscarFlota(String nombre) {
		for (Flota f : flotas)
			if (f.getNombre().equalsIgnoreCase(nombre))
				return f;
		return null;
	}

	/**
	 * Retorna la flota que contiene al Taxi con patente p.
	 * 
	 * @param rut
	 * @return
	 */
	public Flota buscarFlotaTaxi(String p) {
		for (Flota f : flotas) {
			if (f.buscarTaxi(p) != null)
				return f;
		}
		return null;
	}

	/**
	 * Retorna la flota que contiene al taxista con rut == rut.
	 * 
	 * @param rut
	 * @return
	 */
	public Flota buscarFlotaTaxista(Rut rut) {
		for (Flota f : flotas) {
			if (f.buscarTaxista(rut) != null)
				return f;
		}
		return null;
	}

	public Paradero buscarParadero(String nombre) {
		Paradero p;
		for (Sector s : sectores) {
			p = s.buscarParadero(nombre);
			if (p != null)
				return p;
		}
		return null;
	}

	public Sector buscarSector(Paradero p) {
		if (p == null)
			return null;
		for (Sector s : sectores)
			if (s.buscarParadero(p.getNombre()) != null)
				return s;
		return null;
	}
	
	public Sector buscarSector(String nombre) {
		for (Sector s : sectores)
			if (s.getNombre().equalsIgnoreCase(nombre))
				return s;
		return null; // en caso que no existe el sector con nombre n
	}
	
	public boolean eliminarFlota(String nombre) {
		if( flotas.eliminar(buscarFlota(nombre)) ){
			notificarObservers("Flota");
			return true;
		}
		return false;
	}
	
	public boolean eliminarParadero(String nombre) {
		boolean encontrado = false;
		for (Iterator<Sector> itr = sectores.iterator(); !encontrado
				&& itr.hasNext();) {
			encontrado = itr.next().eliminarParadero(nombre);
		}
		if (!encontrado)
			return false;
		for (Flota f : flotas) {
			f.eliminarParadero(nombre);
		}
		notificarObservers("Paradero");
		return true;
	}
	
	public boolean eliminarPasajero(Rut r){
		for (Flota f: flotas)
				if (f.eliminarPasajero(r)){
					notificarObservers("Pasajero");
					notificarObservers("Rut");
					return true;
				}
		return false;
	}

	public boolean eliminarRut(Rut r){
		if(eliminarTaxista(r)){
			notificarObservers("Taxista");
			notificarObservers("Rut");
			return true;
		}
		
		if(eliminarPasajero(r)){
			notificarObservers("Pasajero");
			notificarObservers("Rut");
			return true;
		}
	
		return false;
	}

	public boolean eliminarSector(String nombre) {
		ListaDoble<Paradero> paraderos = new ListaDoble<>();
		Sector s = buscarSector(nombre);

		if (s == null)
			return false;
		s.listarParaderos(paraderos);

		for (Paradero p : paraderos)
			for (Flota f : flotas)
				f.eliminarParadero(p.getNombre());

		if(sectores.eliminar(s)){
			notificarObservers("Sector");
			return true;
		}
		return false;
	}

	public boolean eliminarTaxi(String patente) {
		Flota f = buscarFlotaTaxi(patente);
		if (f == null)
			return false;
		notificarObservers("Taxi");
		f.eliminarTaxi(patente);
		return true;
	}

	public boolean eliminarTaxista(Rut rut) {
		Flota f = buscarFlotaTaxista(rut);
		if (f == null)
			return false;
		if(f.eliminarTaxista(rut)){
			notificarObservers("Taxista");
			notificarObservers("Rut");
			return true;
		}
		return false;
	}

	public Lista<Flota> getFlotas() {
		return this.flotas;
	}

	public String getNombre() {
		return nombre;
	}
	

	public Lista<Sector> getSectores() {
		return this.sectores;
	}

	public void listarFlotas(Lista<Flota> lista) {
		for (Flota f : flotas)
			lista.agregar(f);
	}

	public void listarSectores(Lista<Sector> lista) {
		for (Sector s : sectores)
			lista.agregar(s);
	}

	@Override
	public String toString() {
		return nombre;
	}

	@Override
	public void agregarObserver(Observer o) {
		if(!observers.contains(o))
			observers.add(o);
	}

	@Override
	public void eliminarObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notificarObservers(String estructura) {
		for(Observer o : observers)
			o.actualizar(estructura);
	}
}
