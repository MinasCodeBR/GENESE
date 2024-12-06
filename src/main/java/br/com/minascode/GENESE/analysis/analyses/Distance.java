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

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Distance<T extends ConfigurableCombination> extends AnalysisHandler<T> {

    private Integer[] distanceIntervals; // Possíveis distâncias
    private Integer[] occurrenceCount; // Contagem de ocorrências para cada intervalo
    private List<T>[] drawsByDistance; // Jogos categorizados por distância
    private int maxDistance;
    private final LotteryGameType gameType; // Tipo de jogo para determinar o intervalo

    public Distance(List<T> drawList, LotteryGameType gameType) {
        this.gameType = gameType;
        process(drawList, defineNumbers(), gameType);
    }

    @Override
    public Integer[] defineNumbers() {
        // Define os intervalos de distâncias possíveis com base no intervalo do jogo
        maxDistance = gameType.getMaxRange() - gameType.getMinRange(); 
        distanceIntervals = new Integer[maxDistance];
        for (int i = 0; i < maxDistance; i++) {
            distanceIntervals[i] = i + 1;
        }
        return distanceIntervals;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void process(List<T> drawList, Integer[] genericArray, LotteryGameType gameType) {
        // Chama o método da classe base para inicializar incidenceLists
        super.process(drawList, genericArray, gameType);
        
        incidenceLists = new ArrayList<>();
        for (int i = 0; i < maxDistance; i++) {
        	incidenceLists.add(new ArrayList<>());
        }	

        // Inicializa as estruturas específicas da classe Distance
        occurrenceCount = new Integer[genericArray.length];
        drawsByDistance = new ArrayList[genericArray.length];
        for (int i = 0; i < genericArray.length; i++) {
            occurrenceCount[i] = 0;
            drawsByDistance[i] = new ArrayList<>();
        }

        // Processa as distâncias específicas
        for (T draw : drawList) {
            calculateDistances(draw, genericArray);
        }
    }

    private void calculateDistances(T draw, Integer[] intervals) {
        // Ordena os números do sorteio
        int[] numbers = draw.getNumbers();
        Arrays.sort(numbers);

        // Analisa as distâncias entre números consecutivos
        for (int i = 0; i < numbers.length - 1; i++) {
            int distance = numbers[i + 1] - numbers[i];

            // Localiza o índice correspondente no array de intervalos
            for (int j = 0; j < intervals.length; j++) {
                if (distance == intervals[j]) {
                    // Verifica se a combinação já foi adicionada
                    if (!drawsByDistance[j].contains(draw)) {
                        occurrenceCount[j]++;
                        drawsByDistance[j].add(draw); // Adiciona o sorteio correspondente
                        incidenceLists.get(j).add(draw); // Adiciona sorteio à lista de incidências
                    }
                }
            }
        }
    }


    public Integer[] getOccurrenceCount() {
        return occurrenceCount;
    }

    public List<T> getDrawsByDistance(int distance) {
        int maxDistance = gameType.getMaxRange() - gameType.getMinRange();
        if (distance < 1 || distance > maxDistance) {
            throw new IllegalArgumentException("Distância inválida! Deve estar entre 1 e " + maxDistance + ".");
        }
        return drawsByDistance[distance - 1];
    }
}
