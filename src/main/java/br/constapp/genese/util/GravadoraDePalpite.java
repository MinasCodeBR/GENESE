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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.constapp.genese.gui.panels.PainelResultadoPalpites;

public class GravadoraDePalpite {

	public static void salvaArquivo() throws IOException {
		JFileChooser salvandoArquivo = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos de texto (*.txt)", "*.txt", "txt");
		salvandoArquivo.setFileFilter(filter);
		salvandoArquivo.setSelectedFile(new File("palpites.txt"));
		int resultado = salvandoArquivo.showSaveDialog(null);
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File arquivo = salvandoArquivo.getSelectedFile();
		String stgArquivo = salvandoArquivo.getSelectedFile().toString();

		if (arquivo.exists()) {

			int escolha = JOptionPane.showConfirmDialog(null, "O arquivo já existe! Deseja sobrescrevê-lo?", null,
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (escolha != JOptionPane.YES_OPTION) {

				if (escolha == JOptionPane.NO_OPTION) {

					if (stgArquivo.endsWith(".txt")) {
						stgArquivo = stgArquivo.substring(0, stgArquivo.lastIndexOf(".txt")) + time();
					} else {
						stgArquivo = stgArquivo.substring(0, stgArquivo.lastIndexOf(".")) + time();
					}

					arquivo = new File(stgArquivo);

					System.out.println(stgArquivo);
				}
				if (escolha == JOptionPane.CANCEL_OPTION || escolha == JOptionPane.CLOSED_OPTION) {
					return;
				}
			}
		}

		if (!stgArquivo.endsWith(".txt")) {
			arquivo = new File(stgArquivo + ".txt");
		}

		PrintWriter pw = new PrintWriter(new FileWriter(arquivo));

		pw.printf("+-----------Palpites-----------+%n%n");

		pw.print((PainelResultadoPalpites.getTextPane().getText()));

		pw.printf("+------------------------------+%n");

		pw.close();
	}

	private static String time() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy_HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}
