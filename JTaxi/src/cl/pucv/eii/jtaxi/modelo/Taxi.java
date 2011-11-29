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
import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

public class Taxi {
	
    private String patente;
    private String marca;
    private String modelo;
    private ListIterator<Paradero> recorrido;
    private Pasajero[] pasajeros;
    private int nPasajeros = 0;
    private Taxista taxista;

    public Taxi(String patente,String marca,String modelo,int capacidad){
        this.patente=patente;
        this.marca=marca;
        this.modelo=modelo;
        pasajeros=new Pasajero[capacidad];
    }
    
    public void cargarRecorrido(ListaCircular<Paradero> lista){
    	recorrido = lista.listIterator();
    }
    
    public void agregarPasajero(Pasajero p) throws CapacidadExcedidaException{
    	if (p == null)
    		throw new IllegalArgumentException("Pasajero no puede ser nulo.");
    	if (nPasajeros == pasajeros.length)
    		throw new CapacidadExcedidaException("Ha excedido la capacidad de pasajeros del taxi.");
    	
    	pasajeros[nPasajeros] = p;
    	nPasajeros++;
    }

    public String siguienteParada(){
    	if(estaOcupado()){
    		String d = pasajeros[nPasajeros-1].getDestino();
    		nPasajeros--;
    		return d;
    	} else {
    		if (recorrido != null && recorrido.hasNext())
    			return recorrido.next().getDireccion();
    	}
    	return null;
    }
  
    public void setTaxista(Taxista t){
    	taxista = t;
    }
    
    public int getCapacidad(){
    	return pasajeros.length;
    }
    
    public Taxista getTaxista(){
    	return taxista;
    }

    public boolean estaOcupado(){
        return (nPasajeros > 0);
    }

    public String getPatente(){
        return patente;
    }

    public String getModelo(){
        return modelo;
    }

    public String getMarca(){
        return marca;
    }
    
    public int getNPasajeros(){
    	return nPasajeros;
    }
    
    public void listarPasajeros(Lista<Pasajero> lista){
    	for(int i = 1;i<=nPasajeros;i++)
    		lista.agregar(pasajeros[i-1]);
    }
    
    public boolean eliminarPasajero(Rut r){
    	for(int i=1;i<=nPasajeros;i++)
    		if(pasajeros[i-1].getRut().equals(r)){
    			pasajeros[i-1] = pasajeros[nPasajeros-1];
    			nPasajeros--;
    			return true;
    		}
    	return false;
    }
    
    public boolean contienePasajero(Rut r){
    	for(int i=1;i<=nPasajeros;i++)
    		if(pasajeros[i-1].getRut().equals(r)){
    			return true;
    		}
    	return false;
    }
    
}
