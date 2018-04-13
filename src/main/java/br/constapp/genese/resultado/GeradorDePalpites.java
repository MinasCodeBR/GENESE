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

package br.constapp.genese.resultado;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import br.constapp.genese.analise.JogoRepetido;
import br.constapp.genese.gui.panels.PainelResultadoPalpites;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.serialization.Deserializador;
import br.constapp.genese.util.GravadoraDePalpite;

public class GeradorDePalpites implements Runnable {

	private static List<Jogo> listaPosFiltro;
	private List<Jogo> listaPalpites;
	private int num;

	public GeradorDePalpites() {
	}

	@Override
	public void run() {

		PainelResultadoPalpites.botaoLivre(false);
		PainelResultadoPalpites.painelCfgHabilitado(false);
		geraPalpites();
		PainelResultadoPalpites.painelCfgHabilitado(true);
		PainelResultadoPalpites.botaoLivre(true);

	}

	private List<Jogo> geraPalpites() {

		listaPalpites = new ArrayList<>();

		Random r = new Random();

		for (int i = 0; i < Integer.parseInt(PainelResultadoPalpites.getTxtQtdJogos().getText()); i++) {

			criaListaPosFiltro();

			Collections.shuffle(listaPosFiltro); // Embaralha a lista

			Jogo palpite = listaPosFiltro.get(r.nextInt(listaPosFiltro.size()));

			// palpite = new Jogo(9999, "11/03/1991", 1, 3, 13, 22, 29, 32, 0, "0", 10,
			// "100", 100, "10", "33000000");

			jaSorteado(palpite);

			PainelResultadoPalpites.setTextPanePalpites(palpite + "\n\n");

			listaPalpites.add(palpite);

		}

		if (Integer.parseInt(PainelResultadoPalpites.getTxtQtdJogos().getText()) > 1) {
			PainelResultadoPalpites.setLblDisplay("Palpites gerados");
		} else {
			PainelResultadoPalpites.setLblDisplay("Palpite gerado");
		}

		PainelResultadoPalpites.getBtnEstouComSorte().setText("Estou com sorte");
		PainelResultadoPalpites.getBtnEstouComSorte().setEnabled(true);

		if (PainelResultadoPalpites.getChckbxSalvar().isSelected()) {
			try {
				GravadoraDePalpite.salvaArquivo();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return listaPalpites;
	}

	@SuppressWarnings("unchecked")
	private void criaListaPosFiltro() {

		listaPosFiltro = new ArrayList<>();
		Random r;
		Integer[] favoritos = new Integer[] { 21, 24, 47, 49, 28, 23, 27, 34, 48, 22, 26, 32, 40, 45 };

		PainelResultadoPalpites.setProgressBar(true);
		PainelResultadoPalpites.setLblDisplay("Gerando, aguarde...");

		// for (int i = 1; i <= 3; i++) {

		r = new Random();

		if (PainelResultadoPalpites.getChckbxUsarFav().isSelected()) {
			num = favoritos[r.nextInt(13)];
		} else {
			num = r.nextInt(50) + 1;
		}

		String nomeArquivo = "lista_" + num + ".ser";

		try {
			listaPosFiltro.addAll((List<Jogo>) Deserializador
					.deserializa(DefineDiretorio.getDiretorioFiltro() + File.separator + nomeArquivo));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// }
		PainelResultadoPalpites.setProgressBar(false);

	}

	private boolean jaSorteado(Jogo jogoPalpite) {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		for (Jogo jogo : listaJogos) {

			if (jogoPalpite.getPrimeiraDezena() == jogo.getPrimeiraDezena()
					&& jogoPalpite.getSegundaDezena() == jogo.getSegundaDezena()
					&& jogoPalpite.getTerceiraDezena() == jogo.getTerceiraDezena()
					&& jogoPalpite.getQuartaDezena() == jogo.getQuartaDezena()
					&& jogoPalpite.getQuintaDezena() == jogo.getQuintaDezena()
					&& jogoPalpite.getSextaDezena() == jogo.getSextaDezena()) {

				PainelResultadoPalpites.setTextPanePalpites("\nEste jogo já saiu no concurso " + jogo.getConcurso()
						+ " da Mega-Sena \nem " + jogo.getDataSorteio());

				if (!JogoRepetido.temJogoRepetido(listaJogos)) {
					PainelResultadoPalpites.setTextPanePalpites(
							". Dos " + listaJogos.size() + " sorteios realizados,\nnunca houve uma repetição.\n");
				} else {
					PainelResultadoPalpites.setTextPanePalpites(".\n");
				}
				return true;
			}
		}
		return false;
	}

	public static List<Jogo> getListaPosFiltro() {
		return listaPosFiltro;
	}

}
