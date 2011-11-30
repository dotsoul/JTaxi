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
package cl.pucv.eii.jtaxi.main;

import java.awt.EventQueue;
import java.awt.SplashScreen;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import cl.pucv.eii.jtaxi.gui.JTaxi;
import cl.pucv.eii.jtaxi.modelo.Central;

public class Main {

	public static void main(String... args) {
		Main app = new Main();
		app.correr();
	}

	private void correr() {
		SplashScreen splash = SplashScreen.getSplashScreen();
		/*
		 * Si se ejecuta vía JAR aparece una SplashScreen al comienzo y dejamos
		 * el hilo esperar 1,5 segs para que se vea. (Un poco de EyeCandy no
		 * hace mal) En caso contrario continua la aplicación normalmente.
		 */
		if (splash != null) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			splash.close();
		}

		setSkin();
		// El objeto JFrame debe ser creado dentro del Event Dispatching Thread
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				String input = null;
				JTaxi jtaxi = new JTaxi();//Creando el JFrame
				boolean valido = false; //Necesitamos sí o sí una central
				while(!valido){
					input = JOptionPane.showInputDialog(jtaxi, "Ingrese nombre de la central:",
							"Crear central", JOptionPane.QUESTION_MESSAGE);
					if (input != null) {
						if (input.trim().length() > 0
								&& !input.matches("[\u00A0]+")) { //Nada de caracteres vacios
							valido = true;
						} else {
							JOptionPane.showMessageDialog(jtaxi,
									"El nombre no puede ser vacío.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(jtaxi,
								"Debe crear una central para continuar.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				jtaxi.setCentral(new Central(input)); //Le entregamos la central al frame.

			}
		});
	}

	private void setSkin(String skin) {
		try {
			UIManager.setLookAndFeel(skin);
		} catch (Exception e) {
			// Si hay alguna exception se vuelve al tema default.
			setSkin(UIManager.getCrossPlatformLookAndFeelClassName());
		}
	}

	private void setSkin() {
		// Intenta obtener el look del sistena.
		setSkin(UIManager.getSystemLookAndFeelClassName());
	}

}
