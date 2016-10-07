import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.util.DownloadAndUnzip;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Criado por Rafael Teixeira
 * em 04/09/2016.
 */
public class DownloadAndUnzipTest {

    private static URL url;

    public static void main(String[] args) {


        File targetDir = new File(DefineDiretorio.getDiretorioCEF());

        try {
            url = new URL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_mgsasc.zip");
        } catch (MalformedURLException e1) {
            System.err.println("Ocorreu um problema com o link(URL) para download dos jogos - " + e1.toString());
        }

        try {
            DownloadAndUnzip.downloadArquivo(url, targetDir);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
