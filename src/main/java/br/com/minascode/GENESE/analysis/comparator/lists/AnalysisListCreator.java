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

package br.com.minascode.GENESE.analysis.comparator.lists;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.comparator.util.NumberChecker;
import br.com.minascode.GENESE.combination.ConfigurableCombination;

public class AnalysisListCreator {

	// Método genérico
	public static <T extends ConfigurableCombination> AnalysisLists<T> create(List<T> drawList,
			Integer[] genericArray) {
		List<T> generalList = new ArrayList<>();
		List<T> zeroList = new ArrayList<>();
		int drawId = 1;

		for (T draw : drawList) {
			for (int number : genericArray) {
				// Verifica se o número está presente na combinação
				if (NumberChecker.containsNumber(draw, number)) {
					generalList.add(draw);
				} else {
					zeroList.add(draw);
				}
			}
			draw.setId(drawId); // Configura o ID usando o método da interface ConfigurableCombination
			drawId++;
		}

		return new AnalysisLists<>(generalList, zeroList);
	}
}
