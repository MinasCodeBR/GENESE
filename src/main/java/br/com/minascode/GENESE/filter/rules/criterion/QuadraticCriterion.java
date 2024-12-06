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

package br.com.minascode.GENESE.filter.rules.criterion;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.analyses.Quadratic;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.util.Calc;

public class QuadraticCriterion<T extends ConfigurableCombination> implements Criterion<T> {
	private final Quadratic<T> quadratic;
	private LotteryGameType gameType;
	private final int drawListSize;
	private int maxRange;

	public QuadraticCriterion(List<T> drawList, LotteryGameType gameType) {
		this.quadratic = new Quadratic<>(drawList, gameType);
		this.gameType = gameType;
		this.drawListSize = drawList.size();
	}

	@Override
	public List<Integer> evaluate(double cutOff) {
		
		maxRange = gameType.getSelectionSize();
		if (gameType == LotteryGameType.LOTO_FACIL) {
			maxRange = quadratic.defineNumbers().length;
		}
		
		List<Integer> quadraticsToRemove = new ArrayList<>();

		for (int i = 0; i <= maxRange; i++) {
			List<T> incidenceList = quadratic.getIncidenceList(i);
			double percentage = Calc.percentage(incidenceList.size(), drawListSize);
			if (percentage < cutOff) {
				quadraticsToRemove.add(i);
			}
		}
		return quadraticsToRemove;
	}
}