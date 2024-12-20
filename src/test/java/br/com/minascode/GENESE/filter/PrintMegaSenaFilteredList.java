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

package br.com.minascode.GENESE.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.minascode.GENESE.entities.MegaSena;
import br.com.minascode.GENESE.path.DefineDirectory;
import br.com.minascode.GENESE.serialization.Deserializer;

public class PrintMegaSenaFilteredList {

	private static ArrayList<MegaSena> listaParaImprimir;
	static String nomeArquivo;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		listaParaImprimir = new ArrayList<MegaSena>();
		
		nomeArquivo = "lista_" + 114 + ".ser";
		
		System.out.println(nomeArquivo);

		try {
			listaParaImprimir.addAll((List<MegaSena>) Deserializer
					.deserialize(DefineDirectory.getMegaSenaFilterDir() + File.separator + nomeArquivo));
		} catch (Exception e) {
			e.printStackTrace();
		}// TODO Auto-generated method stub		
		
				
		int i = 0;
		for (MegaSena jogo : listaParaImprimir) {
			i++;
			System.out.println(jogo);			
		}	
		System.out.println(i);
	}

}
