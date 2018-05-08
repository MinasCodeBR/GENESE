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

package br.constapp.genese.gui.frames;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import br.constapp.genese.gui.Principal;

public class FrameSobre extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel painelSobre;
	private static JTextArea txtrLicense;
	private static JLabel lblVersion;
	private JScrollPane textScrollPane;

	public FrameSobre() {

		super("Sobre");
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);

		criaTxtrLicense();
		criaPainelSobre();
		criaJanela();

		Principal.frameSobreEstaFechado(false);

	}

	private void criaPainelSobre() {

		painelSobre = new JPanel();
		painelSobre.setBackground(Color.WHITE);

		JLabel lblGenese = new JLabel("Genese");
		lblGenese.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblFrase = new JLabel("Gerador de números e estatísticas para Mega-Sena.");
		lblFrase.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lblVersao = new JLabel("Versão:");

		JLabel lblEmail = new JLabel("Copyright (C)  2018 - rafaelfst@live.com");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 11));

		lblVersion = new JLabel("1.0.0");

		GroupLayout gl_painelSobre = new GroupLayout(painelSobre);
		gl_painelSobre.setHorizontalGroup(gl_painelSobre.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelSobre.createSequentialGroup().addContainerGap().addComponent(lblEmail)
						.addPreferredGap(ComponentPlacement.RELATED, 224, Short.MAX_VALUE).addComponent(lblVersao)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblVersion).addContainerGap())
				.addGroup(gl_painelSobre.createSequentialGroup().addGap(179).addComponent(lblGenese)
						.addContainerGap(180, Short.MAX_VALUE))
				.addGroup(gl_painelSobre.createSequentialGroup().addContainerGap(61, Short.MAX_VALUE)
						.addComponent(lblFrase).addGap(61))
				.addGroup(gl_painelSobre.createSequentialGroup().addContainerGap()
						.addComponent(textScrollPane, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
						.addContainerGap()));
		gl_painelSobre.setVerticalGroup(gl_painelSobre.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelSobre.createSequentialGroup().addContainerGap().addComponent(lblGenese)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblFrase).addGap(18)
						.addComponent(textScrollPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addGroup(gl_painelSobre.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVersao).addComponent(lblEmail).addComponent(lblVersion))
						.addGap(4)));
		painelSobre.setLayout(gl_painelSobre);

	}

	private void criaTxtrLicense() {
		
		File file = getResourceAsFile("LICENSE.txt");
		FileInputStream fis = null;
		String texto = "";

		try {
			fis = new FileInputStream(file);
			int content;
			while ((content = fis.read()) != -1) {
				texto += (char) content;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		txtrLicense = new JTextArea(texto);
		txtrLicense.setMargin(new Insets(2, 12, 2, 2));
		txtrLicense.setWrapStyleWord(true);
		txtrLicense.setLineWrap(true);
		txtrLicense.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrLicense.setEditable(false);

		textScrollPane = new JScrollPane(txtrLicense);
		textScrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		textScrollPane.setBorder(null);
		textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		textScrollPane.setPreferredSize(new Dimension(250, 145));
		textScrollPane.setMinimumSize(new Dimension(10, 10));
		txtrLicense.setCaretPosition(0);

	}
	
	private static File getResourceAsFile(String resourcePath) {
	    try {
	        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
	        if (in == null) {
	            return null;
	        }

	        File tempFile = File.createTempFile(String.valueOf(in.hashCode()), ".tmp");
	        tempFile.deleteOnExit();

	        try (FileOutputStream out = new FileOutputStream(tempFile)) {
	            //copy stream
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = in.read(buffer)) != -1) {
	                out.write(buffer, 0, bytesRead);
	            }
	        }
	        return tempFile;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	private void criaJanela() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// <- prevent closing
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.frameSobreEstaFechado(true);
				dispose();
			}
		});

		setSize(420, 360);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		setVisible(true);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(6)
						.addComponent(painelSobre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(5)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout
						.createSequentialGroup().addGap(9).addComponent(painelSobre, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(99, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

	}
}
