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

package br.com.minascode.GENESE.analysis.analyses;

import java.util.Arrays;
import java.util.List;

import br.com.minascode.GENESE.analysis.LotteryAnalysis;
import br.com.minascode.GENESE.analysis.analyses.Multiplicity;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class MultiplicityTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {
	
	private List<T> draws;
	private LotteryGameType gameType;
	private Multiplicity<T> multiplicity;
	private int maxIncidence;

	public MultiplicityTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {	
	}

	@Override
	public void printAnalysis() {
		for (int i = 2; i <= gameType.getMaxRange(); i++) {
			multiplicity = new Multiplicity<>(draws, i, gameType);
			
			maxIncidence = gameType.getSelectionSize();
			if (multiplicity.getMultiplesList().size() < maxIncidence) {
				maxIncidence = multiplicity.getMultiplesList().size();
			}
			
			System.out.print("Múltiplos de " + multiplicity.getMultipleOf() + ": ");
			System.out.println(Arrays.toString(multiplicity.defineNumbers(i)) + "\n");
			for (int j = 0; j <= maxIncidence; j++) {
				System.out.println(j + " incidencias para múltiplos de " + i + ": "
						+ Calc.percentage(multiplicity.getIncidenceList(j).size(), draws.size()) + "%");
			}
			System.out.println();
		}
		
	}

}
