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

import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class PainelResultadoGanhadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private static JPanel painelResultadoGanhadores;
	private static JLabel lblGanhadoresSena;
	private static JLabel lblGanhadoresQuina;
	private static JLabel lblRateioSena;
	private static JLabel lblGanhadoresQuadra;
	private static JLabel lblRateioQuadra;
	private static JLabel lblRateioQuina;
	private static JLabel lblDataSorteio;

	public PainelResultadoGanhadores() {
		preparaPainelResultadoGanhadores();
	}

	public static void geraEtiquetasCubicos(Jogo jogo) {

		if (jogo.getNumGanhadoresSena() != 0) {
			String s = " ganhadores";
			if (jogo.getNumGanhadoresSena() == 1) {
				s = " ganhador";
			}
			lblGanhadoresSena.setText(jogo.getNumGanhadoresSena() + s);
			lblRateioSena.setText("Rateio - R$ " + jogo.getRateioSena());
		} else {
			lblGanhadoresSena.setText("Acumulou!");
			lblRateioSena.setText("Estimativa para próximo concurso: R$ " + jogo.getEstimativaPremio());
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

		lblDataSorteio.setText("Data do sorteio: " + jogo.getDataSorteio());
		painelResultadoGanhadores.setBorder(new TitledBorder(null, "Concurso " + jogo.getConcurso(), TitledBorder.CENTER,
				TitledBorder.TOP, null, null));

	}

	private static void preparaPainelResultadoGanhadores() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		List<Jogo> listaJogos = FabricaDeJogos.getListaJogos();

		Jogo jogo = new Jogo();
		jogo = listaJogos.get(listaJogos.size() - 1);

		painelResultadoGanhadores = new JPanel();
		painelResultadoGanhadores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelResultadoGanhadores.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
		painelResultadoGanhadores.setBackground(Color.WHITE);

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
		lblDataSorteio = new JLabel("Data do sorteio: 11/03/1991");
		lblDataSorteio.setHorizontalAlignment(SwingConstants.CENTER);

		geraEtiquetasCubicos(jogo);

		JLabel lblSena = new JLabel("Sena");
		lblSena.setForeground(new Color(0, 128, 0));
		lblSena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSena.setAlignmentX(0.5f);

		JLabel lblQuina = new JLabel("Quina");
		lblQuina.setPreferredSize(new Dimension(42, 14));
		lblQuina.setMinimumSize(new Dimension(42, 14));
		lblQuina.setMaximumSize(new Dimension(42, 14));
		lblQuina.setForeground(new Color(51, 51, 204));
		lblQuina.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuina.setAlignmentX(0.5f);

		JLabel lblQuadra = new JLabel("Quadra");
		lblQuadra.setForeground(new Color(218, 165, 32));
		lblQuadra.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuadra.setAlignmentX(0.5f);

		GroupLayout gl_painelResultadoGanhadores = new GroupLayout(painelResultadoGanhadores);
		gl_painelResultadoGanhadores.setHorizontalGroup(
			gl_painelResultadoGanhadores.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_painelResultadoGanhadores.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_painelResultadoGanhadores.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDataSorteio, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_painelResultadoGanhadores.createSequentialGroup()
							.addComponent(lblQuina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
							.addComponent(lblGanhadoresQuina, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioQuina, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_painelResultadoGanhadores.createSequentialGroup()
							.addComponent(lblQuadra, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
							.addComponent(lblGanhadoresQuadra, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioQuadra, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_painelResultadoGanhadores.createSequentialGroup()
							.addComponent(lblSena)
							.addPreferredGap(ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
							.addComponent(lblGanhadoresSena, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblRateioSena, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_painelResultadoGanhadores.setVerticalGroup(
			gl_painelResultadoGanhadores.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_painelResultadoGanhadores.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_painelResultadoGanhadores.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSena)
						.addComponent(lblGanhadoresSena, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioSena)
					.addGap(39)
					.addGroup(gl_painelResultadoGanhadores.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblGanhadoresQuina, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuina, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioQuina)
					.addGap(40)
					.addGroup(gl_painelResultadoGanhadores.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGanhadoresQuadra, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuadra))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRateioQuadra)
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addComponent(lblDataSorteio)
					.addContainerGap())
		);
		painelResultadoGanhadores.setLayout(gl_painelResultadoGanhadores);

	}

	public static JPanel getPainelResultadoGanhadores() {
		return painelResultadoGanhadores;
	}
}
