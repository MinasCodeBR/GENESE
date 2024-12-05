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

package br.com.minascode.GENESE.serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Classe para deserializar objetos de um arquivo serializado.
 */
public final class Deserializer {

	/**
	 * Construtor privado para evitar instanciação.
	 */
	private Deserializer() {}

	/**
	 * Deserializa um objeto a partir de um arquivo serializado.
	 *
	 * @param path O caminho do arquivo serializado.
	 * @return O objeto deserializado.
	 * @throws Exception Se ocorrer algum erro durante a deserialização.
	 */
	public static Object deserialize(String path) throws Exception {
		try (FileInputStream inFile = new FileInputStream(path);
				ObjectInputStream objInStream = new ObjectInputStream(inFile)) {
			return objInStream.readObject();
		}
	}
}
