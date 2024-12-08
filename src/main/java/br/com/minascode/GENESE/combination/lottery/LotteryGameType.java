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

package br.com.minascode.GENESE.combination.lottery;

public enum LotteryGameType {
    MEGA_SENA(1, 60, 6, 6),
    LOTO_FACIL(1, 25, 15, 5);

    private final int minRange;       // Menor número do jogo
    private final int maxRange;       // Maior número do jogo
    private final int selectionSize;  // Quantidade de números escolhidos no jogo
    private final int lineCount;      // Quantidade de linhas no jogo

    LotteryGameType(int minRange, int maxRange, int selectionSize, int lineCount) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.selectionSize = selectionSize;
        this.lineCount = lineCount;
    }

    public int getMinRange() {
        return minRange;
    }

    public int getMaxRange() {
        return maxRange;
    }

    public int getSelectionSize() {
        return selectionSize;
    }

    public int getLineCount() {
        return lineCount;
    }
}

