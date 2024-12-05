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
import br.com.minascode.GENESE.analysis.analyses.Fibonacci;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class FibonacciTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {
	
	private List<T> draws;
	private LotteryGameType gameType;
	private Fibonacci<T> fibonacci;
	private int maxIncidence;
	

	public FibonacciTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		fibonacci = new Fibonacci<>(draws, gameType);
		
		maxIncidence = gameType.getSelectionSize();
		if (fibonacci.getFibonacciNumbers().length < maxIncidence) {
			maxIncidence = fibonacci.getFibonacciNumbers().length;
		}
		
	}

	@Override
	public void printAnalysis() {
		System.out.println("Fibonacci numbers: " + Arrays.toString(fibonacci.getFibonacciNumbers()) + "\n" );
		for (int i = 0; i <= maxIncidence; i++) {
			System.out.println("Fibonacci " + i + " incidences: "
					+ Calc.percentage(fibonacci.getIncidenceList(i).size(), draws.size()) + "%");
		}		
	}
}
