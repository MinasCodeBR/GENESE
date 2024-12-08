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

package br.com.minascode.GENESE.filter;

import java.util.List;

import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.draw.LotoFacilDrawFactory;
import br.com.minascode.GENESE.draw.util.LotoFacilScannerConfig;
import br.com.minascode.GENESE.draw.util.XlsxScanner;
import br.com.minascode.GENESE.entities.LotoFacil;
import br.com.minascode.GENESE.filter.rules.criterion.cut.CutStrategy;
import br.com.minascode.GENESE.filter.rules.criterion.cut.LotoFacilCutStrategy;
import br.com.minascode.GENESE.path.DefineDirectory;

class LotoFacilFilterTest {

	public static void main(String[] args) {
		
        XlsxScanner lotoFacilScanner = new XlsxScanner(new LotoFacilScannerConfig());
        LotoFacilDrawFactory.createList(lotoFacilScanner.getDrawList());
        List<LotoFacil> drawList = LotoFacilDrawFactory.getLotoFacilDrawsList();

        CutStrategy cutStrategy = new LotoFacilCutStrategy();

		CombinationFilterManager<LotoFacil> filterManager = new CombinationFilterManager<LotoFacil>(DefineDirectory.getLotoFacilCombinationDir(), 
				DefineDirectory.getLotoFacilFilterDir(), drawList, cutStrategy, LotteryGameType.LOTO_FACIL);

        System.out.println("Iniciando o processamento...");
        filterManager.processCombinations();
        System.out.println("Processamento concluído.");
		
	}

}
