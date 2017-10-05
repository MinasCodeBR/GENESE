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

package br.constapp.genese.jogo.modelo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Jogo implements Externalizable {

    private transient static final long serialVersionUID = 1L;
    private transient int concurso;
    private transient String dataSorteio;
    protected int primeiraDezena;
    protected int segundaDezena;
    protected int terceiraDezena;
    protected int quartaDezena;
    protected int quintaDezena;
    protected int sextaDezena;
    private transient int numGanhadoresSena;
    private transient int numGanhadoresQuina;
    private transient int numGanhadoresQuadra;
    private transient String rateioSena;
    private transient String rateioQuina;
    private transient String rateioQuadra;
    private transient String estimativaPremio;

    public Jogo() {
    }

    public Jogo(int concurso, String dataSorteio, int primeiraDezena, int segundaDezena, int terceiraDezena,
                int quartaDezena, int quintaDezena, int sextaDezena, int numGanhadoresSena, String rateioSena,
                int numGanhadoresQuina, String rateioQuina, int numGanhadoresQuadra, String rateioQuadra,
                String estimativaPremio) {

        super();
        this.concurso = concurso;
        this.dataSorteio = dataSorteio;
        this.primeiraDezena = primeiraDezena;
        this.segundaDezena = segundaDezena;
        this.terceiraDezena = terceiraDezena;
        this.quartaDezena = quartaDezena;
        this.quintaDezena = quintaDezena;
        this.sextaDezena = sextaDezena;
        this.numGanhadoresSena = numGanhadoresSena;
        this.rateioSena = rateioSena;
        this.numGanhadoresQuina = numGanhadoresQuina;
        this.rateioQuina = rateioQuina;
        this.numGanhadoresQuadra = numGanhadoresQuadra;
        this.rateioQuadra = rateioQuadra;
        this.estimativaPremio = estimativaPremio;
    }

    @Override
    public String toString() {

        return primeiraDezena + " " + segundaDezena + " " + terceiraDezena + " " + quartaDezena + " " + quintaDezena
                + " " + sextaDezena;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        primeiraDezena = in.readInt();
        segundaDezena = in.readInt();
        terceiraDezena = in.readInt();
        quartaDezena = in.readInt();
        quintaDezena = in.readInt();
        sextaDezena = in.readInt();

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        out.writeInt(primeiraDezena);
        out.writeInt(segundaDezena);
        out.writeInt(terceiraDezena);
        out.writeInt(quartaDezena);
        out.writeInt(quintaDezena);
        out.writeInt(sextaDezena);

    }

    public int getConcurso() {
        return concurso;
    }

    public void setConcurso(int concurso) {
        this.concurso = concurso;
    }

    public String getDataSorteio() {
        return dataSorteio;
    }

    public void setDataSorteio(String dataSorteio) {
        this.dataSorteio = dataSorteio;
    }

    public int getPrimeiraDezena() {
        return primeiraDezena;
    }

    public void setPrimeiraDezena(int primeiraDezena) {
        this.primeiraDezena = primeiraDezena;
    }

    public int getSegundaDezena() {
        return segundaDezena;
    }

    public void setSegundaDezena(int segundaDezena) {
        this.segundaDezena = segundaDezena;
    }

    public int getTerceiraDezena() {
        return terceiraDezena;
    }

    public void setTerceiraDezena(int terceiraDezena) {
        this.terceiraDezena = terceiraDezena;
    }

    public int getQuartaDezena() {
        return quartaDezena;
    }

    public void setQuartaDezena(int quartaDezena) {
        this.quartaDezena = quartaDezena;
    }

    public int getQuintaDezena() {
        return quintaDezena;
    }

    public void setQuintaDezena(int quintaDezena) {
        this.quintaDezena = quintaDezena;
    }

    public int getSextaDezena() {
        return sextaDezena;
    }

    public void setSextaDezena(int sextaDezena) {
        this.sextaDezena = sextaDezena;
    }

    public int getNumGanhadoresSena() {
        return numGanhadoresSena;
    }

    public void setNumGanhadoresSena(int numGanhadores) {
        this.numGanhadoresSena = numGanhadores;
    }

    public int getNumGanhadoresQuina() {
        return numGanhadoresQuina;
    }

    public void setNumGanhadoresQuina(int numGanhadoresQuina) {
        this.numGanhadoresQuina = numGanhadoresQuina;
    }

    public int getNumGanhadoresQuadra() {
        return numGanhadoresQuadra;
    }

    public void setNumGanhadoresQuadra(int numGanhadoresQuadra) {
        this.numGanhadoresQuadra = numGanhadoresQuadra;
    }

    public String getRateioSena() {
        return rateioSena;
    }

    public void setRateioSena(String rateioSena) {
        this.rateioSena = rateioSena;
    }

    public String getRateioQuina() {
        return rateioQuina;
    }

    public void setRateioQuina(String rateioQuina) {
        this.rateioQuina = rateioQuina;
    }

    public String getRateioQuadra() {
        return rateioQuadra;
    }

    public void setRateioQuadra(String rateioQuadra) {
        this.rateioQuadra = rateioQuadra;
    }

    public String getEstimativaPremio() {
        return estimativaPremio;
    }

    public void setEstimativaPremio(String estimativaPremio) {
        this.estimativaPremio = estimativaPremio;
    }
}
