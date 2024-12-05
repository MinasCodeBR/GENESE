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

package br.com.minascode.GENESE.analysis.comparator.counter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.minascode.GENESE.combination.ConfigurableCombination;

public class OccurrenceCounter {

    // Método genérico para contar ocorrências de combinações
    public static <T extends ConfigurableCombination> Map<Integer, Integer> countOccurrences(List<T> combinations) {
        Map<Integer, Integer> counter = new HashMap<>();

        for (T combination : combinations) {
            // Supondo que a combinação tenha um método `getId` único para identificar cada combinação
            int id = combination.getId();
            counter.put(id, counter.getOrDefault(id, 0) + 1);
        }

        return counter;
    }
}


