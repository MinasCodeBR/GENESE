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

public class Multiplicity<T extends ConfigurableCombination> extends AnalysisHandler<T> {

	private List<Integer> multiplesList;
    private int multipleOf;
    private final LotteryGameType gameType;

    public Multiplicity(List<T> drawList, int multipleOf, LotteryGameType gameType) {
        this.gameType = gameType;        
        if (multipleOf < 2 || multipleOf > gameType.getMaxRange()) {
            throw new IllegalArgumentException("Entrada inválida! Múltiplos da " + gameType +  " vão de 2 a " + gameType.getMaxRange());
        }        
        process(drawList, defineNumbers(multipleOf), gameType);
    }

    @Override
    public Integer[] defineNumbers() {
        throw new UnsupportedOperationException("Use defineNumbers(int n) for this class.");
    }

    @Override
    public Integer[] defineNumbers(int n) {
        this.multipleOf = n;

        // Calculate the multiples within the range of the game type
        multiplesList = new ArrayList<>();
        for (int i = 1; i <= gameType.getMaxRange(); i++) {
            if (i % multipleOf == 0) {
                multiplesList.add(i);
            }
        }

        return multiplesList.toArray(new Integer[0]);
    }

    public int getMultipleOf() {
        return multipleOf;
    }
    
    public List<Integer> getMultiplesList () {
    	return multiplesList;
    }    
}

