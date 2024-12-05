/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 1.0
 * Licença: GNU General Public License v3.0
 * 
 * Este programa é um software livre: você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation, seja a versão 3 da
 * Licença, ou (a seu critério) qualquer versão posterior.
 * 
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou ADEQUAÇÃO A UM DETERMINADO FIM. Veja a
 * Licença Pública Geral GNU para mais detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa. Caso contrário, veja <https://www.gnu.org/licenses/>.
 */

package br.com.minascode.GENESE.draw;

import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.entities.MegaSena;

public class MegaSenaDrawFactory {

    private static List<MegaSena> megaSenaDrawsList;

    private MegaSenaDrawFactory() {}

    public static void createList(List<String> list) {

        MegaSena megaSena = new MegaSena();
        megaSenaDrawsList = new ArrayList<>();
        megaSenaDrawsList.add(megaSena);

        int column = 0;

        for (int u = 0; u < list.size(); u++) {

            if (column >= 18) {
                column = 0;
                megaSena = new MegaSena();
                megaSenaDrawsList.add(megaSena);
            }

            switch (column) {
                case 0:                	
                    megaSena.setId(Integer.parseInt(list.get(u)));                    
                    break;
                case 1:
                    megaSena.setDate(list.get(u));
                    break;
                case 2:
                    megaSena.setFirstNumber(Integer.parseInt(list.get(u)));
                    break;
                case 3:
                    megaSena.setSecondNumber(Integer.parseInt(list.get(u)));
                    break;
                case 4:
                    megaSena.setThirdNumber(Integer.parseInt(list.get(u)));
                    break;
                case 5:
                    megaSena.setFourthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 6:
                    megaSena.setFifthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 7:
                    megaSena.setSixthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 8:
                    megaSena.setSenaWinners(Integer.parseInt(list.get(u)));
                    break;
                case 9:
                	megaSena.setSenaPrize((list.get(u)));
                    break;
                case 10:
                	megaSena.setQuinaWinners(Integer.parseInt(list.get(u)));
                    break;
                case 11:
                	megaSena.setQuinaPrize((list.get(u)));
                    break;
                case 12:
                	megaSena.setQuadraWinners(Integer.parseInt(list.get(u)));
                    break;
                case 13:
                    megaSena.setQuadraPrize((list.get(u)));
                    break;                    
                case 16:
                	megaSena.setEstimatedPrize((list.get(u)));
                	break;
            }
            column++;
        }
    }

    public static List<MegaSena> getMegaSenaDrawsList() {
        if (megaSenaDrawsList != null) {
            return new ArrayList<>(megaSenaDrawsList);
        }
        return new ArrayList<>();
    }
}
