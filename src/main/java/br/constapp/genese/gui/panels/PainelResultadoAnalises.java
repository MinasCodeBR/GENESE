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

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import br.constapp.genese.gui.text.CubicosText;
import br.constapp.genese.gui.text.FaixaDeNumerosText;
import br.constapp.genese.gui.text.FibonacciText;
import br.constapp.genese.gui.text.MaisMenosText;
import br.constapp.genese.gui.text.MultiplicidadeText;
import br.constapp.genese.gui.text.ParOuImparText;
import br.constapp.genese.gui.text.PrimalidadeText;
import br.constapp.genese.gui.text.QuadrantesText;
import br.constapp.genese.gui.text.QuadraticosText;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		JScrollPane barraRolagemTxtArea = new JScrollPane();
		barraRolagemTxtArea.setViewportView(textArea);
		GroupLayout gl_painelTextArea = new GroupLayout(painelTextArea);
		gl_painelTextArea.setHorizontalGroup(gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup().addContainerGap()
						.addComponent(barraRolagemTxtArea, GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
						.addContainerGap()));
		gl_painelTextArea.setVerticalGroup(gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup().addContainerGap()
						.addComponent(barraRolagemTxtArea, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
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
						.addComponent(comboBox, 0, 511, Short.MAX_VALUE).addContainerGap())
				.addComponent(painelTextArea, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE));
		gl_painelResultadoAnalises.setVerticalGroup(gl_painelResultadoAnalises.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultadoAnalises.createSequentialGroup().addGap(25)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(painelTextArea, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)));

		painelResultadoAnalises.setLayout(gl_painelResultadoAnalises);

	}

	public static JPanel getPainelResultadoAnalises() {
		return painelResultadoAnalises;
	}

	private void preparaComboBox() {

		String[] classesAnalise = new String[] { "Cúbicos", "Fibonacci", "Par ou Ímpar", "Primalidade", "Quadráticos",
				"Mais e Menos Sorteados", "Quadrantes" ,"Faixa de números", "Multiplicidade"};

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(classesAnalise);

		comboBox = new JComboBox<String>(comboModel);
		comboBox.setMaximumRowCount(9);
		comboBox.setRequestFocusEnabled(false);

		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(comboBox.getSelectedIndex());

				if (comboBox.getSelectedItem() == "Cúbicos") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					CubicosText.processaCubicos();
				}

				if (comboBox.getSelectedItem() == "Faixa de números") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					FaixaDeNumerosText.processaFaixaDeNumeros();
				}

				if (comboBox.getSelectedItem() == "Fibonacci") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					FibonacciText.processaFibonacci();
				}

				if (comboBox.getSelectedItem() == "Multiplicidade") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					MultiplicidadeText.processaMultiplicidade();
				}

				if (comboBox.getSelectedItem() == "Par ou Ímpar") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					ParOuImparText.processaParOuImpar();
				}

				if (comboBox.getSelectedItem() == "Primalidade") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					PrimalidadeText.processaPrimalidade();
				}

				if (comboBox.getSelectedItem() == "Quadráticos") {

					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					QuadraticosText.processaQuadráticos();
				}

				if (comboBox.getSelectedItem() == "Mais e Menos Sorteados") {
					
					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					MaisMenosText.processaMaisMenos();
				}
				
				if (comboBox.getSelectedItem() == "Quadrantes") {
					
					textArea.setText("");
					textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
					QuadrantesText.processaQuadrantes();
				}
			}

		});

	}
}
