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
import cl.pucv.eii.jtaxi.modelo.Paradero;
import cl.pucv.eii.jtaxi.modelo.Sector;
import cl.pucv.eii.jtaxi.utilidades.listas.Lista;

public class SectorTableModel extends AbstractTableModel {

	private Lista<Sector> sectores;
	
	public SectorTableModel (Central central){
		this.sectores = central.getSectores();
	}
	
	@Override
	public String getColumnName(int columnIndex){
		String[] columnName = {"Nombre", "Nº Paraderos"};
		return columnName[columnIndex];
	}
	
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return sectores.tamaño();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Sector item = sectores.getObject(rowIndex);
		ListaContadora<Paradero> nParaderos = new ListaContadora<>();
		item.listarParaderos(nParaderos);
		Object[] aux = {item.getNombre(),new Integer(nParaderos.tamaño())};
		return aux[columnIndex];
	}

}
