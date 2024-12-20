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
import br.com.minascode.GENESE.analysis.analyses.Primality;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class PrimalityTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {

	private List<T> draws;
	private LotteryGameType gameType;
	private Primality<T> primality;
	int maxRange;

	public PrimalityTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		primality = new Primality<>(draws, gameType);
		maxRange = gameType.getSelectionSize();
		if (gameType == LotteryGameType.LOTO_FACIL) {
			maxRange = primality.defineNumbers(gameType.getMaxRange()).length;
		}
	}

	@Override
	public void printAnalysis() {
		Integer[] numbers = primality.defineNumbers(gameType.getMaxRange());
		System.out.println("Números primos dentro do intervalo: " + Arrays.toString(numbers));
		for (int i = 0; i <= maxRange; i++) {
			System.out.println(i + " números primos: "
					+ Calc.percentage(primality.getIncidenceList(i).size(), draws.size()) + "%");
		}
	}
}
