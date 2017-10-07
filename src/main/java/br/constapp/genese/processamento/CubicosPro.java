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

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

import java.util.Arrays;
import java.util.List;

public class CubicosPro {

    public static void processaCubicos() {
        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        int numCombinacoes = 0;
        int size0Cubo = 0;
        int size1Cubo = 0;
        int size2Cubos = 0;
        int size3Cubos = 0;

        Cubicos c = new Cubicos(listaJogos);

        numCombinacoes += listaJogos.size();
        size0Cubo += c.getLista0Cubico().size();
        size1Cubo += c.getLista1Cubico().size();
        size2Cubos += c.getLista2Cubicos().size();
        size3Cubos += c.getLista3Cubicos().size();
        Integer[] numerosCubicos = c.defineNumerosCubicos();

        PainelResultadoAnalises.setTextArea("Apostando em:              Chance\n\n");        

        PainelResultadoAnalises.setTextArea("1 número cúbico:" + "           " + Calc.porcentagem(size1Cubo, numCombinacoes) + "\n");

        PainelResultadoAnalises.setTextArea("2 números cúbicos:" + "         " + Calc.porcentagem(size2Cubos, numCombinacoes) + "\n");

        PainelResultadoAnalises.setTextArea("3 números cúbicos:" + "         " + Calc.porcentagem(size3Cubos, numCombinacoes) + "\n");
        
        PainelResultadoAnalises.setTextArea("nenhum cúbico:" + "             " + Calc.porcentagem(size0Cubo, numCombinacoes) + "\n\n");
        
        PainelResultadoAnalises.setTextArea("Números cúbicos: " + Arrays.toString(numerosCubicos));
    }

}
