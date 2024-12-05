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

public class OddOrEven<T extends ConfigurableCombination> extends AnalysisHandler<T> {
    public static final boolean EVEN = true;
    public static final boolean ODD = false;

    private final boolean parity;
    private final LotteryGameType gameType;

    public OddOrEven(List<T> drawList, boolean parity, LotteryGameType gameType) {
        this.parity = parity;
        this.gameType = gameType;
        Integer[] definedNumbers = defineNumbers();
        process(drawList, definedNumbers, gameType); // Chama o método genérico
    }

    @Override
    public Integer[] defineNumbers() {
        List<Integer> numbers = new ArrayList<>();
        int maxNumber = gameType.getMaxRange(); // Adapta ao tipo de loteria (ex.: 60 para Mega-Sena, 25 para Loto Fácil)
        
        for (int i = 1; i <= maxNumber; i++) {
            if (parity == EVEN && i % 2 == 0) {
                numbers.add(i); // Números pares
            } else if (parity == ODD && i % 2 != 0) {
                numbers.add(i); // Números ímpares
            }
        }
        return numbers.toArray(new Integer[0]);
    }
}






