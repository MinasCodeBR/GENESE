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

package br.constapp.genese.processamento;

import br.constapp.genese.analise.Quadraticos;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class QuadraticosPro {

	public static void processaQuadráticos() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		int numCombinacoes = 0;
		int size0Quadrado = 0;
		int size1Quadrado = 0;
		int size2Quadrados = 0;
		int size3Quadrados = 0;
		int size4Quadrados = 0;
		int size5Quadrados = 0;
		int size6Quadrados = 0;

		Quadraticos q = new Quadraticos(listaJogos);

		numCombinacoes += listaJogos.size();
		size0Quadrado += q.getLista0Quadrado().size();
		size1Quadrado += q.getLista1Quadrado().size();
		size2Quadrados += q.getLista2Quadrados().size();
		size3Quadrados += q.getLista3Quadrados().size();
		size4Quadrados += q.getLista4Quadrados().size();
		size5Quadrados += q.getLista5Quadrados().size();
		size6Quadrados += q.getLista6Quadrados().size();

		PainelResultadoAnalises.setTextArea("Apostando em                 Chance\n\n");

		PainelResultadoAnalises
				.setTextArea("1 número quadrado:" + "         " + Calc.porcentagem(size1Quadrado, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"2 números quadrados:" + "       " + Calc.porcentagem(size2Quadrados, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"3 números quadrados:" + "       " + Calc.porcentagem(size3Quadrados, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"4 números quadrados:" + "       " + Calc.porcentagem(size4Quadrados, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"5 números quadrados:" + "       " + Calc.porcentagem(size5Quadrados, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"6 números quadrados:" + "       " + Calc.porcentagem(size6Quadrados, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("nenhum quadrado:" + "           " + Calc.porcentagem(size0Quadrado, numCombinacoes) + "\n\n");

		PainelResultadoAnalises.setTextArea("Nºs quadráticos: " + Arrays.toString(q.defineNumerosQuadrados()));

	}

}
