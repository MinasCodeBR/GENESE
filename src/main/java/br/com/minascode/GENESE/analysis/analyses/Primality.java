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

package br.com.minascode.GENESE.analysis.analyses;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.analysis.AnalysisHandler;
import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;

public class Primality<T extends ConfigurableCombination> extends AnalysisHandler<T> {

	Integer[] primeNumbers;
	
    public Primality(List<T> drawList, LotteryGameType gameType) {
        primeNumbers = defineNumbers(gameType.getMaxRange());
        process(drawList, primeNumbers, gameType);
    }

    @Override
    public Integer[] defineNumbers() {
    	throw new UnsupportedOperationException("Use defineNumbers(int maxRange) for this class.");
    }

    public Integer[] defineNumbers(int maxRange) {
        List<Integer> primes = new ArrayList<>();

        for (int i = 1; i <= maxRange; i++) {
            if (isPrime(i)) {
                primes.add(i);
            }
        }

        return primes.toArray(new Integer[0]);
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public List<T> getIncidenceList(int incidence) {
        if (incidence < 0 || incidence > primeNumbers.length) {
            throw new IllegalArgumentException("Incidência inválida! Deve estar entre 0 e " + primeNumbers.length);
        }
    	return super.getIncidenceList(incidence);
    }
}

