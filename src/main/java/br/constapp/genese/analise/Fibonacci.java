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

public class Fibonacci {

    private static int[] vetAux = new int[11];
    private static int k;
    private List<Jogo> lista0Fibonacci, lista1Fibonacci, lista2Fibonacci, lista3Fibonacci, lista4Fibonacci, lista5Fibonacci,
            lista6Fibonacci;
    private Integer[] vetFibonacci;

    public Fibonacci(List<Jogo> listaJogos) {
        executaFibonacci(listaJogos);
    }


    private static Integer fibo(int n) {
        k = 1;
        return recursao(n);
    }

    private static Integer recursao(int n) {
        if (n < 0) {
            return vetAux[0];
        } else {
            if (k < 3) {
                vetAux[n] = k - 1;
                k++;
            } else {
                vetAux[n] = vetAux[n + 1] + vetAux[n + 2];
            }
            return recursao(n - 1);
        }
    }

    private void executaFibonacci(List<Jogo> listaJogos) {

        vetFibonacci = new Integer[9];
        int index = 0;
        for (int i = 2; i <= 10; i++) {
            vetFibonacci[index] = fibo(i);
            index++;
        }

        Contador contador = new Contador(listaJogos, vetFibonacci);

        lista0Fibonacci = contador.getLista0Incidencia();
        lista1Fibonacci = contador.getLista1Incidencia();
        lista2Fibonacci = contador.getLista2Incidencias();
        lista3Fibonacci = contador.getLista3Incidencias();
        lista4Fibonacci = contador.getLista4Incidencias();
        lista5Fibonacci = contador.getLista5Incidencias();
        lista6Fibonacci = contador.getLista6Incidencias();
    }

    public List<Jogo> getLista0Fibonacci() {
        return lista0Fibonacci;
    }

    public List<Jogo> getLista1Fibonacci() {
        return lista1Fibonacci;
    }

    public List<Jogo> getLista2Fibonacci() {
        return lista2Fibonacci;
    }

    public List<Jogo> getLista3Fibonacci() {
        return lista3Fibonacci;
    }

    public List<Jogo> getLista4Fibonacci() {
        return lista4Fibonacci;
    }

    public List<Jogo> getLista5Fibonacci() {
        return lista5Fibonacci;
    }

    public List<Jogo> getLista6Fibonacci() {
        return lista6Fibonacci;
    }

	public Integer[] getVetFibonacci() {
		return vetFibonacci;
	}
}
