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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class UnzipFile {

    private UnzipFile(){}

    /**
     * Descompacta o arquivo
     *
     * @param theFile Arquivo baixado
     * @param targetDir pastaDestino
     * @return theFile
     * @throws IOException exception
     */
    
	public static File unpackFile(File theFile, File targetDir) throws IOException {

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

    private static void copyInputStream(InputStream in, OutputStream out) throws IOException {
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
