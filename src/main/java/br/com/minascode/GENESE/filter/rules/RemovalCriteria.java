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

package br.com.minascode.GENESE.filter.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.minascode.GENESE.analysis.analyses.OddOrEven;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.filter.rules.criterion.Criterion;
import br.com.minascode.GENESE.filter.rules.criterion.CubicCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.DistanceCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.FibonacciCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.MultiplicityCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.NumberRangeCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.OddOrEvenCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.PrimalityCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.QuadrantCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.QuadraticCriterion;
import br.com.minascode.GENESE.filter.rules.criterion.cut.CutStrategy;

public class RemovalCriteria<T extends ConfigurableCombination> {

    private final List<T> drawList;
    private final Map<String, List<?>> removalResults;
    private final Map<String, Criterion<T>> criteriaMap;
    private final Map<String, Double> cutValues;
    private final LotteryGameType gameType;

    public RemovalCriteria(List<T> drawList, CutStrategy cutStrategy, LotteryGameType gameType) {
        this.drawList = drawList;
        this.removalResults = new HashMap<>();
        this.criteriaMap = new HashMap<>();
        this.cutValues = cutStrategy.getCutValues();
        this.gameType = gameType;

        setCriteria();
        calculateCriteria();
    }

    private void setCriteria() {
        this.criteriaMap.put("distancesCut", new DistanceCriterion<>(drawList, gameType));
        this.criteriaMap.put("multiplesCut", new MultiplicityCriterion<>(drawList, gameType));
        this.criteriaMap.put("parityCut", new OddOrEvenCriterion<>(drawList, OddOrEven.EVEN, gameType));
        this.criteriaMap.put("primesCut", new PrimalityCriterion<>(drawList, gameType));
        this.criteriaMap.put("quadrantsCut", new QuadrantCriterion<>(drawList, gameType));
        this.criteriaMap.put("rangesCut", new NumberRangeCriterion<>(drawList, gameType));
        this.criteriaMap.put("fibonacciCut", new FibonacciCriterion<>(drawList, gameType));
        this.criteriaMap.put("quadraticsCut", new QuadraticCriterion<>(drawList, gameType));
        this.criteriaMap.put("cubicsCut", new CubicCriterion<>(drawList, gameType));
    }

    private void calculateCriteria() {
        for (Map.Entry<String, Criterion<T>> entry : criteriaMap.entrySet()) {
            String key = entry.getKey();
            Criterion<T> criterion = entry.getValue();
            double cutOff = cutValues.get(key);
            removalResults.put(key.replace("Cut", "ToRemove"), criterion.evaluate(cutOff));
        }
    }

    public List<?> getRemovalResult(String criteriaKey) {
        return removalResults.get(criteriaKey);
    }

    public Map<String, List<?>> getAllRemovalResults() {
        return removalResults;
    }
}