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

package br.constapp.genese.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.gui.guiutils.GuiUtils;
import br.constapp.genese.gui.model.ModeloTabelaJogos;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.util.Calc;
import br.constapp.genese.util.DownloadAndUnzip;
import br.constapp.genese.util.TestaConexao;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String OS_NAME = System.getProperty("os.name");
	private JTabbedPane tabbedPane;
	private JPanel painelTable;
	private JPanel painelGanhadores;
	private JPanel painelResultado;
	private JLabel lblGanhadoresSena;
	private JLabel lblSena;
	private JLabel lblQuina;
	private JLabel lblGanhadoresQuina;
	private JLabel lblRateioSena;
	private JLabel lblGanhadoresQuadra;
	private JLabel lblEstimativaPremio;
	private JLabel lblEstimativa;
	private JLabel lblRateioQuadra;
	private JLabel lblRateioQuina;
	private JLabel lblQuadra;
	private JTable tabelaJogos;
	private ModeloTabelaJogos modeloTabelaJogos;
	private List<Jogo> listaJogos;
	private URL url;
	private JLabel lblDataSorteio;
	private JPanel painelAnalises;
	private JPanel painelTextArea;
	private JTextArea textArea;
	private JComboBox comboBoxAnalises;

	private Principal() {
		super("GENESE");
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);

		baixaJogos();

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		listaJogos = FabricaDeJogos.getListaJogos();

		Jogo jogo = new Jogo();
		jogo = listaJogos.get(listaJogos.size() - 1);

		montaTela(jogo);

	}

	public static void main(String[] args) {

		// Set the look and feel

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if (OS_NAME.toLowerCase().contains("windows")) {
					if ("Windows".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				} else {
					if ("Metal".equals(info.getName())) {
						javax.swing.UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Principal();
			}
		});

	}

	private void montaTela(Jogo jogo) {

		criaJTable();
		preparaPainelResultado(jogo);
		preparaPainelTable();
		preparaPainelAnalises();
		preparaTabbedPane();

		criaJanela();

	}

	private void criaJTable() {

		modeloTabelaJogos = new ModeloTabelaJogos(listaJogos);
		DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
		centralizado.setHorizontalAlignment(SwingConstants.CENTER);

		tabelaJogos = new JTable(modeloTabelaJogos);
		tabelaJogos.setBackground(Color.WHITE);
		tabelaJogos.getTableHeader().setReorderingAllowed(false);
		tabelaJogos.getTableHeader().setResizingAllowed(false);
		tabelaJogos.setPreferredScrollableViewportSize(new Dimension(400, 0));
		tabelaJogos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabelaJogos.getColumnModel().getColumn(1).setPreferredWidth(110);
		tabelaJogos.getColumnModel().getColumn(0).setCellRenderer(centralizado);
		tabelaJogos.getColumnModel().getColumn(1).setCellRenderer(centralizado);

		tabelaJogos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

					JTable target = (JTable) e.getSource();
					int row = target.getSelectedRow();

					Jogo jogo = new Jogo();
					jogo = listaJogos.get(row);

					geraEtiquetas(jogo);

				}
			}
		});
	}

	private void preparaPainelTable() {
		painelTable = new JPanel();
		painelTable.setBackground(Color.WHITE);
		JScrollPane barraRolagem = new JScrollPane(tabelaJogos);
		barraRolagem.setBackground(Color.WHITE);
		GroupLayout gl_painelTable = new GroupLayout(painelTable);
		gl_painelTable.setHorizontalGroup(
			gl_painelTable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTable.createSequentialGroup()
					.addComponent(barraRolagem, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_painelTable.setVerticalGroup(
			gl_painelTable.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_painelTable.createSequentialGroup()
					.addGap(22)
					.addComponent(barraRolagem, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
		);
		painelTable.setLayout(gl_painelTable);

		GuiUtils.scrollToVisible(tabelaJogos, tabelaJogos.getRowCount() - 1);

	}

	private void preparaPainelResultado(Jogo jogo) {

		painelResultado = new JPanel();
		painelResultado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelResultado.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
		painelResultado.setBackground(Color.WHITE);

		lblGanhadoresSena = new JLabel();
		lblGanhadoresSena.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhadoresSena.setText("1 ganhador");
		lblGanhadoresSena.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGanhadoresSena.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanhadoresSena.setForeground(new Color(0, 128, 0));
		lblRateioSena = new JLabel();
		lblRateioSena.setText("Rateio - R$ 1.000.000,00");
		lblRateioSena.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGanhadoresQuina = new JLabel();
		lblGanhadoresQuina.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhadoresQuina.setText("100 ganhadores");
		lblGanhadoresQuina.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGanhadoresQuina.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanhadoresQuina.setForeground(new Color(51, 51, 204));
		lblRateioQuina = new JLabel();
		lblRateioQuina.setText("Rateio - R$ 45.000,00");
		lblRateioQuina.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGanhadoresQuadra = new JLabel();
		lblGanhadoresQuadra.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanhadoresQuadra.setText("1000 ganhadores");
		lblGanhadoresQuadra.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblGanhadoresQuadra.setHorizontalTextPosition(SwingConstants.CENTER);
		lblGanhadoresQuadra.setForeground(new Color(218, 165, 32));
		lblRateioQuadra = new JLabel();
		lblRateioQuadra.setText("Rateio - R$ 250,00");
		lblRateioQuadra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEstimativaPremio = new JLabel();
		lblEstimativaPremio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimativaPremio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDataSorteio = new JLabel("Data do sorteio: 11/03/1991");
		lblDataSorteio.setHorizontalAlignment(SwingConstants.CENTER);

		geraEtiquetas(jogo);

		lblSena = new JLabel("Sena");
		lblSena.setForeground(new Color(0, 128, 0));
		lblSena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSena.setAlignmentX(0.5f);

		lblQuina = new JLabel("Quina");
		lblQuina.setPreferredSize(new Dimension(42, 14));
		lblQuina.setMinimumSize(new Dimension(42, 14));
		lblQuina.setMaximumSize(new Dimension(42, 14));
		lblQuina.setForeground(new Color(51, 51, 204));
		lblQuina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuina.setAlignmentX(0.5f);

		lblQuadra = new JLabel("Quadra");
		lblQuadra.setForeground(new Color(218, 165, 32));
		lblQuadra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuadra.setAlignmentX(0.5f);

		GroupLayout gl_painelResultado = new GroupLayout(painelResultado);
		gl_painelResultado.setHorizontalGroup(
			gl_painelResultado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelResultado.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_painelResultado.createSequentialGroup()
							.addComponent(lblQuadra, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
							.addComponent(lblGanhadoresQuadra, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioQuadra, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_painelResultado.createSequentialGroup()
							.addComponent(lblQuina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addComponent(lblGanhadoresQuina, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioQuina, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_painelResultado.createSequentialGroup()
							.addComponent(lblSena)
							.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
							.addComponent(lblGanhadoresSena, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioSena, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(lblDataSorteio, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelResultado.setVerticalGroup(
			gl_painelResultado.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_painelResultado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelResultado.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSena)
						.addComponent(lblGanhadoresSena, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioSena)
					.addGap(18)
					.addGroup(gl_painelResultado.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblGanhadoresQuina, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioQuina)
					.addGap(18)
					.addGroup(gl_painelResultado.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGanhadoresQuadra, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuadra))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioQuadra)
					.addGap(18)
					.addComponent(lblDataSorteio)
					.addContainerGap(36, Short.MAX_VALUE))
		);
		painelResultado.setLayout(gl_painelResultado);

		preparaPainelGanhadores();

	}

	private void preparaPainelGanhadores() {
		painelGanhadores = new JPanel();
		painelGanhadores.setBackground(Color.WHITE);

		lblEstimativa = new JLabel("Estimativa para próximo concurso");
		lblEstimativa.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstimativa.setFont(new Font("Arial", Font.PLAIN, 11));

		GroupLayout gl_PainelGanhadores = new GroupLayout(painelGanhadores);
		gl_PainelGanhadores.setHorizontalGroup(
			gl_PainelGanhadores.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelGanhadores.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_PainelGanhadores.createParallelGroup(Alignment.LEADING)
						.addComponent(painelResultado, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(lblEstimativaPremio, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addComponent(lblEstimativa, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_PainelGanhadores.setVerticalGroup(
			gl_PainelGanhadores.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_PainelGanhadores.createSequentialGroup()
					.addContainerGap()
					.addComponent(painelResultado, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblEstimativa)
					.addGap(5)
					.addComponent(lblEstimativaPremio)
					.addContainerGap(35, Short.MAX_VALUE))
		);

		painelGanhadores.setLayout(gl_PainelGanhadores);

	}

	private void geraEtiquetas(Jogo jogo) {

		if (jogo.getNumGanhadoresSena() != 0) {
			String s = " ganhadores";
			if (jogo.getNumGanhadoresSena() == 1) {
				s = " ganhador";
			}
			lblGanhadoresSena.setText(jogo.getNumGanhadoresSena() + s);
			lblRateioSena.setText("Rateio - R$ " + jogo.getRateioSena());
		} else {
			lblGanhadoresSena.setText("Acumulou!");
			lblRateioSena.setText("Nenhum ganhador");
		}

		if (jogo.getNumGanhadoresQuina() != 0) {
			String s = " ganhadores";
			if (jogo.getNumGanhadoresQuina() == 1) {
				s = " ganhador";
			}
			lblGanhadoresQuina.setText(jogo.getNumGanhadoresQuina() + s);
			lblRateioQuina.setText("Rateio - R$ " + jogo.getRateioQuina());
		} else {
			lblGanhadoresQuina.setText("Nenhum ganhador");
			lblRateioQuina.setText("Nenhum ganhador");
		}

		if (jogo.getNumGanhadoresQuadra() != 0) {
			String s = " ganhadores";
			if (jogo.getNumGanhadoresQuadra() == 1) {
				s = " ganhador";
			}
			lblGanhadoresQuadra.setText(jogo.getNumGanhadoresQuadra() + s);
			lblRateioQuadra.setText("Rateio - R$ " + jogo.getRateioQuadra());
		} else {
			lblGanhadoresQuadra.setText("Nenhum ganhador");
			lblRateioQuadra.setText("Nenhum ganhador");
		}

		lblEstimativaPremio.setText("R$ " + jogo.getEstimativaPremio());
		lblDataSorteio.setText("Data do sorteio: " + jogo.getDataSorteio());
		painelResultado.setBorder(new TitledBorder(null, "Concurso " + jogo.getConcurso(), TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

	}
	
	private void criaTextArea() {
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
	}

	private void preparaPainelTextArea() {
		
		painelTextArea = new JPanel();
		painelTextArea.setBackground(Color.WHITE);
		painelTextArea.setBorder(null);
		
		criaTextArea();
		
		GroupLayout gl_painelTextArea = new GroupLayout(painelTextArea);
		gl_painelTextArea.setHorizontalGroup(
			gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_painelTextArea.setVerticalGroup(
			gl_painelTextArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelTextArea.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.addContainerGap())
		);
		painelTextArea.setLayout(gl_painelTextArea);
	}
	
	private void criaComboBoxAnalises() {
		
		String[] classesAnalise = new String[]{"Cubicos", "Faixa de números"};
		
		comboBoxAnalises = new JComboBox(classesAnalise);
		
		comboBoxAnalises.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {	
    			System.out.println(comboBoxAnalises.getSelectedIndex());
    			
    			if (comboBoxAnalises.getSelectedIndex() == 0) {
    		        ScannerDeHtm scan = new ScannerDeHtm();
    		        FabricaDeJogos.criaJogo(scan.getListaSorteios());
    		        List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

    		        int numCombinacoes = 0;
    		        int size0Cubo = 0;
    		        int size1Cubo = 0;
    		        int size2Cubos = 0;
    		        int size3Cubos = 0;

    		        Cubicos c = new Cubicos(listaJogos);

    		        numCombinacoes += listaJogos.size();
    		        size0Cubo += c.getLista0Cubico().size();
    		        size1Cubo += c.getLista1Cubico().size();
    		        size2Cubos += c.getLista2Cubicos().size();
    		        size3Cubos += c.getLista3Cubicos().size();
    		        
    		        String text = "Jogos com 0 número cubico: " + Calc.porcentagem(size0Cubo, numCombinacoes) + 
    		        		"\nJogos com 1 número cubico: " + Calc.porcentagem(size1Cubo, numCombinacoes) + 
    		        		"\nJogos com 2 números cubicos: " + Calc.porcentagem(size2Cubos, numCombinacoes) + 
    		        		"\nJogos com 3 números cubicos: " + Calc.porcentagem(size3Cubos, numCombinacoes);
    		        textArea.setText(text);

    		        System.out.println(numCombinacoes);

    		        System.out.println("Jogos com 0 número cubico: " + Calc.porcentagem(size0Cubo, numCombinacoes));

    		        System.out.println("Jogos com 1 número cubico: " + Calc.porcentagem(size1Cubo, numCombinacoes));

    		        System.out.println("Jogos com 2 números cubicos: " + Calc.porcentagem(size2Cubos, numCombinacoes));

    		        System.out.println("Jogos com 3 números cubicos: " + Calc.porcentagem(size3Cubos, numCombinacoes));
    			}
    			
    			if (comboBoxAnalises.getSelectedIndex() == 1) {
    				textArea.setText("");
    			}
    			
    		}
});
		
	}

	private void preparaPainelAnalises() {

		painelAnalises = new JPanel();
		painelAnalises.setBackground(Color.WHITE);

		preparaPainelTextArea();		
		criaComboBoxAnalises();

		GroupLayout gl_painelAnalises = new GroupLayout(painelAnalises);
		gl_painelAnalises.setHorizontalGroup(
			gl_painelAnalises.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAnalises.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelAnalises.createParallelGroup(Alignment.LEADING)
						.addComponent(painelTextArea, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 304, Short.MAX_VALUE)
						.addComponent(comboBoxAnalises, Alignment.TRAILING, 0, 304, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelAnalises.setVerticalGroup(
			gl_painelAnalises.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelAnalises.createSequentialGroup()
					.addContainerGap()
					.addComponent(comboBoxAnalises, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(painelTextArea, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		painelAnalises.setLayout(gl_painelAnalises);
	}

	private void preparaTabbedPane() {
		ImageIcon imagemTituloTab = new ImageIcon("imagem.png");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));

		tabbedPane.addTab("Análises", null, painelAnalises, "Números da sorte");
		tabbedPane.addTab("Ganhadores", imagemTituloTab, painelGanhadores, "Resultados");

	}

	private void criaJanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(550, 360);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		setVisible(true);

		Container cPane = getContentPane();

		cPane.add(painelTable);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(painelTable, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(painelTable, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
					.addContainerGap())
		);
		cPane.setLayout(groupLayout);

	}

	private void atualizaTabelaJogos() {

		baixaJogos();

		int sizeAntes = listaJogos.size();

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		listaJogos = FabricaDeJogos.getListaJogos();

		modeloTabelaJogos.limpar();
		modeloTabelaJogos.addListaJogos(listaJogos);

		int sizeDepois = listaJogos.size();

		String diferencaListas = String.valueOf(sizeDepois - sizeAntes);

		if (sizeAntes == sizeDepois) {
			JOptionPane.showMessageDialog(null, "Concurso nº " + listaJogos.size() + " é o último resultado disponível",
					"Atualizar Jogos", JOptionPane.INFORMATION_MESSAGE);
		} else {
			if (sizeDepois - sizeAntes == 1) {
				JOptionPane.showMessageDialog(null, "Foi adicionado " + diferencaListas + " jogo à lista",
						"Atualizar Jogos", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Foram adicionados " + diferencaListas + " jogos à lista",
						"Atualizar Jogos", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void baixaJogos() {
		File targetDir = new File(DefineDiretorio.getDiretorioCEF());

		if (!TestaConexao.estaConectado()) {
			JOptionPane.showMessageDialog(null, "Você está sem conexão com a internet.", "Sem conexão",
					JOptionPane.ERROR_MESSAGE);
		} else {

			try {
				url = new URL("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_mgsasc.zip");
			} catch (MalformedURLException e1) {
				System.err.println("Ocorreu um problema com o link(URL) para download dos jogos - " + e1.toString());
			}

			try {
				DownloadAndUnzip.downloadArquivo(url, targetDir);

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
