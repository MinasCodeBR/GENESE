package br.constapp.genese.gui.text;

import java.util.List;

import br.constapp.genese.analise.MaisMenos;
import br.constapp.genese.analise.modelo.ModeloMaisMenos;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class MaisMenosText {

	private static ModeloMaisMenos[] arrayModelosOrganizado;

	public static void processaMaisMenos() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		MaisMenos maisMenos = new MaisMenos(60, listaJogos);
		
		arrayModelosOrganizado = maisMenos.getArrayModelosOrganizado();

		setTextArea();
	}

	private static void setTextArea() {
		PainelResultadoAnalises.setTextArea("Os 10 MAIS sorteados:\n\n");

		for (int i = 0; i <= 9; i++) {
			PainelResultadoAnalises.setTextArea(("Dezena " + Calc.zeroToLeft(arrayModelosOrganizado[i].getDezena()) + " = "
					+ arrayModelosOrganizado[i].getRepeticoes() + " vezes\n"));
		}
		
		PainelResultadoAnalises.setTextArea("\nOs 10 MENOS sorteados:\n\n");
		
		for (int i = 50; i <= 59; i++) {
			PainelResultadoAnalises.setTextArea(("Dezena " + Calc.zeroToLeft(arrayModelosOrganizado[i].getDezena()) + " = "
					+ arrayModelosOrganizado[i].getRepeticoes() + " vezes\n"));
		}
	}

}
