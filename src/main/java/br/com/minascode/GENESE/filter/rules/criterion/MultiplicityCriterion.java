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

package br.com.minascode.GENESE.filter.rules.criterion;

import br.com.minascode.GENESE.analysis.analyses.Multiplicity;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplicityCriterion<T extends ConfigurableCombination> implements Criterion<T> {
    private final List<T> drawList;
    private final int drawListSize;
    private final LotteryGameType gameType;

    public MultiplicityCriterion(List<T> drawList, LotteryGameType gameType) {
        this.drawList = drawList;
        this.drawListSize = drawList.size();
        this.gameType = gameType;
    }

    @Override
    public List<List<Integer>> evaluate(double cutOff) {
        List<List<Integer>> multiplesToRemove = new ArrayList<>();
        for (int i = 2; i <= gameType.getMaxRange(); i++) {
            Multiplicity<T> multiplicity = new Multiplicity<>(drawList, i, gameType);
            for (int j = 0; j <= gameType.getSelectionSize(); j++) {
                List<T> incidenceList = multiplicity.getIncidenceList(j);
                double percentage = Calc.percentage(incidenceList.size(), drawListSize);
                if (percentage < cutOff) {
                    multiplesToRemove.add(Arrays.asList(i, j));
                }
            }
        }
        return multiplesToRemove;
    }
}