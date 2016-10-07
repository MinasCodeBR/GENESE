package analise;

import br.constapp.genese.analise.JogoRepetido;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;

import java.util.List;

/**
 * Criado por Rafael Teixeira
 * em 11/09/2016.
 */
public class JogoRepetidoTest {

    public static void main(String[] args) {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        JogoRepetido.buscaJogoRepetido(listaJogos);

    }
}
