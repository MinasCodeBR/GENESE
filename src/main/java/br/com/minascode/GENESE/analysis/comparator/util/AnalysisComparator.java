/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 1.0
 * Licença: GNU General Public License v3.0
 * 
 * Este programa é um software livre: você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation, seja a versão 3 da
 * Licença, ou (a seu critério) qualquer versão posterior.
 * 
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou ADEQUAÇÃO A UM DETERMINADO FIM. Veja a
 * Licença Pública Geral GNU para mais detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa. Caso contrário, veja <https://www.gnu.org/licenses/>.
 */

package br.com.minascode.GENESE.analysis.comparator.util;

import java.util.List;

import br.com.minascode.GENESE.entities.MegaSena;

public class AnalysisComparator implements Comparable<AnalysisComparator> {

	private int number;
	private int repetitions;
	private List<MegaSena> drawList;

	public AnalysisComparator() {
	}

	public AnalysisComparator(int number, int repetitions) {
		super();
		this.number = number;
		this.repetitions = repetitions;
	}

	public AnalysisComparator(int number, int repetitions, List<MegaSena> drawList) {
		super();
		this.number = number;
		this.repetitions = repetitions;
		this.drawList = drawList;
	}
	
	@Override
	public int compareTo(AnalysisComparator otherModel) {
		 if (this.repetitions > otherModel.getRepetitions()) {
	          return -1;
	     }
	     if (this.repetitions < otherModel.getRepetitions()) {
	          return 1;
	     }
	     return 0;
	}

	@Override
	public String toString() {
		return number + " " + repetitions;
	}

	public int getNumber() {
		return number;
	}

	public int getRepetitions() {
		return repetitions;
	}

	public List<MegaSena> getDrawList() {
		return drawList;
	}
}

