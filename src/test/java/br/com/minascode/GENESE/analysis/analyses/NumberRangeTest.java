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

import java.util.Arrays;
import java.util.List;

import br.com.minascode.GENESE.analysis.LotteryAnalysis;
import br.com.minascode.GENESE.analysis.analyses.NumberRange;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class NumberRangeTest<T extends ConfigurableCombination> implements LotteryAnalysis<T> {

	private List<T> draws;
	private LotteryGameType gameType;
	private NumberRange<T> range;
	private int lineCount;
	List<T> result;
	int maxIncidence;

	public NumberRangeTest(List<T> draws, LotteryGameType gameType) {
		this.draws = draws;
		this.gameType = gameType;
		analyze();
	}

	@Override
	public void analyze() {
		range = new NumberRange<>(draws, gameType);
		lineCount = gameType.getLineCount();		
        maxIncidence = gameType.getSelectionSize();        
        if (gameType == LotteryGameType.LOTO_FACIL) {
        	maxIncidence = gameType.getMaxRange() / lineCount;
        } 
	}

	@Override
	public void printAnalysis() {
		for (int line = 0; line < lineCount; line++) {
			System.out.println("Números da linha " + (line + 1) + ": " + Arrays.toString(range.getLineNumbers(line)));
			for (int incidence = 0; incidence <= maxIncidence; incidence++) {
				result = range.getIncidencesByLine(line, incidence);
				System.out.println("Linha " + (line + 1) + " com " + incidence + " incidencias: "
						+ Calc.percentage(result.size(), draws.size()) + "%");
			}
			System.out.println();
		}
	}
}
