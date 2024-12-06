/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 0.0.1
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
import br.com.minascode.GENESE.analysis.analyses.Quadratic;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class QuadraticTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {

	private List<T> draws;
	private LotteryGameType gameType;
	private Quadratic<T> quadratic;
	private int maxRange;

	public QuadraticTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		quadratic = new Quadratic<>(draws, gameType);
		
		maxRange = gameType.getSelectionSize();
		if (gameType == LotteryGameType.LOTO_FACIL) {
			maxRange = quadratic.defineNumbers().length;
		}
		
	}

	@Override
	public void printAnalysis() {
		Integer[] squareNumbers = quadratic.defineNumbers();
		System.out.print("Números quadráticos dentro do intervalo do jogo: ");
		System.out.println(Arrays.toString(squareNumbers));

		for (int i = 0; i <= maxRange; i++) {
			System.out.println(i + " números quadrados: "
					+ Calc.percentage(quadratic.getIncidenceList(i).size(), draws.size()) + "%");
		}

	}

}
