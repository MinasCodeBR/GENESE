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

package br.com.minascode.GENESE.combination;

import java.util.concurrent.TimeUnit;

import br.com.minascode.GENESE.combination.CombinationFactory;
import br.com.minascode.GENESE.combination.lottery.LotoFacilCombination;
import br.com.minascode.GENESE.entities.LotoFacil;

public class LotoFacilCombinationTest {

	public static void main(String[] args) {

		long inicio = System.currentTimeMillis();
		
        CombinationFactory<LotoFacil> factory = new CombinationFactory<>(
        		new LotoFacilCombination(),
                LotoFacil::new // Passa o construtor de MegaSena como Supplier
        );

		factory.generateCombinations();

		long tempoTotal = System.currentTimeMillis() - inicio;
		if (tempoTotal < 1000) {
			System.out.println("Tempo total: " + tempoTotal + " milissegundos");
		} else if (TimeUnit.MILLISECONDS.toSeconds(tempoTotal) < 60) {
			System.out.println("Tempo total: " + TimeUnit.MILLISECONDS.toSeconds(tempoTotal) + " segundos");
		} else if (TimeUnit.MILLISECONDS.toMinutes(tempoTotal) < 60) {
			System.out.println("Tempo total: " + TimeUnit.MILLISECONDS.toMinutes(tempoTotal) + " minutos");
		} else if (TimeUnit.MILLISECONDS.toHours(tempoTotal) < 24) {
			System.out.println("Tempo total: " + TimeUnit.MILLISECONDS.toHours(tempoTotal) + " horas");
		}
	}
}
