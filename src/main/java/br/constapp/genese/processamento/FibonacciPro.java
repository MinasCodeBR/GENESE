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

import br.constapp.genese.analise.Fibonacci;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class FibonacciPro {

	public static void processaFibonacci() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		int numCombinacoes = 0;
		int size0Fibonacci = 0;
		int size1Fibonacci = 0;
		int size2Fibonacci = 0;
		int size3Fibonacci = 0;
		int size4Fibonacci = 0;
		int size5Fibonacci = 0;
		int size6Fibonacci = 0;

		Fibonacci f = new Fibonacci(listaJogos);

		numCombinacoes += listaJogos.size();
		size0Fibonacci += f.getLista0Fibonacci().size();
		size1Fibonacci += f.getLista1Fibonacci().size();
		size2Fibonacci += f.getLista2Fibonacci().size();
		size3Fibonacci += f.getLista3Fibonacci().size();
		size4Fibonacci += f.getLista4Fibonacci().size();
		size5Fibonacci += f.getLista5Fibonacci().size();
		size6Fibonacci += f.getLista6Fibonacci().size();

		PainelResultadoAnalises.setTextArea("Apostando em              Chance\n\n");

		PainelResultadoAnalises.setTextArea(
				"1 número fibonacci:" + "       " + Calc.porcentagem(size1Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"2 números fibonacci:" + "      " + Calc.porcentagem(size2Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"3 números fibonacci:" + "      " + Calc.porcentagem(size3Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"4 números fibonacci:" + "      " + Calc.porcentagem(size4Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"5 números fibonacci:" + "      " + Calc.porcentagem(size5Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"6 números fibonacci:" + "      " + Calc.porcentagem(size6Fibonacci, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"nenhum fibonacci:" + "         " + Calc.porcentagem(size0Fibonacci, numCombinacoes) + "\n\n");

		PainelResultadoAnalises.setTextArea("Nºs Fibonacci: " + Arrays.toString(f.getVetFibonacci()));

	}

}
