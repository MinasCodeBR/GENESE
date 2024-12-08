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

package br.com.minascode.GENESE.filter.filters;

import java.util.List;

import br.com.minascode.GENESE.analysis.analyses.Multiplicity;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.filter.Filter;
import br.com.minascode.GENESE.filter.rules.RemovalCriteria;

public class MultiplicityFilter<T extends ConfigurableCombination> implements Filter<T> {

	private final RemovalCriteria<T> removalCriteria;
	private final LotteryGameType gameType;

	public MultiplicityFilter(RemovalCriteria<T> removalCriteria, LotteryGameType gameType) {
		this.removalCriteria = removalCriteria;
		this.gameType = gameType;
	}

	@Override
	public List<T> customFilter(List<T> combinations) {
		if (combinations.isEmpty())
			return combinations;

		@SuppressWarnings("unchecked")
		List<List<Integer>> multiplesToRemove = (List<List<Integer>>) removalCriteria
				.getRemovalResult("multiplesToRemove");

		for (List<Integer> number : multiplesToRemove) {
			if (number.size() < 2) {
				System.err.println("Dados de remoção inválidos: " + number);
				continue;
			}
			int multipleOf = number.get(0);
			int toRemove = number.get(1);

			Multiplicity<T> multiplicity = new Multiplicity<>(combinations, multipleOf, gameType);

			combinations.removeAll(multiplicity.getIncidenceList(toRemove));
		}

		return combinations;
	}

	@Override
	public boolean shouldApply(LotteryGameType gameType) {
		return true;
	}
}