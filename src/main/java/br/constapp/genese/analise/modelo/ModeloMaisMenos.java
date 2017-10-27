package br.constapp.genese.analise.modelo;

public class ModeloMaisMenos {

	private int dezena;
	private int repeticoes;

	public ModeloMaisMenos() {
	}

	public ModeloMaisMenos(int dezena, int repeticoes) {
		super();
		this.dezena = dezena;
		this.repeticoes = repeticoes;
	}

	@Override
	public String toString() {
		return dezena + " " + repeticoes;
	}

	public int getDezena() {
		return dezena;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

}
