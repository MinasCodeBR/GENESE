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

import br.com.minascode.GENESE.combination.lottery.LotteryGameType;
import br.com.minascode.GENESE.path.DefineDirectory;
import br.com.minascode.GENESE.util.ConnectionTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadGameSpreadsheet {

    private String url;
    private String fileName;
    private String downloadsDirectory;
    private LotteryGameType gameType;

    public DownloadGameSpreadsheet(LotteryGameType gameType) {
    	this.gameType = gameType;
        downloadsDirectory = DefineDirectory.getCefDirectory();

        switch (gameType) {
            case MEGA_SENA:
                url = "https://loterias.caixa.gov.br/Paginas/Mega-Sena.aspx";
                fileName = "MegaSena.xlsx";
                break;
            case LOTO_FACIL:
                url = "https://loterias.caixa.gov.br/Paginas/Lotofacil.aspx";
                fileName = "Lotofacil.xlsx";
                break;
            default:
                throw new IllegalArgumentException("Tipo de jogo não suportado: " + gameType);
        }

        if (!ConnectionTest.connected()) {
            JOptionPane.showMessageDialog(null, "Você não está conectado à Internet.", "Sem conexão",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            if (accessWebsite(url)) {
                handleDownloadedFile();
            } else {
                System.out.println("Falha ao acessar o site ou baixar a planilha.");
            }
        }
    }

    private boolean accessWebsite(String url) {
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    	WebDriverManager.chromedriver().setup();
    	
        ChromeOptions options = new ChromeOptions();        

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadsDirectory);
        
        
        options.setExperimentalOption("prefs", prefs);
        //options.addArguments("--headless=new");
        options.addArguments("--window-size=800,600");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        

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

            Thread.sleep(5000);

            return true;
        } catch (TimeoutException | NoSuchElementException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar baixar a planilha: " + e.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void handleDownloadedFile() {
        File latestDownloadedFile = findLatestDownloadedFile(downloadsDirectory);
        if (latestDownloadedFile != null) {
            if (fileExistsWithFileName(downloadsDirectory, fileName)) {
                deleteFilesWithFileName(downloadsDirectory, fileName);
            }
            renameFile(latestDownloadedFile, fileName);
            System.out.println("Download dos jogos " + gameType + " realizado! Arquivo salvo em: " + downloadsDirectory);
        } else {
            System.out.println("Nenhum arquivo encontrado no diretório após o download.");
        }
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
                    .max(Comparator.comparingLong(DownloadGameSpreadsheet::getLastModifiedTime)).orElse(null)
                    .toFile();
        } catch (IOException e) {
            e.printStackTrace();
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
                    .forEach(DownloadGameSpreadsheet::deleteFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


