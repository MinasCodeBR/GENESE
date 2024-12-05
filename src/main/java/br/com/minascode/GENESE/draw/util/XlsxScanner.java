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

package br.com.minascode.GENESE.draw.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxScanner {

    private final GameScannerConfig gameConfig;
    private List<String> drawList;

    public XlsxScanner(GameScannerConfig gameConfig) {
        this.gameConfig = gameConfig;
        try {
            readSheet();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("resource")
    private void readSheet() throws IOException {
        drawList = new ArrayList<>();
        String fileName = gameConfig.getSheetPath(); // Caminho específico do arquivo Excel
        try (FileInputStream file = new FileInputStream(fileName)) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0); // Obter a primeira planilha

            boolean isFirstRow = true;

            for (Row row : sheet) {
                if (isFirstRow) {
                    isFirstRow = false;
                    continue;
                }

                for (Cell cell : row) {
                    String cellValue = getCellValue(cell);
                    String cleanedValue = cleanCellCharacters(cellValue);
                    drawList.add(cleanedValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Limpa valores indesejados
        drawList.removeIf(p -> p.replaceAll("(?<![0-9])[./,]|[./,](?![0-9])", "").trim().isEmpty());

        // Executa a limpeza final na lista apenas se necessário
        if (gameConfig.requiresCleanDrawList()) {
            cleanDrawList();
        }
    }

    private String getCellValue(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private String cleanCellCharacters(String cellValue) {
        String cleanedValue = cellValue.replaceAll("(?<![0-9])[./,]|[./,](?![0-9])", "");
        cleanedValue = cleanedValue.replaceAll("\\s+", " ");
        cleanedValue = cleanedValue.trim();
        cleanedValue = cleanedValue.replaceAll("[A-Za-z!?À-ÿ():$;'\\s¬-]", "").replaceAll("0,00", "0");
        return cleanedValue;
    }

    private void cleanDrawList() {
        int drawStep = gameConfig.getDrawStep();
        int index = 0;

        while (index + drawStep < drawList.size()) {
            int currentDraw = Integer.parseInt(drawList.get(index));
            String nextDraw = drawList.get(index + drawStep);

            if (isInteger(nextDraw)) {
                int nextDrawNumber = Integer.parseInt(nextDraw);

                if (nextDrawNumber - currentDraw != 1) {
                    drawList.remove(index + drawStep);
                } else {
                    index += drawStep;
                }
            } else {
                drawList.remove(index + drawStep);
            }
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public List<String> getDrawList() {
        return new ArrayList<>(drawList);
    }
}

