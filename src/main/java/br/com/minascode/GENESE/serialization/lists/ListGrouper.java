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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.serialization.Deserializer;
import br.com.minascode.GENESE.serialization.Serializer;

public class ListGrouper<T extends ConfigurableCombination> {

    private static final Logger logger = LoggerFactory.getLogger(ListGrouper.class);

    private final String filterDirectory;
    private final String outputDirectory;
    private final String outputFileName;

    public ListGrouper(String filterDirectory, String outputDirectory, String outputFileName) {
        this.filterDirectory = filterDirectory;
        this.outputDirectory = outputDirectory;
        this.outputFileName = outputFileName;
    }

    public void groupLists() {
        File filterFolder = new File(filterDirectory);
        File[] combinationFiles = filterFolder.listFiles();

        if (combinationFiles == null || combinationFiles.length == 0) {
            logger.error("O diretório de combinações filtradas está vazio ou inválido: {}", filterDirectory);
            return;
        }

        List<T> groupLists = new ArrayList<>();

        for (File file : combinationFiles) {
            try {
                List<T> combinations = deserialize(file);
                if (combinations != null && !combinations.isEmpty()) {
                    groupLists.addAll(combinations);
                    logger.info("Processando arquivo: {}", file.getName());
                }
            } catch (Exception e) {
                logger.error("Erro ao processar arquivo {}: {}", file.getName(), e.getMessage(), e);
            }
        }

        if (groupLists.isEmpty()) {
            logger.info("Não foram encontradas listas para agrupar.");
            return;
        }

        File outputFolder = new File(outputDirectory);
        if (!outputFolder.exists() && !outputFolder.mkdirs()) {
            logger.error("Não foi possível criar o diretório de saída: {}", outputDirectory);
            return;
        }

        String outputPath = new File(outputDirectory, outputFileName).getPath();
        try {
            serializeBatch(outputPath, groupLists);
            logger.info("Lista agrupada salva em: {}", outputPath);
        } catch (IOException e) {
            logger.error("Erro ao serializar as listas: {}", e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
	private List<T> deserialize(File file) throws IOException, ClassNotFoundException {
        // Implementação da desserialização com segurança de tipos.
        List<T> combinations = new ArrayList<>();
		try {
			combinations = (List<T>) Deserializer.deserialize(file.getPath());
		} catch (Exception e) {
			System.err.println("Erro ao converter o objeto no arquivo: " + file.getName());
			return new ArrayList<>();
		}
        return combinations;
    }

    private void serializeBatch(String outputPath, List<T> groupLists) throws IOException {
        Serializer.startBatchSerialization(outputPath);
        for (T combination : groupLists) {
            Serializer.serializeToBatch(combination);
        }
        Serializer.endBatchSerialization();
    }
}
