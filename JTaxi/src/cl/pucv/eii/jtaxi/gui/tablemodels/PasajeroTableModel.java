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
package cl.pucv.eii.jtaxi.gui.tablemodels;

import javax.swing.table.AbstractTableModel;

import cl.pucv.eii.jtaxi.interfaces.Observable;
import cl.pucv.eii.jtaxi.interfaces.Observer;
import cl.pucv.eii.jtaxi.modelo.Central;
import cl.pucv.eii.jtaxi.modelo.Flota;
import cl.pucv.eii.jtaxi.modelo.Pasajero;
import cl.pucv.eii.jtaxi.modelo.Taxi;
import cl.pucv.eii.jtaxi.utilidades.listas.ListaDoble;

@SuppressWarnings("serial")
public class PasajeroTableModel extends AbstractTableModel implements Observer {

	private ListaDoble<Pasajero> lista;
	private Central central;
	
	public PasajeroTableModel (Observable o, Central central){
		o.agregarObserver(this);
		this.central = central;
		updateLista();
	}
	
	@Override
	public String getColumnName(int columnIndex){
		String[] columnName = {"Nombre", "RUT","Teléfono","Destino"};
		return columnName[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return lista.tamaño();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Pasajero item = lista.getObject(rowIndex);
		Object[] aux = {item.getNombre(),item.getRut().toString(),item.getTelefono(),
				item.getDestino().toString()};
		return aux[columnIndex];
	}
	
	private void updateLista(){
		lista = new ListaDoble<>();
		ListaDoble<Flota> liFlotas = new ListaDoble<>();
		ListaDoble<Taxi> liTaxi = new ListaDoble<>();
		central.listarFlotas(liFlotas);
		for (Flota f : liFlotas)
			f.listarTaxis(liTaxi);
		for (Taxi t : liTaxi)
			t.listarPasajeros(lista);
	}

	@Override
	public void actualizar(String estructura) {
		if("Pasajero".equals(estructura))
			updateLista();
	}

}
