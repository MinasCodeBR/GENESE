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
import br.com.minascode.GENESE.draw.MegaSenaDrawFactory;
import br.com.minascode.GENESE.draw.util.LotoFacilScannerConfig;
import br.com.minascode.GENESE.draw.util.XlsxScanner;
import br.com.minascode.GENESE.entities.LotoFacil;
import br.com.minascode.GENESE.entities.MegaSena;
import br.com.minascode.GENESE.filter.rules.RemovalCriteria;
import br.com.minascode.GENESE.filter.rules.criterion.cut.CutStrategy;
import br.com.minascode.GENESE.filter.rules.criterion.cut.LotoFacilCutStrategy;
import br.com.minascode.GENESE.filter.rules.criterion.cut.MegaSenaCutStrategy;

public class RemovalCriteriaTest {
    public static void main(String[] args) {
        XlsxScanner megaSenaScanner = new XlsxScanner(new LotoFacilScannerConfig());
        LotoFacilDrawFactory.createList(megaSenaScanner.getDrawList());
        List<LotoFacil> drawList = LotoFacilDrawFactory.getLotoFacilDrawsList();

        CutStrategy cutStrategy = new LotoFacilCutStrategy();
        RemovalCriteria<LotoFacil> removalCriteria = new RemovalCriteria<>(drawList, cutStrategy, LotteryGameType.LOTO_FACIL);

        // Exemplo de uso
        System.out.println("Distances to Remove: " + removalCriteria.getRemovalResult("distancesToRemove"));
        System.out.println("Multiples to Remove: " + removalCriteria.getRemovalResult("multiplesToRemove"));
        System.out.println("Odd or Even to Remove: " + removalCriteria.getRemovalResult("parityToRemove"));
        System.out.println("Primes to Remove: " + removalCriteria.getRemovalResult("primesToRemove"));
        System.out.println("Quadrants to Remove: " + removalCriteria.getRemovalResult("quadrantsToRemove"));
        System.out.println("Number Ranges to Remove: " + removalCriteria.getRemovalResult("rangesToRemove"));
        System.out.println("Fibonacci to Remove: " + removalCriteria.getRemovalResult("fibonacciToRemove"));
        System.out.println("Quadratics to Remove: " + removalCriteria.getRemovalResult("quadraticsToRemove"));
        System.out.println("Cubics to Remove: " + removalCriteria.getRemovalResult("cubicsToRemove"));
    }    
}
