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

import br.constapp.genese.gui.guiutils.GuiUtils;
import br.constapp.genese.gui.model.ModeloTabelaJogos;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.path.DefineDiretorio;
import br.constapp.genese.util.DownloadAndUnzip;
import br.constapp.genese.util.TestaConexao;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Principal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private JPanel painelTable;
    private JPanel dashboard;
    private JPanel PainelCombinacoes;
    private JTable tabelaJogos;
    private JButton btnAtualizaJogos;
    private JButton btnGanhadores;
    private ModeloTabelaJogos modeloTabelaJogos;
    private List<Jogo> listaJogos;
    private URL url;
    
    private FrameGanhadores frameGanhadores;

    private Principal() {
        super("GENESE");
        setResizable(false);

        baixaJogos();

        ScannerDeHtm scan = new ScannerDeHtm();
        FabricaDeJogos.criaJogo(scan.getListaSorteios());
        listaJogos = FabricaDeJogos.getListaJogos();
        montaTela();

    }

    public static void main(String[] args) {
        new Principal();
    }

    private void montaTela() {

        criaJTable();
        preparaButtonsDashboard();
        preparaDashboard();
        preparaPainelCombinacoes();
        preparaPainelTable();
        preparaTabbedPane();
        criaJanela();

    }

    private void criaJTable() {

        modeloTabelaJogos = new ModeloTabelaJogos(listaJogos);
        DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
        centralizado.setHorizontalAlignment(SwingConstants.CENTER);

        tabelaJogos = new JTable(modeloTabelaJogos);
        tabelaJogos.getTableHeader().setReorderingAllowed(false);
        tabelaJogos.getTableHeader().setResizingAllowed(false);
        tabelaJogos.setPreferredScrollableViewportSize(new Dimension(400, 0));
        tabelaJogos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabelaJogos.getColumnModel().getColumn(1).setPreferredWidth(110);  
        tabelaJogos.getColumnModel().getColumn(0).setCellRenderer(centralizado);
        tabelaJogos.getColumnModel().getColumn(1).setCellRenderer(centralizado);
    }

    private void preparaButtonsDashboard() {
        btnAtualizaJogos();
        btnGanhadores();

    }

    private void btnAtualizaJogos() {
        btnAtualizaJogos = new JButton("Atualizar Jogos");
        btnAtualizaJogos.setBounds(86, 11, 164, 23);
        btnAtualizaJogos.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnAtualizaJogos.addActionListener(e -> {
            atualizaTabelaJogos();
            GuiUtils.scrollToVisible(tabelaJogos, tabelaJogos.getRowCount() - 1);

        });
    }

    private void btnGanhadores() {

        btnGanhadores = new JButton("Ganhadores");
        btnGanhadores.setBounds(86, 45, 164, 23);
        btnGanhadores.setFont(new Font("Tahoma", Font.PLAIN, 12));        
        btnGanhadores.addActionListener(e -> {
            if (!FrameGanhadores.estaAberto()) {
                frameGanhadores = new FrameGanhadores();
                FrameGanhadores.setEstaAberto(true);
            } else {
                frameGanhadores.toFront();
            }
        });
    }

    private void preparaDashboard() {
        dashboard = new JPanel();
        dashboard.setBounds(0, 0, 320, 253);
        dashboard.setLayout(null);
        dashboard.add(btnAtualizaJogos);
        dashboard.add(btnGanhadores);

    }

    private void preparaPainelTable() {
        painelTable = new JPanel();
        painelTable.setLayout(null);
        JScrollPane barraRolagem = new JScrollPane(tabelaJogos);
        barraRolagem.setBounds(332, 0, 196, 253);
        painelTable.add(barraRolagem);
        painelTable.add(dashboard);

    }

    private void preparaPainelCombinacoes() {
        PainelCombinacoes = new JPanel();
        PainelCombinacoes.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(12, 34, 332, 198);
        PainelCombinacoes.add(panel);
        panel.setLayout(null);
        

    }

    private void preparaTabbedPane() {
        ImageIcon imagemTituloTab = new ImageIcon("imagem.png");

        tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.setBounds(0, 0, 533, 281);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
        tabbedPane.addTab("Sorteios", imagemTituloTab, painelTable, "Resultado da Mega-Sena");
        tabbedPane.addTab("Combinações", imagemTituloTab, PainelCombinacoes, "Meus números da sorte");

    }

    private void criaJanela() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(535, 310);
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
        setVisible(true);

        Container cPane = getContentPane();
        getContentPane().setLayout(null);
        cPane.add(tabbedPane);

        GuiUtils.scrollToVisible(tabelaJogos, tabelaJogos.getRowCount() - 1);

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
            JOptionPane.showMessageDialog(null,
                    "Concurso nº " + listaJogos.size() + " é o último resultado disponível", "Atualizar Jogos",
                    JOptionPane.INFORMATION_MESSAGE);
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



