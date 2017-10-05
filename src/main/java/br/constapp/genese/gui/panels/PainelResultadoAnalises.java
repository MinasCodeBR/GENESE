/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2017  Rafael Teixeira
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

package br.constapp.genese.gui.panels;

import br.constapp.genese.processamento.*;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelResultadoAnalises extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel painelResultadoAnalises;
	private JComboBox<String> comboBox;
	private static JPanel painelTextArea;
	private static JTextArea textArea;

	public PainelResultadoAnalises() {

		preparaComboBox();
		preparaPainelTextArea();
		preparaPainelResultadoAnalises();

	}

	public static void setTextArea(String text) {

		textArea.append(text);
	}

	private void preparaPainelTextArea() {

		painelTextArea = new JPanel();
		painelTextArea.setBackground(Color.WHITE);
		painelTextArea.setBorder(null);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		JScrollPane barraRolagemTxtArea = new JScrollPane();
		barraRolagemTxtArea.setViewportView(textArea);
		GroupLayout gl_painelTextArea = new GroupLayout(painelTextArea);
		gl_painelTextArea.setHorizontalGroup(gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup().addContainerGap()
						.addComponent(barraRolagemTxtArea, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addContainerGap()));
		gl_painelTextArea.setVerticalGroup(gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup().addGap(15)
						.addComponent(barraRolagemTxtArea, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
						.addContainerGap()));
		painelTextArea.setLayout(gl_painelTextArea);
	}

	private void preparaPainelResultadoAnalises() {

		painelResultadoAnalises = new JPanel();
		painelResultadoAnalises.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelResultadoAnalises.setBorder(null);
		painelResultadoAnalises.setBackground(Color.WHITE);

		GroupLayout gl_painelResultadoAnalises = new GroupLayout(painelResultadoAnalises);
		gl_painelResultadoAnalises.setHorizontalGroup(gl_painelResultadoAnalises.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelResultadoAnalises.createSequentialGroup().addContainerGap()
						.addGroup(gl_painelResultadoAnalises.createParallelGroup(Alignment.TRAILING)
								.addComponent(painelTextArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 340,
										Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.LEADING, 0, 340, Short.MAX_VALUE))
						.addContainerGap()));
		gl_painelResultadoAnalises.setVerticalGroup(gl_painelResultadoAnalises.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultadoAnalises.createSequentialGroup().addGap(25)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(painelTextArea, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
						.addContainerGap()));

		painelResultadoAnalises.setLayout(gl_painelResultadoAnalises);

	}

	public static JPanel getPainelResultadoAnalises() {
		return painelResultadoAnalises;
	}

	private void preparaComboBox() {

		String[] classesAnalise = new String[]{"Cúbicos", "Faixa de números", "Fibonacci", "Multiplicidade",
				"Par ou Ímpar", "Primalidade", "Quadráticos"};

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(classesAnalise);

		comboBox = new JComboBox<String>(comboModel);
		comboBox.setRequestFocusEnabled(false);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(comboBox.getSelectedIndex());

				if (comboBox.getSelectedItem() == "Cúbicos") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					CubicosPro.processaCubicos();

				}

				if (comboBox.getSelectedItem() == "Faixa de números") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 10));
					FaixaDeNumerosPro.processaFaixaDeNumeros();

				}

				if (comboBox.getSelectedItem() == "Fibonacci") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					FibonacciPro.processaFibonacci();

				}

				if (comboBox.getSelectedItem() == "Multiplicidade") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					MultiplicidadePro.processaMultiplicidade();

				}

				if (comboBox.getSelectedItem() == "Par ou Ímpar") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					ParOuImparPro.processaParOuImpar();

				}

				if (comboBox.getSelectedItem() == "Primalidade") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					PrimalidadePro.processaPrimalidade();

				}

				if (comboBox.getSelectedItem() == "Quadráticos") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					QuadraticosPro.processaQuadráticos();

				}

			}

		});

	}
}
