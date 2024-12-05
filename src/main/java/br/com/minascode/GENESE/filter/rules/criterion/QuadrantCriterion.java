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

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.analyses.Quadrant;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class QuadrantCriterion<T extends ConfigurableCombination> implements Criterion<T> {
    private final Quadrant<T>[] quadrants;
    private final int drawListSize;
    private final LotteryGameType gameType;

    @SuppressWarnings("unchecked")
	public QuadrantCriterion(List<T> drawList, LotteryGameType gameType) {
        this.gameType = gameType;
        this.drawListSize = drawList.size();
        this.quadrants = new Quadrant[4];
        
        for (int i = 1; i <= 4; i++) {
            this.quadrants[i - 1] = new Quadrant<>(drawList, i, gameType);
        }
    }

    @Override
    public List<List<Integer>> evaluate(double cutOff) {
        List<List<Integer>> quadrantsToRemove = new ArrayList<>();
        int quadrantsNumber = 4; // Mega-Sena tem 4 quadrantes

        for (int i = 1; i <= quadrantsNumber; i++) {
            for (int j = 0; j <= gameType.getSelectionSize(); j++) {
                List<T> incidenceList = quadrants[i - 1].getIncidenceList(j);
                double percentage = Calc.percentage(incidenceList.size(), drawListSize);
                if (percentage < cutOff) {
                    quadrantsToRemove.add(List.of(i, j));
                }
            }
        }
        return quadrantsToRemove;
    }
}