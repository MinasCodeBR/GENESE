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

package br.constapp.genese.processamento;

import br.constapp.genese.analise.FaixaDeNumeros;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.List;

public class FaixaDeNumerosPro {

    public static void processaFaixaDeNumeros() {

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        for (int i = 1; i <= 6; i++) {
            FaixaDeNumeros fn = new FaixaDeNumeros(listaJogos, i);

            int numCombinacoes = listaJogos.size();
            int size0Grupo = fn.getLista0Grupo().size();
            int size1Grupo = fn.getLista1Grupo().size();
            int size2Grupo = fn.getLista2Grupo().size();
            int size3Grupo = fn.getLista3Grupo().size();
            int size4Grupo = fn.getLista4Grupo().size();
            int size5Grupo = fn.getLista5Grupo().size();
            int size6Grupo = fn.getLista6Grupo().size();  
            
            String faixa = null;
            
            if (i == 1) {
            	faixa = "1ª faixa (de 1 a 10)";
            }
            if (i == 2) {
            	faixa = "2ª faixa (de 11 a 20)";
            }
            if (i == 3) {
            	faixa = "3ª faixa (de 21 a 30)";
            }
            if (i == 4) {
            	faixa = "4ª faixa (de 31 a 40)";
            }
            if (i == 5) {
            	faixa = "5ª faixa (de 41 a 50)";
            }
            if (i == 6) {
            	faixa = "6ª faixa (de 51 a 60)";
            }
            
            PainelResultadoAnalises.setTextArea(faixa + "\n\n");
            PainelResultadoAnalises.setTextArea("Apostando em               Chance\n\n");
            PainelResultadoAnalises.setTextArea(
                    "1 número da " + i + "ª faixa: " + "    " + Calc.porcentagem(size1Grupo, numCombinacoes) + "\n");

            PainelResultadoAnalises.setTextArea(
                    "2 números da " + i + "ª faixa: " + "   " + Calc.porcentagem(size2Grupo, numCombinacoes) + "\n");

            PainelResultadoAnalises.setTextArea(
                    "3 números da " + i + "ª faixa: " + "   " + Calc.porcentagem(size3Grupo, numCombinacoes) + "\n");

            PainelResultadoAnalises.setTextArea(
                    "4 números da " + i + "ª faixa: " + "   " + Calc.porcentagem(size4Grupo, numCombinacoes) + "\n");

            PainelResultadoAnalises.setTextArea(
                    "5 números da " + i + "ª faixa: " + "   " + Calc.porcentagem(size5Grupo, numCombinacoes) + "\n");

            PainelResultadoAnalises.setTextArea(
                    "6 números da " + i + "ª faixa: " + "   " + Calc.porcentagem(size6Grupo, numCombinacoes) + "\n");
            
            PainelResultadoAnalises.setTextArea(
                    "nenhum dessa faixa: " + "    " + "  " + Calc.porcentagem(size0Grupo, numCombinacoes) + "\n");
            
            if (i < 6) {
            	PainelResultadoAnalises.setTextArea("\n\n");
            }
            
        }

    }

}
