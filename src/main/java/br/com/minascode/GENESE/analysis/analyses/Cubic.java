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

package br.com.minascode.GENESE.analysis.analyses;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Cubic<T extends ConfigurableCombination> extends AnalysisHandler<T> {

    private final LotteryGameType gameType;

    public Cubic(List<T> drawList, LotteryGameType gameType) {
        this.gameType = gameType;
        process(drawList, defineNumbers(), gameType);
    }

    @Override
    public Integer[] defineNumbers() {
        List<Integer> cubicNumbers = new ArrayList<>();
        int maxRange = gameType.getMaxRange(); // Máximo do intervalo do jogo

        for (int n = 1; ; n++) {
            int cubic = (int) Math.pow(n, 3);
            if (cubic > maxRange) break; // Para quando o número cúbico ultrapassa o limite
            cubicNumbers.add(cubic);
        }

        return cubicNumbers.toArray(new Integer[0]);
    }
}

