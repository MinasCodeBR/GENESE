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

import java.util.List;

import br.com.minascode.GENESE.analysis.LotteryAnalysis;
import br.com.minascode.GENESE.analysis.analyses.OddOrEven;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class OddOrEvenTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {

	private List<T> draws;
	private LotteryGameType gameType;
	private OddOrEven<T> evenAnalysis;
	private OddOrEven<T> oddAnalysis;

	public OddOrEvenTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		evenAnalysis = new OddOrEven<T>(draws, OddOrEven.EVEN, gameType);
		oddAnalysis = new OddOrEven<T>(draws, OddOrEven.ODD, gameType);
	}

	@Override
	public void printAnalysis() {
		for (int i = 0; i <= gameType.getSelectionSize(); i++) {
			System.out.println("Jogos com " + i + " pares: "
					+ Calc.percentage(evenAnalysis.getIncidenceList(i).size(), draws.size()) + "%");
		}
		System.out.println();

		for (int i = 0; i <= gameType.getSelectionSize(); i++) {
			System.out.println("Jogos com " + i + " ímpares: "
					+ Calc.percentage(oddAnalysis.getIncidenceList(i).size(), draws.size()) + "%");
		}
	}
}
