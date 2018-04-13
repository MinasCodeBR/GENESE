/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2018  Rafael Teixeira
 *     rafaelfst@live.com
 *
 *     GENESE é um software livre: você pode redistribuí-lo e/ou modificá-lo
 *     dentro dos termos da Licença Pública Geral GNU como publicada pela
 *     Fundação do Software Livre (FSF), na versão 3 da Licença, ou
 *     (na sua opinião) qualquer versão posterior.
 *
 *     Este programa é distribuído na esperança de que possa ser útil,
 *     mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
 *     a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *     Licença Pública Geral GNU para maiores detalhes.
 *
 *     Você deve ter recebido uma cópia da Licença Pública Geral GNU junto
 *     com este programa. Se não, veja <http://www.gnu.org/licenses/>.
 */

package br.constapp.genese.analise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.constapp.genese.analise.modelo.ModeloAnalise;
import br.constapp.genese.jogo.modelo.Jogo;

public class MaisMenos {

	private static Integer[] dezenas;
	private static Integer[] contagem;
	private static ModeloAnalise[] arrayModelos;
	private static ModeloAnalise[] arrayModelosOrganizado;

	private static ModeloAnalise modelo;

	public MaisMenos(int numDezenas, List<Jogo> listaJogos) {
		contaRepeticoes(numDezenas, listaJogos);
		criaArrayModelos(numDezenas);
		ordenaArrayModelos(numDezenas);
	}

	private static ModeloAnalise[] ordenaArrayModelos(int numDezenas) {

		arrayModelosOrganizado = new ModeloAnalise[numDezenas];

		Arrays.sort(arrayModelos, new Comparator<ModeloAnalise>() {
			@Override
			public int compare(ModeloAnalise b1, ModeloAnalise b2) {

				if (b1.getRepeticoes() < b2.getRepeticoes()) {
					return 1;
				}
				if (b1.getRepeticoes() > b2.getRepeticoes()) {
					return -1;
				}
				return 0;
			}
		});
		arrayModelosOrganizado = arrayModelos;
		return arrayModelosOrganizado;
	}

	private static ModeloAnalise[] criaArrayModelos(int numDezenas) {

		arrayModelos = new ModeloAnalise[numDezenas];

		for (int i = 0; i < contagem.length; i++) {

			modelo = new ModeloAnalise(i + 1, contagem[i]);
			arrayModelos[i] = modelo;
		}
		return arrayModelos;
	}

	private static void contaRepeticoes(int numDezenas, List<Jogo> listaJogos) {

		defineDezenas(numDezenas);

		contagem = new Integer[numDezenas];

		for (int i = 0; i < contagem.length; i++) {
			contagem[i] = 0;
		}

		for (Jogo jogo : listaJogos) {
			for (int i = 0; i < dezenas.length; i++) {

				if (jogo.getPrimeiraDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getSegundaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getTerceiraDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getQuartaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getQuintaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getSextaDezena() == dezenas[i]) {
					contagem[i]++;
				}
			}
		}
	}

	private static Integer[] defineDezenas(int i) {
		dezenas = new Integer[i];
		for (int n = 1; n <= i; n++) {
			dezenas[n - 1] = n;
		}
		return dezenas;
	}

	public ModeloAnalise[] getArrayModelos() {
		return arrayModelos;
	}

	public ModeloAnalise[] getArrayModelosOrganizado() {
		return arrayModelosOrganizado;
	}

}