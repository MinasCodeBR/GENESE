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

package br.com.minascode.GENESE.filter.rules.criterion.cut;

import java.util.HashMap;
import java.util.Map;

public class LotoFacilCutStrategy implements CutStrategy {
	
    @Override
    public Map<String, Double> getCutValues() {
        Map<String, Double> cutValues = new HashMap<>();
        cutValues.put("distancesCut", 4.0);
        cutValues.put("multiplesCut", 2.0);
        cutValues.put("parityCut", 2.0);
        cutValues.put("primesCut", 2.0);
        cutValues.put("quadrantsCut", 5.0);
        cutValues.put("rangesCut", 5.0);
        cutValues.put("fibonacciCut", 5.0);
        cutValues.put("quadraticsCut", 5.0);
        cutValues.put("cubicsCut", 10.0);
        return cutValues;
    }
}

