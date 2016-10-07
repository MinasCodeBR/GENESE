package analise;

import br.constapp.genese.analise.Quadraticos;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class QuadraticosTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        int numCombinacoes = 0;
        int size0Quadrado = 0;
        int size1Quadrado = 0;
        int size2Quadrados = 0;
        int size3Quadrados = 0;
        int size4Quadrados = 0;
        int size5Quadrados = 0;
        int size6Quadrados = 0;

        Quadraticos q = new Quadraticos(listaJogos);

        numCombinacoes += listaJogos.size();
        size0Quadrado += q.getLista0Quadrado().size();
        size1Quadrado += q.getLista1Quadrado().size();
        size2Quadrados += q.getLista2Quadrados().size();
        size3Quadrados += q.getLista3Quadrados().size();
        size4Quadrados += q.getLista4Quadrados().size();
        size5Quadrados += q.getLista5Quadrados().size();
        size6Quadrados += q.getLista6Quadrados().size();

        System.out.println(numCombinacoes);

        System.out.println("Jogos com 0 número quadrado: " + Calc.porcentagem(size0Quadrado, numCombinacoes));

        System.out.println("Jogos com 1 número quadrado: " + Calc.porcentagem(size1Quadrado, numCombinacoes));

        System.out.println("Jogos com 2 números quadrados: " + Calc.porcentagem(size2Quadrados, numCombinacoes));

        System.out.println("Jogos com 3 números quadrados: " + Calc.porcentagem(size3Quadrados, numCombinacoes));

        System.out.println("Jogos com 4 números quadrados: " + Calc.porcentagem(size4Quadrados, numCombinacoes));

        System.out.println("Jogos com 5 números quadrados: " + Calc.porcentagem(size5Quadrados, numCombinacoes));

        System.out.println("Jogos com 6 números quadrados: " + Calc.porcentagem(size6Quadrados, numCombinacoes));
    }
}
