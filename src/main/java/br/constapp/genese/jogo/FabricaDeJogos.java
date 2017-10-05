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

import br.constapp.genese.jogo.modelo.Jogo;

import java.util.ArrayList;
import java.util.List;

public class FabricaDeJogos {

    private static List<Jogo> listaJogos;

    private FabricaDeJogos() {}

    public static void criaJogo(List<String> listaSorteios) {

        Jogo jogo = new Jogo();
        listaJogos = new ArrayList<>();
        listaJogos.add(jogo);

        int linha = 0;

        for (int u = 0; u < listaSorteios.size(); u++) {

            if (linha > 17) {
                linha = 0;
                jogo = new Jogo();
                listaJogos.add(jogo);
            }

            switch (linha) {
                case 0:
                    jogo.setConcurso(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 1:
                    jogo.setDataSorteio(listaSorteios.get(u));
                    break;
                case 2:
                    jogo.setPrimeiraDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 3:
                    jogo.setSegundaDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 4:
                    jogo.setTerceiraDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 5:
                    jogo.setQuartaDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 6:
                    jogo.setQuintaDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 7:
                    jogo.setSextaDezena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 9:
                    jogo.setNumGanhadoresSena(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 10:
                    jogo.setRateioSena((listaSorteios.get(u)));
                    break;
                case 11:
                    jogo.setNumGanhadoresQuina(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 12:
                    jogo.setRateioQuina((listaSorteios.get(u)));
                    break;
                case 13:
                    jogo.setNumGanhadoresQuadra(Integer.parseInt(listaSorteios.get(u)));
                    break;
                case 14:
                    jogo.setRateioQuadra((listaSorteios.get(u)));
                    break;
                case 15:
                    jogo.setEstimativaPremio((listaSorteios.get(u)));
                    break;
            }
            linha++;
        }
    }

    public static List<Jogo> getListaJogos() {
        if (listaJogos != null) {
            return new ArrayList<>(listaJogos);
        }
        return new ArrayList<>();
    }
}
