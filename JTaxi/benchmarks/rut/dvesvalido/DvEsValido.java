/*
 * Copyright (c) 2011, Julio Jiménez, Rene Toro, José Vargas. All rights reserved.
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
package rut.dvesvalido;

import java.util.Random;

public class DvEsValido {

	public static void main(String[] args) {
		DvEsValido asdf = new DvEsValido();
		asdf.run();
	}

	public void run() {
		int f = 5;
		String[] randomicos_f = getRandomChars(5000000);
		long[][] resultados = new long[7][4];
		String[] randomicos = null;
		for (int i = 1; i < 7; i++) {
			int n = f*(int)Math.pow(10, i);
			System.out.println("Con n:"+n);
			randomicos = new String[n];
			for (int j = 0; j < randomicos.length; j++)
				randomicos[j] = randomicos_f[j];
			long inicio = 0;
			long total = 0;
			// ////////////// ARRAY
			inicio = System.currentTimeMillis();
			for (String s : randomicos) {
				ConArray.dvEsValido(s);
			}
			total = System.currentTimeMillis() - inicio;
			System.out.println("Con Array se demoró: " + total + "ms");
			resultados[i][0] = total;
			// ///////////////

			// //////////////////// INT Y EXCEPTION
			inicio = System.currentTimeMillis();
			for (String s : randomicos) {
				ConIntYException.dvEsValido(s);
			}
			total = System.currentTimeMillis() - inicio;
			System.out
					.println("Con Int y Exception se demoró: " + total + "ms");
			resultados[i][1] = total;
			// /////////////////////////////

			System.out.println("Pasando a char...");
			char[] rnd_ch = aChar(randomicos);
			randomicos = new String[1];
			System.out.println("Ok");

			// ///////////////////////// char
			inicio = System.currentTimeMillis();
			for (char s : rnd_ch) {
				ConChar.dvEsValido(s);
			}
			total = System.currentTimeMillis() - inicio;
			System.out.println("Con char se demoró: " + total + "ms");
			resultados[i][2] = total;
			// ///////////////////////////

			// //////////////////// char array
			inicio = System.currentTimeMillis();
			for (char s : rnd_ch) {
				ConCharArray.dvEsValido(s);
			}
			total = System.currentTimeMillis() - inicio;
			System.out.println("Con char array se demoró: " + total + "ms");
			resultados[i][3] = total;
		}
		
		for(int i = 0; i<resultados.length;i++){
			System.out.println((f*(int)Math.pow(10, i))+","+resultados[i][0]+","+resultados[i][1]+","+resultados[i][2]+","+resultados[i][3]);
		}

	}

	private String[] getRandomChars(int n) {
		System.out.println("Intentando crear un array random...");
		Random azaroso = new Random();
		String[] strings = new String[n];// 5000000
		String abecedario = "abcdefghijklmnñopqrstuvwxyz";
		for (int i = 0; i < strings.length; i++)
			// One liner mágico jejeje :P - Julio
			strings[i] = (azaroso.nextBoolean()) ? Integer.toString(azaroso
					.nextInt(100)) : Character.toString(abecedario
					.charAt(azaroso.nextInt(abecedario.length())));
		System.out.println("OK!");
		return strings;
	}

	private char[] aChar(String[] strings) {
		char[] chars = new char[strings.length];
		for (int i = 0; i < strings.length; i++) {
			chars[i] = strings[i].charAt(0);
		}
		return chars;
	}

}
