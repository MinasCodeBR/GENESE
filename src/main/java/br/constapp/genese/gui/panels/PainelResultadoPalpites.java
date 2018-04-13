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

package br.constapp.genese.gui.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.resultado.GeradorDePalpites;
import br.constapp.genese.util.CustomDocumentFilter;
import javax.swing.ScrollPaneConstants;
import java.awt.BorderLayout;

public class PainelResultadoPalpites extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel painelResultadoPalpites;
	private static JProgressBar progressBar;
	private static JPanel painelTextPane;
	private static JTextPane textPane;
	private JScrollPane barraRolagemTxtPane;
	private static JLabel lblDisplay;
	private static JButton btnEstouComSorte;
	private static JTextField txtQtdJogos;
	private static JCheckBox chckbxSalvar;
	private static JPanel painelCfg;
	private static JLabel lblQtdJogos;
	private static JCheckBox chckbxUsarFav;
	private static JLabel lblAvisoNumInvalido;
	private static StyledDocument doc;
	private static SimpleAttributeSet keyWord;
	private static JButton btnCopiar;
	private static JButton btnLimpar;
	private static JPanel painelBotoes;
	private static boolean btnLivre;
	private static Thread threadGerador;
	private static int geradosAntes;

	public PainelResultadoPalpites() {

		preparaPainelTextPane();
		preparaPainelCfg();
		preparaPainelBotoes();
		preparaPainelResultadoPalpites();
	}

	private void preparaPainelTextPane() {

		painelTextPane = new JPanel();
		painelTextPane.setBackground(Color.WHITE);
		painelTextPane.setBorder(null);

		textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPane.setEditable(false);

		doc = textPane.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		keyWord = new SimpleAttributeSet();
		StyleConstants.setForeground(keyWord, Color.BLACK);
		StyleConstants.setBackground(keyWord, Color.WHITE);
		StyleConstants.setBold(keyWord, true);

		barraRolagemTxtPane = new JScrollPane();
		barraRolagemTxtPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		barraRolagemTxtPane.setViewportView(textPane);

		progressBar = new JProgressBar();
		progressBar.setFocusable(false);
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		painelTextPane.setLayout(new BorderLayout(0, 0));
		painelTextPane.add(progressBar, BorderLayout.SOUTH);
		painelTextPane.add(barraRolagemTxtPane, BorderLayout.CENTER);
	}

	public static void iniciaThreadGerador() {

		GeradorDePalpites geradorDePalpites = new GeradorDePalpites();
		threadGerador = new Thread(geradorDePalpites);
		System.out.println(threadGerador.getName());

		geradosAntes = (textPane.getText().split("\n").length / 2);
		btnEstouComSorte.setText("PARAR");

		threadGerador.start();
	}

	private static void preparaPainelResultadoPalpites() {

		painelResultadoPalpites = new JPanel();
		painelResultadoPalpites.setFocusable(false);
		painelResultadoPalpites.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelResultadoPalpites.setBorder(null);
		painelResultadoPalpites.setBackground(Color.WHITE);

		lblDisplay = new JLabel("-");
		lblDisplay.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDisplay.setForeground(new Color(0, 153, 51));
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblDisplay.setHorizontalAlignment(SwingConstants.CENTER);

		btnEstouComSorte = new JButton("Estou com sorte");
		btnEstouComSorte.setMargin(new Insets(1, 7, 1, 7));
		btnEstouComSorte.setBackground(new Color(255, 255, 255));
		btnEstouComSorte.setForeground(new Color(0, 0, 0));
		btnEstouComSorte.setFocusable(false);

		btnEstouComSorte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (btnLivre) {

					painelResultadoPalpites.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					if (DefineDiretorio.isDiretorioVazio(DefineDiretorio.getDiretorioFiltro())) {

						DefineDiretorio defineDiretorio = new DefineDiretorio();
						Thread primeiraExe = new Thread(defineDiretorio);
						primeiraExe.start();

					} else {
						iniciaThreadGerador();
					}

					painelResultadoPalpites.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				} else {

					int solicitados = Integer.parseInt(txtQtdJogos.getText());
					int geradosAgora = (textPane.getText().split("\n").length / 2);
					int gerados = 0;

					if (geradosAntes <= geradosAgora) {
						gerados = geradosAgora - geradosAntes;
					} else {
						if (geradosAgora > 0) {
							gerados = geradosAntes - geradosAgora;
						}
					}

					if (gerados < solicitados) {
						txtQtdJogos.setText(String.valueOf(gerados + 1));
					}

					btnEstouComSorte.setEnabled(false);
				}
			}

		});

		GroupLayout gl_painelResultadoPalpites = new GroupLayout(painelResultadoPalpites);
		gl_painelResultadoPalpites.setHorizontalGroup(gl_painelResultadoPalpites.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultadoPalpites.createSequentialGroup().addGap(28)
						.addComponent(lblDisplay, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE).addGap(28))
				.addGroup(gl_painelResultadoPalpites.createSequentialGroup().addGap(49)
						.addComponent(painelCfg, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE).addGap(48))
				.addGroup(gl_painelResultadoPalpites.createSequentialGroup().addGap(49)
						.addGroup(gl_painelResultadoPalpites.createParallelGroup(Alignment.TRAILING)
								.addComponent(painelBotoes, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 313,
										Short.MAX_VALUE)
								.addComponent(painelTextPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 313,
										Short.MAX_VALUE))
						.addGap(48))
				.addGroup(gl_painelResultadoPalpites.createSequentialGroup().addGap(144)
						.addComponent(btnEstouComSorte, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE).addGap(144)));
		gl_painelResultadoPalpites.setVerticalGroup(gl_painelResultadoPalpites.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultadoPalpites.createSequentialGroup().addContainerGap().addComponent(lblDisplay)
						.addGap(18).addComponent(painelCfg, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addGap(3)
						.addComponent(btnEstouComSorte, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(painelTextPane, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(painelBotoes, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		painelResultadoPalpites.setLayout(gl_painelResultadoPalpites);

	}

	private static JPanel preparaPainelBotoes() {

		btnLimpar = new JButton("Limpar");
		btnLimpar.setFocusable(false);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textPane.setText("");
				lblDisplay.setText("-");
			}
		});

		btnCopiar = new JButton("Copiar");
		btnCopiar.setFocusable(false);
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textPane.getText().trim().length() == 0) {
					lblDisplay.setText("Nada para copiar");
				} else {
					textPane.requestFocus();
					textPane.selectAll();
					textPane.copy();
					textPane.getSelectedText();

					lblDisplay.setText("Copiado!");
				}
			}
		});

		painelBotoes = new JPanel();

		GroupLayout gl_PainelBotoes = new GroupLayout(painelBotoes);
		gl_PainelBotoes.setHorizontalGroup(gl_PainelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelBotoes.createSequentialGroup().addGap(88).addComponent(btnLimpar).addGap(10)
						.addComponent(btnCopiar).addContainerGap(89, Short.MAX_VALUE)));
		gl_PainelBotoes.setVerticalGroup(gl_PainelBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelBotoes.createSequentialGroup().addGap(5)
						.addGroup(gl_PainelBotoes.createParallelGroup(Alignment.LEADING)
								.addComponent(btnLimpar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCopiar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		painelBotoes.setLayout(gl_PainelBotoes);
		return painelBotoes;
	}

	private static void preparaPainelCfg() {

		painelCfg = new JPanel();
		painelCfg.setBackground(Color.WHITE);

		lblQtdJogos = new JLabel("Quantidade de jogos:");
		lblQtdJogos.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblAvisoNumInvalido = new JLabel("Quantidade de jogos de 1 a 100");
		lblAvisoNumInvalido.setForeground(Color.RED);
		lblAvisoNumInvalido.setFont(new Font("Tahoma", Font.PLAIN, 9));

		txtQtdJogos = new JTextField();
		lblQtdJogos.setLabelFor(txtQtdJogos);
		((AbstractDocument) txtQtdJogos.getDocument()).setDocumentFilter(new CustomDocumentFilter());

		txtQtdJogos.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				changed();
			}

			public void removeUpdate(DocumentEvent e) {
				changed();
			}

			public void insertUpdate(DocumentEvent e) {
				changed();
			}

			public void changed() {

				Runnable doChanges = new Runnable() {
					@Override
					public void run() {

						if (txtQtdJogos.getText().equals("") || txtQtdJogos.getText().equals("0")) {
							botaoLivre(false);
							btnEstouComSorte.setEnabled(false);
							lblAvisoNumInvalido.setVisible(true);
						} else {
							botaoLivre(true);
							if (btnEstouComSorte.getText() != "PARAR") {
								btnEstouComSorte.setEnabled(true);
							}
							lblAvisoNumInvalido.setVisible(false);
						}

					}
				};
				SwingUtilities.invokeLater(doChanges);
			}
		});

		txtQtdJogos.setText("1");
		txtQtdJogos.setHorizontalAlignment(SwingConstants.CENTER);
		txtQtdJogos.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtQtdJogos.setColumns(10);

		chckbxUsarFav = new JCheckBox("Somente listas favoritas");
		chckbxUsarFav.setBackground(Color.WHITE);
		chckbxUsarFav.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxUsarFav.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxUsarFav.setFocusable(false);

		chckbxSalvar = new JCheckBox("Salvar em arquivo");
		chckbxSalvar.setBackground(Color.WHITE);
		chckbxSalvar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxSalvar.setFocusable(false);
		chckbxSalvar.setHorizontalAlignment(SwingConstants.CENTER);

		GroupLayout gl_painelCfg = new GroupLayout(painelCfg);
		gl_painelCfg.setHorizontalGroup(gl_painelCfg.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCfg.createSequentialGroup().addGap(30)
						.addGroup(gl_painelCfg.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_painelCfg.createSequentialGroup().addGap(52)
										.addComponent(lblAvisoNumInvalido, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(51))
								.addGroup(gl_painelCfg.createSequentialGroup().addComponent(chckbxUsarFav)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxSalvar)))
						.addGap(30))
				.addGroup(gl_painelCfg.createSequentialGroup().addGap(65).addComponent(lblQtdJogos)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txtQtdJogos, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(65, Short.MAX_VALUE)));
		gl_painelCfg.setVerticalGroup(gl_painelCfg.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelCfg.createSequentialGroup().addGap(1)
						.addGroup(gl_painelCfg.createParallelGroup(Alignment.TRAILING).addComponent(lblQtdJogos)
								.addGroup(gl_painelCfg.createSequentialGroup()
										.addComponent(txtQtdJogos, GroupLayout.PREFERRED_SIZE, 16,
												GroupLayout.PREFERRED_SIZE)
										.addGap(1)))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblAvisoNumInvalido)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_painelCfg.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxUsarFav, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxSalvar, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		painelCfg.setLayout(gl_painelCfg);
	}

	public static void setTextPanePalpites(String text) {

		try {
			doc.insertString(doc.getLength(), text, keyWord);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		textPane.setCaretPosition(doc.getLength());
	}

	public static JTextPane getTextPane() {
		return textPane;
	}

	public static void setProgressBar(boolean visivel) {
		progressBar.setVisible(visivel);
	}

	public static void setLblDisplay(String message) {
		lblDisplay.setText(message);
	}

	public static void botaoLivre(Boolean booBtn) {
		btnLivre = booBtn;
		btnCopiar.setEnabled(booBtn);
		btnLimpar.setEnabled(booBtn);
	}

	public static void painelCfgHabilitado(Boolean booCfg) {
		txtQtdJogos.setEnabled(booCfg);
		chckbxSalvar.setEnabled(booCfg);
		chckbxUsarFav.setEnabled(booCfg);
	}

	public static JPanel getPainelResuldatoPalpites() {
		return painelResultadoPalpites;
	}

	public static JCheckBox getChckbxUsarFav() {
		return chckbxUsarFav;
	}

	public static JCheckBox getChckbxSalvar() {
		return chckbxSalvar;
	}

	public static JTextField getTxtQtdJogos() {
		return txtQtdJogos;
	}

	public static JButton getBtnEstouComSorte() {
		return btnEstouComSorte;
	}
}
