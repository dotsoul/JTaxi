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


/**
 * La clase Rut representa al Rol único tributario utilizado para identificar
 * personas en Chile.
 * 
 * Podría ser más genérica, pero en este caso para simplificar el diseño se
 * decidió dejarla solamente para personas naturales, por lo que solo pueden
 * crearse Ruts que comienzen en 1.000.000 hasta 72.000.000 excluyendo el
 * último.
 */
public final class Rut {
	private int número;
	private char dv;
	private static final char[] digitosVerificadores = { '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', 'K' };
	private FormatoRut formato;

	/**
	 * Crea un Rut con los datos recibidos por parámetro.
	 * 
	 * @param número
	 *            rut número de rut
	 * @param dv
	 *            digito verificador
	 * @throws RutInvalidoException
	 *             si el digito verificador es un valor inválido, el número no corresponde a
	 *             persona natural,número no conincide con el digito
	 *             verificador correspondiente
	 */
	public Rut(int número, char dv) throws RutInvalidoException {
		char dv_m = Character.toUpperCase(dv);
		if (!dvEsValido(dv))
			throw new RutInvalidoException(
					"Digito verificador contiene caracteres inválidos.");
		if (número >= 72000000 || número < 100000)
			throw new RutInvalidoException(
					"Número de Rut debe estar estar en el intervalo [1.000.000,72.000.000[");
		if (generarDV(número) != dv)
			throw new RutInvalidoException(
					"El RUT ingresado no coincide con su digito verificador.");
		this.número = número;
		this.dv = dv_m;
		formato = new FormatoDefault();
	}

	/**
	 * Setea el formato de entrada y salida de toString y fromString
	 * 
	 * @param formato
	 *            clase que implemente la interfaz FormatoRut
	 */
	public void setFormato(FormatoRut formato) {
		this.formato = formato;
	}

	/**
	 * Retorna el número de rut
	 * 
	 * @return rut número de rut
	 */
	public long getNumero() {
		return número;
	}

	/**
	 * Retorna el digito verificador
	 * 
	 * @return digito verificador
	 */
	public char getDV() {
		return dv;
	}

	@Override
	/**
	 * Retorna un string que representa al rut de acuerdo al formato configurado.
	 */
	public String toString() {
		return formato.toString(this);
	}

	/**
	 * Retorna un string que representa al rut segun el formato recibido por
	 * parametro
	 * 
	 * @param formato
	 * @return clase que implemente la interfaz FormatoRut
	 */
	public String toString(FormatoRut formato) {
		return formato.toString(this);
	}

	/**
	 * Crea un objeto Rut a partir de un String recibido por parámetro y que
	 * cumpla con el formato por defecto.
	 * 
	 * @param rut
	 *            string que cumpla con el formato por defecto
	 * @return rut Objeto rut creado
	 */
	public static Rut fromString(String rut) {
		return new FormatoDefault().fromString(rut);
	}

	/**
	 * Crea un objeto Rut a partir de un String recibido por parámetro y que
	 * cumpla con el FormatoRut recibido también por parámetro.
	 * 
	 * @param rut
	 *            string que cumpla con formato
	 * @param formato
	 *            Objeto FormatoRut
	 * @return Objeto rut creado
	 */
	public static Rut fromString(String rut, FormatoRut formato) {
		return formato.fromString(rut);
	}

	/**
	 * Genera un digito verificador valido para el rut recibido por parametro.
	 * Implementa el algoritmo del módulo 11 basado en la información que aparece en:
	 * http://es.wikipedia.org/wiki/Rol_%C3%9Anico_Tributario
	 * @param entero
	 *            entre 1.000.000 y 72.000.000
	 * @return char con el db generado para ese rut
	 */
	public static char generarDV(int número) {
		if (número >= 72000000 || número < 100000)
			throw new IllegalArgumentException();
		char dv;
		int suma = 0;
		int i = 2;
		for (; número != 0; número /= 10) {
			suma += (número % 10) * i;
			i = (i == 7) ? 2 : i + 1;
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

	/**
	 * Comprueba que el caracter recibido por parametro sea uno valido
	 * 
	 * @param dv
	 *            digito verificador
	 * @return true si el digito verificador toma un valor válido
	 */
	public static boolean dvEsValido(char dv) {
		char dv_m = Character.toUpperCase(dv);
		for (char d : digitosVerificadores)
			if (dv_m == d)
				return true;
		return false;
	}

	/**
	 * Sobreescrito de Object.
	 */
	@Override
	public int hashCode() {
		return número;
	}

	/**
	 * Retorna true, segun la especificación del contrato de la clase Object y
	 * si el rut es igual en ambos objetos.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		if (número != ((Rut) o).número)
			return false;
		return true;
	}

}
