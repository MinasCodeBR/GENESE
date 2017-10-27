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

import java.util.Arrays;
import java.util.List;

import br.constapp.genese.analise.Fibonacci;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class FibonacciText {

	public static void processaFibonacci() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Fibonacci fibonacci = new Fibonacci(listaJogos);

		setTextArea(fibonacci, listaJogos.size());

	}

	private static void setTextArea(Fibonacci fibonacci, int numCombinacoes) {

		PainelResultadoAnalises.setTextArea("Apostando em              Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número fibonacci:" + "       "
				+ Calc.porcentagem(fibonacci.getLista1Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números fibonacci:" + "      "
				+ Calc.porcentagem(fibonacci.getLista2Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números fibonacci:" + "      "
				+ Calc.porcentagem(fibonacci.getLista3Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("4 números fibonacci:" + "      "
				+ Calc.porcentagem(fibonacci.getLista4Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("5 números fibonacci:" + "      "
				+ Calc.porcentagem(fibonacci.getLista5Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("6 números fibonacci:" + "      "
				+ Calc.porcentagem(fibonacci.getLista6Fibonacci().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum fibonacci:" + "         "
				+ Calc.porcentagem(fibonacci.getLista0Fibonacci().size(), numCombinacoes) + "%\n\n");

		PainelResultadoAnalises.setTextArea("Nºs Fibonacci: " + Arrays.toString(fibonacci.getVetFibonacci()));
	}

}
