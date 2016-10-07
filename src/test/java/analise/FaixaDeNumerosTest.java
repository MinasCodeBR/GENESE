package analise;

import br.constapp.genese.analise.FaixaDeNumeros;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class FaixaDeNumerosTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        for (int i = 1; i <= 6; i++) {
            FaixaDeNumeros fn = new FaixaDeNumeros(listaJogos, i);

            int numCombinacoes = listaJogos.size();
            int size0Grupo = fn.getLista0Grupo().size();
            int size1Grupo = fn.getLista1Grupo().size();
            int size2Grupo = fn.getLista2Grupo().size();
            int size3Grupo = fn.getLista3Grupo().size();
            int size4Grupo = fn.getLista4Grupo().size();
            int size5Grupo = fn.getLista5Grupo().size();
            int size6Grupo = fn.getLista6Grupo().size();

            System.out.println("Jogos com 0 número da " + i + "ª faixa: " + Calc.porcentagem(size0Grupo, numCombinacoes));

            System.out.println("Jogos com 1 número da " + i + "ª faixa: " + Calc.porcentagem(size1Grupo, numCombinacoes));

            System.out.println("Jogos com 2 números da " + i + "ª faixa: " + Calc.porcentagem(size2Grupo, numCombinacoes));

            System.out.println("Jogos com 3 números da " + i + "ª faixa: " + Calc.porcentagem(size3Grupo, numCombinacoes));

            System.out.println("Jogos com 4 números da " + i + "ª faixa: " + Calc.porcentagem(size4Grupo, numCombinacoes));

            System.out.println("Jogos com 5 números da " + i + "ª faixa: " + Calc.porcentagem(size5Grupo, numCombinacoes));

            System.out.println("Jogos com 6 números da " + i + "ª faixa: " + Calc.porcentagem(size6Grupo, numCombinacoes));

            System.out.println(" ");
        }

        System.out.println("1ª faixa = [1-10]; 2ª faixa = [11-20]; 3ª faixa = [21-30] ...");
    }
}
