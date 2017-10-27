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

package br.constapp.genese.analise;

import br.constapp.genese.analise.comparador.Contador;
import br.constapp.genese.jogo.modelo.Jogo;

import java.util.List;

public class Quadrantes {

	private List<Jogo> lista0Incidencia, lista1Incidencia, lista2Incidencias, lista3Incidencias, lista4Incidencias,
			lista5Incidencias, lista6Incidencias;

	public Quadrantes(List<Jogo> listaJogos, int quadrante) {
		executaQuadrantes(listaJogos, quadrante);
	}

	public Integer[] defineQuadrantes(int quadrante) {

		Integer[] grupo = new Integer[15];

		int teto = 0;
		int base = 0;

		if (quadrante == 1 || quadrante == 2) {
			teto = quadrante * 5;
		}
		if (quadrante == 3) {
			teto = 35;
		}
		if (quadrante == 4) {
			teto = 40;
		}
		
		base = teto - 4;

		for (int i = base; i < teto;) {
			for (int u = 0; u < grupo.length; u++) {
				grupo[u] = i++;
			}
			break;
		}

		int index = 0;

		for (int j = 5; j < grupo.length; j++) {
			grupo[j] = grupo[index] + 10;
			index++;
		}
		return grupo;
	}

	private void executaQuadrantes(List<Jogo> listaJogos, int quadrante) {

		Contador contador = new Contador(listaJogos, defineQuadrantes(quadrante));

		lista0Incidencia = contador.getLista0Incidencia();
		lista1Incidencia = contador.getLista1Incidencia();
		lista2Incidencias = contador.getLista2Incidencias();
		lista3Incidencias = contador.getLista3Incidencias();
		lista4Incidencias = contador.getLista4Incidencias();
		lista5Incidencias = contador.getLista5Incidencias();
		lista6Incidencias = contador.getLista6Incidencias();

	}

	public List<Jogo> getLista0Incidencia() {
		return lista0Incidencia;
	}

	public List<Jogo> getLista1Incidencia() {
		return lista1Incidencia;
	}

	public List<Jogo> getLista2Incidencias() {
		return lista2Incidencias;
	}

	public List<Jogo> getLista3Incidencias() {
		return lista3Incidencias;
	}

	public List<Jogo> getLista4Incidencias() {
		return lista4Incidencias;
	}

	public List<Jogo> getLista5Incidencias() {
		return lista5Incidencias;
	}

	public List<Jogo> getLista6Incidencias() {
		return lista6Incidencias;
	}
}
