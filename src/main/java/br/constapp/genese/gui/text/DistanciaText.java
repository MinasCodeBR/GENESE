package br.constapp.genese.gui.text;

import java.util.List;

import br.constapp.genese.analise.Distancia;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class DistanciaText {

	public static void processaDistancia() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Distancia distancia = new Distancia(listaJogos);

		setTextArea(distancia, listaJogos.size());

	}

	private static void setTextArea(Distancia distancia, int numCombinacoes) {

		for (int i = 0; i < distancia.getVetResultado().length; i++) {
			PainelResultadoAnalises
					.setTextArea("Distância " + Calc.zeroToLeft(distancia.getVetResultado()[i].getNumero()) + " = "
							+ Calc.porcentagem(distancia.getVetResultado()[i].getRepeticoes(), numCombinacoes) + "%\n");
		}

	}

}
