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

public class Cubicos {

    private List<Jogo> lista0Cubico, lista1Cubico, lista2Cubicos, lista3Cubicos;

    public Cubicos(List<Jogo> listaJogos) {
        executaCubicos(listaJogos);
    }

    public static Integer[] defineNumerosCubicos() {
        Integer[] numerosCubicos = new Integer[3];
        for (int n = 1; n <= 3; n++) {
            numerosCubicos[n - 1] = (int) Math.pow(n, 3);
        }
        return numerosCubicos;
    }

    private void executaCubicos(List<Jogo> listaJogos) {

        Contador contador = new Contador(listaJogos, defineNumerosCubicos());

        lista0Cubico = contador.getLista0Incidencia();
        lista1Cubico = contador.getLista1Incidencia();
        lista2Cubicos = contador.getLista2Incidencias();
        lista3Cubicos = contador.getLista3Incidencias();
        
        

    }

    public List<Jogo> getLista0Cubico() {
        return lista0Cubico;
    }

    public List<Jogo> getLista1Cubico() {
        return lista1Cubico;
    }

    public List<Jogo> getLista2Cubicos() {
        return lista2Cubicos;
    }

    public List<Jogo> getLista3Cubicos() {
        return lista3Cubicos;
    }
}
