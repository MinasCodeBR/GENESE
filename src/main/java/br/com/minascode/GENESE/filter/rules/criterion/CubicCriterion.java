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

package br.com.minascode.GENESE.filter.rules.criterion;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.analyses.Cubic;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class CubicCriterion<T extends ConfigurableCombination> implements Criterion<T> {
    private final Cubic<T> cubic;
    private final int drawListSize;
    private final LotteryGameType gameType;

    public CubicCriterion(List<T> drawList, LotteryGameType gameType) {
        this.cubic = new Cubic<>(drawList, gameType);
        this.drawListSize = drawList.size();
        this.gameType = gameType;
    }

    @Override
    public List<Integer> evaluate(double cutOff) {
        List<Integer> cubicsToRemove = new ArrayList<>();
        for (int i = 0; i <= gameType.getSelectionSize(); i++) {
            List<T> incidenceList = cubic.getIncidenceList(i);
            double percentage = Calc.percentage(incidenceList.size(), drawListSize);
            if (percentage < cutOff) {
                cubicsToRemove.add(i);
            }
        }
        return cubicsToRemove;
    }
}