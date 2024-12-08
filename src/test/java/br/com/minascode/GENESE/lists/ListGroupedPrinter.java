package br.com.minascode.GENESE.lists;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.entities.MegaSena;
import br.com.minascode.GENESE.path.DefineDirectory;
import br.com.minascode.GENESE.serialization.Deserializer;

public class ListGroupedPrinter<T extends ConfigurableCombination> {

    private static final Logger logger = LoggerFactory.getLogger(ListGroupedPrinter.class);

    private final String filterDirectory;
    private final String fileName;

    public ListGroupedPrinter(String filterDirectory, String fileName) {
        this.filterDirectory = filterDirectory;
        this.fileName = fileName;
    }

    public void printList() throws Exception {
        File file = new File(filterDirectory + File.separator + fileName);

        if (!file.exists()) {
            logger.error("O arquivo não existe: {}", file.getAbsolutePath());
            return;
        }

        try {
            Object deserializedObject = Deserializer.deserialize(file.getPath());

            if (deserializedObject instanceof List) {
                @SuppressWarnings("unchecked")
                List<T> combinations = (List<T>) deserializedObject;

                if (combinations == null || combinations.isEmpty()) {
                    System.out.println("A lista está vazia ou nula.");
                    return;
                }

                System.out.println("Lista de Combinações:");
                for (T combination : combinations) {
                    System.out.println(combination);
                }
            } else {
                logger.error("O arquivo não contém uma lista. Tipo encontrado: {}", deserializedObject.getClass().getName());
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Erro ao desserializar o arquivo {}: {}", file.getName(), e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        String filterDirectory = DefineDirectory.getFilterDirectory() + File.separator + "lista unica";
        String fileName = "megasena_combinations.ser"; // Substitua pelo nome real do arquivo serializado

        ListGroupedPrinter<MegaSena> listPrinter = new ListGroupedPrinter<>(filterDirectory, fileName);
        try {
			listPrinter.printList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
