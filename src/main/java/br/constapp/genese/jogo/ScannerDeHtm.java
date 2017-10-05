/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2017  Rafael Teixeira
 *     rafaelfst@live.com
 *
 *     GENESE é um software livre: você pode redistribuí-lo e/ou modificá-lo
 *     dentro dos termos da Licença Pública Geral GNU como publicada pela
 *     Fundação do Software Livre (FSF), na versão 3 da Licença, ou
 *     (na sua opinião) qualquer versão posterior.
 *
 *     Este programa é distribuído na esperança de que possa ser útil,
 *     mas SEM NENHUMA GARANTIA; sem uma garantia implícita de ADEQUAÇÃO
 *     a qualquer MERCADO ou APLICAÇÃO EM PARTICULAR. Veja a
 *     Licença Pública Geral GNU para maiores detalhes.
 *
 *     Você deve ter recebido uma cópia da Licença Pública Geral GNU junto
 *     com este programa. Se não, veja <http://www.gnu.org/licenses/>.
 */

package br.constapp.genese.jogo;

import br.constapp.genese.path.DefineDiretorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ScannerDeHtm {

    private static List<String> listaHtmFile;
    private static List<String> listaSorteios;

    public ScannerDeHtm() {
        try {
            scanHtmFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void scanHtmFile() throws IOException {

        List<String> listaFiltrada;

        File arquivoHtm = new File(DefineDiretorio.getDiretorioCEF() + "d_megasc.htm");

        try {
            Scanner scanner = new Scanner(arquivoHtm.toPath());

            HtmToList(scanner);

            listaFiltrada = listaHtmFile
                    .stream()
                    .filter(s -> s.startsWith("<td rowspan="))
                    .map(s -> s.substring(s.lastIndexOf("\"") + 2, s.lastIndexOf("</td>")))
                    .collect(Collectors.toList());

            listaFiltrada
                    .removeIf(p -> p.equalsIgnoreCase("&nbsp") || p.equalsIgnoreCase("N�O") || p.equalsIgnoreCase("NÃO") || p.equalsIgnoreCase("SIM"));

            listaSorteios = listaFiltrada;

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo " + arquivoHtm.getAbsolutePath() + " não encontrado - " + e.toString());
        }
    }

    private static void HtmToList(Scanner scanner) {
        listaHtmFile = new ArrayList<>();
        String linha;

        while (scanner.hasNextLine()){
            linha = scanner.nextLine();
            if (!linha.isEmpty()){
                listaHtmFile.add(linha);
            }
        }
        scanner.close();
    }

    public List<String> getListaSorteios() {
        if (listaSorteios != null) {
            return new ArrayList<>(listaSorteios);
        }
        return new ArrayList<>();
    }
}


