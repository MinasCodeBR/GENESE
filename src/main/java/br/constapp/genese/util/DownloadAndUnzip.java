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

package br.constapp.genese.util;

import java.io.*;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class DownloadAndUnzip {

    private DownloadAndUnzip(){}

    private static GetCookie gc = new GetCookie();

    /**
     * Baixa o arquivo de uma URL
     *
     * @param url Arquivo na Web
     * @param targetDir pastaDestino\temp
     * @return unpackArquivo
     * @throws IOException exception
     */

    public static File downloadArquivo(URL url, File targetDir) throws IOException {

        String dirTemp = targetDir + File.separator + "temp";
        File targetDirTemp = new File(dirTemp);
        if (!buildDirectory(targetDirTemp)) {
            throw new IOException("Não foi possível criar o diretório: " + targetDirTemp);
        }

        gc.getCookie(url);
        InputStream in = null;
        try {
            in = new BufferedInputStream(url.openStream(), 1024);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        // garantindo que estamos pegando o arquivo mais recente
        File zip = File.createTempFile("CEF", ".zip", targetDirTemp);
        zip.deleteOnExit();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(zip));

        copyInputStream(in, out);
        out.close();

        return unpackArquivo(zip, targetDir);
    }

    /**
     * Descompacta o arquivo
     *
     * @param theFile Arquivo baixado
     * @param targetDir pastaDestino
     * @return theFile
     * @throws IOException exception
     */
    
	private static File unpackArquivo(File theFile, File targetDir) throws IOException {

        if (!theFile.exists()) {
            throw new IOException(theFile.getAbsolutePath() + " não existe");
        }
        if (!buildDirectory(targetDir)) {
            throw new IOException("Não foi possível criar o diretório: " + targetDir);
        }

        @SuppressWarnings("resource")
        ZipFile zipFile = new ZipFile(theFile);
        for (Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements(); ) {
            ZipEntry entry = entries.nextElement();
            File file = new File(targetDir, File.separator + entry.getName());
            if (!buildDirectory(file.getParentFile())) {
                throw new IOException("Não foi possível criar o diretório: " + file.getParentFile());
            }
            if (!entry.isDirectory()) {
                copyInputStream(zipFile.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(file)));
            } else {
                if (!buildDirectory(file)) {
                    throw new IOException("Não foi possível criar o diretório: " + file);
                }
            }
        }        
        zipFile.close();
        
        System.out.println("Arquivo descompactado em " + targetDir.getAbsolutePath());
        
        return theFile;
    }

    public static void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len = in.read(buffer);
        while (len >= 0) {
            out.write(buffer, 0, len);
            len = in.read(buffer);
        }
        in.close();
        out.close();
    }

    private static boolean buildDirectory(File file) {
        return file.exists() || file.mkdirs();
    }
}
