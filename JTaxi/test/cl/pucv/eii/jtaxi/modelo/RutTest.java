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

import static org.junit.Assert.*;

import org.junit.Test;

import cl.pucv.eii.jtaxi.modelo.RutInvalidoException;
import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

public class RutTest {

	@SuppressWarnings("unused")
	@Test
	public void testRut() {
		boolean esValido = false;
		try {
			Rut valido = new Rut(6585555,'0');
			esValido = true;
		} catch (RutInvalidoException e) {
			fail("Rut Valido no Valida");
		}
		assertTrue(esValido);
		esValido = false;
		try {
			Rut invalido = new Rut(7331694, 'K');
			esValido = true;
			fail("Rut Invalido si Valida");
		} catch (RutInvalidoException e) {
			assert(esValido);
		}

	}
	
	@Test
	public void testDVValido(){
		char[] digitosVerificadores = {'1','2','3','4','5','6','7','8','9','0','K'};
		
		for (char dv : digitosVerificadores)
			assertTrue(Rut.dvEsValido(dv));
		
		for (int i = -100; i<-1;i++)
			assertTrue(!Rut.dvEsValido(Integer.toString(i).charAt(0)));
		
		assertTrue(!Rut.dvEsValido('J'));
		assertTrue(Rut.dvEsValido('k'));
	}

	@Test
	public void testToString() throws RutInvalidoException{
		Rut rut = new Rut(6585555, '0');
		Rut rut2 = new Rut(17422240, '1');
		Rut rut3 = new Rut(19678162 , '5');
		Rut rut4 = new Rut(21979391, 'K');
		assertEquals("6.585.555-0",rut.toString());
		assertEquals("19.678.162-5",rut3.toString());
		assertEquals("21.979.391-K" ,rut4.toString());
		assertEquals("17.422.240-1", rut2.toString());
		assertTrue("21.979.391-k".equalsIgnoreCase(rut4.toString()));
	}
	
	@Test
	public void testFromString() throws RutInvalidoException{
		Rut x = Rut.fromString("17.915.551-6");
		Rut y = Rut.fromString("7.019.147-4");
		Rut z = Rut.fromString("a.654.999-K");
		Rut w = Rut.fromString("01.654.999-K");
		assertEquals("17.915.551-6", x.toString());
		assertEquals("7.019.147-4", y.toString());
		assertNull(w);
		assertNull(z);
		assertNull(Rut.fromString(null));
		assertNull(Rut.fromString("111.111.111-0"));
		assertNull(Rut.fromString("011.111.111-0"));
		assertNull(Rut.fromString("k11.111.111-0"));
		assertNull(Rut.fromString("0.111.111-0"));
		assertNull(Rut.fromString("00.111.111-0"));
	}
	

}
