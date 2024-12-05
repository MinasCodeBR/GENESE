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

import br.com.minascode.GENESE.entities.LotoFacil;

public class LotoFacilDrawFactory {

    private static List<LotoFacil> lotoFacilDrawsList;

    private LotoFacilDrawFactory() {}

    public static void createList(List<String> list) {

        LotoFacil lotoFacil = new LotoFacil();
        lotoFacilDrawsList = new ArrayList<>();
        lotoFacilDrawsList.add(lotoFacil);

        int column = 0;

        for (int u = 0; u < list.size(); u++) {

            if (column >= 31) {
                column = 0;
                lotoFacil = new LotoFacil();
                lotoFacilDrawsList.add(lotoFacil);
            }

            switch (column) {
                case 0:                	
                    lotoFacil.setId(Integer.parseInt(list.get(u)));                    
                    break;
                case 1:
                    lotoFacil.setDate(list.get(u));
                    break;
                case 2:
                    lotoFacil.setFirstNumber(Integer.parseInt(list.get(u)));
                    break;
                case 3:
                    lotoFacil.setSecondNumber(Integer.parseInt(list.get(u)));
                    break;
                case 4:
                    lotoFacil.setThirdNumber(Integer.parseInt(list.get(u)));
                    break;
                case 5:
                    lotoFacil.setFourthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 6:
                    lotoFacil.setFifthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 7:
                    lotoFacil.setSixthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 8:
                    lotoFacil.setSeventhNumber(Integer.parseInt(list.get(u)));
                    break;
                case 9:
                	lotoFacil.setEighthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 10:
                	lotoFacil.setNinthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 11:
                	lotoFacil.setTenthNumber(Integer.parseInt(list.get(u)));
                    break;
                case 12:
                	lotoFacil.setEleventhNumber(Integer.parseInt(list.get(u)));
                    break;
                case 13:
                    lotoFacil.setTwelfthNumber(Integer.parseInt(list.get(u)));
                    break;                    
                case 14:
                	lotoFacil.setThirteenthNumber(Integer.parseInt(list.get(u)));
                	break;
                case 15:
                	lotoFacil.setFourteenthNumber(Integer.parseInt(list.get(u)));
                	break;
                case 16:
                	lotoFacil.setFifteenthNumber(Integer.parseInt(list.get(u)));
                	break;
                case 17:
                	lotoFacil.setFifteenHits(Integer.parseInt(list.get(u)));
                	break;
                case 18:
                	lotoFacil.setFifteenHitsPrize(list.get(u));
                	break;
                case 19:
                	lotoFacil.setFourteenHits(Integer.parseInt(list.get(u)));
                	break;
                case 20:
                	lotoFacil.setFourteenHitsPrize(list.get(u));
                	break;
                case 21:
                	lotoFacil.setThirteenHits(Integer.parseInt(list.get(u)));
                	break;
                case 22:
                	lotoFacil.setThirteenHitsPrize(list.get(u));
                	break;
                case 23:
                	lotoFacil.setTwelveHits(Integer.parseInt(list.get(u)));
                	break;
                case 24:
                	lotoFacil.setTwelveHitsPrize(list.get(u));
                	break;
                case 25:
                	lotoFacil.setElevenHits(Integer.parseInt(list.get(u)));
                	break;
                case 26:
                	lotoFacil.setElevenHitsPrize(list.get(u));
                	break;
                case 29:
                	lotoFacil.setEstimatedPrize(list.get(u));
                	break;
                	
            }
            column++;
        }
    }

    public static List<LotoFacil> getLotoFacilDrawsList() {
        if (lotoFacilDrawsList != null) {
            return new ArrayList<>(lotoFacilDrawsList);
        }
        return new ArrayList<>();
    }
}
