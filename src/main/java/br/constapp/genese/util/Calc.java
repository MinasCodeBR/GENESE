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

package br.constapp.genese.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Calc {

    private Calc() {}

    public static String porcentagem(int qtdElementos, int totalElementos) {
        Double porcentagem = Double.valueOf(qtdElementos * 100d) / Double.valueOf(totalElementos);

        BigDecimal bd = new BigDecimal(porcentagem).setScale(2, RoundingMode.HALF_EVEN);

        return bd.doubleValue() + "%";

    }

    public static BigInteger fatorial(int i) {
        BigInteger n = BigInteger.valueOf(i);
        while (--i > 0) {
            n = n.multiply(BigInteger.valueOf(i));
        }
        return n;
    }
}
