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

/**
 * 
 * @author Julio
 *
 */
public class FormatoDefault implements FormatoRut{
	
	private static final Pattern PATRON_RUT = Pattern.compile("(^[1-9]|^[1-9][0-9])[.][0-9][0-9][0-9][.][0-9][0-9][0-9]-[[0-9]|[K|k]]");
	private Matcher mat;
	
	public Rut fromString(String rut){
		if(rut == null || esFormatoValido(rut) ) 
			return null;
		String[] dv_a = rut.split("-");
		String[] num_a = dv_a[0].split("[.]");

		char dv = dv_a[1].charAt(0);
		StringBuilder sb = new StringBuilder();
		for(String digito : num_a){
			sb.append(digito);
		}
		
		int rut_i = Integer.parseInt(sb.toString());
		Rut rutDeString = null;
		try {
			rutDeString = new Rut(rut_i,dv);
		} catch(RutInvalidoException e){
			return null;
		}
		
		return rutDeString;
	}
	
	private boolean esFormatoValido(String rut){
		mat = PATRON_RUT.matcher(rut);
		return mat.find();
	}
	
	@Override
	public String toString(Rut rut) {
		String conFormato = "";

		char[] arrRut = Long.toString(rut.getNumero()).toCharArray();

		int aux = 3 - (arrRut.length % 3);
		for (char c : arrRut) {
			conFormato += (((aux % 3) == 0)) ? "." + c : c;
			aux++;
		}
		conFormato += "-" + rut.getDV();
		return conFormato;
	}

}
