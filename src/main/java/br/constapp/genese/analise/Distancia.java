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

import br.constapp.genese.analise.modelo.ModeloAnalise;
import br.constapp.genese.jogo.modelo.Jogo;

public class Distancia {

	private static Integer[] vetDistancias;
	private static Integer[] contagem;
	private static ModeloAnalise[] vetResultado;
	private ModeloAnalise modelo;
	private List<Jogo>[] listaDistanciaJogos;

	public Distancia(List<Jogo> listaJogos) {		

		criaArrayModelos(medeDistancia(listaJogos, criaVetorDistancia()));

	}

	private void criaArrayModelos(Integer[] contagem) {
		
		vetResultado = new ModeloAnalise[contagem.length];
		
		for (int i = 0; i < contagem.length; i++) {

			modelo = new ModeloAnalise(i + 1, contagem[i], listaDistanciaJogos[i]);
			vetResultado[i] = modelo;
		}

	}

	@SuppressWarnings("unchecked")
	private Integer[] medeDistancia(List<Jogo> listaJogos, Integer[] vetDistancias) {	
		
		contagem = new Integer[vetDistancias.length];
		listaDistanciaJogos = new ArrayList[vetDistancias.length];
		
		for (int i = 0; i < contagem.length; i++) {
			contagem[i] = 0;
			listaDistanciaJogos[i] = new ArrayList<>();
		}

		for (Jogo jogo : listaJogos) {

			for (int i = 0; i < vetDistancias.length; i++) {				

				if ((jogo.getSegundaDezena() - jogo.getPrimeiraDezena()) == vetDistancias[i]) {
					contagem[i]++;					 
					listaDistanciaJogos[i].add(jogo);
				}

				if ((jogo.getQuartaDezena() - jogo.getTerceiraDezena()) == vetDistancias[i]) {
					contagem[i]++;
					listaDistanciaJogos[i].add(jogo);
				}

				if ((jogo.getSextaDezena() - jogo.getQuintaDezena()) == vetDistancias[i]) {
					contagem[i]++;
					listaDistanciaJogos[i].add(jogo);
				}
				
				if ((jogo.getTerceiraDezena() - jogo.getSegundaDezena()) == vetDistancias[i]) {
					contagem[i]++;
					listaDistanciaJogos[i].add(jogo);
				}
				
				if ((jogo.getQuintaDezena() - jogo.getQuartaDezena()) == vetDistancias[i]) {
					contagem[i]++;
					listaDistanciaJogos[i].add(jogo);
				}
			}
		}
		return contagem;
	}

	private Integer[] criaVetorDistancia() {
		vetDistancias = new Integer[55];

		for (int i = 1; i <= vetDistancias.length; i++) {
			vetDistancias[i - 1] = i;
		}
		return vetDistancias;
	}

	public ModeloAnalise[] getVetResultado() {
		return vetResultado;
	}

}
