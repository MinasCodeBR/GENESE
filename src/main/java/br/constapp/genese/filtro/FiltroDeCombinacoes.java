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

package br.constapp.genese.filtro;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.analise.Distancia;
import br.constapp.genese.analise.FaixaDeNumeros;
import br.constapp.genese.analise.Fibonacci;
import br.constapp.genese.analise.Multiplicidade;
import br.constapp.genese.analise.ParOuImpar;
import br.constapp.genese.analise.Primalidade;
import br.constapp.genese.analise.Quadrantes;
import br.constapp.genese.analise.Quadraticos;
import br.constapp.genese.analise.Sequencia;
import br.constapp.genese.analise.modelo.ModeloAnalise;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.serialization.Deserializador;
import br.constapp.genese.serialization.Serializador;
import br.constapp.genese.util.Calc;

public class FiltroDeCombinacoes {

	private ParOuImpar parOuImpar;
	private Cubicos cubicos;
	private Fibonacci fibonacci;
	private Primalidade primos;
	private Quadraticos quadraticos;
	private Quadrantes quadrantes;
	private Multiplicidade multiplicidade;
	private FaixaDeNumeros faixaDeNumeros;
	private List<Jogo> combinacoes;
	private List<Jogo> listaParOuImparFiltrada;
	private List<Jogo> listaCubicosFiltrada;
	private List<Jogo> listaFibonacciFiltrada;
	private List<Jogo> listaPrimosFiltrada;
	private List<Jogo> listaQuadraticosFiltrada;
	private List<Jogo> listaQuadrantesFiltrada;
	private List<Jogo> listaMultiplicidadeFiltrada;
	private List<Jogo> listaFaixaDeNumerosFiltrada;
	private List<Jogo> listaDistanciaFiltrada;
	private List<Jogo> listaSequenciaFiltrada;
	private List<Jogo> listaFinal;
	private String nomeArquivo;

	public FiltroDeCombinacoes() {

		listaParOuImparFiltrada = new ArrayList<>();
		listaCubicosFiltrada = new ArrayList<>();
		listaFibonacciFiltrada = new ArrayList<>();
		listaPrimosFiltrada = new ArrayList<>();
		listaQuadraticosFiltrada = new ArrayList<>();
		listaQuadrantesFiltrada = new ArrayList<>();
		listaMultiplicidadeFiltrada = new ArrayList<>();
		listaFaixaDeNumerosFiltrada = new ArrayList<>();
		listaDistanciaFiltrada = new ArrayList<>();
		listaSequenciaFiltrada = new ArrayList<>();

		listaFinal = new ArrayList<>();

		long inicio = System.currentTimeMillis();
		
		int concurso = 1;

		for (int i = 1; i <= 51; i++) {

			nomeArquivo = "lista_" + i + ".ser";

			listaFinal(nomeArquivo, concurso);
			
			

			System.out.println((System.currentTimeMillis() - inicio) / 60000 + " min");

		}

		System.out.println("Tempo total: " + (System.currentTimeMillis() - inicio) / 60000 + " min");

	}

	private void listaFinal(String nomeArquivo, int concurso) {

		listaFinal = filtraSequencia(filtraDistancia(
				filtraFaixaDeNumeros(filtraMultiplicidade(filtraQuadrantes(filtraQuadraticos(filtraPrimos(
						filtraFibonacci(filtraCubicos(filtraParOuImpar(criaListaCombinacoes(nomeArquivo)))))))))));
		
		for (Jogo jogo : listaFinal) {
			jogo.setConcurso(concurso);
			concurso++;
		}

		try {
			Serializador.serializa(DefineDiretorio.getDiretorioFiltro() + nomeArquivo, listaFinal);

		} catch (Exception ex) {
			System.err.println("Falha ao serializar - " + ex.toString());
		}

		listaFinal.clear();

	}

	private List<Jogo> filtraSequencia(List<Jogo> listaDistanciaFiltrada) {

		listaSequenciaFiltrada.addAll(listaDistanciaFiltrada);

		Sequencia sequencia = new Sequencia(listaDistanciaFiltrada);
		
		System.out.println("Filtrando sequência");

		listaSequenciaFiltrada.removeAll(sequencia.getListaPrimeiroCampo());
		listaSequenciaFiltrada.removeAll(sequencia.getListaSegundoCampo());
		listaSequenciaFiltrada.removeAll(sequencia.getListaTerceiroCampo());
		listaSequenciaFiltrada.removeAll(sequencia.getListaQuartoCampo());
		listaSequenciaFiltrada.removeAll(sequencia.getListaSequencia());

		listaDistanciaFiltrada.clear();

		return listaSequenciaFiltrada;

	}

	private List<Jogo> filtraDistancia(List<Jogo> listaFaixaDeNumerosFiltrada) {

		listaDistanciaFiltrada.addAll(listaFaixaDeNumerosFiltrada);

		Distancia distancia = new Distancia(listaFaixaDeNumerosFiltrada);
		ModeloAnalise[] vetResultado = distancia.getVetResultado();

		for (int i = 0; i < distancia.getVetResultado().length; i++) {
			
			System.out.println("Filtrando distância " + i);

			if (Calc.porcentagem(vetResultado[i].getRepeticoes(), listaFaixaDeNumerosFiltrada.size()) < 10) {
				listaDistanciaFiltrada.removeAll(vetResultado[i].getLista());
			}
		}
		listaFaixaDeNumerosFiltrada.clear();

		return listaDistanciaFiltrada;

	}

	private List<Jogo> filtraFaixaDeNumeros(List<Jogo> listaMultiplicidadeFiltrada) {

		listaFaixaDeNumerosFiltrada.addAll(listaMultiplicidadeFiltrada);

		for (int i = 1; i <= 6; i++) {

			System.out.println("Filtrando faixa " + i);

			faixaDeNumeros = new FaixaDeNumeros(listaMultiplicidadeFiltrada, i);

			if (Calc.porcentagem(faixaDeNumeros.getLista0Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista0Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista1Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista1Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista2Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista2Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista3Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista3Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista4Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista4Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista5Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista5Grupo());
			}
			if (Calc.porcentagem(faixaDeNumeros.getLista6Grupo().size(), listaMultiplicidadeFiltrada.size()) < 5) {
				listaFaixaDeNumerosFiltrada.removeAll(faixaDeNumeros.getLista6Grupo());
			}
		}
		listaMultiplicidadeFiltrada.clear();

		for (int i = 0; i < listaFaixaDeNumerosFiltrada.size(); i++) {
			listaFaixaDeNumerosFiltrada.get(i).setConcurso(i + 1);
		}

		return listaFaixaDeNumerosFiltrada;
	}

	private List<Jogo> filtraMultiplicidade(List<Jogo> listaQuadrantesFiltrada) {

		listaMultiplicidadeFiltrada.addAll(listaQuadrantesFiltrada);

		for (int i = 2; i <= 30; i++) {

			System.out.println("Filtrando múltiplos de " + i);

			multiplicidade = new Multiplicidade(listaQuadrantesFiltrada, i);

			if (Calc.porcentagem(multiplicidade.getLista0Multiplo().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista0Multiplo());
			}
			if (Calc.porcentagem(multiplicidade.getLista1Multiplo().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista1Multiplo());
			}
			if (Calc.porcentagem(multiplicidade.getLista2Multiplos().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista2Multiplos());
			}
			if (Calc.porcentagem(multiplicidade.getLista3Multiplos().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista3Multiplos());
			}
			if (Calc.porcentagem(multiplicidade.getLista4Multiplos().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista4Multiplos());
			}
			if (Calc.porcentagem(multiplicidade.getLista5Multiplos().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista5Multiplos());
			}
			if (Calc.porcentagem(multiplicidade.getLista6Multiplos().size(), listaQuadrantesFiltrada.size()) < 6) {
				listaMultiplicidadeFiltrada.removeAll(multiplicidade.getLista6Multiplos());
			}
		}
		listaQuadrantesFiltrada.clear();

		for (int i = 0; i < listaMultiplicidadeFiltrada.size(); i++) {
			listaMultiplicidadeFiltrada.get(i).setConcurso(i + 1);
		}

		return listaMultiplicidadeFiltrada;
	}

	private List<Jogo> filtraQuadrantes(List<Jogo> listaQuadraticosFiltrada) {

		listaQuadrantesFiltrada.addAll(listaQuadraticosFiltrada);

		System.out.println("Filtrando quadrantes");

		for (int i = 1; i <= 4; i++) {

			quadrantes = new Quadrantes(listaQuadraticosFiltrada, i);

			if (Calc.porcentagem(quadrantes.getLista0Incidencia().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista0Incidencia());
			}
			if (Calc.porcentagem(quadrantes.getLista1Incidencia().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista1Incidencia());
			}
			if (Calc.porcentagem(quadrantes.getLista2Incidencias().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista2Incidencias());
			}
			if (Calc.porcentagem(quadrantes.getLista3Incidencias().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista3Incidencias());
			}
			if (Calc.porcentagem(quadrantes.getLista4Incidencias().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista4Incidencias());
			}
			if (Calc.porcentagem(quadrantes.getLista5Incidencias().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista5Incidencias());
			}
			if (Calc.porcentagem(quadrantes.getLista6Incidencias().size(), listaQuadraticosFiltrada.size()) < 10) {
				listaQuadrantesFiltrada.removeAll(quadrantes.getLista6Incidencias());
			}
		}
		listaQuadraticosFiltrada.clear();

		for (int i = 0; i < listaQuadrantesFiltrada.size(); i++) {
			listaQuadrantesFiltrada.get(i).setConcurso(i + 1);
		}
		return listaQuadrantesFiltrada;
	}

	private List<Jogo> filtraQuadraticos(List<Jogo> listaPrimosFiltrada) {

		quadraticos = new Quadraticos(listaPrimosFiltrada);
		listaQuadraticosFiltrada.addAll(listaPrimosFiltrada);

		System.out.println("Filtrando quadráticos");

		if (Calc.porcentagem(quadraticos.getLista0Quadrado().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista0Quadrado());
		}
		if (Calc.porcentagem(quadraticos.getLista1Quadrado().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista1Quadrado());
		}
		if (Calc.porcentagem(quadraticos.getLista2Quadrados().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista2Quadrados());
		}
		if (Calc.porcentagem(quadraticos.getLista3Quadrados().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista3Quadrados());
		}
		if (Calc.porcentagem(quadraticos.getLista4Quadrados().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista4Quadrados());
		}
		if (Calc.porcentagem(quadraticos.getLista5Quadrados().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista5Quadrados());
		}
		if (Calc.porcentagem(quadraticos.getLista6Quadrados().size(), listaPrimosFiltrada.size()) < 10) {
			listaQuadraticosFiltrada.removeAll(quadraticos.getLista6Quadrados());
		}
		listaPrimosFiltrada.clear();

		for (int i = 0; i < listaQuadraticosFiltrada.size(); i++) {
			listaQuadraticosFiltrada.get(i).setConcurso(i + 1);
		}

		return listaQuadraticosFiltrada;
	}

	private List<Jogo> filtraPrimos(List<Jogo> listaFibonacciFiltrada) {

		primos = new Primalidade(listaFibonacciFiltrada);
		listaPrimosFiltrada.addAll(listaFibonacciFiltrada);

		System.out.println("Filtrando números primos");

		if (Calc.porcentagem(primos.getLista0Primo().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista0Primo());
		}
		if (Calc.porcentagem(primos.getLista1Primo().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista1Primo());
		}
		if (Calc.porcentagem(primos.getLista2Primos().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista2Primos());
		}
		if (Calc.porcentagem(primos.getLista3Primos().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista3Primos());
		}
		if (Calc.porcentagem(primos.getLista4Primos().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista4Primos());
		}
		if (Calc.porcentagem(primos.getLista5Primos().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista5Primos());
		}
		if (Calc.porcentagem(primos.getLista6Primos().size(), listaFibonacciFiltrada.size()) < 10) {
			listaPrimosFiltrada.removeAll(primos.getLista6Primos());
		}
		listaFibonacciFiltrada.clear();

		for (int i = 0; i < listaPrimosFiltrada.size(); i++) {
			listaPrimosFiltrada.get(i).setConcurso(i + 1);
		}

		return listaPrimosFiltrada;
	}

	private List<Jogo> filtraFibonacci(List<Jogo> listaCubicosFiltrada) {

		fibonacci = new Fibonacci(listaCubicosFiltrada);
		listaFibonacciFiltrada.addAll(listaCubicosFiltrada);

		System.out.println("Filtrando números fibonacci");

		if (Calc.porcentagem(fibonacci.getLista0Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista0Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista1Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista1Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista2Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista2Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista3Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista3Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista4Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista4Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista5Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista5Fibonacci());
		}
		if (Calc.porcentagem(fibonacci.getLista6Fibonacci().size(), listaCubicosFiltrada.size()) < 10) {
			listaFibonacciFiltrada.removeAll(fibonacci.getLista6Fibonacci());
		}
		listaCubicosFiltrada.clear();

		for (int i = 0; i < listaFibonacciFiltrada.size(); i++) {
			listaFibonacciFiltrada.get(i).setConcurso(i + 1);
		}

		return listaFibonacciFiltrada;
	}

	private List<Jogo> filtraCubicos(List<Jogo> listaParOuImparFiltrada) {

		cubicos = new Cubicos(listaParOuImparFiltrada);
		listaCubicosFiltrada.addAll(listaParOuImparFiltrada);

		System.out.println("Filtrando cúbicos");

		if (Calc.porcentagem(cubicos.getLista0Cubico().size(), listaParOuImparFiltrada.size()) < 10) {
			listaCubicosFiltrada.removeAll(cubicos.getLista0Cubico());
		}
		if (Calc.porcentagem(cubicos.getLista1Cubico().size(), listaParOuImparFiltrada.size()) < 10) {
			listaCubicosFiltrada.removeAll(cubicos.getLista1Cubico());
		}
		if (Calc.porcentagem(cubicos.getLista2Cubicos().size(), listaParOuImparFiltrada.size()) < 10) {
			listaCubicosFiltrada.removeAll(cubicos.getLista2Cubicos());
		}
		if (Calc.porcentagem(cubicos.getLista3Cubicos().size(), listaParOuImparFiltrada.size()) < 10) {
			listaCubicosFiltrada.removeAll(cubicos.getLista3Cubicos());
		}
		listaParOuImparFiltrada.clear();

		for (int i = 0; i < listaCubicosFiltrada.size(); i++) {
			listaCubicosFiltrada.get(i).setConcurso(i + 1);
		}

		return listaCubicosFiltrada;
	}

	private List<Jogo> filtraParOuImpar(List<Jogo> combinacoes) {

		parOuImpar = new ParOuImpar(combinacoes);
		listaParOuImparFiltrada.addAll(combinacoes);

		System.out.println("Filtrando pares e ímpares");

		if (Calc.porcentagem(parOuImpar.getLista0Par().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista0Par());
		}
		if (Calc.porcentagem(parOuImpar.getLista1Par().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista1Par());
		}
		if (Calc.porcentagem(parOuImpar.getLista2Pares().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista2Pares());
		}
		if (Calc.porcentagem(parOuImpar.getLista3Pares().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista3Pares());
		}
		if (Calc.porcentagem(parOuImpar.getLista4Pares().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista4Pares());
		}
		if (Calc.porcentagem(parOuImpar.getLista5Pares().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista5Pares());
		}
		if (Calc.porcentagem(parOuImpar.getLista6Pares().size(), combinacoes.size()) < 9) {
			listaParOuImparFiltrada.removeAll(parOuImpar.getLista6Pares());
		}
		combinacoes.clear();

		for (int i = 0; i < listaParOuImparFiltrada.size(); i++) {
			listaParOuImparFiltrada.get(i).setConcurso(i + 1);
		}

		return listaParOuImparFiltrada;
	}

	@SuppressWarnings("unchecked")
	private List<Jogo> criaListaCombinacoes(String nomeArquivo) {

		combinacoes = new ArrayList<>();

		System.out.println("Lendo " + nomeArquivo);

		long inicio = System.currentTimeMillis();

		try {
			combinacoes = (List<Jogo>) Deserializador
					.deserializa(DefineDiretorio.getDiretorioCombinacoes() + File.separator + nomeArquivo);
			System.out
					.println("Tempo: " + (System.currentTimeMillis() - inicio) / 1000 + " segundos");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return combinacoes;
	}

	public List<Jogo> getListaFinal() {
		return listaFinal;
	}

}
