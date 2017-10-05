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

package br.constapp.genese.gui;

import br.constapp.genese.gui.guiutils.GuiUtils;
import br.constapp.genese.gui.model.ModeloTabelaJogos;
import br.constapp.genese.gui.panels.PainelResultadoAnalises;
import br.constapp.genese.gui.panels.PainelResultadoGanhadores;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.util.DownloadAndUnzip;
import br.constapp.genese.util.TestaConexao;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String OS_NAME = System.getProperty("os.name");
	private JTabbedPane tabbedPane;
	private JPanel painelTable;
	private JPanel painelAnalises;
	private JPanel painelResultadoAnalises;
	private JPanel painelGanhadores;
	private JPanel painelResultadoGanhadores;
	private JTable tabelaJogos;
	private ModeloTabelaJogos modeloTabelaJogos;
	private List<Jogo> listaJogos;
	private URL url;

	private Principal() {
		super("GENESE");
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);

		baixaJogos();

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		listaJogos = FabricaDeJogos.getListaJogos();

		montaTela();

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

	private void montaTela() {

		preparaPainelResultadoGanhadores();
		preparaPainelResultadoAnalises();
		criaJTableJogos();
		preparaPainelTable();
		preparaTabbedPane();
		criaJanela();

	}

	private void criaJTableJogos() {

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
		tabelaJogos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tabelaJogos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (!tabelaJogos.getSelectionModel().isSelectionEmpty() && !e.getValueIsAdjusting()) {

					int linhaSelecionada = tabelaJogos.getSelectedRow() + 1;
					int linhaAnterior = e.getFirstIndex() + 1;

					if (linhaSelecionada == linhaAnterior) {
						linhaAnterior = e.getLastIndex() + 1;
					}

					if (linhaSelecionada == 0) {
						linhaSelecionada = e.getLastIndex() + 1;
					}

					System.out.println("Linha selecionada: " + linhaSelecionada);
					System.out.println("Seleção anterior: " + linhaAnterior);
					System.out.println("");

					int row = (int) tabelaJogos.getValueAt(tabelaJogos.getSelectedRow(), 0) - 1;

					Jogo jogo = new Jogo();
					jogo = listaJogos.get(row);

					PainelResultadoGanhadores.geraEtiquetasCubicos(jogo);
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
		gl_painelTable.setHorizontalGroup(gl_painelTable.createParallelGroup(Alignment.TRAILING)
				.addComponent(barraRolagem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE));
		gl_painelTable.setVerticalGroup(gl_painelTable.createParallelGroup(Alignment.LEADING).addComponent(barraRolagem,
				GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE));
		painelTable.setLayout(gl_painelTable);

		GuiUtils.scrollToVisible(tabelaJogos, tabelaJogos.getRowCount() - 1);

	}

	private void preparaPainelResultadoGanhadores() {

		painelResultadoGanhadores = new PainelResultadoGanhadores();
		painelResultadoGanhadores = PainelResultadoGanhadores.getPainelResultadoGanhadores();

		preparaPainelGanhadores();

	}

	private void preparaPainelGanhadores() {

		painelGanhadores = new JPanel();
		painelGanhadores.setBackground(Color.WHITE);

		GroupLayout gl_PainelGanhadores = new GroupLayout(painelGanhadores);
		gl_PainelGanhadores.setHorizontalGroup(gl_PainelGanhadores.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelGanhadores.createSequentialGroup().addContainerGap()
						.addComponent(painelResultadoGanhadores, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
						.addContainerGap()));
		gl_PainelGanhadores.setVerticalGroup(gl_PainelGanhadores.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PainelGanhadores.createSequentialGroup().addContainerGap()
						.addComponent(painelResultadoGanhadores, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
						.addContainerGap()));

		painelGanhadores.setLayout(gl_PainelGanhadores);

	}

	private void preparaPainelResultadoAnalises() {

		painelResultadoAnalises = new JPanel();

		new PainelResultadoAnalises();

		painelResultadoAnalises = PainelResultadoAnalises.getPainelResultadoAnalises();

		preparaPainelAnalises();

	}

	private void preparaPainelAnalises() {

		painelAnalises = new JPanel();
		painelAnalises.setBorder(new EmptyBorder(1, 10, 30, 10));
		painelAnalises.setBackground(Color.WHITE);

		GroupLayout gl_painelAnalises = new GroupLayout(painelAnalises);
		gl_painelAnalises.setHorizontalGroup(gl_painelAnalises.createParallelGroup(Alignment.CENTER)
				.addComponent(painelResultadoAnalises).addGap(0, 324, Short.MAX_VALUE));
		gl_painelAnalises.setVerticalGroup(gl_painelAnalises.createParallelGroup(Alignment.CENTER)
				.addComponent(painelResultadoAnalises).addGap(0, 292, Short.MAX_VALUE)

		);
		painelAnalises.setLayout(gl_painelAnalises);

	}

	private void preparaTabbedPane() {
		ImageIcon imagemTituloTab = new ImageIcon("imagem.png");

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));

		tabbedPane.addTab("Análises", imagemTituloTab, painelAnalises, "Números da sorte");
		tabbedPane.addTab("Ganhadores", imagemTituloTab, painelGanhadores, "Resultados");

	}

	private void criaJanela() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(595, 395);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		setVisible(true);

		Container cPane = getContentPane();

		cPane.add(painelTable);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(painelTable, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(painelTable,
								GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE))
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
				.addContainerGap()));
		cPane.setLayout(groupLayout);

	}

/*	private void atualizaTabelaJogos() {

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
	}*/

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
