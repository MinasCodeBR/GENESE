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

package br.com.minascode.GENESE.util;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionTest {

    private ConnectionTest() {}

    public static boolean connected() {
        try (Socket socket = new Socket()) {
            // Tenta conectar ao DNS público do Google com um timeout de 2 segundos
            socket.connect(new InetSocketAddress("8.8.8.8", 53), 2000);
            System.out.println("Conectado!");
            return true;
        } catch (Exception e) {
            // Se não conseguir conectar, considera sem conexão
            System.out.println("Não conectado!");
            return false;
        }
    }
}

