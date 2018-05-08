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

package br.constapp.genese.analise;

import java.util.ArrayList;
import java.util.List;

import br.constapp.genese.jogo.modelo.Jogo;

public class Sequencia {

	private List<Jogo> listaPrimeiroCampo;
	private List<Jogo> listaSegundoCampo;
	private List<Jogo> listaTerceiroCampo;
	private List<Jogo> listaQuartoCampo;
	private List<Jogo> listaSequencia;

	public Sequencia(List<Jogo> listaJogos) {

		buscaSequencia(listaJogos);
	}

	private void buscaSequencia(List<Jogo> listaJogos) {

		listaPrimeiroCampo = new ArrayList<>();
		listaSegundoCampo = new ArrayList<>();
		listaTerceiroCampo = new ArrayList<>();
		listaQuartoCampo = new ArrayList<>();
		listaSequencia = new ArrayList<>();

		for (Jogo jogo : listaJogos) {

			if (jogo.getPrimeiraDezena() + 1 == jogo.getSegundaDezena()
					&& jogo.getSegundaDezena() + 1 == jogo.getTerceiraDezena()) {
				listaPrimeiroCampo.add(jogo);
			}

			if (jogo.getSegundaDezena() + 1 == jogo.getTerceiraDezena()
					&& jogo.getTerceiraDezena() + 1 == jogo.getQuartaDezena()) {
				listaSegundoCampo.add(jogo);
			}

			if (jogo.getTerceiraDezena() + 1 == jogo.getQuartaDezena()
					&& jogo.getQuartaDezena() + 1 == jogo.getQuintaDezena()) {
				listaTerceiroCampo.add(jogo);
			}

			if (jogo.getQuartaDezena() + 1 == jogo.getQuintaDezena()
					&& jogo.getQuintaDezena() + 1 == jogo.getSextaDezena()) {
				listaQuartoCampo.add(jogo);
			}

			if (jogo.getPrimeiraDezena() + 1 == jogo.getSegundaDezena()
					&& jogo.getSegundaDezena() + 1 == jogo.getTerceiraDezena()
					&& jogo.getTerceiraDezena() + 1 == jogo.getQuartaDezena()
					&& jogo.getQuartaDezena() + 1 == jogo.getQuintaDezena()
					&& jogo.getQuintaDezena() + 1 == jogo.getSextaDezena()) {
				listaSequencia.add(jogo);
			}

		}
	}

	public List<Jogo> getListaPrimeiroCampo() {
		return listaPrimeiroCampo;
	}

	public List<Jogo> getListaSegundoCampo() {
		return listaSegundoCampo;
	}

	public List<Jogo> getListaTerceiroCampo() {
		return listaTerceiroCampo;
	}

	public List<Jogo> getListaQuartoCampo() {
		return listaQuartoCampo;
	}

	public List<Jogo> getListaSequencia() {
		return listaSequencia;
	}

}
