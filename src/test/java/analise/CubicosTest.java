package analise;

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class CubicosTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        int numCombinacoes = 0;
        int size0Cubo = 0;
        int size1Cubo = 0;
        int size2Cubos = 0;
        int size3Cubos = 0;

        Cubicos c = new Cubicos(listaJogos);

        numCombinacoes += listaJogos.size();
        size0Cubo += c.getLista0Cubico().size();
        size1Cubo += c.getLista1Cubico().size();
        size2Cubos += c.getLista2Cubicos().size();
        size3Cubos += c.getLista3Cubicos().size();

        System.out.println(numCombinacoes);

        System.out.println("Jogos com 0 número cubico: " + Calc.porcentagem(size0Cubo, numCombinacoes));

        System.out.println("Jogos com 1 número cubico: " + Calc.porcentagem(size1Cubo, numCombinacoes));

        System.out.println("Jogos com 2 números cubicos: " + Calc.porcentagem(size2Cubos, numCombinacoes));

        System.out.println("Jogos com 3 números cubicos: " + Calc.porcentagem(size3Cubos, numCombinacoes));

    }
}
