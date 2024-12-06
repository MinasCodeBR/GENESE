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

package br.com.minascode.GENESE.analysis;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import br.com.minascode.GENESE.analysis.analyses.CubicTest;
import br.com.minascode.GENESE.analysis.analyses.DistanceTest;
import br.com.minascode.GENESE.analysis.analyses.FibonacciTest;
import br.com.minascode.GENESE.analysis.analyses.MultiplicityTest;
import br.com.minascode.GENESE.analysis.analyses.NumberFrequencyTest;
import br.com.minascode.GENESE.analysis.analyses.NumberRangeTest;
import br.com.minascode.GENESE.analysis.analyses.OddOrEvenTest;
import br.com.minascode.GENESE.analysis.analyses.PrimalityTest;
import br.com.minascode.GENESE.analysis.analyses.QuadrantTest;
import br.com.minascode.GENESE.analysis.analyses.QuadraticTest;
import br.com.minascode.GENESE.analysis.analyses.SequenceTest;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.draw.LotoFacilDrawFactory;
import br.com.minascode.GENESE.draw.MegaSenaDrawFactory;
import br.com.minascode.GENESE.draw.util.LotoFacilScannerConfig;
import br.com.minascode.GENESE.draw.util.MegaSenaScannerConfig;
import br.com.minascode.GENESE.draw.util.XlsxScanner;
import br.com.minascode.GENESE.entities.LotoFacil;
import br.com.minascode.GENESE.entities.MegaSena;

public class LotteryAnalysisRunner {

	private static List<LotoFacil> lotoFacilDraws;
	private static LotteryGameType lotoFacilGameType;
	private static List<MegaSena> megaSenaDraws;
	private static LotteryGameType megaSenaGameType;
	private static CubicTest<?> cubicTest;
	private static DistanceTest<?> distanceTest;
	private static FibonacciTest<?> fibonacciTest;
	private static MultiplicityTest<?> multiplicityTest;
	private static NumberFrequencyTest<?> frequencyTest;
	private static NumberRangeTest<?> numberRangeTest;
	private static OddOrEvenTest<?> oddOrEvenTest;
	private static PrimalityTest<?> primalityTest;
	private static QuadrantTest<?> quadrantTest;
	private static QuadraticTest<?> quadraticTest;
	private static SequenceTest<?> sequenceTest;

	public static void main(String[] args) {

//		loadLotoFacilGames();		
//		loadClasses(LotoFacil.class, lotoFacilDraws, lotoFacilGameType);
		
		loadMegaSenaGames();
		loadClasses(MegaSena.class, megaSenaDraws, megaSenaGameType);

		cubicTest.printAnalysis();
		System.out.println();
		distanceTest.printAnalysis();
		System.out.println();
		fibonacciTest.printAnalysis();
		System.out.println();
		multiplicityTest.printAnalysis();
		frequencyTest.printAnalysis();
		System.out.println();
		numberRangeTest.printAnalysis();
		oddOrEvenTest.printAnalysis();
		System.out.println();
		primalityTest.printAnalysis();
		System.out.println();
		quadrantTest.printAnalysis();
		quadraticTest.printAnalysis();
		// sequenceTest.printAnalysis();

	}

	public static <T extends ConfigurableCombination> void loadClasses(Class<T> clazz, List<T> drawList,
			LotteryGameType gameType) {
		try {
			cubicTest = new CubicTest<>(drawList, gameType);
			distanceTest = new DistanceTest<>(drawList, gameType);
			fibonacciTest = new FibonacciTest<>(drawList, gameType);
			multiplicityTest = new MultiplicityTest<>(drawList, gameType);
			frequencyTest = new NumberFrequencyTest<>(drawList, gameType);
			numberRangeTest = new NumberRangeTest<>(drawList, gameType);
			oddOrEvenTest = new OddOrEvenTest<>(drawList, gameType);
			primalityTest = new PrimalityTest<>(drawList, gameType);
			quadrantTest = new QuadrantTest<>(drawList, gameType);
			quadraticTest = new QuadraticTest<>(drawList, gameType);
			sequenceTest = new SequenceTest<>(drawList, gameType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void loadLotoFacilGames() {
		XlsxScanner scanner = new XlsxScanner(new LotoFacilScannerConfig());
		LotoFacilDrawFactory.createList(scanner.getDrawList());
		lotoFacilDraws = LotoFacilDrawFactory.getLotoFacilDrawsList();

		lotoFacilGameType = LotteryGameType.LOTO_FACIL;
	}

	public static void loadMegaSenaGames() {
		XlsxScanner scanner = new XlsxScanner(new MegaSenaScannerConfig());
		MegaSenaDrawFactory.createList(scanner.getDrawList());
		megaSenaDraws = MegaSenaDrawFactory.getMegaSenaDrawsList();

		megaSenaGameType = LotteryGameType.MEGA_SENA;
	}
}
