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

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class CubicosText {

	public static void processaCubicos() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Cubicos cubicos = new Cubicos(listaJogos);

		setTextArea(cubicos, listaJogos.size());
	}

	private static void setTextArea(Cubicos cubicos, int numCombinacoes) {

		PainelResultadoAnalises.setTextArea("Apostando em:              Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número cúbico:" + "           "
				+ Calc.porcentagem(cubicos.getLista1Cubico().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números cúbicos:" + "         "
				+ Calc.porcentagem(cubicos.getLista2Cubicos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números cúbicos:" + "         "
				+ Calc.porcentagem(cubicos.getLista3Cubicos().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum cúbico:" + "             "
				+ Calc.porcentagem(cubicos.getLista0Cubico().size(), numCombinacoes) + "%\n\n");

		PainelResultadoAnalises.setTextArea("Números cúbicos: " + Arrays.toString(cubicos.defineNumerosCubicos()));
	}

}
