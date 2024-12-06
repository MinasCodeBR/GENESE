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

package br.com.minascode.GENESE.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.minascode.GENESE.analysis.comparator.counter.OccurrenceCounter;
import br.com.minascode.GENESE.analysis.comparator.lists.AnalysisListCreator;
import br.com.minascode.GENESE.analysis.comparator.lists.AnalysisLists;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

class AnalysisEngine<T extends ConfigurableCombination> {

    private List<List<T>> incidenceLists;
    private List<T> generalList;
    private final LotteryGameType gameType;

    protected AnalysisEngine(List<T> drawList, Integer[] genericArray, LotteryGameType gameType) {
        this.gameType = gameType; // Configuração do tipo de jogo
        process(drawList, genericArray);
    }

    private void process(List<T> drawList, Integer[] genericArray) {
        // Inicializar as listas com base no range do tipo de jogo
        incidenceLists = new ArrayList<>();
        for (int i = 0; i <= gameType.getSelectionSize(); i++) {
            incidenceLists.add(new ArrayList<>());
        }

        AnalysisLists<T> drawLists = AnalysisListCreator.create(drawList, genericArray);
        this.generalList = drawLists.getGeneralList();

        for (int i = gameType.getMinRange(); i <= gameType.getSelectionSize(); i++) {
            countList(drawLists.getGeneralList(), drawList, i);
        }

        countZeroList(drawLists.getZeroList(), drawList, genericArray.length);
    }

    private void countList(List<T> sourceList, List<T> drawList, int incidence) {
        Map<Integer, Integer> counter = OccurrenceCounter.countOccurrences(sourceList);

        for (Map.Entry<Integer, Integer> item : counter.entrySet()) {
            if (item.getValue() == incidence) {
                incidenceLists.get(incidence).add(drawList.get(item.getKey() - 1));
            }
        }
    }

    private void countZeroList(List<T> zeroList, List<T> drawList, int requiredOccurrences) {
        Map<Integer, Integer> counter = OccurrenceCounter.countOccurrences(zeroList);

        for (Map.Entry<Integer, Integer> item : counter.entrySet()) {
            if (item.getValue() == requiredOccurrences) {
                incidenceLists.get(0).add(drawList.get(item.getKey() - 1));
            }
        }
    }

    protected List<T> getIncidenceList(int incidence) {
        if (incidence < 0 || incidence > gameType.getSelectionSize()) {
            throw new IllegalArgumentException("Incidência deve estar dentro do range do tipo de jogo.");
        }
        return incidenceLists.get(incidence);
    }

    protected int getTotalGeneral() {
        return generalList != null ? generalList.size() : 0;
    }
}


