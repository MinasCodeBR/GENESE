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

package br.com.minascode.GENESE.serialization;

import java.io.*;

/**
 * Classe para serializar objetos em um arquivo.
 */
public final class Serializer {

    private static ObjectOutputStream objectStream = null;

    /**
     * Construtor privado para evitar instanciação.
     */
    private Serializer() {}

    /**
     * Inicia a serialização em lote.
     *
     * @param path O caminho do arquivo onde os objetos serão serializados.
     * @throws IOException Se ocorrer algum erro ao abrir o arquivo.
     */
    public static void startBatchSerialization(String path) throws IOException {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        objectStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
    }

    /**
     * Serializa um objeto para o lote atual.
     *
     * @param obj O objeto a ser serializado.
     * @throws IOException Se ocorrer algum erro durante a escrita.
     */
    public static void serializeToBatch(Object obj) throws IOException {
        if (objectStream == null) {
            throw new IllegalStateException("Batch serialization has not been started. Call startBatchSerialization first.");
        }
        objectStream.writeObject(obj);
    }

    /**
     * Finaliza a serialização em lote.
     *
     * @throws IOException Se ocorrer algum erro ao fechar o arquivo.
     */
    public static void endBatchSerialization() throws IOException {
        if (objectStream != null) {
            objectStream.close();
            objectStream = null;
        }
    }

    /**
     * Serializa um objeto em um arquivo.
     *
     * @param path O caminho do arquivo onde o objeto será serializado.
     * @param obj  O objeto a ser serializado.
     * @throws IOException Se ocorrer algum erro durante a serialização.
     */
    public static void serialize(String path, Object obj) throws IOException {
        File file = new File(path);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objOutStream.writeObject(obj);
        }
    }

    /**
     * Serializa um texto em um arquivo com codificação UTF-8.
     *
     * @param path     O caminho do arquivo onde o texto será serializado.
     * @param textFile O texto a ser serializado.
     * @throws Exception Se ocorrer algum erro durante a serialização.
     */
    public static void serializeUtf8(String path, String textFile) throws Exception {
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path), "UTF-8")) {
            outputStreamWriter.write(textFile);
        }
    }
}
