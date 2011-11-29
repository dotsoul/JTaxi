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

import cl.pucv.eii.jtaxi.gui.ListaContadora;
import cl.pucv.eii.jtaxi.modelo.Central;
import cl.pucv.eii.jtaxi.modelo.Flota;
import cl.pucv.eii.jtaxi.modelo.Paradero;
import cl.pucv.eii.jtaxi.modelo.Taxi;
import cl.pucv.eii.jtaxi.modelo.Taxista;
import cl.pucv.eii.jtaxi.utilidades.listas.Lista;

public class FlotaTableModel extends AbstractTableModel {

	private Lista<Flota> flotas;
	
	public FlotaTableModel (Central central){
		this.flotas = central.getFlotas();
	}
	
	@Override
	public String getColumnName(int columnIndex){
		String[] columnName = {"Nombre", "Nº Taxistas", "Nº Paraderos", "Nº Taxis"};
		return columnName[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return flotas.tamaño();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Flota item = flotas.getObject(rowIndex);
		ListaContadora<Taxista> nTaxistas = new ListaContadora<>();
		ListaContadora<Paradero> nParaderos = new ListaContadora<>();
		ListaContadora<Taxi> nTaxis = new ListaContadora<>();
		item.listarTaxista(nTaxistas);
		item.listarTaxis(nTaxis);
		item.listarParaderos(nParaderos);
		Object[] aux = {item.getNombre(),new Integer(nTaxistas.tamaño()),new Integer(nParaderos.tamaño()),new Integer(nTaxis.tamaño())};
		return aux[columnIndex];
	}

}