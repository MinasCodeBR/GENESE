import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.constapp.genese.analise.modelo.ModeloAnalise;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.serialization.Deserializador;

class SorteiosUtil {

	private static List<Jogo> listaPalpites;
	private static List<Jogo> listaJogos;
	private static int conta;
	private static File arquivo;
	private static Integer[] sorteiosPorLista;
	private static Integer[] favoritos;
	private static List<ModeloAnalise> listModelo;

	@SuppressWarnings("unchecked")
	public static void achaSorteios() throws IOException {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		listaJogos = FabricaDeJogos.getListaJogos();

		listaPalpites = new ArrayList<>();
		sorteiosPorLista = new Integer[51];
		int totalSorteios = 0;
		int totalCombinacoes = 0;

		arquivo = new File(DefineDiretorio.getDiretorioRaiz() + "print.txt"); // **
		PrintWriter pw = new PrintWriter(new FileWriter(arquivo)); // **

		for (int i = 1; i <= 51; i++) {

			String nomeArquivo = "lista_" + i + ".ser";

			try {
				listaPalpites = ((List<Jogo>) Deserializador
						.deserializa(DefineDiretorio.getDiretorioFiltro() + File.separator + nomeArquivo));
				pw.println("\nLista " + i + ": " + listaPalpites.size()); // **

				System.out.println("\nLista " + i + ": " + listaPalpites.size());
				conta = 0;

				for (Jogo jogoPalpite : listaPalpites) {
					for (Jogo jogo : listaJogos) {

						if (jogoPalpite.getPrimeiraDezena() == jogo.getPrimeiraDezena()
								&& jogoPalpite.getSegundaDezena() == jogo.getSegundaDezena()
								&& jogoPalpite.getTerceiraDezena() == jogo.getTerceiraDezena()
								&& jogoPalpite.getQuartaDezena() == jogo.getQuartaDezena()
								&& jogoPalpite.getQuintaDezena() == jogo.getQuintaDezena()
								&& jogoPalpite.getSextaDezena() == jogo.getSextaDezena()) {

							pw.println("\nEste jogo já saiu no concurso " + jogo.getConcurso() + " da Mega-Sena \nem "
									+ jogo.getDataSorteio()); // **

							System.out.println("\nEste jogo já saiu no concurso " + jogo.getConcurso()
									+ " da Mega-Sena \nem " + jogo.getDataSorteio());
							conta++;

						}
					}
				}

				pw.println("\nJogos na lista: " + conta); // **

				System.out.println("\nJogos na lista: " + conta);
				totalSorteios += conta;
				sorteiosPorLista[i - 1] = conta;
				totalCombinacoes += listaPalpites.size();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		pw.println(listaPalpites.size());
		pw.println("Total de combinações" + totalCombinacoes); // **
		pw.println(totalSorteios);

		System.out.println("Total de combinações: " + totalCombinacoes);
		System.out.println(totalSorteios);

		pw.close();
	}

	private static void defineListModelo() {

		listModelo = new ArrayList<>();

		for (int i = 0; i < sorteiosPorLista.length; i++) {
			ModeloAnalise modelo = new ModeloAnalise(i + 1, sorteiosPorLista[i]);
			listModelo.add(modelo);
		}

		Collections.sort(listModelo);
	}

	public static Integer[] defineFavoritos() {

		defineListModelo();

		favoritos = new Integer[sorteiosPorLista.length];

		int index = 0;

		for (ModeloAnalise modelo : listModelo) {

			favoritos[index] = modelo.getNumero();
			index++;

		}

		return favoritos;
	}

	public static List<Jogo> getListaPalpites() {
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
