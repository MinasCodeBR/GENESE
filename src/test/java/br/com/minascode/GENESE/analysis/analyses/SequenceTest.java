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

import java.util.List;

import br.com.minascode.GENESE.analysis.LotteryAnalysis;
import br.com.minascode.GENESE.analysis.analyses.Sequence;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class SequenceTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {

	private List<T> draws;
	private LotteryGameType gameType;
	private Sequence<T> sequence;

	public SequenceTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		sequence = new Sequence<>(draws, gameType);
	}

	@Override
	public void printAnalysis() {
		// Verifica sequências completas
		List<T> fullSequence = sequence.getFullSequence();
		System.out.println("Jogos com sequência completa:");
		for (T draw : fullSequence) {
			System.out.println(draw);
		}
		System.out.println();

		// Verifica sequências parciais
		int sequentialFieldCount = sequence.getSequentialFieldCount();
		for (int i = 0; i < sequentialFieldCount; i++) {
			List<T> partialSequence = sequence.getSequentialField(i);
			System.out.printf("Jogos com sequência parcial no campo %d:%n", i + 1);
			for (T draw : partialSequence) {
				System.out.println(draw);
			}
		}
	}
}
