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

import br.constapp.genese.jogo.modelo.Jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JogoRepetido {

    private JogoRepetido() {}

    public static void buscaJogoRepetido(List<Jogo> listaJogos) {

        List<String> jogosRepetidos = new ArrayList<>();
        List<String> dezenas = new ArrayList<>();

        listaJogos.forEach(jogo -> dezenas.add(String.valueOf(jogo)));

        Map<String, Integer> contador = new HashMap<>();
        for (String x : dezenas) {

            if (!contador.containsKey(x)) {
                contador.put(x, 0);
            }
            contador.put(x, contador.get(x) + 1);
        }

        for (Map.Entry<String, Integer> item : contador.entrySet()) {
            if (item.getValue() >= 2) {
                jogosRepetidos.add(item.getKey());
                System.out.println("Jogo repetido encontrado!\n" + "(" + item.getValue() + "x) " + item.getKey());
            }
        }
        if (jogosRepetidos.size() == 0) {
            System.out.println("Sem repetições!");
        }

    }

}
