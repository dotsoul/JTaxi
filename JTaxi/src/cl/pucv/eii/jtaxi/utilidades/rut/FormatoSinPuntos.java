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
package cl.pucv.eii.jtaxi.utilidades.rut;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FormatoSinPuntos implements FormatoRut{
	
	private static final Pattern PATRON_RUT = Pattern.compile("[0-9]{7,8}-[[0-9]|[K|k]]");
	private Matcher mat;

	@Override
	public Rut fromString(String rut) {
		if(rut == null || esFormatoValido(rut) ) 
			return null;
		String[] dv_a = rut.split("-");
		char dv = dv_a[1].charAt(0);
		int rut_i = Integer.parseInt(dv_a[0]);
		
		Rut nRut = null;
		try{
			nRut = new Rut(rut_i, dv);
		} catch(RutInvalidoException e) {
			return null;
		}
		
		return nRut;
	}

	@Override
	public String toString(Rut rut) {
		return (rut != null) ? Long.toString(rut.getNumero())+"-"+Character.toString(rut.getDV()) : null;
	}
	
	private boolean esFormatoValido(String rut){
		mat = PATRON_RUT.matcher(rut);
		return mat.find();
	}

}
