/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 1.0
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

package br.com.minascode.GENESE.combination;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import br.com.minascode.GENESE.serialization.Serializer;
import br.com.minascode.GENESE.util.Calc;

public class CombinationFactory<T> {

    private final Combination combinationConfig;
    private final Supplier<T> combinationSupplier;
    private final List<T> combinationList = new ArrayList<>();
    private final int listSize = 100000;
    private int id = 0;
    private int fileNumber = 1;
    private long totalCombinations;
    private long remainingCombinations;
    

    public CombinationFactory(Combination combinationConfig, Supplier<T> combinationSupplier) {
        this.combinationConfig = combinationConfig;
        this.combinationSupplier = combinationSupplier;
        setupCombinations();
    }

    private void setupCombinations() {
        int n = combinationConfig.getNumbersCount();
        int k = combinationConfig.getCombinationSize();
        totalCombinations = Calc.factorial(n)
                .divide(Calc.factorial(n - k).multiply(Calc.factorial(k)))
                .longValue();
        remainingCombinations = totalCombinations;
    }

    public void generateCombinations() {
        int n = combinationConfig.getNumbersCount();
        int k = combinationConfig.getCombinationSize();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }

        int[] indexes = new int[k];
        for (int i = 0; i < k; i++) {
            indexes[i] = i;
        }

        while (true) {
            T combination = createCombination(numbers, indexes);
            
            serialize(combination);

            if (remainingCombinations == 0) {
                break;
            }

            int t = k - 1;
            while (t != 0 && indexes[t] == numbers.length - k + t) {
                t--;
            }
            if (t < 0) {
                break;
            }
            indexes[t]++;
            for (int i = t + 1; i < k; i++) {
                indexes[i] = indexes[i - 1] + 1;
            }
        }        
    }

    private T createCombination(int[] numbers, int[] indexes) {    	
        T combination = combinationSupplier.get();
        if (combination instanceof ConfigurableCombination configurable) {
            configurable.configureCombination(numbers, indexes);
            configurable.setId(id);
        }
        id++;
        return combination;
    }

    private void serialize(T combination) {
        combinationList.add(combination);
        remainingCombinations--;

        if (combinationList.size() == listSize || remainingCombinations == 0) {
            try {
            	String directory = combinationConfig.getDirectory();
                String fileName = directory + File.separator + "lista_" + fileNumber + ".ser";
                Serializer.serialize(fileName, combinationList);
            } catch (Exception ex) {
                System.err.println("Serialization failed - " + ex.toString());
            }
            combinationList.clear();
            fileNumber++;
        }
        if (remainingCombinations % 10000000 == 0) {
            System.out.println("Checkpoint: " + remainingCombinations + " combinações restantes.");
        }
    }
}
