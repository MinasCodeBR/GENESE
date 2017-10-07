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

import br.constapp.genese.analise.Primalidade;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class PrimalidadePro {

	public static void processaPrimalidade() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		int numCombinacoes = 0;
		int size0Primo = 0;
		int size1Primo = 0;
		int size2Primos = 0;
		int size3Primos = 0;
		int size4Primos = 0;
		int size5Primos = 0;
		int size6Primos = 0;

		Primalidade primos = new Primalidade(listaJogos);

		numCombinacoes += listaJogos.size();
		size0Primo += primos.getLista0Primo().size();
		size1Primo += primos.getLista1Primo().size();
		size2Primos += primos.getLista2Primos().size();
		size3Primos += primos.getLista3Primos().size();
		size4Primos += primos.getLista4Primos().size();
		size5Primos += primos.getLista5Primos().size();
		size6Primos += primos.getLista6Primos().size();

		PainelResultadoAnalises.setTextArea("Apostando em                Chance\n\n");

		PainelResultadoAnalises.setTextArea(
				"1 número primo:" + "              " + Calc.porcentagem(size1Primo, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"2 números primos:" + "            " + Calc.porcentagem(size2Primos, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"3 números primos:" + "            " + Calc.porcentagem(size3Primos, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"4 números primos:" + "            " + Calc.porcentagem(size4Primos, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"5 números primos:" + "            " + Calc.porcentagem(size5Primos, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"6 números primos:" + "            " + Calc.porcentagem(size6Primos, numCombinacoes) + "\n");

		PainelResultadoAnalises.setTextArea(
				"nenhum primo:" + "                " + Calc.porcentagem(size0Primo, numCombinacoes) + "\n\n");
		
		PainelResultadoAnalises.setTextArea("Nºs primos: " + Arrays.toString(primos.defineNumerosPrimos()));

	}

}
