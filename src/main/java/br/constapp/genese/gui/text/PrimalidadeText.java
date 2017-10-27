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

import br.constapp.genese.analise.Primalidade;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class PrimalidadeText {

	public static void processaPrimalidade() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Primalidade primos = new Primalidade(listaJogos);

		setTextArea(primos, listaJogos.size());

	}

	private static void setTextArea(Primalidade primos, int numCombinacoes) {

		PainelResultadoAnalises.setTextArea("Apostando em                Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número primo:" + "              "
				+ Calc.porcentagem(primos.getLista1Primo().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números primos:" + "            "
				+ Calc.porcentagem(primos.getLista2Primos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números primos:" + "            "
				+ Calc.porcentagem(primos.getLista3Primos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("4 números primos:" + "            "
				+ Calc.porcentagem(primos.getLista4Primos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("5 números primos:" + "            "
				+ Calc.porcentagem(primos.getLista5Primos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("6 números primos:" + "            "
				+ Calc.porcentagem(primos.getLista6Primos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum primo:" + "                "
				+ Calc.porcentagem(primos.getLista0Primo().size(), numCombinacoes) + "%\n\n");

		PainelResultadoAnalises.setTextArea("Nºs primos: " + Arrays.toString(primos.defineNumerosPrimos()));
	}

}
