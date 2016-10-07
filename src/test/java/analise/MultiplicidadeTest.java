package analise;

import br.constapp.genese.analise.Multiplicidade;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class MultiplicidadeTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        for (int i = 2; i <= 60; i++) {

            Multiplicidade m = new Multiplicidade(listaJogos, i);

            int numCombinacoes = listaJogos.size();
            int size0Multiplo = m.getLista0Multiplo().size();
            int size1Multiplo = m.getLista1Multiplo().size();
            int size2Multiplos = m.getLista2Multiplos().size();
            int size3Multiplos = m.getLista3Multiplos().size();
            int size4Multiplos = m.getLista4Multiplos().size();
            int size5Multiplos = m.getLista5Multiplos().size();
            int size6Multiplos = m.getLista6Multiplos().size();


            System.out.println("Jogos com 0 múltiplo de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size0Multiplo, numCombinacoes));

            System.out.println("Jogos com 1 múltiplo de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size1Multiplo, numCombinacoes));

            System.out.println("Jogos com 2 múltiplos de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size2Multiplos, numCombinacoes));

            System.out.println("Jogos com 3 múltiplos de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size3Multiplos, numCombinacoes));

            System.out.println("Jogos com 4 múltiplos de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size4Multiplos, numCombinacoes));

            System.out.println("Jogos com 5 múltiplos de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size5Multiplos, numCombinacoes));

            System.out.println("Jogos com 6 múltiplos de " + m.getMultiplosDe() + ": "
                    + Calc.porcentagem(size6Multiplos, numCombinacoes));

            System.out.println(" ");
        }

    }
}
