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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class NumberRange<T extends ConfigurableCombination> extends AnalysisHandler<T> {

	private final LotteryGameType gameType;
	Map<T, Map<Integer, Integer>> incidenceMap;

	public NumberRange(List<T> drawList, LotteryGameType gameType) {
		this.gameType = gameType;
		Integer[] definedNumbers = defineNumbers();
		process(drawList, definedNumbers, gameType);
	}

	@Override
	public Integer[] defineNumbers() {
		// Define os inícios das faixas (linhas da cartela)
		int totalNumbers = gameType.getMaxRange(); // Total de números (e.g., 60 para Mega Sena, 25 para Loto Fácil)
		int numbersPerLine = totalNumbers / gameType.getLineCount(); // Números por linha
		Integer[] lineStarts = new Integer[gameType.getLineCount()];

		for (int i = 0; i < gameType.getLineCount(); i++) {
			lineStarts[i] = (i * numbersPerLine) + 1; // Início de cada linha (1, 11, 21, etc.)
		}
		return lineStarts;
	}

	/**
	 * Processa a lista de jogos e separa as incidências de dezenas por faixa.
	 *
	 * @param drawList       Lista de combinações do jogo.
	 * @param definedNumbers Início de cada faixa definida.
	 * @param gameType       Tipo do jogo.
	 */

	@Override
	public void process(List<T> drawList, Integer[] definedNumbers, LotteryGameType gameType) {
		int lineCount = gameType.getLineCount(); // Número de faixas
		int numbersPerLine = gameType.getMaxRange() / lineCount; // Tamanho de cada faixa

		// Inicializa as listas de incidências: 0 a lineCount
		incidenceLists = new ArrayList<>();
		for (int i = 0; i <= lineCount; i++) { // Inclui a lista para incidência 0
			incidenceLists.add(new ArrayList<>());
		}

		// Mapa para rastrear incidências por faixa
		incidenceMap = new HashMap<>();

		for (T draw : drawList) {
			int[] numbers = draw.getNumbers(); // Números da combinação
			Map<Integer, Integer> currentIncidenceMap = new HashMap<>();

			// Contar incidências em cada faixa
			for (int number : numbers) {
				int lineIndex = (number - 1) / numbersPerLine; // Determina a faixa do número
				if (lineIndex >= 0 && lineIndex < lineCount) {
					currentIncidenceMap.put(lineIndex, currentIncidenceMap.getOrDefault(lineIndex, 0) + 1);
				}
			}

			// Salvar mapa de incidências para o jogo atual
			incidenceMap.put(draw, currentIncidenceMap);

			// Determinar em qual lista adicionar a combinação
			int totalIncidences = currentIncidenceMap.size(); // Número de faixas diferentes que aparecem na combinação
			if (totalIncidences == 0) {
				incidenceLists.get(0).add(draw);
			} else if (totalIncidences <= lineCount) {
				incidenceLists.get(totalIncidences).add(draw);
			}

			// Log para depuração
//			System.out.println("Combinação: " + Arrays.toString(numbers) + ", Incidências: " + currentIncidenceMap);
		}
	}

	public List<T> getIncidencesByLine(int lineIndex, int incidenceCount) {
		if (lineIndex < 0 || lineIndex >= gameType.getLineCount() || incidenceCount < 0 || incidenceCount > gameType.getSelectionSize()
				|| incidenceCount > (gameType.getMaxRange() / gameType.getLineCount())) {
			throw new IllegalArgumentException("Índice de linha inválido!");
		}

		List<T> results = new ArrayList<>();

		for (List<T> incidenceList : incidenceLists) {
			for (T draw : incidenceList) {
				// Verifica se a combinação tem a quantidade de incidências especificada para a
				// faixa
				Map<Integer, Integer> currentIncidenceMap = incidenceMap.get(draw);
				if (currentIncidenceMap != null && currentIncidenceMap.getOrDefault(lineIndex, 0) == incidenceCount) {
					results.add(draw);
				}
			}
		}

		// Log para depuração
//		System.out.println(
//				"Resultados para linha " + (lineIndex + 1) + " com " + incidenceCount + " incidências: " + results);
		return results;
	}

	/**
	 * Retorna os números pertencentes a uma linha específica da cartela.
	 *
	 * @param lineIndex O índice da linha (começando em 0).
	 * @return Os números pertencentes à linha.
	 */
	public Integer[] getLineNumbers(int lineIndex) {
		if (lineIndex < 0 || lineIndex >= gameType.getLineCount()) {
			throw new IllegalArgumentException(
					"Índice de linha inválido! Deve estar entre 0 e " + (gameType.getLineCount() - 1) + ".");
		}

		int numbersPerLine = gameType.getMaxRange() / gameType.getLineCount();
		int start = lineIndex * numbersPerLine + 1;
		Integer[] lineNumbers = new Integer[numbersPerLine];

		for (int i = 0; i < numbersPerLine; i++) {
			lineNumbers[i] = start + i;
		}
		return lineNumbers;
	}
}
