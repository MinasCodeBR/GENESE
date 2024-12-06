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

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CustomDocumentFilter extends DocumentFilter {

	private boolean isValid(String testText) {
        if (testText.length() > 3) {
           return false;
        }
        if (testText.isEmpty()) {        	
           return true;
        }
        int intValue = 1;
        try {
           intValue = Integer.parseInt(testText.trim());
        } catch (NumberFormatException e) {
           return false;
        }
        return intValue >= 1 && intValue <= 100;
    }

     @Override
     public void insertString(FilterBypass fb, int offset, String text,
           AttributeSet attr) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
		sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        sb.insert(offset, text);
        if (isValid(sb.toString())) {
           super.insertString(fb, offset, text, attr);
        }
     }

     @Override
     public void replace(FilterBypass fb, int offset, int length,
           String text, AttributeSet attrs) throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        int end = offset + length;
        sb.replace(offset, end, text);
        if (isValid(sb.toString())) {
           super.replace(fb, offset, length, text, attrs);
        }
     }

     @Override
     public void remove(FilterBypass fb, int offset, int length)
           throws BadLocationException {
        StringBuilder sb = new StringBuilder();
        sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
        int end = offset + length;
        sb.delete(offset, end);
        if (isValid(sb.toString())) {
           super.remove(fb, offset, length);
        }
     }
  }
