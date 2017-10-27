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

import java.util.List;

import br.constapp.genese.analise.ParOuImpar;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class ParOuImparText {
	
	public static void processaParOuImpar() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		ParOuImpar parOuImpar = new ParOuImpar(listaJogos);

		setTextArea(parOuImpar, listaJogos.size());

	}

	private static void setTextArea(ParOuImpar parOuImpar, int numCombinacoes) {

		PainelResultadoAnalises.setTextArea("PARES\n\n");

		PainelResultadoAnalises.setTextArea("Apostando em           Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número  par:" + "           "
				+ Calc.porcentagem(parOuImpar.getLista1Par().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números pares:" + "        "
				+ Calc.porcentagem(parOuImpar.getLista2Pares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números pares:" + "        "
				+ Calc.porcentagem(parOuImpar.getLista3Pares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("4 números pares:" + "        "
				+ Calc.porcentagem(parOuImpar.getLista4Pares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("5 números pares:" + "        "
				+ Calc.porcentagem(parOuImpar.getLista5Pares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("6 números pares:" + "        "
				+ Calc.porcentagem(parOuImpar.getLista6Pares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum par:" + "              "
				+ Calc.porcentagem(parOuImpar.getLista0Par().size(), numCombinacoes) + "%\n\n");

		PainelResultadoAnalises.setTextArea("ÍMPARES\n\n");

		PainelResultadoAnalises.setTextArea("Apostando em             Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número  ímpar:" + "          "
				+ Calc.porcentagem(parOuImpar.getLista1Impar().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números ímpares:" + "       "
				+ Calc.porcentagem(parOuImpar.getLista2Impares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números ímpares:" + "       "
				+ Calc.porcentagem(parOuImpar.getLista3Impares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("4 números ímpares:" + "       "
				+ Calc.porcentagem(parOuImpar.getLista4Impares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("5 números ímpares:" + "       "
				+ Calc.porcentagem(parOuImpar.getLista5Impares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("6 números ímpares:" + "       "
				+ Calc.porcentagem(parOuImpar.getLista6Impares().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum ímpar:" + "             "
				+ Calc.porcentagem(parOuImpar.getLista0Impar().size(), numCombinacoes) + "%\n");
	}

}
