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

package br.com.minascode.GENESE.filter;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.filter.filters.CubicFilter;
import br.com.minascode.GENESE.filter.filters.DistanceFilter;
import br.com.minascode.GENESE.filter.filters.FibonacciFilter;
import br.com.minascode.GENESE.filter.filters.MultiplicityFilter;
import br.com.minascode.GENESE.filter.filters.NumberRangeFilter;
import br.com.minascode.GENESE.filter.filters.OddOrEvenFilter;
import br.com.minascode.GENESE.filter.filters.PrimalityFilter;
import br.com.minascode.GENESE.filter.filters.QuadrantFilter;
import br.com.minascode.GENESE.filter.filters.QuadraticFilter;
import br.com.minascode.GENESE.filter.filters.SequenceFilter;
import br.com.minascode.GENESE.filter.rules.RemovalCriteria;
import br.com.minascode.GENESE.filter.rules.criterion.cut.CutStrategy;
import br.com.minascode.GENESE.serialization.Deserializer;
import br.com.minascode.GENESE.serialization.Serializer;

public class CombinationFilterManager<T extends ConfigurableCombination> {

    private final ExecutorService executor;
    private final RemovalCriteria<T> removalCriteria;
    private final String combinationsDirectory;
    private final String filterDirectory;
    private final long startTime;
    private final LotteryGameType gameType;

    public CombinationFilterManager(String combinationsDirectory, String filterDirectory,
            List<T> drawList, CutStrategy cutStrategy, LotteryGameType gameType) {
        this.combinationsDirectory = combinationsDirectory;
        this.filterDirectory = filterDirectory;
        this.removalCriteria = new RemovalCriteria<>(drawList, cutStrategy, gameType);
        this.executor = Executors.newFixedThreadPool(2);
        this.startTime = System.currentTimeMillis();
        this.gameType = gameType;
    }

    public void processCombinations() {
        File combinationsFolder = new File(combinationsDirectory);
        File[] combinationFiles = combinationsFolder.listFiles();

        if (combinationFiles == null || combinationFiles.length == 0) {
            System.err.println("O diretório de combinações está vazio ou inválido.");
            return;
        }

        for (File file : combinationFiles) {
            executor.submit(() -> processFile(file));            
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(2, TimeUnit.HOURS)) {
                System.err.println("O tempo limite para processamento foi excedido.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("O processamento foi interrompido: " + e.getMessage());
        }

        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Tempo total de execução: " + formatTime(totalTime));
    }

    private void processFile(File file) {    	
    	System.out.println("\nProcessando: " + file.getName());
        try {
            @SuppressWarnings("unchecked")
            List<T> combinations = (List<T>) Deserializer.deserialize(file.getPath());           

            if (combinations != null && !combinations.isEmpty()) {
                // Aplica os filtros às combinações
                combinations = applyFilters(combinations);

                // Serializa o resultado para o diretório de saída com o mesmo nome de arquivo
                String outputPath = new File(filterDirectory, file.getName()).getPath();
                Serializer.serialize(outputPath, combinations);
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar arquivo " + file.getName() + ": " + e.getMessage());
            e.printStackTrace();
        }        
    }

    private List<T> applyFilters(List<T> combinations) {
        // Cria a lista de filtros, instanciando-os com o tipo de jogo apropriado e a instância de RemovalCriteria
        List<Filter<T>> filters = List.of(
                new DistanceFilter<>(removalCriteria, gameType),
                new MultiplicityFilter<>(removalCriteria, gameType), 
                new OddOrEvenFilter<>(removalCriteria, gameType),
                new PrimalityFilter<>(removalCriteria, gameType), 
                new QuadrantFilter<>(removalCriteria, gameType),
                new NumberRangeFilter<>(removalCriteria, gameType), 
                new FibonacciFilter<>(removalCriteria, gameType),
                new QuadraticFilter<>(removalCriteria, gameType), 
                new CubicFilter<>(removalCriteria, gameType),                
                new SequenceFilter<>(gameType)
        );

        for (Filter<T> filter : filters) {
        	if (filter.shouldApply(gameType)) {
        		combinations = filter.customFilter(combinations);
        	}
        }

        return combinations;
    }

    private String formatTime(long millis) {
        if (millis < 1000) {
            return millis + " milissegundos";
        } else if (millis < 60_000) {
            return TimeUnit.MILLISECONDS.toSeconds(millis) + " segundos";
        } else if (millis < 3_600_000) {
            return TimeUnit.MILLISECONDS.toMinutes(millis) + " minutos";
        } else {
            return TimeUnit.MILLISECONDS.toHours(millis) + " horas";
        }
    }
}