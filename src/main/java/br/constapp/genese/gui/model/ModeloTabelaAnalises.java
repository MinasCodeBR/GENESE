/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2017  Rafael Teixeira
 *     rafaelfst@live.com
 *
 *     GENESE é um software livre: você pode redistribuí-lo e/ou modificá-lo
 *     dentro dos termos da Licença Pública Geral GNU como publicada pela
 *     Fundação do Software Livre (FSF), na versão 3 da Licença, ou
 *     (na sua opinião) qualquer versão posterior.
 *
 *     Este programa é distribuído na esperança de que possa ser útil,
 *     mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
 *     a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *     Licença Pública Geral GNU para maiores detalhes.
 *
 *     Você deve ter recebido uma cópia da Licença Pública Geral GNU junto
 *     com este programa. Se não, veja <http://www.gnu.org/licenses/>.
 */

package br.constapp.genese.gui.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModeloTabelaAnalises extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private List<String> listaAnalises;
	private String[] colunas = { "Incidência", "Faixa" };

	public ModeloTabelaAnalises(List<String> listaAnalises) {

		this.listaAnalises = listaAnalises;

	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getRowCount() {
		if (listaAnalises != null) {
			return listaAnalises.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaAnalises.get(rowIndex);
		case 1:
			return listaAnalises.get(rowIndex);

		}

		return null;
	}

	public void addListaAnalises(List<String> lista) {
		// Pega o tamanho antigo da tabela, que servirá
		// como índice para o primeiro dos novos registros
		int indice = getRowCount();
//		lista.removeAll(listaJogos);
		// Adiciona os registros.
		listaAnalises.addAll(lista);

		// Notifica a mudança.
		fireTableRowsInserted(indice, indice + lista.size());
	}
	
	// Remove todos os registros.
	public void limpar() {
	    // Remove todos os elementos da lista.
		listaAnalises.clear();
	 
	    // Notifica a mudança.
	    fireTableDataChanged();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

}
