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
import cl.pucv.eii.jtaxi.utilidades.listas.ListaSimple;

public class Central {
	
    private String nombre;
    private ListaSimple<Flota> flotas;
    private ListaSimple<Sector> sectores;

    public Central(String nombre){
        this.nombre = nombre;
        flotas = new ListaSimple<Flota>();
        sectores = new ListaSimple<Sector>();
    }
    
    public boolean agregarFlota(Flota f){
        if(f != null && buscarFlota(f.getNombre()) == null){
            flotas.agregar(f);
                    return true;
        }
        return false;
    }
    
    public boolean eliminarFlota(String nombre){
    	return flotas.eliminar(buscarFlota(nombre));
    }
    
    /**
     * Retorna la flota que contiene al taxista con rut == rut.
     * @param rut
     * @return
     */
    public Flota buscarFlotaTaxista(Rut rut){
    	for(Flota f: flotas){
    		if (f.buscarTaxista(rut) != null)
    			return f;
    	}
    	return null;
    }
    
    /**
     * Retorna la flota que contiene al Taxi con patente p.
     * @param rut
     * @return
     */
    public Flota buscarFlotaTaxi(String p){
    	for(Flota f: flotas){
    		if (f.buscarTaxi(p) != null)
    			return f;
    	}
    	return null;
    }
    
    /**
     * Retorna la flota con nombre equivalente a nombre.
     * @param nombre
     * @return
     */
    public Flota buscarFlota(String nombre){
    	for(Flota f: flotas)
    		if(f.getNombre().equals(nombre))
    			return f;
    	return null;
    }
    
    public void listarFlotas(Lista<Flota> lista){
    	for(Flota f: flotas)
    		lista.agregar(f);
    }
    
    public boolean agregarSector(Sector s){
        if(s != null && buscarSector(s.getNombre()) == null){
            sectores.agregar(s);
                    return true;
        }
        return false;
    }
    public boolean eliminarSector(String nombre){
        return sectores.eliminar(buscarSector(nombre));
    }
    
    public Sector buscarSector(String nombre){
    	for(Sector s: sectores)
    		if(s.getNombre().equals(nombre))
    			return s;
    	return null; //en caso que no existe el sector con nombre n
    }
    
    public Sector buscarSector(Paradero p){
    	if (p == null) return null;
    	for(Sector s: sectores)
    		if( s.buscarParadero(p.getNombre()) != null)
    			return s;
    	return null;
    }
    
    public void listarSectores(Lista<Sector> lista){
    	for(Sector s: sectores)
    		lista.agregar(s);
    }

    public Paradero buscarParadero(String nombre){
    	Paradero p;
    	for(Sector s: sectores){
    		p = s.buscarParadero(nombre);
    		if (p != null) return p;
    	}
    	return null;
    }

    public boolean eliminarTaxista(Rut rut){
    	Flota f = buscarFlotaTaxista(rut);
    	if (f == null)
    		return false;
    	return f.eliminarTaxista(rut);
    }
    
    public String getNombre(){
        return nombre;
    }
    
	@Override
	public String toString() {
		return nombre;
	}

}
