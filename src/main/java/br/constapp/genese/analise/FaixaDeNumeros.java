/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2018  Rafael Teixeira
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

package br.constapp.genese.analise;

import br.constapp.genese.analise.comparador.Contador;
import br.constapp.genese.jogo.modelo.Jogo;

import java.util.List;

public class FaixaDeNumeros {

    private List<Jogo> lista0Grupo, lista1Grupo, lista2Grupo, lista3Grupo, lista4Grupo, lista5Grupo, lista6Grupo;

    public FaixaDeNumeros(List<Jogo> listaJogos, int faixa) {

        executaFaixaDeNumeros(listaJogos, faixa);
    }

    private Integer[] defineGrupo(int faixa) {

        Integer[] grupo = new Integer[10];
        int teto = faixa * 10;
        int base = teto - 9;

        for (int i = base; i < teto;) {
            for (int u = 0; u < grupo.length; u++) {
                grupo[u] = i++;
            }
            break;
        }
        return grupo;
    }

    private void executaFaixaDeNumeros(List<Jogo> listaJogos, int faixa) {


        Contador contador = new Contador(listaJogos, defineGrupo(faixa));

        lista0Grupo = contador.getLista0Incidencia();
        lista1Grupo = contador.getLista1Incidencia();
        lista2Grupo = contador.getLista2Incidencias();
        lista3Grupo = contador.getLista3Incidencias();
        lista4Grupo = contador.getLista4Incidencias();
        lista5Grupo = contador.getLista5Incidencias();
        lista6Grupo = contador.getLista6Incidencias();

    }

    public List<Jogo> getLista0Grupo() {
        return lista0Grupo;
    }

    public List<Jogo> getLista1Grupo() {
        return lista1Grupo;
    }

    public List<Jogo> getLista2Grupo() {
        return lista2Grupo;
    }

    public List<Jogo> getLista3Grupo() {
        return lista3Grupo;
    }

    public List<Jogo> getLista4Grupo() {
        return lista4Grupo;
    }

    public List<Jogo> getLista5Grupo() {
        return lista5Grupo;
    }

    public List<Jogo> getLista6Grupo() {
        return lista6Grupo;
    }
}
