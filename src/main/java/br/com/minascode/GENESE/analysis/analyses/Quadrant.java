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

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Quadrant<T extends ConfigurableCombination> extends AnalysisHandler<T> {

    private int selectedQuadrant;
    private final LotteryGameType gameType;

    public Quadrant(List<T> drawList, int quadrant, LotteryGameType gameType) {
        this.gameType = gameType;
        process(drawList, defineNumbers(quadrant), gameType);
    }

    @Override
    public Integer[] defineNumbers() {
        throw new UnsupportedOperationException("Use defineNumbers(int quadrant) for this class.");
    }

    @Override
    public Integer[] defineNumbers(int quadrant) {
        if (quadrant < 1 || quadrant > 4) {
            throw new IllegalArgumentException("Quadrant must be between 1 and 4.");
        }

        this.selectedQuadrant = quadrant;

        // Obtém informações do tipo de jogo
        int totalNumbers = gameType.getMaxRange(); 
        int lineCount = gameType.getLineCount();
        int numbersPerLine = totalNumbers / lineCount;

        List<Integer> quadrantNumbers = new ArrayList<>();

        int startRow = ((quadrant - 1) / 2) * (lineCount / 2);      // Parte superior/inferior
        int startCol = ((quadrant - 1) % 2) * (numbersPerLine / 2); // Parte esquerda/direita

        for (int row = startRow; row < startRow + lineCount / 2; row++) {
            for (int col = startCol; col < startCol + numbersPerLine / 2; col++) {
                int number = (row * numbersPerLine) + col + 1;
                if (number <= totalNumbers) {
                    quadrantNumbers.add(number);
                }
            }
        }

        return quadrantNumbers.toArray(new Integer[0]);
    }

    public int getSelectedQuadrant() {
        return selectedQuadrant;
    }
}

