package br.constapp.genese.gui.text;

import java.util.List;

import br.constapp.genese.analise.Sequencia;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class SequenciaText {

	public static void processaSequencia() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Sequencia sequencia = new Sequencia(listaJogos);

		setTextArea(sequencia, listaJogos.size());
	}

	private static void setTextArea(Sequencia sequencia, int numCombinacoes) {

		PainelResultadoAnalises.setTextArea("Primeiro campo: "
				+ Calc.porcentagem(sequencia.getListaPrimeiroCampo().size(), numCombinacoes) + "% (X X X - - -)\n");
		for (Jogo jogo : sequencia.getListaPrimeiroCampo()) {
			PainelResultadoAnalises.setTextArea(jogo + "   Concurso: " + jogo.getConcurso() + "\n");
		}

		PainelResultadoAnalises.setTextArea("\nSegundo campo: "
				+ Calc.porcentagem(sequencia.getListaSegundoCampo().size(), numCombinacoes) + "% (- X X X - -)\n");
		for (Jogo jogo : sequencia.getListaSegundoCampo()) {
			PainelResultadoAnalises.setTextArea(jogo + "   Concurso: " + jogo.getConcurso() + "\n");
		}

		PainelResultadoAnalises.setTextArea("\nTerceiro campo: "
				+ Calc.porcentagem(sequencia.getListaTerceiroCampo().size(), numCombinacoes) + "% (- - X X X -)\n");
		for (Jogo jogo : sequencia.getListaTerceiroCampo()) {
			PainelResultadoAnalises.setTextArea(jogo + "   Concurso: " + jogo.getConcurso() + "\n");
		}

		PainelResultadoAnalises.setTextArea(
				"\nQuarto campo: " + Calc.porcentagem(sequencia.getListaQuartoCampo().size(), numCombinacoes) + "% (- - - X X X)\n");
		for (Jogo jogo : sequencia.getListaQuartoCampo()) {
			PainelResultadoAnalises.setTextArea(jogo + "   Concurso: " + jogo.getConcurso() + "\n");
		}

		PainelResultadoAnalises.setTextArea("\nAs seis dezenas em sequência: "
				+ Calc.porcentagem(sequencia.getListaSequencia().size(), numCombinacoes) + "% (X X X X X X)\n");
		for (Jogo jogo : sequencia.getListaSequencia()) {
			PainelResultadoAnalises.setTextArea(jogo + "   Concurso: " + jogo.getConcurso() + "\n");
		}

	}

}
