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
package cl.pucv.eii.jtaxi.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import cl.pucv.eii.jtaxi.gui.tablemodels.FlotaTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.ParaderoTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.PasajeroTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.RutTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.SectorTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.TaxiTableModel;
import cl.pucv.eii.jtaxi.gui.tablemodels.TaxistaTableModel;
import cl.pucv.eii.jtaxi.modelo.Central;
import cl.pucv.eii.jtaxi.modelo.Flota;
import cl.pucv.eii.jtaxi.modelo.Paradero;
import cl.pucv.eii.jtaxi.modelo.Pasajero;
import cl.pucv.eii.jtaxi.modelo.RutInvalidoException;
import cl.pucv.eii.jtaxi.modelo.Sector;
import cl.pucv.eii.jtaxi.modelo.Taxi;
import cl.pucv.eii.jtaxi.modelo.Taxista;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaDoble;
import cl.pucv.eii.jtaxi.utilidades.rut.Rut;

@SuppressWarnings("serial")
public class Inspector extends JDialog implements ActionListener {
	private JButton agregarBoton = new JButton("Agregar");
	private JButton buscarBoton = new JButton("Buscar");
	private JButton eliminarBoton = new JButton("Eliminar");
	private JComboBox<String> estructuraCB = new JComboBox<>();
	private JLabel estructuraLabel = new JLabel("Estructura: ");
	private JButton inspeccionarBoton = new JButton("Inspeccionar");
	private JLabel manipularLabel = new JLabel("Manipular Estructuras");
	private JScrollPane scroll = new JScrollPane();
	private JSeparator separador = new JSeparator();
	private JTable tabla = new JTable();
	private Central central;
	private ListaDoble<Rut> rutsTemporales;
	private HashMap<String, AbstractTableModel> estructuraModelo = new HashMap<>();

	public Inspector(JFrame dueño, Central central, ListaDoble<Rut> r) {
		super(dueño, "Manipular Estructuras de JTaxi", true);
		this.central = central;
		this.rutsTemporales = r;
		init();
		setLocationRelativeTo(dueño);
		setResizable(false);
	}

	private void init() {

		manipularLabel.setFont(new java.awt.Font("Tahoma", 1, 14));

		String[] aux = new String[] { "Sector", "Paradero", "Flota", "Taxi",
				"Taxista", "Pasajero", "Rut" };
		estructuraCB.setModel(new DefaultComboBoxModel<String>(aux));
		
		setEstructuraModelo(aux);
		
		estructuraCB.setSelectedIndex(2);
		estructuraCB.setActionCommand("cambioEstructura");
		estructuraCB.addActionListener(this);

		ListaDoble<Flota> flotas = new ListaDoble<>();
		central.listarFlotas(flotas);
		FlotaTableModel fModel = new FlotaTableModel(central);

		tabla.setModel(fModel);
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.setColumnSelectionAllowed(false);
		agregarBoton.setMinimumSize(new java.awt.Dimension(95, 23));
		eliminarBoton.setMinimumSize(new java.awt.Dimension(95, 23));
		buscarBoton.setMinimumSize(new java.awt.Dimension(95, 23));
		inspeccionarBoton.setMinimumSize(new java.awt.Dimension(95, 23));

		scroll.setViewportView(tabla);

		eliminarBoton.addActionListener(this);
		buscarBoton.addActionListener(this);
		agregarBoton.addActionListener(this);
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup().addContainerGap()
								.addComponent(manipularLabel)
								.addContainerGap(261, Short.MAX_VALUE))
				.addComponent(separador, javax.swing.GroupLayout.DEFAULT_SIZE,
						422, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(estructuraLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(estructuraCB,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										71,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(280, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(agregarBoton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										96,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(eliminarBoton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										96,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(buscarBoton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										96,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(inspeccionarBoton,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										96,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(scroll,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										402, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(manipularLabel)
								.addGap(8, 8, 8)
								.addComponent(separador,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(estructuraLabel)
												.addComponent(
														estructuraCB,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														agregarBoton,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														eliminarBoton,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														buscarBoton,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(inspeccionarBoton))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scroll,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										275,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		inspeccionarBoton.setVisible(false);
		pack();
	}

	private void setEstructuraModelo(String[] estructuras) {
		//{ "Sector", "Paradero", "Flota", "Taxi","Taxista", "Pasajero", "Rut" };
		AbstractTableModel[] modelos = new AbstractTableModel[] {
				new SectorTableModel(central),
				new ParaderoTableModel(central),
				new FlotaTableModel(central),
				new TaxiTableModel(central),
				new TaxistaTableModel(central),
				new PasajeroTableModel(central),
				new RutTableModel(central, rutsTemporales)
				};
		for(int i = 0;i<estructuras.length;i++){
			estructuraModelo.put(estructuras[i], modelos[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		switch (evt.getActionCommand()) {
		case "cambioEstructura":
			tabla.setModel(estructuraModelo.get(estructuraCB.getSelectedItem().toString()));
			tabla.updateUI();
			break;

		case "Eliminar":
			String eliminarEstructura = estructuraCB.getSelectedItem()
					.toString();
			int filaSeleccionada = tabla.getSelectedRow();
			if (filaSeleccionada >= 0)
				switch (eliminarEstructura) {
				case "Taxista":
					central.eliminarTaxista(Rut.fromString(tabla.getValueAt(
							filaSeleccionada, 1).toString()));
					break;
				case "Flota":
					central.eliminarFlota(tabla.getValueAt(filaSeleccionada, 0)
							.toString());
					break;
				case "Sector":
					central.eliminarSector(tabla
							.getValueAt(filaSeleccionada, 0).toString());
					break;
				case "Taxi":
					central.eliminarTaxi(tabla.getValueAt(filaSeleccionada, 1)
							.toString());
					break;
				case "Pasajero":
					central.eliminarPasajero(Rut.fromString(tabla.getValueAt(
							filaSeleccionada, 1).toString()));
					break;
				case "Paradero":
					central.eliminarParadero(tabla.getValueAt(filaSeleccionada,
							0).toString());
					break;
				case "Rut":
					Rut auxR = null;
					try {
						auxR = new Rut(Integer.parseInt(tabla.getValueAt(
								filaSeleccionada, 0).toString()), tabla
								.getValueAt(filaSeleccionada, 1).toString()
								.charAt(0));
					} catch (RutInvalidoException e) {
						e.printStackTrace();
					}
					if (!rutsTemporales.eliminar(auxR))
						central.eliminarRut(auxR);
					break;
				}
			tabla.updateUI();
			break;
		case "Buscar":
			String buscarEstructura = estructuraCB.getSelectedItem().toString();
			String buscarAuxString;
			Rut buscarRutAux;
			switch (buscarEstructura) {
			case "Taxista":
				buscarRutAux = pedirRut("Ingrese rut de taxista.");
				if (!locate(1, buscarRutAux.toString()))
					mostrarDialogoError("No se ha encontrado el taxista.");
				break;
			case "Flota":
				buscarAuxString = pedirString("Ingrese un nombre de flota.");
				if (!locate(0, buscarAuxString))
					mostrarDialogoError("No se ha encontrado la flota.");
				break;
			case "Sector":
				buscarAuxString = pedirString("Ingrese un nombre de sector.");
				if (!locate(0, buscarAuxString))
					mostrarDialogoError("No se ha encontrado el sector");
				break;
			case "Taxi":
				buscarAuxString = pedirString("Ingrese una patente");
				if (!locate(1, buscarAuxString))
					mostrarDialogoError("No se ha encontrado el taxi");
				break;
			case "Pasajero":
				buscarRutAux = pedirRut("Ingrese rut de pasajero.");
				if (!locate(1, buscarRutAux.toString()))
					mostrarDialogoError("No se ha encontrado el pasajero");
				break;
			case "Paradero":
				buscarAuxString = pedirString("Ingrese un nombre de paradero");
				if (!locate(0, buscarAuxString))
					mostrarDialogoError("No se ha encontrado el paradero");
				break;
			case "Rut":
				buscarRutAux = pedirRut("Ingrese un rut.");
				buscarAuxString = Long.toString(buscarRutAux.getNumero());
				if (!locate(0, buscarAuxString))
					mostrarDialogoError("No se ha encontrado el rut");
				break;
			}
			break;
		case "Agregar":
			String agregarEstructura = estructuraCB.getSelectedItem()
					.toString();
			String auxAgregarS;
			Rut auxAgregarR;
			switch (agregarEstructura) {
			case "Taxista":
				Taxista nuevoTaxista = new Taxista(
						pedirString("Ingrese nombre de taxista:"),
						pedirRut("Ingrese rut de taxista"), pedirInt(
								"Ingrese sueldo", 1));
				Flota flotaNuevoTaxista = central
						.buscarFlota(pedirString("Ingrese nombre flota"));
				if (flotaNuevoTaxista == null)
					mostrarDialogoError("No se encuentra la flota.");
				else if (!central.agregarTaxistaFlota(nuevoTaxista,
						flotaNuevoTaxista))
					mostrarDialogoError("Ya existe otro taxista con ese rut");
				break;
			case "Flota":
				auxAgregarS = pedirString("Ingrese nombre flota: ");
				Flota nuevaFlota = new Flota(auxAgregarS);
				if (!central.agregarFlota(nuevaFlota))
					mostrarDialogoError("Ya existe una flota con ese nombre.");
				break;
			case "Sector":
				auxAgregarS = pedirString("Ingrese nombre sector: ");
				Sector nuevoSector = new Sector(auxAgregarS);
				if (!central.agregarSector(nuevoSector))
					mostrarDialogoError("Ya existe un sector con ese nombre.");
				break;
			case "Taxi":
				Taxi nuevoTaxi = new Taxi(pedirString("Ingrese patente:"),
						pedirString("Ingrese marca:"),
						pedirString("Ingrese modelo:"), pedirInt(
								"Ingrese capacidad > 0:", 1));
				Flota flotaNuevoTaxi = central
						.buscarFlota(pedirString("Ingrese nombre flota"));
				if (flotaNuevoTaxi == null)
					mostrarDialogoError("No se encuentra la flota.");
				else if (!central.agregarTaxiFlota(nuevoTaxi, flotaNuevoTaxi)) {
					mostrarDialogoError("Ya existe otro taxi con esa patente.");
				}
				break;
			case "Pasajero":
				Pasajero nuevoPasajero = new Pasajero(
						pedirString("Ingrese nombre del pasajero"),
						pedirRut("Ingrese rut del pasajero"),
						pedirInt("Ingrese teléfono del pasajero", 0),
						pedirString("Ingrese la dirección de destino del pasajero"));
				auxAgregarS = pedirString("Ingrese patente del taxi.");
				Flota faux = central.buscarFlotaTaxi(auxAgregarS);
				if (faux == null) {
					mostrarDialogoError("El taxi no existe");
				} else {
					if (!central
							.agregarPasajeroTaxi(nuevoPasajero, auxAgregarS))
						mostrarDialogoError("Ya existe el pasajero");
				}

				break;
			case "Paradero":
				Paradero nuevoParadero = new Paradero(
						pedirString("Ingrese nombre de paradero:"),
						pedirString("Ingrese dirección del paradero"));
				Sector sectorNuevoParadero = central
						.buscarSector(pedirString("Ingrese nombre del sector:"));

				if (sectorNuevoParadero == null)
					mostrarDialogoError("No se encuentra el sector.");
				else if (!central.agregarParaderoSector(nuevoParadero,
						sectorNuevoParadero.getNombre()))
					mostrarDialogoError("Ya existe un paradero con ese nombre.");

				break;
			case "Rut":
				auxAgregarR = pedirRut("Ingrese un rut valido y en formato XX.XXX.XXX-X/X.XXX.XXX-X");
				auxAgregarS = Long.toString(auxAgregarR.getNumero());
				if (!locate(0, auxAgregarS))
					rutsTemporales.agregar(auxAgregarR);
				else
					mostrarDialogoError("Ya existe el rut en el sistema.");
				break;
			}
			tabla.updateUI();
			break;

		}

	}

	// Jeje
	private boolean locate(int col, String value) {
		for (int i = 0; i < tabla.getRowCount(); i++) {
			if (tabla.getValueAt(i, col).toString().equals(value)) {
				tabla.setRowSelectionInterval(i, i);
				return true;
			}
		}
		return false;
	}

	private int pedirInt(String mensaje, int min) {
		String input;
		int n = -1;
		boolean valido = false;
		while (!valido) {
			input = pedirString(mensaje);
			try {
				n = Integer.parseInt(input);
				if (n > min) {
					valido = true;
				} else {
					mostrarDialogoError("Debe ser mayor que " + min);
				}
			} catch (NumberFormatException e) {
				mostrarDialogoError("Debe ingresar un número");
			}

		}

		return n;
	}

	private void mostrarDialogoError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private String pedirString(String mensaje) {
		String input = null;
		boolean valido = false;
		while (!valido) {
			input = JOptionPane.showInputDialog(this, mensaje,
					"Ingresar datos", JOptionPane.QUESTION_MESSAGE);
			if (input != null) {
				if (input.trim().length() > 0 && !input.matches("[\u00A0]+")) {
					valido = true;
				} else {
					JOptionPane.showMessageDialog(this,
							"No puede ingresar datos vacios.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		return input;
	}

	private Rut pedirRut(String mensaje) {
		boolean valido = false;
		String input = null;
		Rut r = null;
		while (!valido) {
			input = pedirString(mensaje);
			r = Rut.fromString(input);
			if (r != null)
				return r;
			else
				mostrarDialogoError("Ha ingresado un rut no válido o con un formato distinto a: X.XXX.XXX-X o: XX.XXX.XXX-X.");
		}

		return null;
	}

}
