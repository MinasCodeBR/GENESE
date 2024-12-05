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

package br.com.minascode.GENESE.analysis.comparator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.minascode.GENESE.entities.MegaSena;

public class RepeatedCombination {

	private RepeatedCombination() {
	}

	public static boolean hasRepeatedCombination(List<MegaSena> combinationList) {

		List<String> repeatedCombinations = new ArrayList<>();
		List<String> combinationStrings = new ArrayList<>();

		combinationList.forEach(combination -> combinationStrings.add(String.valueOf(combination)));

		Map<String, Integer> counter = new HashMap<>();
		for (String x : combinationStrings) {

			if (!counter.containsKey(x)) {
				counter.put(x, 0);
			}
			counter.put(x, counter.get(x) + 1);
		}

		for (Map.Entry<String, Integer> item : counter.entrySet()) {
			if (item.getValue() >= 2) {
				repeatedCombinations.add(item.getKey());
				System.out.println("Repeated combination found!\n" + "(" + item.getValue() + "x) " + item.getKey());
				return true;
			}
		}
		if (repeatedCombinations.size() == 0) {
			System.out.println("No repetitions found!");
			return false;
		}
		return true;

	}

}
