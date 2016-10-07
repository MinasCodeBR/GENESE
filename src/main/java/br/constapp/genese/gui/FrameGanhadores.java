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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import java.awt.Component;
import java.awt.Rectangle;

public class FrameGanhadores extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel painelResultado;
	private JPanel painelGanhadores;
	private JLabel lblSena;
	private JLabel lblGanhadoresSena;
	private JLabel lblQuina;
	private JLabel lblGanhadoresQuina;
	private JLabel lblQuadra;
	private JLabel lblGanhadoresQuadra;
	private JLabel lblRateioSena;
	private JLabel lblRateioQuina;
	private JLabel lblRateioQuadra;
	private JLabel lblEstimativaPremio;
	private Jogo jogo;
	private List<Jogo> listaJogos;
	private static boolean estaAberto;
	private String[] listaJogosArray;
	private JComboBox comboBox;
	private JPanel painelComboBox;

	public FrameGanhadores() {

		super("Ganhadores");
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		montaTela();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				setEstaAberto(false);

				/*
				 * if (JOptionPane.showConfirmDialog(null,"Deseja sair")==
				 * JOptionPane.OK_OPTION){ salvarDados(); System.exit(0);
				 * 
				 * }
				 */

			}
		});

	}

	public void montaTela() {
		
		preparaPainelComboBox();
		geraEtiquetas();
		preparaPainelResultado();
		preparaPainelGanhadores();
		preparaFrameGanhadores();

	}

	private void preparaFrameGanhadores() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(260, 305);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		setVisible(true);
		getContentPane().setLayout(null);
		getContentPane().add(painelGanhadores);		
		getContentPane().add(painelComboBox);
    	
	}
	
	private void preparaPainelComboBox() {
		
		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());

		listaJogos = FabricaDeJogos.getListaJogos();		
		
		painelComboBox = new JPanel();
		painelComboBox.setBounds(10, 10, 234, 29);		
		
        listaJogosArray = new String[listaJogos.size()];
        
        for(int i = 1; i <= listaJogos.size(); i++) {
        	listaJogosArray[i - 1] = "Concurso " + i;        	
        }       
        
        BasicComboBoxRenderer.UIResource UIResource = new BasicComboBoxRenderer.UIResource();  
        UIResource.setHorizontalAlignment(SwingConstants.CENTER);
        
        painelComboBox = new JPanel();
    	painelComboBox.setBounds(0, 0, 254, 31);    	
    	painelComboBox.setLayout(null);
    	
    	comboBox = new JComboBox(listaJogosArray);
    	comboBox.setBounds(0, 0, 254, 31);
    	
    	comboBox.setRenderer(UIResource);
    	comboBox.setFont(new Font("Dialog", Font.PLAIN, 17));
    	
    	jogo = listaJogos.get(comboBox.getSelectedIndex());
    	comboBox.setSelectedIndex(listaJogos.size() - 1);
    	System.out.println("Jogo selecionado: " + comboBox.getSelectedIndex());
    	
    	comboBox.addActionListener(new ActionListener() {
    		
    		@Override
    		public void actionPerformed(ActionEvent e) {				
    			jogo = listaJogos.get(comboBox.getSelectedIndex());
    			geraEtiquetas();
    			preparaPainelResultado();    			
    			painelResultado.revalidate();
    			painelResultado.repaint();
    			preparaPainelGanhadores();    			
    			painelGanhadores.revalidate();
    			painelGanhadores.repaint();
    			System.out.println(comboBox.getSelectedIndex());
    			
    		}
    	});
    	
    	painelComboBox.add(comboBox);
        
	}

	private void geraEtiquetas() {

		lblGanhadoresSena = new JLabel();
		lblGanhadoresSena.setBounds(82, 7, 123, 18);
		lblGanhadoresSena.setForeground(new Color(0, 128, 0));
		lblRateioSena = new JLabel();
		lblRateioSena.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRateioSena.setBounds(7, 31, 227, 14);
		lblGanhadoresQuina = new JLabel();
		lblGanhadoresQuina.setBounds(82, 67, 123, 18);
		lblGanhadoresQuina.setForeground(new Color(51, 51, 204));
		lblRateioQuina = new JLabel();
		lblRateioQuina.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRateioQuina.setBounds(7, 91, 227, 14);
		lblGanhadoresQuadra = new JLabel();
		lblGanhadoresQuadra.setBounds(82, 126, 123, 18);
		lblGanhadoresQuadra.setForeground(new Color(218, 165, 32));
		lblRateioQuadra = new JLabel();
		lblRateioQuadra.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRateioQuadra.setBounds(7, 150, 227, 14);

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

	}

	private void preparaPainelResultado() {

		painelResultado = new JPanel();
		painelResultado.setBackground(new Color(255, 255, 255));
		painelResultado.setBounds(10, 11, 234, 175);
		painelResultado.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelResultado.setLayout(null);

		lblSena = new JLabel("Sena");
		lblSena.setBounds(7, 7, 37, 18);
		lblSena.setForeground(new Color(0, 128, 0));
		lblSena.setFont(new Font("Arial", Font.PLAIN, 15));
		painelResultado.add(lblSena);

		lblGanhadoresSena.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGanhadoresSena.setHorizontalAlignment(SwingConstants.LEFT);
		painelResultado.add(lblGanhadoresSena);

		lblQuina = new JLabel("Quina");
		lblQuina.setBounds(7, 67, 57, 18);
		lblQuina.setForeground(new Color(51, 51, 204));
		lblQuina.setFont(new Font("Arial", Font.PLAIN, 15));
		painelResultado.add(lblQuina);

		lblGanhadoresQuina.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGanhadoresQuina.setHorizontalAlignment(SwingConstants.LEFT);
		painelResultado.add(lblGanhadoresQuina);

		lblQuadra = new JLabel("Quadra");
		lblQuadra.setBounds(7, 126, 57, 18);
		lblQuadra.setForeground(new Color(218, 165, 32));
		lblQuadra.setFont(new Font("Arial", Font.PLAIN, 15));
		painelResultado.add(lblQuadra);

		lblGanhadoresQuadra.setFont(new Font("Arial", Font.PLAIN, 11));
		lblGanhadoresQuadra.setHorizontalAlignment(SwingConstants.LEFT);
		painelResultado.add(lblGanhadoresQuadra);

		lblRateioSena.setFont(new Font("Arial", Font.PLAIN, 11));
		painelResultado.add(lblRateioSena);

		lblRateioQuina.setFont(new Font("Arial", Font.PLAIN, 11));
		painelResultado.add(lblRateioQuina);

		lblRateioQuadra.setFont(new Font("Arial", Font.PLAIN, 11));
		painelResultado.add(lblRateioQuadra);

	}

	private void preparaPainelGanhadores() {

		painelGanhadores = new JPanel();
		painelGanhadores.setBackground(new Color(255, 255, 255));
		painelGanhadores.setBounds(0, 29, 254, 247);
		painelGanhadores.setLayout(null);

		JLabel lblEstimativa = new JLabel("Estimativa para próximo concurso");
		lblEstimativa.setBounds(0, 197, 254, 14);
		lblEstimativa.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEstimativa.setHorizontalAlignment(SwingConstants.CENTER);
		painelGanhadores.add(lblEstimativa);
		lblEstimativaPremio = new JLabel();
		lblEstimativaPremio.setBounds(0, 216, 254, 14);

		lblEstimativaPremio.setText("R$ " + jogo.getEstimativaPremio());

		lblEstimativaPremio.setFont(new Font("Arial", Font.PLAIN, 11));
		lblEstimativaPremio.setHorizontalAlignment(SwingConstants.CENTER);
		painelGanhadores.add(lblEstimativaPremio);

		painelGanhadores.add(painelResultado);	

	}

	public static boolean estaAberto() {
		return estaAberto;
	}

	public static void setEstaAberto(boolean estaAberto) {
		FrameGanhadores.estaAberto = estaAberto;
	}
}
