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

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Fibonacci<T extends ConfigurableCombination> extends AnalysisHandler<T> {

	private Integer[] fibonacciNumbers;
	private final LotteryGameType gameType;

	public Fibonacci(List<T> drawList, LotteryGameType gameType) {
		this.gameType = gameType;
		process(drawList, defineNumbers(), gameType);
	}

	@Override
	public Integer[] defineNumbers() {
		List<Integer> fibonacciList = new ArrayList<>();
		int a = 0, b = 1;
		while (b <= gameType.getMaxRange()) { // Gera números Fibonacci dentro do intervalo do jogo
			int next = a + b;
			a = b;
			b = next;
			if (b <= gameType.getMaxRange()) {
				fibonacciList.add(b);
			}
		}
		fibonacciNumbers = fibonacciList.toArray(new Integer[0]);
		return fibonacciNumbers;
	}

	public Integer[] getFibonacciNumbers() {
		return fibonacciNumbers;
	}
}
