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

public class Quadraticos {

    private List<Jogo> lista0Quadrado, lista1Quadrado, lista2Quadrados, lista3Quadrados, lista4Quadrados,
            lista5Quadrados, lista6Quadrados;

    public Quadraticos(List<Jogo> listaJogos) {
        executaQuadraticos(listaJogos);
    }

    private Integer[] defineNumerosQuadrados() {
        Integer[] numerosQuadrados = new Integer[7];
        for (int n = 1; n <= 7; n++) {
            numerosQuadrados[n - 1] = (int) Math.pow(n, 2);
        }
        return numerosQuadrados;
    }

    private void executaQuadraticos(List<Jogo> listaJogos) {

        Contador contador = new Contador(listaJogos, defineNumerosQuadrados());

        lista0Quadrado = contador.getLista0Incidencia();
        lista1Quadrado = contador.getLista1Incidencia();
        lista2Quadrados = contador.getLista2Incidencias();
        lista3Quadrados = contador.getLista3Incidencias();
        lista4Quadrados = contador.getLista4Incidencias();
        lista5Quadrados = contador.getLista5Incidencias();
        lista6Quadrados = contador.getLista6Incidencias();

    }

    public List<Jogo> getLista0Quadrado() {
        return lista0Quadrado;
    }

    public List<Jogo> getLista1Quadrado() {
        return lista1Quadrado;
    }

    public List<Jogo> getLista2Quadrados() {
        return lista2Quadrados;
    }

    public List<Jogo> getLista3Quadrados() {
        return lista3Quadrados;
    }

    public List<Jogo> getLista4Quadrados() {
        return lista4Quadrados;
    }

    public List<Jogo> getLista5Quadrados() {
        return lista5Quadrados;
    }

    public List<Jogo> getLista6Quadrados() {
        return lista6Quadrados;
    }

}
