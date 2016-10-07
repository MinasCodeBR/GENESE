/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2016  Rafael Teixeira
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

public class Multiplicidade {

    private List<Jogo> lista0Multiplo, lista1Multiplo, lista2Multiplos, lista3Multiplos, lista4Multiplos,
            lista5Multiplos, lista6Multiplos;
    private int multiplosDe;

    public Multiplicidade(List<Jogo> listaJogos, int multiplosDe) {
        executaMultiplicidade(listaJogos, multiplosDe);
    }

    private Integer[] defineMultiplosDe(int multiplosDe) {

        int elementos = (60 / multiplosDe);
        Integer[] multiplos = new Integer[elementos];
        Integer resultado = 0;
        for (int i = 0; i < elementos; i++) {
            resultado += multiplosDe;
            multiplos[i] = resultado;
        }
        return multiplos;
    }

    private void executaMultiplicidade(List<Jogo> listaJogos, int multiplosDe) {
        this.multiplosDe = multiplosDe;
        Contador contador = new Contador(listaJogos, defineMultiplosDe(multiplosDe));

        lista0Multiplo = contador.getLista0Incidencia();
        lista1Multiplo = contador.getLista1Incidencia();
        lista2Multiplos = contador.getLista2Incidencias();
        lista3Multiplos = contador.getLista3Incidencias();
        lista4Multiplos = contador.getLista4Incidencias();
        lista5Multiplos = contador.getLista5Incidencias();
        lista6Multiplos = contador.getLista6Incidencias();

    }

    public int getMultiplosDe() {
        return multiplosDe;
    }

    public List<Jogo> getLista0Multiplo() {
        return lista0Multiplo;
    }

    public List<Jogo> getLista1Multiplo() {
        return lista1Multiplo;
    }

    public List<Jogo> getLista2Multiplos() {
        return lista2Multiplos;
    }

    public List<Jogo> getLista3Multiplos() {
        return lista3Multiplos;
    }

    public List<Jogo> getLista4Multiplos() {
        return lista4Multiplos;
    }

    public List<Jogo> getLista5Multiplos() {
        return lista5Multiplos;
    }

    public List<Jogo> getLista6Multiplos() {
        return lista6Multiplos;
    }

}
