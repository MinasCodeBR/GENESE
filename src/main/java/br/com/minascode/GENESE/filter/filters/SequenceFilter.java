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

import br.com.minascode.GENESE.analysis.analyses.Sequence;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.filter.Filter;

public class SequenceFilter<T extends ConfigurableCombination> implements Filter<T> {
    private final LotteryGameType gameType;

    public SequenceFilter(LotteryGameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public List<T> customFilter(List<T> combinations) {
        if (combinations.isEmpty()) return combinations;

        Sequence<T> sequence = new Sequence<>(combinations, gameType);

        // Remover todas as sequências indesejadas
        combinations.removeAll(sequence.getFullSequence());

        return combinations;
    }

	@Override
	public boolean shouldApply(LotteryGameType gameType) {
		return true;
	}
}
