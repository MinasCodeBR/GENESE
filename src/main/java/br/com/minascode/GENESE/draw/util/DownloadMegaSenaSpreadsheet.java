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

package br.com.minascode.GENESE.draw.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.minascode.GENESE.path.DefineDirectory;
import br.com.minascode.GENESE.util.ConnectionTest;

public class DownloadMegaSenaSpreadsheet {

    String url = "https://loterias.caixa.gov.br/Paginas/Mega-Sena.aspx";
    String downloadsDirectory = DefineDirectory.getCefDirectory();

    public DownloadMegaSenaSpreadsheet() {

        if (!ConnectionTest.connected()) {
            JOptionPane.showMessageDialog(null, "Você não está conectado a Internet.", "Sem conexão",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (accessWebsite(url)) {
                String newFileName = "MegaSena.xlsx";

                File latestDownloadedFile = findLatestDownloadedFile(downloadsDirectory);
                if (latestDownloadedFile != null) {
                    if (fileExistsWithFileName(downloadsDirectory, newFileName)) {
                        deleteFilesWithFileName(downloadsDirectory, newFileName);
                    }
                    renameFile(latestDownloadedFile, newFileName);
                    System.out.println("Download realizado! Arquivo salvo em: " + downloadsDirectory);
                } else {
                    System.out.println("Nenhum arquivo encontrado no diretório após o download.");
                }
            } else {
                System.out.println("Falha ao acessar o site ou baixar a planilha.");
            }
        }
    }

    private boolean accessWebsite(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadsDirectory);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get(url);

            return downloadSpreadsheet(driver);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            driver.close();
        }
    }

    private boolean downloadSpreadsheet(WebDriver driver) {
        try {
            WebElement downloadButton = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"btnResultados\"]")));
            downloadButton.click();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return true;
        } catch (TimeoutException timeOut) {
            JOptionPane.showMessageDialog(null,
                    "Tempo limite atingido. O sistema da Caixa pode estar sobrecarregado ou offline. Por favor, tente novamente mais tarde.\n"
                            + "Se o problema persistir, o layout do site pode ter sido alterado. "
                            + "Nesse caso, você precisará esperar que este programa seja atualizado.\n\n" + timeOut,
                    "TIMEOUT ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NoSuchElementException noElement) {
            JOptionPane.showMessageDialog(null,
                    "Elemento não encontrado. Por favor, tente novamente mais tarde.\n"
                            + "Se o problema persistir, o layout do site pode ter sido alterado. "
                            + "Nesse caso, você precisará esperar que este programa seja atualizado.\n\n" + noElement,
                    "ELEMENT NOT FOUND", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static long getLastModifiedTime(Path path) {
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            return attributes.lastModifiedTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static File findLatestDownloadedFile(String directory) {
        try (Stream<Path> stream = Files.walk(Paths.get(directory), FileVisitOption.FOLLOW_LINKS)) {
            return stream.filter(Files::isRegularFile).filter(path -> path.toString().endsWith(".xlsx"))
                    .max(Comparator.comparingLong(DownloadMegaSenaSpreadsheet::getLastModifiedTime)).orElse(null)
                    .toFile();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException nullPointerException) {
            JOptionPane.showMessageDialog(null,
                    "A planilha contendo os resultados não foi encontrada. Por favor, tente novamente mais tarde.\n"
                            + "Se o problema persistir, o layout do site pode ter sido alterado. "
                            + "Nesse caso, você precisará esperar que este programa seja atualizado.\n\n",
                    "SPREADSHEET NOT FOUND", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void renameFile(File file, String newName) {
        File newFile = new File(file.getParent(), newName);
        if (file.renameTo(newFile)) {
            file = newFile;
        }
    }

    public static boolean fileExistsWithFileName(String directory, String fileName) {
        try (Stream<Path> stream = Files.walk(Paths.get(directory), FileVisitOption.FOLLOW_LINKS)) {
            return stream.filter(Files::isRegularFile).anyMatch(path -> path.getFileName().toString().equals(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void deleteFile(Path path) {
        try {
            Files.delete(path);
            System.out.println("Planilha anterior deletada.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFilesWithFileName(String directory, String fileName) {
        try (Stream<Path> stream = Files.walk(Paths.get(directory), FileVisitOption.FOLLOW_LINKS)) {
            stream.filter(Files::isRegularFile).filter(path -> path.getFileName().toString().equals(fileName))
                    .forEach(DownloadMegaSenaSpreadsheet::deleteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

