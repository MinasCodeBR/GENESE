/*
 *     GENESE - Gerador de Números e Estatísticas para Mega-Sena
 *     Copyright (C)  2016  Rafael Teixeira
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
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class PainelCubicos extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel painelCubicos;
	private static JComboBox<String> comboBoxCub;
	private static int numCombinacoes = 0;
	private static int size0Cubo = 0;
	private static int size1Cubo = 0;
	private static int size2Cubos = 0;
	private static int size3Cubos = 0;
	private static JLabel lbl_2;
	private static JLabel lbl_resultado;

    public PainelCubicos() {

		preparaPainelCubicos();

	}

	public static void geraEtiquetas() {

		String[] opt = new String[] { "-", "0", "1", "2", "3" };

		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<String>(opt);

		comboBoxCub = new JComboBox<String>(comboModel);
		comboBoxCub.setRequestFocusEnabled(false);
		comboBoxCub.setFont(new Font("Open Sans", Font.PLAIN, 15));
		comboBoxCub.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBoxCub.setBackground(Color.WHITE);

		comboBoxCub.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println(comboBoxCub.getSelectedIndex());

				if (comboBoxCub.getSelectedItem() == "-") {

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_2.setText("número cúbico:");

					lbl_resultado.setFont(new Font("Open Sans", Font.PLAIN, 20));
					lbl_resultado.setText(" - %");

				}

				if (comboBoxCub.getSelectedItem() == "0") {

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_2.setText("número cúbico:");

					lbl_resultado.setFont(new Font("Open Sans", Font.PLAIN, 20));
					lbl_resultado.setText(Calc.porcentagem(size0Cubo, numCombinacoes));

				}

				if (comboBoxCub.getSelectedItem() == "1") {

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_2.setText("número cúbico:");

					lbl_resultado.setFont(new Font("Open Sans", Font.PLAIN, 20));
					lbl_resultado.setText(Calc.porcentagem(size1Cubo, numCombinacoes));

				}

				if (comboBoxCub.getSelectedItem() == "2") {

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_2.setText("números cúbicos:");

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_resultado.setText(Calc.porcentagem(size2Cubos, numCombinacoes));

				}

				if (comboBoxCub.getSelectedItem() == "3") {

					lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
					lbl_2.setText("números cúbicos:");

					lbl_resultado.setFont(new Font("Open Sans", Font.PLAIN, 20));
					lbl_resultado.setText(Calc.porcentagem(size3Cubos, numCombinacoes));

				}

			}
		});

	}

	private static void preparaPainelCubicos() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

        Cubicos cubicos = new Cubicos(listaJogos);

		numCombinacoes += listaJogos.size();
		size0Cubo += cubicos.getLista0Cubico().size();
		size1Cubo += cubicos.getLista1Cubico().size();
		size2Cubos += cubicos.getLista2Cubicos().size();
		size3Cubos += cubicos.getLista3Cubicos().size();

		painelCubicos = new JPanel();
		painelCubicos.setBackground(Color.WHITE);

		JLabel lbl_1 = new JLabel();
		lbl_1.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lbl_1.setText("Jogos com");

		lbl_2 = new JLabel();
		lbl_2.setFont(new Font("Open Sans", Font.PLAIN, 16));
		lbl_2.setText("número cúbico: ");
		lbl_resultado = new JLabel();
		lbl_resultado.setText("- %");
		lbl_resultado.setFont(new Font("Open Sans", Font.PLAIN, 20));

		geraEtiquetas();

		GroupLayout gl_painelCubicos = new GroupLayout(painelCubicos);
		gl_painelCubicos.setHorizontalGroup(gl_painelCubicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCubicos.createSequentialGroup().addContainerGap().addComponent(lbl_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(comboBoxCub, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lbl_resultado)
						.addContainerGap(18, Short.MAX_VALUE)));
		gl_painelCubicos.setVerticalGroup(gl_painelCubicos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCubicos.createSequentialGroup().addContainerGap()
						.addGroup(gl_painelCubicos.createParallelGroup(Alignment.BASELINE).addComponent(lbl_2)
								.addComponent(lbl_1)
								.addComponent(comboBoxCub, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lbl_resultado))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		painelCubicos.setLayout(gl_painelCubicos);

	}

	public static JPanel getPainelCubicos() {
		return painelCubicos;

	}

}
