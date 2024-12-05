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

package br.com.minascode.GENESE.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class Calc {

	private Calc() {
	}

	public static Double percentage(double numberOfElements, double totalElements) {

		if (totalElements == 0) {
	        throw new IllegalArgumentException("Total elements cannot be zero.");
	    }

	    BigDecimal numberOfElementsBD = BigDecimal.valueOf(numberOfElements);
	    BigDecimal totalElementsBD = BigDecimal.valueOf(totalElements);
	    BigDecimal hundred = BigDecimal.valueOf(100);

	    BigDecimal percentage = numberOfElementsBD
	        .multiply(hundred)
	        .divide(totalElementsBD, 2, RoundingMode.HALF_EVEN);

	    return percentage.doubleValue();

	}

	public static BigInteger factorial(int i) {
		BigInteger n = BigInteger.valueOf(i);
		while (--i > 0) {
			n = n.multiply(BigInteger.valueOf(i));
		}
		return n;
	}

	public static String zeroToLeft(int number) {
		if (number <= 9) {
			String convertedNumber = String.format("%02d", number);
			return convertedNumber;
		}
		String numberToString = String.valueOf(number);
		return numberToString;
	}
}
