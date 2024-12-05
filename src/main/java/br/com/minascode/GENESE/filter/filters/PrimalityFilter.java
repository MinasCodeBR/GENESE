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

package br.com.minascode.GENESE.filter.filters;

import java.util.List;

import br.com.minascode.GENESE.analysis.analyses.Primality;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.filter.Filter;
import br.com.minascode.GENESE.filter.rules.RemovalCriteria;

public class PrimalityFilter<T extends ConfigurableCombination> implements Filter<T> {

    private final RemovalCriteria<T> removalCriteria;
    private final LotteryGameType gameType;

    public PrimalityFilter(RemovalCriteria<T> removalCriteria, LotteryGameType gameType) {
        this.removalCriteria = removalCriteria;
        this.gameType = gameType;
    }

    @Override
    public List<T> customFilter(List<T> combinations) {
        if (combinations.isEmpty()) return combinations;

        // Cria o analisador de primalidade com base nas combinações e no tipo de jogo
        Primality<T> primality = new Primality<>(combinations, gameType);

        // Remove todas as combinações que contêm os números primos especificados
        @SuppressWarnings("unchecked")
        List<Integer> primesToRemove = (List<Integer>) removalCriteria.getRemovalResult("primesToRemove");
        if (primesToRemove != null) {
            for (Integer primeToRemove : primesToRemove) {
                combinations.removeAll(primality.getIncidenceList(primeToRemove));
            }
        }

        return combinations;
    }

	@Override
	public boolean shouldApply(LotteryGameType gameType) {
		return true;
	}
}