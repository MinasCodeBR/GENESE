package analise;

import br.constapp.genese.analise.Primalidade;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class PrimalidadeTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        int numCombinacoes = 0;
        int size0Primo = 0;
        int size1Primo = 0;
        int size2Primos = 0;
        int size3Primos = 0;
        int size4Primos = 0;
        int size5Primos = 0;
        int size6Primos = 0;

        Primalidade primos = new Primalidade(listaJogos);

        numCombinacoes += listaJogos.size();
        size0Primo += primos.getLista0Primo().size();
        size1Primo += primos.getLista1Primo().size();
        size2Primos += primos.getLista2Primos().size();
        size3Primos += primos.getLista3Primos().size();
        size4Primos += primos.getLista4Primos().size();
        size5Primos += primos.getLista5Primos().size();
        size6Primos += primos.getLista6Primos().size();

        System.out.println(numCombinacoes);

        System.out.println("Jogos com 0 número primo: " + Calc.porcentagem(size0Primo, numCombinacoes));

        System.out.println("Jogos com 1 número primo: " + Calc.porcentagem(size1Primo, numCombinacoes));

        System.out.println("Jogos com 2 números primos: " + Calc.porcentagem(size2Primos, numCombinacoes));

        System.out.println("Jogos com 3 números primos: " + Calc.porcentagem(size3Primos, numCombinacoes));

        System.out.println("Jogos com 4 números primos: " + Calc.porcentagem(size4Primos, numCombinacoes));

        System.out.println("Jogos com 5 números primos: " + Calc.porcentagem(size5Primos, numCombinacoes));

        System.out.println("Jogos com 6 números primos: " + Calc.porcentagem(size6Primos, numCombinacoes));

    }

}
