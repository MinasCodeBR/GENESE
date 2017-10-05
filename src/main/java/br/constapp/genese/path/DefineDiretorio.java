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

package br.constapp.genese.path;

import java.io.File;
import java.io.IOException;

public final class DefineDiretorio {

    private static String diretorioRaiz;
    private static String diretorioCEF;
    private static String diretorioCombinacoes;
    private static final String USERPROFILE = System.getenv("USERPROFILE");
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String OS_NAME = System.getProperty("os.name");

    private DefineDiretorio() {}

    public static String getDiretorioRaiz() {
        try {
            setDiretorioRaiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diretorioRaiz + File.separator;
    }

    private static void setDiretorioRaiz() throws IOException {

        if (OS_NAME.toLowerCase().contains("windows")) {
            diretorioRaiz = (USERPROFILE + "\\AppData\\Local\\GENESE");
        } else {
            if (OS_NAME.toLowerCase().contains("linux")) {
                diretorioRaiz = (USER_HOME + "/.genese/CEF");
            }
        }
        File targetDir = new File(diretorioRaiz);
        if (!buildDirectory(targetDir)) {
            throw new IOException("Não foi possível criar o diretório: " + targetDir);
        }
    }

    public static String getDiretorioCEF() {
        try {
            setDiretorioCEF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diretorioCEF + File.separator;
    }

    private static void setDiretorioCEF() throws IOException {

        if (OS_NAME.toLowerCase().contains("windows")) {
            diretorioCEF = (USERPROFILE + "\\AppData\\Local\\GENESE\\CEF");
        } else {
            if (OS_NAME.toLowerCase().contains("linux")) {
                diretorioCEF = (USER_HOME + "/.genese/CEF");
            }
        }
        File targetDir = new File(diretorioCEF);
        if (!buildDirectory(targetDir)) {
            throw new IOException("Não foi possível criar o diretório: " + targetDir);
        }
    }
    
    public static String getDiretorioCombinacoes() {
        try {
        	setDiretorioCombinacoes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return diretorioCombinacoes + File.separator;
    }

    private static void setDiretorioCombinacoes() throws IOException {

        if (OS_NAME.toLowerCase().contains("windows")) {
        	diretorioCombinacoes = (USERPROFILE + "\\AppData\\Local\\GENESE\\serial\\cmb");
        } else {
            if (OS_NAME.toLowerCase().contains("linux")) {
            	diretorioCombinacoes = (USER_HOME + "/.genese/serial/cmb");
            }
        }
        File targetDir = new File(diretorioCombinacoes);
        if (!buildDirectory(targetDir)) {
            throw new IOException("Não foi possível criar o diretório: " + targetDir);
        }
    }

    private static boolean buildDirectory(File file) {
        return file.exists() || file.mkdirs();
    }
}

