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

package br.constapp.genese.analise.comparador;

import br.constapp.genese.jogo.modelo.Jogo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contador {

    private List<Jogo> listaGeral, listaZero;
    private List<Jogo> lista0Incidencia, lista1Incidencia, lista2Incidencias, lista3Incidencias, lista4Incidencias,
            lista5Incidencias, lista6Incidencias;
    private Integer[] vetGenerico;
    private int totalGeral;

    public Contador(List<Jogo> listaJogos, Integer[] vetGenerico) {

        this.vetGenerico = vetGenerico;

        executaContador(listaJogos);

    }

    private void criaListaGeral(List<Jogo> listaJogos) {
        listaGeral = new ArrayList<>();
        listaZero = new ArrayList<>();

        for (Jogo jogo : listaJogos) {
            for (int i = 0; i < vetGenerico.length; i++) {

                if (jogo.getPrimeiraDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getSegundaDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getTerceiraDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getQuartaDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getQuintaDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getSextaDezena() == vetGenerico[i]) {
                    listaGeral.add(jogo);
                }

                if (jogo.getPrimeiraDezena() != vetGenerico[i] && jogo.getSegundaDezena() != vetGenerico[i]
                        && jogo.getTerceiraDezena() != vetGenerico[i] && jogo.getQuartaDezena() != vetGenerico[i]
                        && jogo.getQuintaDezena() != vetGenerico[i] && jogo.getSextaDezena() != vetGenerico[i]) {
                    listaZero.add(jogo);
                }
            }
        }
    }

    private void contaListaGeral(List<Jogo> listaJogos, int incidencia) {

        Map<Integer, Integer> contador = new HashMap<>();
        for (Jogo jogo : listaGeral) {

            if (!contador.containsKey(jogo.getConcurso())) {
                contador.put(jogo.getConcurso(), 0);
            }
            contador.put(jogo.getConcurso(), contador.get(jogo.getConcurso()) + 1);
        }

        for (Map.Entry<Integer, Integer> item : contador.entrySet()) {
            if (item.getValue() == incidencia) {

                if (incidencia == 1) {
                    lista1Incidencia.add(listaJogos.get(item.getKey() - 1));
                }

                if (incidencia == 2) {
                    lista2Incidencias.add(listaJogos.get(item.getKey() - 1));
                }

                if (incidencia == 3) {
                    lista3Incidencias.add(listaJogos.get(item.getKey() - 1));
                }

                if (incidencia == 4) {
                    lista4Incidencias.add(listaJogos.get(item.getKey() - 1));
                }

                if (incidencia == 5) {
                    lista5Incidencias.add(listaJogos.get(item.getKey() - 1));
                }

                if (incidencia == 6) {
                    lista6Incidencias.add(listaJogos.get(item.getKey() - 1));
                }
            }
        }
    }

    private void contaListaZero(List<Jogo> listaJogos) {

        Map<Integer, Integer> contadorZero = new HashMap<>();
        for (Jogo jogo : listaZero) {

            if (!contadorZero.containsKey(jogo.getConcurso())) {
                contadorZero.put(jogo.getConcurso(), 0);
            }
            contadorZero.put(jogo.getConcurso(), contadorZero.get(jogo.getConcurso()) + 1);
        }

        for (Map.Entry<Integer, Integer> item : contadorZero.entrySet()) {
            if (item.getValue() == vetGenerico.length) {

                lista0Incidencia.add(listaJogos.get(item.getKey() - 1));
            }
        }
    }

    private void executaContador(List<Jogo> listaJogos) {

        lista0Incidencia = new ArrayList<>();
        lista1Incidencia = new ArrayList<>();
        lista2Incidencias = new ArrayList<>();
        lista3Incidencias = new ArrayList<>();
        lista4Incidencias = new ArrayList<>();
        lista5Incidencias = new ArrayList<>();
        lista6Incidencias = new ArrayList<>();

        criaListaGeral(listaJogos);

        for (int i = 1; i <= 6; i++) {
            contaListaGeral(listaJogos, i);
        }
        contaListaZero(listaJogos);

        totalGeral = listaGeral.size();
    }

    public List<Jogo> getLista0Incidencia() {
        return lista0Incidencia;
    }

    public List<Jogo> getLista1Incidencia() {
        return lista1Incidencia;
    }

    public List<Jogo> getLista2Incidencias() {
        return lista2Incidencias;
    }

    public List<Jogo> getLista3Incidencias() {
        return lista3Incidencias;
    }

    public List<Jogo> getLista4Incidencias() {
        return lista4Incidencias;
    }

    public List<Jogo> getLista5Incidencias() {
        return lista5Incidencias;
    }

    public List<Jogo> getLista6Incidencias() {
        return lista6Incidencias;
    }

    public int getTotalGeral() { return totalGeral; }
}
