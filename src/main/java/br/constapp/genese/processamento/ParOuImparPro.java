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

import br.constapp.genese.analise.ParOuImpar;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

public class ParOuImparPro {

	public static void processaParOuImpar() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();
		
		int numCombinacoes = 0;
		int size0Par = 0;
		int size1Par = 0;
		int size2Pares = 0;
		int size3Pares = 0;
		int size4Pares = 0;
		int size5Pares = 0;
		int size6Pares = 0;
		int size0Impar = 0;
		int size1Impar = 0;
		int size2Impares = 0;
		int size3Impares = 0;
		int size4Impares = 0;
		int size5Impares = 0;
		int size6Impares = 0;

		ParOuImpar pi = new ParOuImpar(listaJogos);

		numCombinacoes += listaJogos.size();
		size0Par += pi.getLista0Par().size();
		size1Par += pi.getLista1Par().size();
		size2Pares += pi.getLista2Pares().size();
		size3Pares += pi.getLista3Pares().size();
		size4Pares += pi.getLista4Pares().size();
		size5Pares += pi.getLista5Pares().size();
		size6Pares += pi.getLista6Pares().size();
		size0Impar += pi.getLista0Impar().size();
		size1Impar += pi.getLista1Impar().size();
		size2Impares += pi.getLista2Impares().size();
		size3Impares += pi.getLista3Impares().size();
		size4Impares += pi.getLista4Impares().size();
		size5Impares += pi.getLista5Impares().size();
		size6Impares += pi.getLista6Impares().size();

		PainelResultadoAnalises.setTextArea("PARES\n\n");

		PainelResultadoAnalises.setTextArea("Apostando em           Chance\n\n");

		PainelResultadoAnalises
				.setTextArea("1 número  par:" + "           " + Calc.porcentagem(size1Par, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("2 números pares:" + "        " + Calc.porcentagem(size2Pares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("3 números pares:" + "        " + Calc.porcentagem(size3Pares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("4 números pares:" + "        " + Calc.porcentagem(size4Pares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("5 números pares:" + "        " + Calc.porcentagem(size5Pares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("6 números pares:" + "        " + Calc.porcentagem(size6Pares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("nenhum par:" + "              " + Calc.porcentagem(size0Par, numCombinacoes) + "\n\n");

		PainelResultadoAnalises.setTextArea("ÍMPARES\n\n");

		PainelResultadoAnalises.setTextArea("Apostando em             Chance\n\n");

		PainelResultadoAnalises
				.setTextArea("1 número  ímpar:" + "          " + Calc.porcentagem(size1Impar, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("2 números ímpares:" + "       " + Calc.porcentagem(size2Impares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("3 números ímpares:" + "       " + Calc.porcentagem(size3Impares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("4 números ímpares:" + "       " + Calc.porcentagem(size4Impares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("5 números ímpares:" + "       " + Calc.porcentagem(size5Impares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("6 números ímpares:" + "       " + Calc.porcentagem(size6Impares, numCombinacoes) + "\n");

		PainelResultadoAnalises
				.setTextArea("nenhum ímpar:" + "             " + Calc.porcentagem(size0Impar, numCombinacoes) + "\n");

	}

}
