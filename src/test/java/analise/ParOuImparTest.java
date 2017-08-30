package analise;

import br.constapp.genese.analise.ParOuImpar;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class ParOuImparTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        long totalPares = 0;
        long totalImpares = 0;
        int numCombinacoes = 0;
        int size0Par = 0;
        int size1Par = 0;
        int size2Pares = 0;
        int size3Pares = 0;
        int size4Pares = 0;
        int size5Pares = 0;
        int size6Pares = 0;
        int size0Impar = 0;
        int size1Impar = 0;
        int size2Impares = 0;
        int size3Impares = 0;
        int size4Impares = 0;
        int size5Impares = 0;
        int size6Impares = 0;

        ParOuImpar pi = new ParOuImpar(listaJogos);

        totalPares += pi.getTotalPares();
        totalImpares += pi.getTotalImpares();
        numCombinacoes += listaJogos.size();
        size0Par += pi.getLista0Par().size();
        size1Par += pi.getLista1Par().size();
        size2Pares += pi.getLista2Pares().size();
        size3Pares += pi.getLista3Pares().size();
        size4Pares += pi.getLista4Pares().size();
        size5Pares += pi.getLista5Pares().size();
        size6Pares += pi.getLista6Pares().size();
        size0Impar += pi.getLista0Impar().size();
        size1Impar += pi.getLista1Impar().size();
        size2Impares += pi.getLista2Impares().size();
        size3Impares += pi.getLista3Impares().size();
        size4Impares += pi.getLista4Impares().size();
        size5Impares += pi.getLista5Impares().size();
        size6Impares += pi.getLista6Impares().size();

        System.out.println(numCombinacoes);

        System.out.println("Total de números pares: " + totalPares);

        System.out.println("Total de números impares: " + totalImpares);

        System.out.println();

        System.out.println("Jogos com 0 número  par:   " + Calc.porcentagem(size0Par, numCombinacoes));

        System.out.println("Jogos com 1 número  par:   " + Calc.porcentagem(size1Par, numCombinacoes));

        System.out.println("Jogos com 2 números pares: " + Calc.porcentagem(size2Pares, numCombinacoes));

        System.out.println("Jogos com 3 números pares: " + Calc.porcentagem(size3Pares, numCombinacoes));

        System.out.println("Jogos com 4 números pares: " + Calc.porcentagem(size4Pares, numCombinacoes));

        System.out.println("Jogos com 5 números pares: " + Calc.porcentagem(size5Pares, numCombinacoes));

        System.out.println("Jogos com 6 números pares: " + Calc.porcentagem(size6Pares, numCombinacoes));

        System.out.println();

        System.out.println("Jogos com 0 número  impar:   " + Calc.porcentagem(size0Impar, numCombinacoes));

        System.out.println("Jogos com 1 número  impar:   " + Calc.porcentagem(size1Impar, numCombinacoes));

        System.out.println("Jogos com 2 números impares: " + Calc.porcentagem(size2Impares, numCombinacoes));

        System.out.println("Jogos com 3 números impares: " + Calc.porcentagem(size3Impares, numCombinacoes));

        System.out.println("Jogos com 4 números impares: " + Calc.porcentagem(size4Impares, numCombinacoes));

        System.out.println("Jogos com 5 números impares: " + Calc.porcentagem(size5Impares, numCombinacoes));

        System.out.println("Jogos com 6 números impares: " + Calc.porcentagem(size6Impares, numCombinacoes));

    }
}
