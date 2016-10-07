package analise;

import br.constapp.genese.analise.Fibonacci;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class FibonacciTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        int numCombinacoes = 0;
        int size0Fibonacci = 0;
        int size1Fibonacci = 0;
        int size2Fibonacci = 0;
        int size3Fibonacci = 0;
        int size4Fibonacci = 0;
        int size5Fibonacci = 0;
        int size6Fibonacci = 0;

        Fibonacci f = new Fibonacci(listaJogos);

        numCombinacoes += listaJogos.size();
        size0Fibonacci += f.getLista0Fibonacci().size();
        size1Fibonacci += f.getLista1Fibonacci().size();
        size2Fibonacci += f.getLista2Fibonacci().size();
        size3Fibonacci += f.getLista3Fibonacci().size();
        size4Fibonacci += f.getLista4Fibonacci().size();
        size5Fibonacci += f.getLista5Fibonacci().size();
        size6Fibonacci += f.getLista6Fibonacci().size();

        System.out.println(numCombinacoes);

        System.out.println("Jogos com 0 número fibonacci: " + Calc.porcentagem(size0Fibonacci, numCombinacoes));

        System.out.println("Jogos com 1 número fibonacci: " + Calc.porcentagem(size1Fibonacci, numCombinacoes));

        System.out.println("Jogos com 2 números fibonacci: " + Calc.porcentagem(size2Fibonacci, numCombinacoes));

        System.out.println("Jogos com 3 números fibonacci: " + Calc.porcentagem(size3Fibonacci, numCombinacoes));

        System.out.println("Jogos com 4 números fibonacci: " + Calc.porcentagem(size4Fibonacci, numCombinacoes));

        System.out.println("Jogos com 5 números fibonacci: " + Calc.porcentagem(size5Fibonacci, numCombinacoes));

        System.out.println("Jogos com 6 números fibonacci: " + Calc.porcentagem(size6Fibonacci, numCombinacoes));

    }
}
