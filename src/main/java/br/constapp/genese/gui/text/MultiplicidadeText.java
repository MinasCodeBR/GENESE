/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2017  Rafael Teixeira
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

package br.constapp.genese.gui.text;

import br.constapp.genese.analise.Multiplicidade;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class MultiplicidadeText {

	public static void processaMultiplicidade() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		for (int i = 2; i <= 60; i++) {

			Multiplicidade multiplicidade = new Multiplicidade(listaJogos, i);

			setTextArea(multiplicidade, listaJogos.size(), i);

		}

	}

	private static void setTextArea(Multiplicidade multiplicidade, int numCombinacoes, int i) {

		PainelResultadoAnalises.setTextArea("MÚLTIPLOS DE " + i + "\n\n");

		PainelResultadoAnalises.setTextArea("Apostando em                    Chance\n\n");

		if (i < 11) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("2 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("3 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista3Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("4 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista4Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("5 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista5Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("6 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista6Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
		if (i >= 11 && i < 13) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("2 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("3 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista3Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("4 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista4Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("5 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista5Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
		if (i >= 13 && i < 16) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("2 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("3 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista3Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("4 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista4Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
		if (i >= 16 && i < 21) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("2 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("3 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista3Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
		if (i >= 21 && i < 31) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises
					.setTextArea("2 múltiplos de " + multiplicidade.getMultiplosDe() + ":                  "
							+ Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
		if (i >= 31 && i <= 60) {

			PainelResultadoAnalises
					.setTextArea("1 múltiplo de " + multiplicidade.getMultiplosDe() + ":                   "
							+ Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), numCombinacoes) + "%\n");

			PainelResultadoAnalises.setTextArea("nenhum múltiplo de " + multiplicidade.getMultiplosDe() + ":          "
					+ Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), numCombinacoes) + "%\n\n");

			PainelResultadoAnalises.setTextArea(
					"múltiplos de " + i + ": \n" + Arrays.toString(multiplicidade.defineMultiplosDe(i)) + "\n\n");

		}
	}

}
