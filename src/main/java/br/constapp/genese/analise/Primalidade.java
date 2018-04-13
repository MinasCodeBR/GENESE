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

import java.util.ArrayList;
import java.util.List;

public class Primalidade {

    private List<Jogo> lista0Primo, lista1Primo, lista2Primos, lista3Primos, lista4Primos, lista5Primos, lista6Primos;

    public Primalidade(List<Jogo> listaJogos) {
        executaPrimalidade(listaJogos);
    }

    public Integer[] defineNumerosPrimos() {

        List<Integer> dezenas = new ArrayList<>();
        Integer[] primos = new Integer[17];

        for (int i = 0; i < 60; i++) {
            dezenas.add(i + 1);
        }

        int contador;
        int numero = 0;
        int index = 0;

        for (Integer i : dezenas) {
            contador = 0;
            for (int u = 1; u <= i; u++) {
                if (i % u == 0) {
                    contador++;
                    numero = u;
                }
            }
            if (contador == 2) {
                primos[index] = numero;
                index++;
            }
        }
        return primos;
    }

    private void executaPrimalidade(List<Jogo> listaJogos) {

        Contador contador = new Contador(listaJogos, defineNumerosPrimos());

        lista0Primo = contador.getLista0Incidencia();
        lista1Primo = contador.getLista1Incidencia();
        lista2Primos = contador.getLista2Incidencias();
        lista3Primos = contador.getLista3Incidencias();
        lista4Primos = contador.getLista4Incidencias();
        lista5Primos = contador.getLista5Incidencias();
        lista6Primos = contador.getLista6Incidencias();

    }

    public List<Jogo> getLista0Primo() {
        return lista0Primo;
    }

    public List<Jogo> getLista1Primo() {
        return lista1Primo;
    }

    public List<Jogo> getLista2Primos() {
        return lista2Primos;
    }

    public List<Jogo> getLista3Primos() {
        return lista3Primos;
    }

    public List<Jogo> getLista4Primos() {
        return lista4Primos;
    }

    public List<Jogo> getLista5Primos() {
        return lista5Primos;
    }

    public List<Jogo> getLista6Primos() {
        return lista6Primos;
    }

}
