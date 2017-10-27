package br.constapp.genese.gui.text;

import java.util.List;

import br.constapp.genese.analise.Quadrantes;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class QuadrantesText {

	public static void processaQuadrantes() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		for (int i = 1; i <= 4; i++) {

			Quadrantes quadrantes = new Quadrantes(listaJogos, i);

			setTextArea(quadrantes, listaJogos.size(), i);

		}
	}

	private static void setTextArea(Quadrantes quadrantes, int numCombinacoes, int i) {

		PainelResultadoAnalises.setTextArea("Apostando em                            Chance\n\n");

		PainelResultadoAnalises.setTextArea("1 número do " + i + "º quadrante:          "
				+ Calc.porcentagem(quadrantes.getLista1Incidencia().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("2 números do " + i + "º quadrante:         "
				+ Calc.porcentagem(quadrantes.getLista2Incidencias().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("3 números do " + i + "º quadrante:         "
				+ Calc.porcentagem(quadrantes.getLista3Incidencias().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("4 números do " + i + "º quadrante:         "
				+ Calc.porcentagem(quadrantes.getLista4Incidencias().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("5 números do " + i + "º quadrante:         "
				+ Calc.porcentagem(quadrantes.getLista5Incidencias().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("6 números do " + i + "º quadrante:         "
				+ Calc.porcentagem(quadrantes.getLista6Incidencias().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("nenhum do " + i + "º quadrante:            "
				+ Calc.porcentagem(quadrantes.getLista0Incidencia().size(), numCombinacoes) + "%\n");

		PainelResultadoAnalises.setTextArea("\n");
	}

}
