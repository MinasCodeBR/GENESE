import br.constapp.genese.combinacoes.FabricaDeCombinacoes;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.serialization.Deserializador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Criado por rafael
 * em 25/09/16.
 */
public class CombinacoesTest {

    static Integer[] numeros;
    private static List<Jogo> combinacoes;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        combinacoes = new ArrayList<Jogo>();

        numeros = new Integer[60];
        for (int i = 0; i < 60; i++) {
            numeros[i] = i + 1;
        }

        FabricaDeCombinacoes.criaCombinacao(numeros, "lista_");

        try {
            combinacoes = (List<Jogo>) Deserializador.deserializa(DefineDiretorio.getDiretorioCombinacoes() +
                    File.separator + "lista_51.ser");
            for (Jogo jogo : combinacoes) {
                System.out.println(jogo);
            }
            System.out.println(combinacoes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
