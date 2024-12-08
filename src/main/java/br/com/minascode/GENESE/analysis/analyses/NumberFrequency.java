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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class NumberFrequency<T extends ConfigurableCombination> extends AnalysisHandler<T> {

	private Integer[] numbers;
	private Integer[] occurrenceCount;
	private final LotteryGameType gameType;

	public NumberFrequency(List<T> drawList, LotteryGameType gameType) {
		this.gameType = gameType;
		numbers = defineNumbers();
		occurrenceCount = new Integer[numbers.length];
		Arrays.fill(occurrenceCount, 0);
		process(drawList, numbers, gameType);
	}

	@Override
	public Integer[] defineNumbers() {
		int totalNumbers = gameType.getMaxRange();
		Integer[] universe = new Integer[totalNumbers];
		for (int i = 0; i < totalNumbers; i++) {
			universe[i] = i + 1;
		}
		return universe;
	}

	@Override
	public void process(List<T> drawList, Integer[] genericArray, LotteryGameType gameType) {
		super.process(drawList, genericArray, gameType);
		
		occurrenceCount = new Integer[genericArray.length];
		incidenceLists = new ArrayList<>(genericArray.length);
		
		for (int i = 0; i < genericArray.length; i++) {
			occurrenceCount[i] = 0;
			incidenceLists.add(new ArrayList<>());
		}
		
		for (T draw : drawList) {
			countOccurrence(draw, genericArray);
		}
	}

	private void countOccurrence(T draw, Integer[] numbers) {		
		int[] drawNumbers = draw.getNumbers();

		for (int number : drawNumbers) {
			for (int i = 0; i < numbers.length; i++) {
				if (number == numbers[i]) {
					occurrenceCount[i]++;
					incidenceLists.get(i).add(draw);
				}
			}
		}
	}

	public Integer[] getOccurrenceCount() {
		return occurrenceCount;
	}

	public List<Integer> getNumbersSortedByOccurrence() {
		// Retorna os números ordenados por frequência (descendente)
		return Arrays.stream(numbers).sorted((n1, n2) -> occurrenceCount[n2 - 1] - occurrenceCount[n1 - 1])
				.collect(Collectors.toList());
	}

	public List<Integer> getOccurrenceCountSorted() {
		// Retorna as contagens de ocorrência ordenadas
		return Arrays.stream(numbers).sorted((n1, n2) -> occurrenceCount[n2 - 1] - occurrenceCount[n1 - 1])
				.map(n -> occurrenceCount[n - 1]).collect(Collectors.toList());
	}
}
