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

public class LotoFacil implements Externalizable, ConfigurableCombination {

	private transient static final long serialVersionUID = 1L;
	private int id;
	private transient String date;
	private int firstNumber;
	private int secondNumber;
	private int thirdNumber;
	private int fourthNumber;
	private int fifthNumber;
	private int sixthNumber;
	private int seventhNumber;
	private int eighthNumber;
	private int ninthNumber;
	private int tenthNumber;
	private int eleventhNumber;
	private int twelfthNumber;
	private int thirteenthNumber;
	private int fourteenthNumber;
	private int fifteenthNumber;
	private transient int fifteenHits;
	private transient int fourteenHits;
	private transient int thirteenHits;
	private transient int twelveHits;
	private transient int elevenHits;
	private transient String fifteenHitsPrize;
	private transient String fourteenHitsPrize;
	private transient String thirteenHitsPrize;
	private transient String twelveHitsPrize;
	private transient String elevenHitsPrize;
	private transient String estimatedPrize;

	public LotoFacil() {
	}

	public LotoFacil(int id, String date, int firstNumber, int secondNumber, int thirdNumber, int fourthNumber,
			int fifthNumber, int sixthNumber, int seventhNumber, int eighthNumber, int ninthNumber, int tenthNumber,
			int eleventhNumber, int twelfthNumber, int thirteenthNumber, int fourteenthNumber, int fifteenthNumber,
			int sixteenthNumber, int seventeenthNumber, int eighteenthNumber, int nineteenthNumber, int twentiethNumber,
			int twentyFirstNumber, int twentySecondNumber, int twentyThirdNumber, int twentyFourthNumber,
			int twentyFifthNumber, int fifteenHits, int fourteenHits, int thirteenHits, int twelveHits, int elevenHits,
			String fifteenHitsPrize, String fourteenHitsPrize, String thirteenHitsPrize, String twelveHitsPrize,
			String elevenHitsPrize, String estimatedPrize) {

		this.id = id;
		this.date = date;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.thirdNumber = thirdNumber;
		this.fourthNumber = fourthNumber;
		this.fifthNumber = fifthNumber;
		this.sixthNumber = sixthNumber;
		this.seventhNumber = seventhNumber;
		this.eighthNumber = eighthNumber;
		this.ninthNumber = ninthNumber;
		this.tenthNumber = tenthNumber;
		this.eleventhNumber = eleventhNumber;
		this.twelfthNumber = twelfthNumber;
		this.thirteenthNumber = thirteenthNumber;
		this.fourteenthNumber = fourteenthNumber;
		this.fifteenthNumber = fifteenthNumber;
		this.fifteenHits = fifteenHits;
		this.fourteenHits = fourteenHits;
		this.thirteenHits = thirteenHits;
		this.twelveHits = twelveHits;
		this.elevenHits = elevenHits;
		this.fifteenHitsPrize = fifteenHitsPrize;
		this.fourteenHitsPrize = fourteenHitsPrize;
		this.thirteenHitsPrize = thirteenHitsPrize;
		this.twelveHitsPrize = twelveHitsPrize;
		this.elevenHitsPrize = elevenHitsPrize;
		this.estimatedPrize = estimatedPrize;
	}

	@Override
	public String toString() {
		return Calc.zeroToLeft(firstNumber) + " " + Calc.zeroToLeft(secondNumber) + " "
				+ Calc.zeroToLeft(thirdNumber) + " " + Calc.zeroToLeft(fourthNumber) + " "
				+ Calc.zeroToLeft(fifthNumber) + " " + Calc.zeroToLeft(sixthNumber) + " "
				+ Calc.zeroToLeft(seventhNumber) + " " + Calc.zeroToLeft(eighthNumber) + " "
				+ Calc.zeroToLeft(ninthNumber) + " " + Calc.zeroToLeft(tenthNumber) + " "
				+ Calc.zeroToLeft(eleventhNumber) + " " + Calc.zeroToLeft(twelfthNumber) + " "
				+ Calc.zeroToLeft(thirteenthNumber) + " " + Calc.zeroToLeft(fourteenthNumber) + " "
				+ Calc.zeroToLeft(fifteenthNumber);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		id = in.readInt();
		firstNumber = in.readInt();
		secondNumber = in.readInt();
		thirdNumber = in.readInt();
		fourthNumber = in.readInt();
		fifthNumber = in.readInt();
		sixthNumber = in.readInt();
		seventhNumber = in.readInt();
		eighthNumber = in.readInt();
		ninthNumber = in.readInt();
		tenthNumber = in.readInt();
		eleventhNumber = in.readInt();
		twelfthNumber = in.readInt();
		thirteenthNumber = in.readInt();
		fourteenthNumber = in.readInt();
		fifteenthNumber = in.readInt();
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
		out.writeInt(seventhNumber);
		out.writeInt(eighthNumber);
		out.writeInt(ninthNumber);
		out.writeInt(tenthNumber);
		out.writeInt(eleventhNumber);
		out.writeInt(twelfthNumber);
		out.writeInt(thirteenthNumber);
		out.writeInt(fourteenthNumber);
		out.writeInt(fifteenthNumber);
	}

	@Override
	public void configureCombination(int[] numbers, int[] indexes) {
		this.firstNumber = numbers[indexes[0]];
		this.secondNumber = numbers[indexes[1]];
		this.thirdNumber = numbers[indexes[2]];
		this.fourthNumber = numbers[indexes[3]];
		this.fifthNumber = numbers[indexes[4]];
		this.sixthNumber = numbers[indexes[5]];
		this.seventhNumber = numbers[indexes[6]];
		this.eighthNumber = numbers[indexes[7]];
		this.ninthNumber = numbers[indexes[8]];
		this.tenthNumber = numbers[indexes[9]];
		this.eleventhNumber = numbers[indexes[10]];
		this.twelfthNumber = numbers[indexes[11]];
		this.thirteenthNumber = numbers[indexes[12]];
		this.fourteenthNumber = numbers[indexes[13]];
		this.fifteenthNumber = numbers[indexes[14]];
	}
	
	@Override
	public int[] getNumbers() {
		return new int[] { this.firstNumber, this.secondNumber, this.thirdNumber, this.fourthNumber, this.fifthNumber,
				this.sixthNumber, this.seventhNumber, this.eighthNumber, this.ninthNumber, this.tenthNumber,
				this.eleventhNumber, this.twelfthNumber, this.thirteenthNumber, this.fourteenthNumber,
				this.fifteenthNumber };
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

	public int getSeventhNumber() {
		return seventhNumber;
	}

	public void setSeventhNumber(int seventhNumber) {
		this.seventhNumber = seventhNumber;
	}

	public int getEighthNumber() {
		return eighthNumber;
	}

	public void setEighthNumber(int eighthNumber) {
		this.eighthNumber = eighthNumber;
	}

	public int getNinthNumber() {
		return ninthNumber;
	}

	public void setNinthNumber(int ninthNumber) {
		this.ninthNumber = ninthNumber;
	}

	public int getTenthNumber() {
		return tenthNumber;
	}

	public void setTenthNumber(int tenthNumber) {
		this.tenthNumber = tenthNumber;
	}

	public int getEleventhNumber() {
		return eleventhNumber;
	}

	public void setEleventhNumber(int eleventhNumber) {
		this.eleventhNumber = eleventhNumber;
	}

	public int getTwelfthNumber() {
		return twelfthNumber;
	}

	public void setTwelfthNumber(int twelfthNumber) {
		this.twelfthNumber = twelfthNumber;
	}

	public int getThirteenthNumber() {
		return thirteenthNumber;
	}

	public void setThirteenthNumber(int thirteenthNumber) {
		this.thirteenthNumber = thirteenthNumber;
	}

	public int getFourteenthNumber() {
		return fourteenthNumber;
	}

	public void setFourteenthNumber(int fourteenthNumber) {
		this.fourteenthNumber = fourteenthNumber;
	}

	public int getFifteenthNumber() {
		return fifteenthNumber;
	}

	public void setFifteenthNumber(int fifteenthNumber) {
		this.fifteenthNumber = fifteenthNumber;
	}

	public int getFifteenHits() {
		return fifteenHits;
	}

	public void setFifteenHits(int fifteenHits) {
		this.fifteenHits = fifteenHits;
	}

	public int getFourteenHits() {
		return fourteenHits;
	}

	public void setFourteenHits(int fourteenHits) {
		this.fourteenHits = fourteenHits;
	}

	public int getThirteenHits() {
		return thirteenHits;
	}

	public void setThirteenHits(int thirteenHits) {
		this.thirteenHits = thirteenHits;
	}

	public int getTwelveHits() {
		return twelveHits;
	}

	public void setTwelveHits(int twelveHits) {
		this.twelveHits = twelveHits;
	}

	public int getElevenHits() {
		return elevenHits;
	}

	public void setElevenHits(int elevenHits) {
		this.elevenHits = elevenHits;
	}

	public String getFifteenHitsPrize() {
		return fifteenHitsPrize;
	}

	public void setFifteenHitsPrize(String fifteenHitsPrize) {
		this.fifteenHitsPrize = fifteenHitsPrize;
	}

	public String getFourteenHitsPrize() {
		return fourteenHitsPrize;
	}

	public void setFourteenHitsPrize(String fourteenHitsPrize) {
		this.fourteenHitsPrize = fourteenHitsPrize;
	}

	public String getThirteenHitsPrize() {
		return thirteenHitsPrize;
	}

	public void setThirteenHitsPrize(String thirteenHitsPrize) {
		this.thirteenHitsPrize = thirteenHitsPrize;
	}

	public String getTwelveHitsPrize() {
		return twelveHitsPrize;
	}

	public void setTwelveHitsPrize(String twelveHitsPrize) {
		this.twelveHitsPrize = twelveHitsPrize;
	}

	public String getElevenHitsPrize() {
		return elevenHitsPrize;
	}

	public void setElevenHitsPrize(String elevenHitsPrize) {
		this.elevenHitsPrize = elevenHitsPrize;
	}

	public String getEstimatedPrize() {
		return estimatedPrize;
	}

	public void setEstimatedPrize(String estimatedPrize) {
		this.estimatedPrize = estimatedPrize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
