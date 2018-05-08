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
