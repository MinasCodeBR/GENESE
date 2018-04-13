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
 */

package br.constapp.genese.analise.modelo;

import java.util.List;

import br.constapp.genese.jogo.modelo.Jogo;

public class ModeloAnalise {

	private int numero;
	private int repeticoes;
	private List<Jogo> lista;

	public ModeloAnalise() {
	}

	public ModeloAnalise(int numero, int repeticoes) {
		super();
		this.numero = numero;
		this.repeticoes = repeticoes;
	}

	public ModeloAnalise(int numero, int repeticoes, List<Jogo> lista) {
		super();
		this.numero = numero;
		this.repeticoes = repeticoes;
		this.lista = lista;
	}

	@Override
	public String toString() {
		return numero + " " + repeticoes;
	}

	public int getNumero() {
		return numero;
	}

	public int getRepeticoes() {
		return repeticoes;
	}

	public List<Jogo> getLista() {
		return lista;
	}

}
