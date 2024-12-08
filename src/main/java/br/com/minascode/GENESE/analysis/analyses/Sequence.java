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
import java.util.List;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Sequence<T extends ConfigurableCombination> {

    private List<List<T>> sequentialFields;
    private List<T> fullSequence;

    public Sequence(List<T> drawList, LotteryGameType gameType) {
        findSequences(drawList, gameType);
    }

    private void findSequences(List<T> drawList, LotteryGameType gameType) {
        int selectionSize = gameType.getSelectionSize();

        sequentialFields = new ArrayList<>();
        for (int i = 0; i < selectionSize - 1; i++) {
            sequentialFields.add(new ArrayList<>());
        }

        fullSequence = new ArrayList<>();

        for (T draw : drawList) {
            int[] numbers = draw.getNumbers();
            if (numbers.length < selectionSize) continue;

            // Verifica sequências parciais (pares consecutivos)
            for (int i = 0; i < selectionSize - 1; i++) {
                if (numbers[i] + 1 == numbers[i + 1]) {
                	sequentialFields.get(Math.min(i, sequentialFields.size() - 1)).add(draw);
                }
            }

            // Verifica sequência completa
            if (isFullSequence(numbers, selectionSize)) {
                fullSequence.add(draw);                
            }
        }
    }
    
    private boolean isFullSequence(int[] numbers, int sequenceLength) {
        int count = 1;

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] + 1 == numbers[i + 1]) {
                count++;
                if (count >= sequenceLength) {
                    return true;
                }
            } else {
                count = 1; // Reinicia o contador se a sequência for interrompida
            }
        }

        return false;
    }

    public List<T> getFullSequence() {
        return fullSequence;
    }

    public List<T> getSequentialField(int index) {
        if (index < 0 || index >= sequentialFields.size()) {
            throw new IllegalArgumentException("Índice inválido para sequências parciais.");
        }
        return sequentialFields.get(index);
    }

    public int getSequentialFieldCount() {
        return sequentialFields.size();
    }
}
