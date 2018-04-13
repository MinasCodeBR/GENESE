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
 *
 *     Esta classe faz uso de recursos da biblioteca 'Combinatorics'.
 *     Combinatorics Library ou combinatoricslib é uma biblioteca
 *     sob licença da GNU LGPL, de autoria de Dmytro Paukov <d.paukov@gmail.com>.
 *     <https://github.com/dpaukov/combinatoricslib/blob/master/LICENSE>
 */

package br.constapp.genese.combinacoes;

import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.serialization.Serializador;
import br.constapp.genese.util.Calc;
import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FabricaDeCombinacoes {


    private FabricaDeCombinacoes() {}

    public static void criaCombinacao(Integer[] vetorInicial, String nomeArquivo) {


        List<Jogo> listaCombinacoes = new ArrayList<>();

        int elementos = 0;
        int numArquivo = 1;
        int index = 1;

        String arquivoSer = File.separator + nomeArquivo + "1";

        BigInteger temp = Calc.fatorial(vetorInicial.length).divide(Calc.fatorial(vetorInicial.length - 6));
        long qtdCombinacoes = temp.divide(Calc.fatorial(6)).longValue();
        long resto = qtdCombinacoes;

        // Cria o vetor inicial
        ICombinatoricsVector<Integer> initialVector = Factory.createVector(vetorInicial);

        // Cria um gerador de combinações simples para gerar combinações de 6 do vetor inicial
        Generator<Integer> gen = Factory.createSimpleCombinationGenerator(initialVector, 6);

        for (ICombinatoricsVector<Integer> combination : gen) {

            Jogo jogo = new Jogo();
            final Pattern pat = Pattern.compile("[0-9]+");
            final Matcher mat = pat.matcher(combination.toString());
            int i = 1;
            while (mat.find() && i < 7) {
                switch (i) {
                    case 1:
                        jogo.setPrimeiraDezena(Integer.valueOf(mat.group()));
                    case 2:
                        jogo.setSegundaDezena(Integer.valueOf(mat.group()));
                    case 3:
                        jogo.setTerceiraDezena(Integer.valueOf(mat.group()));
                    case 4:
                        jogo.setQuartaDezena(Integer.valueOf(mat.group()));
                    case 5:
                        jogo.setQuintaDezena(Integer.valueOf(mat.group()));
                    case 6:
                        jogo.setSextaDezena(Integer.valueOf(mat.group()));
                }
                i++;
            }
            
            jogo.setConcurso(index);
            
            if (index < 1000000) {
            	index++;
            } else {
            	index = 1;
            }
            
            listaCombinacoes.add(jogo);
            elementos++;
            qtdCombinacoes--;
            System.out.println(qtdCombinacoes);

            if (resto > 0) {
                if (elementos == 1000000) {
                    try {
                        Serializador.serializa(DefineDiretorio.getDiretorioCombinacoes() + arquivoSer +
                                ".ser", listaCombinacoes);

                    } catch (Exception ex) {
                        System.err.println("Falha ao serializar - " + ex.toString());
                    }
                    listaCombinacoes.clear();
                    numArquivo++;
                    arquivoSer = File.separator + nomeArquivo + numArquivo;
                    elementos = 0;
                    resto = qtdCombinacoes - 1000000;
                } else {
                    if (elementos == listaCombinacoes.size() && qtdCombinacoes == 0) {
                        try {
                            Serializador.serializa(DefineDiretorio.getDiretorioCombinacoes() + arquivoSer +
                                    ".ser", listaCombinacoes);

                        } catch (Exception ex) {
                            System.err.println("Falha ao serializar - " + ex.toString());
                        }
                    }
                }
            } else {
                if (qtdCombinacoes == 0) {
                    try {
                        Serializador.serializa(DefineDiretorio.getDiretorioCombinacoes() + arquivoSer +
                                ".ser", listaCombinacoes);

                    } catch (Exception ex) {
                        System.err.println("Falha ao serializar - " + ex.toString());
                    }
                    listaCombinacoes.clear();
                }
            }
        }
    }
}
