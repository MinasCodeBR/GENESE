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

package br.constapp.genese.util;

import java.net.*;
import java.util.List;

class GetCookie {

    void getCookie(URL url) {
        try {
            // Instancia CookieManager;
            // assegura de configurar CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // pega conteudo de URLConnection;
            // os cookies sao definidos pelo site
            URLConnection connection = url.openConnection();
            connection.getContent();

            // recebe os cookies da CookieStore
            CookieStore cookieJar = manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            for (HttpCookie cookie : cookies) {
                System.out.println("CookieHandler - Cookie recuperado: " + cookie);
            }
        } catch (Exception e) {
            System.out.println("Não foi possível obter cookie usando CookieHandler");

        }
    }
}
