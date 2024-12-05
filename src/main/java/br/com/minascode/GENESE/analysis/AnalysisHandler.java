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

package br.com.minascode.GENESE.analysis;

import java.util.ArrayList;
import java.util.List;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public abstract class AnalysisHandler<T extends ConfigurableCombination> {
    
    protected List<List<T>> incidenceLists;

    public void process(List<T> drawList, Integer[] genericArray, LotteryGameType gameType) {
        AnalysisEngine<T> engine = new AnalysisEngine<>(drawList, genericArray, gameType);
        incidenceLists = new ArrayList<>();
        for (int i = 0; i <= gameType.getSelectionSize(); i++) {
            incidenceLists.add(engine.getIncidenceList(i));
        }		
    }

    public abstract Integer[] defineNumbers();
    
    public Integer[] defineNumbers(int n) {
        return null;
    }

    public List<T> getIncidenceList(int incidence) {
        if (incidence < 0 || incidence > incidenceLists.size()) {
            throw new IllegalArgumentException("Incidência inválida!");
        }
        return incidenceLists.get(incidence);
    }
}
