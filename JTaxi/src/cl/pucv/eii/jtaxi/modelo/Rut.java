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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La clase Rut representa al Rol único tributario utilizado para identificar personas en Chile.
 * 
 * TODO Implementar patrón Strategy en toString() y fromString()
 *
 */
public class Rut {
	private int rut;
	private char dv;
	private static final char[] digitosVerificadores = { '1', '2', '3', '4','5', '6', '7', '8', '9', '0', 'K' };
	private static final Pattern PATRON_RUT = Pattern.compile("(^[1-9]|^[1-9][0-9])[.][0-9][0-9][0-9][.][0-9][0-9][0-9]-[[0-9]|[K|k]]");
	
	public Rut(int rut, char dv) throws RutInvalidoException {
		char dv_m = Character.toUpperCase(dv);
		if (!dvEsValido(dv))
			throw new RutInvalidoException("Digito verificador contiene caracteres inválidos.");
		if (rut >= 72000000 || rut < 100000)
			throw new RutInvalidoException("Rut debe estar estar en el intervalo [1.000.000,72.000.000[");
		if (generarDV(rut) != dv)
			throw new RutInvalidoException("El RUT ingresado no coincide con su digito verificador.");
	
		this.rut = rut;
		this.dv = dv_m;
	}

	public long getNumero() {
		return rut;
	}

	public char getDV() {
		return dv;
	}

	@Override
	public String toString() {
		String conFormato = "";

		char[] arrRut = Long.toString(rut).toCharArray();

		int aux = 3 - (arrRut.length % 3);
		for (char c : arrRut) {
			conFormato += (((aux % 3) == 0)) ? "." + c : c;
			aux++;
		}
		conFormato += "-" + dv;
		return conFormato;
	}


	public static char generarDV(int rut) {
		if (rut >= 72000000 || rut < 100000)
			throw new IllegalArgumentException();
		char dv;
		int suma = 0;
		int i = 2;
		for(; rut != 0; rut /= 10){
			suma += (rut % 10)*i;
			i = (i == 7) ? 2 : i+1;
		}
		i = 11 - (suma % 11);
		
		if (i == 11)
			dv = '0';
		else if (i == 10)
			dv = 'K';
		else
			dv = Character.forDigit(i, 10);
		
		return dv;
	}

	public static boolean dvEsValido(char dv) {
		char dv_m = Character.toUpperCase(dv);
		for (char d : digitosVerificadores)
			if (dv_m == d)
				return true;
		return false;
	}
	
	public static Rut fromString(String rut){
		if(rut == null ) return null;
		if(!esFormatoValido(rut))
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
	
	private static boolean esFormatoValido(String rut){
		Matcher mat;
		mat = PATRON_RUT.matcher(rut);
		return mat.find();
	}

	@Override
	public int hashCode() {
		return rut;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		if (rut != ((Rut)o).rut)
			return false;
		return true;
	}
	
}
