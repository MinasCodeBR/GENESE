/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 0.0.1
 * Licença: GNU General Public License v3.0
 * 
 * Este programa é um software livre: você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation, seja a versão 3 da
 * Licença, ou (a seu critério) qualquer versão posterior.
 * 
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou ADEQUAÇÃO A UM DETERMINADO FIM. Veja a
 * Licença Pública Geral GNU para mais detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa. Caso contrário, veja <https://www.gnu.org/licenses/>.
 */

package br.com.minascode.GENESE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.minascode.GENESE.analysis.comparator.util.AnalysisComparator;
import br.com.minascode.GENESE.draw.MegaSenaDrawFactory;
import br.com.minascode.GENESE.draw.util.MegaSenaScannerConfig;
import br.com.minascode.GENESE.draw.util.XlsxScanner;
import br.com.minascode.GENESE.entities.MegaSena;
import br.com.minascode.GENESE.path.DefineDirectory;
import br.com.minascode.GENESE.serialization.Deserializer;

class SorteiosUtil {

	private static List<MegaSena> listaPalpites;
	private static List<MegaSena> listaJogos;
	private static int conta;
	private static File arquivo;
	private static Integer[] sorteiosPorLista;
	private static Integer[] favoritos;
	private static List<AnalysisComparator> listModelo;

	@SuppressWarnings("unchecked")
	public static void achaSorteios() throws IOException {

		XlsxScanner scan = new XlsxScanner(new MegaSenaScannerConfig());
		MegaSenaDrawFactory.createList(scan.getDrawList());
		listaJogos = MegaSenaDrawFactory.getMegaSenaDrawsList();

		listaPalpites = new ArrayList<>();
		sorteiosPorLista = new Integer[501];
		int totalSorteios = 0;
		int totalCombinacoes = 0;

		arquivo = new File(DefineDirectory.getRootDirectory() + "print.txt");
		PrintWriter pw = new PrintWriter(new FileWriter(arquivo));

		for (int i = 1; i <= 501; i++) {

			String nomeArquivo = "lista_" + i + ".ser";

			try {
				listaPalpites = ((List<MegaSena>) Deserializer
						.deserialize(DefineDirectory.getMegaSenaFilterDir() + File.separator + nomeArquivo));
				pw.println("\nLista " + i + ": " + listaPalpites.size());

				System.out.println("\nLista " + i + ": " + listaPalpites.size());
				conta = 0;

				for (MegaSena jogoPalpite : listaPalpites) {
					for (MegaSena jogo : listaJogos) {

						if (jogoPalpite.getFirstNumber() == jogo.getFirstNumber()
								&& jogoPalpite.getSecondNumber() == jogo.getSecondNumber()
								&& jogoPalpite.getThirdNumber() == jogo.getThirdNumber()
								&& jogoPalpite.getFourthNumber() == jogo.getFourthNumber()
								&& jogoPalpite.getFifthNumber() == jogo.getFifthNumber()
								&& jogoPalpite.getSixthNumber() == jogo.getSixthNumber()) {

							pw.println("\nEste jogo já saiu no concurso " + jogo.getId() + " da Mega-Sena \nem "
									+ jogo.getDate());

							System.out.println("\nEste jogo já saiu no concurso " + jogo.getId()
									+ " da Mega-Sena \nem " + jogo.getDate());
							conta++;

						}
					}
				}

				pw.println("\nJogos na lista: " + conta);

				System.out.println("\nJogos na lista: " + conta);
				totalSorteios += conta;
				sorteiosPorLista[i - 1] = conta;
				totalCombinacoes += listaPalpites.size();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		pw.println(listaPalpites.size());
		pw.println("Total de combinações" + totalCombinacoes);
		pw.println(totalSorteios);

		System.out.println("Total de combinações: " + totalCombinacoes);
		System.out.println(totalSorteios);

		pw.close();
	}

	private static void defineListModelo() {

		listModelo = new ArrayList<>();

		for (int i = 0; i < sorteiosPorLista.length; i++) {
			AnalysisComparator modelo = new AnalysisComparator(i + 1, sorteiosPorLista[i]);
			listModelo.add(modelo);
		}

		Collections.sort(listModelo);
	}

	public static Integer[] defineFavoritos() {

		defineListModelo();

		favoritos = new Integer[sorteiosPorLista.length];

		int index = 0;

		for (AnalysisComparator modelo : listModelo) {

			favoritos[index] = modelo.getNumber();
			index++;

		}

		return favoritos;
	}

	public static List<MegaSena> getListaPalpites() {
		return listaPalpites;
	}

	public static void main(String[] args) {

		try {
			SorteiosUtil.achaSorteios();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Sorteios por lista: " + Arrays.toString(sorteiosPorLista));

		System.out.println("Favoritos: " + Arrays.toString(SorteiosUtil.defineFavoritos()));

	}

}
