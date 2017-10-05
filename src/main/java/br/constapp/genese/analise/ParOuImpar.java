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

package br.constapp.genese.analise;

import br.constapp.genese.analise.comparador.Contador;
import br.constapp.genese.jogo.modelo.Jogo;

import java.util.List;

public class ParOuImpar {

    private List<Jogo> lista0Par, lista1Par, lista2Pares, lista3Pares, lista4Pares, lista5Pares, lista6Pares;
    private List<Jogo> lista0Impar, lista1Impar, lista2Impares, lista3Impares, lista4Impares, lista5Impares, lista6Impares;
    private Integer[] dezenasPares, dezenasImpares;
    private int totalPares, totalImpares;



    public ParOuImpar(List<Jogo> listaJogos) {
        executaParOuImpar(listaJogos);
    }

    private void separaParImpar() {
        int indicePar = 0;
        int indiceImpar = 0;
        dezenasPares = new Integer[30];
        dezenasImpares = new Integer[30];
        for (int i = 1; i <= 60; i++) {
            if (i % 2 == 0) {
                dezenasPares[indicePar] = i;
                indicePar++;
            } else {
                dezenasImpares[indiceImpar] = i;
                indiceImpar++;
            }
        }
    }

    private void executaParOuImpar(List<Jogo> listaJogos) {
        Contador contador;

        separaParImpar();

        contador = new Contador(listaJogos, dezenasPares);

        lista0Par = contador.getLista0Incidencia();
        lista1Par = contador.getLista1Incidencia();
        lista2Pares = contador.getLista2Incidencias();
        lista3Pares = contador.getLista3Incidencias();
        lista4Pares = contador.getLista4Incidencias();
        lista5Pares = contador.getLista5Incidencias();
        lista6Pares = contador.getLista6Incidencias();

        totalPares = contador.getTotalGeral() / 6;

        contador = new Contador(listaJogos, dezenasImpares);

        lista0Impar = contador.getLista0Incidencia();
        lista1Impar = contador.getLista1Incidencia();
        lista2Impares = contador.getLista2Incidencias();
        lista3Impares = contador.getLista3Incidencias();
        lista4Impares = contador.getLista4Incidencias();
        lista5Impares = contador.getLista5Incidencias();
        lista6Impares = contador.getLista6Incidencias();


        totalImpares = contador.getTotalGeral() / 6;
    }

    public int getTotalPares() {
        return totalPares;
    }

    public int getTotalImpares() {
        return totalImpares;
    }

    public List<Jogo> getLista0Par() {
        return lista0Par;
    }

    public List<Jogo> getLista1Par() {
        return lista1Par;
    }

    public List<Jogo> getLista2Pares() {
        return lista2Pares;
    }

    public List<Jogo> getLista3Pares() {
        return lista3Pares;
    }

    public List<Jogo> getLista4Pares() {
        return lista4Pares;
    }

    public List<Jogo> getLista5Pares() {
        return lista5Pares;
    }

    public List<Jogo> getLista6Pares() {
        return lista6Pares;
    }

    public List<Jogo> getLista0Impar() {
        return lista0Impar;
    }

    public List<Jogo> getLista1Impar() {
        return lista1Impar;
    }

    public List<Jogo> getLista2Impares() {
        return lista2Impares;
    }

    public List<Jogo> getLista3Impares() {
        return lista3Impares;
    }

    public List<Jogo> getLista4Impares() {
        return lista4Impares;
    }

    public List<Jogo> getLista5Impares() {
        return lista5Impares;
    }

    public List<Jogo> getLista6Impares() {
        return lista6Impares;
    }
}
