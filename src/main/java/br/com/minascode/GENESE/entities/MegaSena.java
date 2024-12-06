/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 0.0.1
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

package br.com.minascode.GENESE.entities;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import br.com.minascode.GENESE.combination.ConfigurableCombination;
import br.com.minascode.GENESE.util.Calc;

public class MegaSena implements Externalizable, ConfigurableCombination {

	private transient static final long serialVersionUID = 1L;
	private int id;
	private transient String date;
	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private int fourthNumber;
	private int fifthNumber;
	private int sixthNumber;
	private transient int senaWinners;
	private transient int quinaWinners;
	private transient int quadraWinners;
	private transient String senaPrize;
	private transient String quinaPrize;
	private transient String quadraPrize;
	private transient String estimatedPrize;

	public MegaSena() {
	}

	public MegaSena(int id, String date, int firstNumber, int secondNumber, int thirdNumber, int fourthNumber,
			int fifthNumber, int sixthNumber, int senaWinners, int quinaWinners, int quadraWinners, String senaPrize,
			String quinaPrize, String quadraPrize, String estimatedPrize) {

		this.id = id;
		this.date = date;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.thirdNumber = thirdNumber;
		this.fourthNumber = fourthNumber;
		this.fifthNumber = fifthNumber;
		this.sixthNumber = sixthNumber;
		this.senaWinners = senaWinners;
		this.quinaWinners = quinaWinners;
		this.quadraWinners = quadraWinners;
		this.senaPrize = senaPrize;
		this.quinaPrize = quinaPrize;
		this.quadraPrize = quadraPrize;
		this.estimatedPrize = estimatedPrize;
	}

	@Override
	public String toString() {
		return Calc.zeroToLeft(firstNumber) + "   " + Calc.zeroToLeft(secondNumber) + "   "
				+ Calc.zeroToLeft(thirdNumber) + "   " + Calc.zeroToLeft(fourthNumber) + "   "
				+ Calc.zeroToLeft(fifthNumber) + "   " + Calc.zeroToLeft(sixthNumber);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException {
		id = in.readInt();
		firstNumber = in.readInt();
		secondNumber = in.readInt();
		thirdNumber = in.readInt();
		fourthNumber = in.readInt();
		fifthNumber = in.readInt();
		sixthNumber = in.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(id);
		out.writeInt(firstNumber);
		out.writeInt(secondNumber);
		out.writeInt(thirdNumber);
		out.writeInt(fourthNumber);
		out.writeInt(fifthNumber);
		out.writeInt(sixthNumber);
	}

	@Override
	public void configureCombination(int[] numbers, int[] indexes) {
		this.firstNumber = numbers[indexes[0]];
		this.secondNumber = numbers[indexes[1]];
		this.thirdNumber = numbers[indexes[2]];
		this.fourthNumber = numbers[indexes[3]];
		this.fifthNumber = numbers[indexes[4]];
		this.sixthNumber = numbers[indexes[5]];
	}

	@Override
	public int[] getNumbers() {
		return new int[] { this.firstNumber, this.secondNumber, this.thirdNumber, this.fourthNumber, this.fifthNumber,
				this.sixthNumber };
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	public int getThirdNumber() {
		return thirdNumber;
	}

	public void setThirdNumber(int thirdNumber) {
		this.thirdNumber = thirdNumber;
	}

	public int getFourthNumber() {
		return fourthNumber;
	}

	public void setFourthNumber(int fourthNumber) {
		this.fourthNumber = fourthNumber;
	}

	public int getFifthNumber() {
		return fifthNumber;
	}

	public void setFifthNumber(int fifthNumber) {
		this.fifthNumber = fifthNumber;
	}

	public int getSixthNumber() {
		return sixthNumber;
	}

	public void setSixthNumber(int sixthNumber) {
		this.sixthNumber = sixthNumber;
	}

	public int getSenaWinners() {
		return senaWinners;
	}

	public void setSenaWinners(int senaWinners) {
		this.senaWinners = senaWinners;
	}

	public int getQuinaWinners() {
		return quinaWinners;
	}

	public void setQuinaWinners(int quinaWinners) {
		this.quinaWinners = quinaWinners;
	}

	public int getQuadraWinners() {
		return quadraWinners;
	}

	public void setQuadraWinners(int quadraWinners) {
		this.quadraWinners = quadraWinners;
	}

	public String getSenaPrize() {
		return senaPrize;
	}

	public void setSenaPrize(String senaPrize) {
		this.senaPrize = senaPrize;
	}

	public String getQuinaPrize() {
		return quinaPrize;
	}

	public void setQuinaPrize(String quinaPrize) {
		this.quinaPrize = quinaPrize;
	}

	public String getQuadraPrize() {
		return quadraPrize;
	}

	public void setQuadraPrize(String quadraPrize) {
		this.quadraPrize = quadraPrize;
	}

	public String getEstimatedPrize() {
		return estimatedPrize;
	}

	public void setEstimatedPrize(String estimatedPrize) {
		this.estimatedPrize = estimatedPrize;
	}
}
