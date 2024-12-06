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

package br.com.minascode.GENESE.serialization.lists;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.serialization.Deserializer;
import br.com.minascode.GENESE.serialization.Serializer;

public class ListUngrouper<T extends ConfigurableCombination> {

    private static final Logger LOGGER = Logger.getLogger(ListUngrouper.class.getName());

    private final String inputDirectory;
    private final String outputDirectory;
    private final int listSize;

    public ListUngrouper(String inputDirectory, String outputDirectory, int listSize) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
        this.listSize = listSize;
    }

    public void ungroupLists() {
        File inputFolder = new File(inputDirectory);
        File[] inputFiles = inputFolder.listFiles();

        if (inputFiles == null || inputFiles.length == 0) {
            LOGGER.warning("O diretório de entrada está vazio ou inválido.");
            return;
        }

        // Cria o diretório de saída, se necessário
        File outputFolder = new File(outputDirectory);
        if (!outputFolder.exists() && !outputFolder.mkdirs()) {
            LOGGER.severe("Não foi possível criar o diretório de saída: " + outputDirectory);
            return;
        }

        int fileNumber = 1;
        List<T> currentBatch = new ArrayList<>();

        for (File inputFile : inputFiles) {
            try {
                // Desserializa o arquivo atual
                @SuppressWarnings("unchecked")
                List<T> combinations = (List<T>) Deserializer.deserialize(inputFile.getPath());

                if (combinations != null) {
                    for (T combination : combinations) {
                        currentBatch.add(combination);

                        // Quando o batch atingir o tamanho máximo, serializa e limpa a lista
                        if (currentBatch.size() == listSize) {
                            serializeBatch(currentBatch, fileNumber);
                            currentBatch.clear();
                            fileNumber++;
                        }
                    }
                }

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Erro ao processar o arquivo " + inputFile.getName() + ": " + e.getMessage(), e);
            }
        }

        // Serializa o restante, se houver
        if (!currentBatch.isEmpty()) {
            try {
                serializeBatch(currentBatch, fileNumber);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Erro ao serializar o lote final: " + e.getMessage(), e);
            }
        }

        LOGGER.info("Processo de desagrupamento concluído.");
    }

    private void serializeBatch(List<T> batch, int fileNumber) throws IOException {
        String outputPath = new File(outputDirectory, "lista_" + fileNumber + ".ser").getPath();
        Serializer.serialize(outputPath, batch);
        LOGGER.info("Lote salvo em: " + outputPath);
    }
}
