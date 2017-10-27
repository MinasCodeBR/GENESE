package br.constapp.genese.analise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.constapp.genese.analise.modelo.ModeloMaisMenos;
import br.constapp.genese.jogo.modelo.Jogo;

public class MaisMenos {

	private static Integer[] dezenas;
	private static Integer[] contagem;
	private static ModeloMaisMenos[] arrayModelos;
	private static ModeloMaisMenos[] arrayModelosOrganizado;

	private static ModeloMaisMenos modelo;

	public MaisMenos(int numDezenas, List<Jogo> listaJogos) {
		contaRepeticoes(numDezenas, listaJogos);
		criaArrayModelos(numDezenas);
		ordenaArrayModelos(numDezenas);
	}

	private static ModeloMaisMenos[] ordenaArrayModelos(int numDezenas) {

		arrayModelosOrganizado = new ModeloMaisMenos[numDezenas];

		Arrays.sort(arrayModelos, new Comparator<ModeloMaisMenos>() {
			@Override
			public int compare(ModeloMaisMenos b1, ModeloMaisMenos b2) {

				if (b1.getRepeticoes() < b2.getRepeticoes()) {
					return 1;
				}
				if (b1.getRepeticoes() > b2.getRepeticoes()) {
					return -1;
				}
				return 0;
			}
		});
		arrayModelosOrganizado = arrayModelos;
		return arrayModelosOrganizado;
	}

	private static ModeloMaisMenos[] criaArrayModelos(int numDezenas) {

		arrayModelos = new ModeloMaisMenos[numDezenas];

		for (int i = 0; i < contagem.length; i++) {

			modelo = new ModeloMaisMenos(i + 1, contagem[i]);
			arrayModelos[i] = modelo;
		}
		return arrayModelos;
	}

	private static void contaRepeticoes(int numDezenas, List<Jogo> listaJogos) {

		defineDezenas(numDezenas);

		contagem = new Integer[numDezenas];

		for (int i = 0; i < contagem.length; i++) {
			contagem[i] = 0;
		}

		for (Jogo jogo : listaJogos) {
			for (int i = 0; i < dezenas.length; i++) {

				if (jogo.getPrimeiraDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getSegundaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getTerceiraDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getQuartaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getQuintaDezena() == dezenas[i]) {
					contagem[i]++;
				}

				if (jogo.getSextaDezena() == dezenas[i]) {
					contagem[i]++;
				}
			}
		}
	}

	private static Integer[] defineDezenas(int i) {
		dezenas = new Integer[i];
		for (int n = 1; n <= i; n++) {
			dezenas[n - 1] = n;
		}
		return dezenas;
	}

	public ModeloMaisMenos[] getArrayModelos() {
		return arrayModelos;
	}

	public ModeloMaisMenos[] getArrayModelosOrganizado() {
		return arrayModelosOrganizado;
	}

}