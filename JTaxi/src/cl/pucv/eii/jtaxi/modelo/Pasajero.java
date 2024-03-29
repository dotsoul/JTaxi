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

import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

public class Pasajero extends Persona {

    private String destino;
    private int teléfono;

    public Pasajero(String nombre, Rut rut, int teléfono, String destino){
        super(nombre, rut);
        this.destino = destino;
        this.teléfono=teléfono;
    }

    public String getDestino() {
        return destino;
    }

    public int getTelefono() {
        return teléfono;
    }
    
}
