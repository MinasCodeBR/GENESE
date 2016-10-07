import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.modelo.Jogo;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 09/09/2016.
 */
public class FabricaDeJogosTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();

        FabricaDeJogos.criaJogo(scan.getListaSorteios());

        List<Jogo> lista = FabricaDeJogos.getListaJogos();
        lista.forEach(l -> System.out.println(l.getConcurso() + " " + l.getDataSorteio() + " " +
                l.getPrimeiraDezena() + " " + l.getSegundaDezena() + " " + l.getTerceiraDezena() +
        " " + l.getQuartaDezena() + " " + l.getQuintaDezena() + " " + l.getSextaDezena()));

    }

}
