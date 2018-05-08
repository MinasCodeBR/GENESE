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
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import br.constapp.genese.analise.Cubicos;
import br.constapp.genese.analise.Fibonacci;
import br.constapp.genese.analise.Multiplicidade;
import br.constapp.genese.analise.ParOuImpar;
import br.constapp.genese.analise.Primalidade;
import br.constapp.genese.analise.Quadrantes;
import br.constapp.genese.analise.Quadraticos;
import br.constapp.genese.analise.Sequencia;
import br.constapp.genese.gui.Principal;
import br.constapp.genese.jogo.FabricaDeJogos;
import br.constapp.genese.jogo.ScannerDeHtm;
import br.constapp.genese.jogo.modelo.Jogo;
import br.constapp.genese.util.Calc;

public class FrameSobreAnalises extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private JPanel topPanel;
	private JTextPane textPane;
	private JScrollPane textScrollPane;
	private List<Jogo> listaJogos;

	public FrameSobreAnalises() {

		super("Sobre as análises");
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setResizable(false);
	}

	private void criaTextPane() {

		ScannerDeHtm scan = new ScannerDeHtm();
		FabricaDeJogos.criaJogo(scan.getListaSorteios());
		listaJogos = FabricaDeJogos.getListaJogos();

		Cubicos cubicos = new Cubicos(listaJogos);
		Fibonacci fibonacci = new Fibonacci(listaJogos);
		ParOuImpar parOuImpar = new ParOuImpar(listaJogos);
		Primalidade primos = new Primalidade(listaJogos);
		Quadraticos quadraticos = new Quadraticos(listaJogos);
		Quadrantes quadrante1 = new Quadrantes(listaJogos, 1);
		Multiplicidade multiplosDe2 = new Multiplicidade(listaJogos, 2);
		Random r = new Random();
		Jogo jogo = listaJogos.get(r.nextInt(listaJogos.size() - 1));

		Sequencia sequencia = new Sequencia(listaJogos);

		Jogo jogoPrimeiroCampo = sequencia.getListaPrimeiroCampo()
				.get(r.nextInt(sequencia.getListaPrimeiroCampo().size() - 1));

		Jogo jogoSegundoCampo = sequencia.getListaSegundoCampo()
				.get(r.nextInt(sequencia.getListaSegundoCampo().size() - 1));

		Jogo jogoTerceiroCampo = sequencia.getListaTerceiroCampo()
				.get(r.nextInt(sequencia.getListaTerceiroCampo().size() - 1));

		Jogo jogoQuartoCampo = sequencia.getListaQuartoCampo()
				.get(r.nextInt(sequencia.getListaQuartoCampo().size() - 1));

		String[] initString = { "Cúbicos\n\n", // largeAndBlue

				"Temos um número cúbico quando elevamos qualquer número da ordem dos Naturais à potência 3. "
						+ "Em nosso caso, os possíveis cúbicos de 1 a 60 são:", // regular
				" " + Arrays.toString(Cubicos.defineNumerosCubicos()) + "\n", // bold

				"\n", // iconCubicos

				"Saiba que: \n\n", // regular

				Calc.porcentagem(cubicos.getLista0Cubico().size(), listaJogos.size()) + "%", // bold
				" de TODOS os sorteios realizados até " + listaJogos.get(listaJogos.size() - 1).getDataSorteio() + ",", // regular
				" não continham ", // bold
				"números cúbicos.\nOs três números cúbicos, 1, 8 e 27, só saíram ", // regular
				"juntos ", // bold
				"em um mesmo jogo em ", // regular
				cubicos.getLista3Cubicos().size() + "", // bold
				" dos " + listaJogos.size() + " sorteios.\n", // regular

				"\n\nFibonacci\n\n", // largeAndBlue

				"A Sequência de Fibonacci, é uma sucessão de números inteiros, começando normalmente\npor 0 e 1, "
						+ "na qual cada termo subsequente corresponde à soma dos dois anteriores.\n\n", // regular

				"1 + 1 = 2 + 3 = 5 + 3 = 8...", // small
				" ", // iconFibo
				" ", // iconConcha

				"\n\nOs números Fibonacci tem aplicações na análise de mercados financeiros, "
						+ "na ciência da computação e na teoria dos jogos. Também aparece em configurações biológicas, "
						+ "como na disposição dos galhos das árvores ou das folhas em uma haste.\n\n", // regular
				"Na Mega-Sena, ", // regular
				Calc.porcentagem(fibonacci.getLista1Fibonacci().size(), listaJogos.size()) + "% ", // bold
				"de todos os sorteios tinham ", // regular
				"1 ", // bold
				"número fibonacci. Jogos sem nenhum número da sequência representam ", // regular
				Calc.porcentagem(fibonacci.getLista0Fibonacci().size(), listaJogos.size()) + "% ", // bold
				"do total.", // regular

				"\n\nPar ou Ímpar\n\n", // largeAndBlue

				"Números pares são todos aqueles que, divididos por 2, o resto é igual a zero. Números ímpares "
						+ "ao serem divididos por 2, o resto é igual a 1.\n\n", // regular

				"Pares        ", // italic
				" ", // iconPar

				"\nÍmpares     ", // italic
				" ", // iconImpar

				"\n\nEntre os sorteios da Mega-Sena, ", // regular
				Calc.porcentagem(parOuImpar.getLista3Pares().size(), listaJogos.size()) + "%", // bold
				" dos jogos contém 3 pares e 3 ímpares.\nQuase tão expressíva é a incidência de jogos"
						+ " com 4 pares e 2 ímpares (", // regular
				Calc.porcentagem(parOuImpar.getLista4Pares().size(), listaJogos.size()) + "%", // bold
				") que tende a ser igual a frequência de 4 ímpares e 2 pares (", // regular
				Calc.porcentagem(parOuImpar.getLista4Impares().size(), listaJogos.size()) + "%", // bold
				"). Seis Pares saíram em ", // regular
				Calc.porcentagem(parOuImpar.getLista6Pares().size(), listaJogos.size()) + "%", // bold
				" das vezes.Seis ímpares em ", // regular
				Calc.porcentagem(parOuImpar.getLista6Impares().size(), listaJogos.size()) + "%.", // bold

				"\n\nPrimalidade\n\n", // largeAndBlue

				"Números primos são os números naturais com apenas dois divisores diferentes: "
						+ "\n1 e o próprio número analisado."
						+ "\nTá pensando em jogar na Mega-Sena? Caso positivo inclua 1 ou 2 números primos em seu jogo.\n\n"
						+ "Veja porque:\n\n", // regular
				Calc.porcentagem(primos.getLista1Primo().size(), listaJogos.size()) + "%", // bold
				" dos jogos tem ", // regular
				"1 ", // bold
				"número primo.\n", // regular
				Calc.porcentagem(primos.getLista2Primos().size(), listaJogos.size()) + "%", // bold
				" dos jogos tem ", // regular
				"2 ", // bold
				"números primos.\n", // regular
				Calc.porcentagem(primos.getLista3Primos().size(), listaJogos.size()) + "%", // bold
				" dos jogos tem ", // regular
				"3 ", // bold
				"números primos.\n", // regular
				"Jogos sem ", // regular
				"nenhum ", // bold
				"número primo representam ", // regular
				Calc.porcentagem(primos.getLista0Primo().size(), listaJogos.size()) + "%", // bold
				" do total.", // regular

				"\n\nQuadráticos\n\n", // largeAndBlue

				"Número quadrado é um inteiro que pode ser escrito como o quadrado de outro número inteiro.\n", // regular
				" ", // iconQuadrado1
				" ", // iconQuadrado2
				"\n\n" + Calc.porcentagem(quadraticos.getLista0Quadrado().size(), listaJogos.size()) + "%", // bold,
				" dos sorteios ", // regular
				"não tem ", // bold
				"números quadráticos.\n", // regular
				Calc.porcentagem(quadraticos.getLista1Quadrado().size(), listaJogos.size()) + "%", // bold,
				" tem 1 número quadrático.\n", // regular
				Calc.porcentagem(quadraticos.getLista1Quadrado().size(), listaJogos.size()) + "%", // bold,
				" tem 2 números quadráticos.\n", // regular
				Calc.porcentagem(quadraticos.getLista3Quadrados().size(), listaJogos.size()) + "%", // bold,
				"   tem 3 números quadráticos.\n", // regular

				"\n\nQuadrantes\n\n", // largeAndBlue

				"Ao dividirmos o cartão de apostas desenhando duas barras que se cruzam ao meio em ângulo de 90°, "
						+ "temos os 4 quadrantes. São eles:\n\n", // regular
				" ", // img

				"\n\nSaiba que os sorteios ficam bem distribuídos entre esses quadrantes, ou seja, se jogar as 6 dezenas "
						+ "somente no primeiro quadrante, sua chance de acerto será pequena. ", // regular
				"Mas se seu jogo estiver dividido entre os quatro quadrantes, você tem aproximadamente ", // regular
				Calc.porcentagem(quadrante1.getLista2Incidencias().size(), listaJogos.size()) + "%", // bold,
				" de chance.", // regular

				"\n\nFaixa de números\n\n", // largeAndBlue
				"Temos 6 faixas possíveis entre as 60 dezenas, são elas:\n\n", // regular

				"1ª ", // bold
				"faixa (de 01 a 10)\n", // regular
				"2ª ", // bold
				"faixa (de 11 a 20)\n", // regular
				"3ª ", // bold
				"faixa (de 21 a 30)\n", // regular
				"4ª ", // bold
				"faixa (de 31 a 40)\n", // regular
				"5ª ", // bold
				"faixa (de 41 a 50)\n", // regular
				"6ª ", // bold
				"faixa (de 51 a 60)\n", // regular

				"\nConfira a estatística completa na aba \"análises\". Basta fechar esta "
						+ "janela para voltar ao programa.", // regular

				"\n\nMúltiplos\n\n", // largeAndBlue

				"Denominamos múltiplo de um número o produto desse número por um número natural qualquer."
						+ " Um bom exemplo de múltiplos é encontrado na tradicional tabuada.\n\n", // regular
				"	2 x 1 = 2  |\r\n" + "	2 x 2 = 4  |\r\n" + "	2 x 3 = 6  |\r\n" + " 	2 x 4 = 8 ", // regular
				" | onde 2, 4, 6, 8.. são múltiplos de 2.", // regular

				"\n\nFalando neles, de todos os sorteios realizados até "
						+ listaJogos.get(listaJogos.size() - 1).getDataSorteio() + ", apenas ", // regular
				Calc.porcentagem(multiplosDe2.getLista0Multiplo().size(), listaJogos.size()) + "%", // bold,
				" dos jogos ", // regular
				"não continham ", // bold
				"múltiplos de 2.", // regular

				"\n\nDistância\n\n", // largeAndBlue

				"Achamos a distância entre as dezenas em um sorteio colocando esse jogo em ordem crescente e \r\n"
						+ "analisando a diferença entre um número e sua casa vizinha. Assim:\n\n", // regular

				"" + jogo.toString(), // largeAndGray
				"\n\npegando as duas primeiras dezenas, fica: ", // regular
				"\n" + Calc.zeroToLeft(jogo.getSegundaDezena()) + " - " + Calc.zeroToLeft(jogo.getPrimeiraDezena()), // largeAndGray
				" = " + (jogo.getSegundaDezena() - jogo.getPrimeiraDezena()), // largeAndGray
				"\nLogo, aqui temos a distância " + (jogo.getSegundaDezena() - jogo.getPrimeiraDezena())
						+ " entre essas dezenas\n\n", // regular

				"pegando as dezenas do meio, fica: ", // regular
				"\n" + Calc.zeroToLeft(jogo.getQuartaDezena()) + " - " + Calc.zeroToLeft(jogo.getTerceiraDezena()), // largeAndGray
				" = " + (jogo.getQuartaDezena() - jogo.getTerceiraDezena()), // largeAndGray
				"\nLogo, aqui temos a distância " + (jogo.getQuartaDezena() - jogo.getTerceiraDezena())
						+ " entre essas dezenas\n\n", // regular

				"pegando as duas do final, fica: ", // regular
				"\n" + Calc.zeroToLeft(jogo.getSextaDezena()) + " - " + Calc.zeroToLeft(jogo.getQuintaDezena()), // largeAndGray
				" = " + (jogo.getSextaDezena() - jogo.getQuintaDezena()), // largeAndGray
				"\nLogo, aqui temos a distância " + (jogo.getSextaDezena() - jogo.getQuintaDezena())
						+ " entre essas dezenas", // regular

				"\n\nSequência\n\n", // largeAndBlue

				"Aqui analisamos as dezenas de 3 em 3, da seguinte forma:\n\n", // regular

				"Primeiro campo: ", // regular
				"" + jogoPrimeiroCampo.getPrimeiraDezena() + " " + jogoPrimeiroCampo.getSegundaDezena() + " "
						+ jogoPrimeiroCampo.getTerceiraDezena(), // largeAndGray
				" " + jogoPrimeiroCampo.getQuartaDezena() + " " + jogoPrimeiroCampo.getQuintaDezena() + " "
						+ jogoPrimeiroCampo.getSextaDezena(), // regular

				"\n\nSegundo campo: " + jogoSegundoCampo.getPrimeiraDezena(), // regular
				" " + jogoSegundoCampo.getSegundaDezena() + " " + jogoSegundoCampo.getTerceiraDezena() + " "
						+ jogoSegundoCampo.getQuartaDezena(), // largeAndGray
				" " + jogoSegundoCampo.getQuintaDezena() + " " + jogoSegundoCampo.getSextaDezena(), // regular

				"\n\nTerceiro campo: " + jogoTerceiroCampo.getPrimeiraDezena() + " "
						+ jogoTerceiroCampo.getSegundaDezena(), // regular
				" " + jogoTerceiroCampo.getTerceiraDezena() + " " + jogoTerceiroCampo.getQuartaDezena() + " "
						+ jogoTerceiroCampo.getQuintaDezena(), // largeAndGray
				" " + jogoTerceiroCampo.getSextaDezena(), // regular

				"\n\nQuarto campo: " + jogoQuartoCampo.getPrimeiraDezena() + " " + jogoQuartoCampo.getSegundaDezena()
						+ " " + jogoQuartoCampo.getTerceiraDezena() + " ", // regular
				jogoQuartoCampo.getQuartaDezena() + " " + jogoQuartoCampo.getQuintaDezena() + " "
						+ jogoQuartoCampo.getSextaDezena(), // largeAndGray
				"\n\nTambém buscamos por uma sequência completa. Para nossos palpites, iremos evitar sequências... ", // regular

		};

		String[] initStyles = { "largeAndBlue", "regular", "bold", "iconCubicos", "regular", "bold", "regular", "bold",
				"regular", "bold", "regular", "bold", "regular", "largeAndBlue", "regular", "small", "iconFibonacci",
				"iconConcha", "regular", "regular", "bold", "regular", "bold", "regular", "bold", "regular",
				"largeAndBlue", "regular", "italic", "iconPar", "italic", "iconImpar", "regular", "bold", "regular",
				"bold", "regular", "bold", "regular", "bold", "regular", "bold", "largeAndBlue", "regular", "bold",
				"regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold",
				"regular", "regular", "bold", "regular", "bold", "regular", "largeAndBlue", "regular", "iconQuadrado1",
				"iconQuadrado2", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold",
				"regular", "largeAndBlue", "regular", "iconQuadrante", "regular", "regular", "bold", "regular",
				"largeAndBlue", "regular", "bold", "regular", "bold", "regular", "bold", "regular", "bold", "regular",
				"bold", "regular", "bold", "regular", "regular", "largeAndBlue", "regular", "regular", "regular",
				"regular", "bold", "regular", "bold", "regular", "largeAndBlue", "regular", "largeAndGray", "regular",
				"largeAndGray", "largeAndGray", "regular", "regular", "largeAndGray", "largeAndGray", "regular",
				"regular", "largeAndGray", "largeAndGray", "regular", "largeAndBlue", "regular", "regular",
				"largeAndGray", "regular", "regular", "largeAndGray", "regular", "regular", "largeAndGray", "regular",
				"regular", "largeAndGray", "regular"

		};

		textPane = new JTextPane();
		textPane.setEditable(false);
		StyledDocument doc = textPane.getStyledDocument();
		addStylesToDocument(doc);

		try {
			for (int i = 0; i < initString.length; i++) {
				doc.insertString(doc.getLength(), initString[i], doc.getStyle(initStyles[i]));
			}
		} catch (BadLocationException ble) {
			System.err.println("Couldn't insert initial text into text pane.");
		}

		// Put the text pane in a scroll pane.
		textScrollPane = new JScrollPane(textPane);
		textScrollPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		textScrollPane.setBorder(null);
		textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textScrollPane.setPreferredSize(new Dimension(250, 145));
		textScrollPane.setMinimumSize(new Dimension(10, 10));
		textPane.setCaretPosition(0);
	}

	private void addStylesToDocument(StyledDocument doc) {
		// Initialize some styles.
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "Tahoma");

		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);

		s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);

		s = doc.addStyle("small", regular);
		StyleConstants.setFontSize(s, 10);

		s = doc.addStyle("largeAndBlue", regular);
		StyleConstants.setForeground(s, Color.blue);
		StyleConstants.setFontSize(s, 16);

		s = doc.addStyle("largeAndGray", regular);
		StyleConstants.setForeground(s, Color.DARK_GRAY);
		StyleConstants.setBold(s, true);
		StyleConstants.setFontSize(s, 16);

		s = doc.addStyle("iconCubicos", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconCubicos = createImageIcon("/img/cubicos.png", "cubicos");
		if (iconCubicos != null) {
			StyleConstants.setIcon(s, iconCubicos);
		}

		s = doc.addStyle("iconFibonacci", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconFibonacci = createImageIcon("/img/fibonacci.png", "Fibo1");
		if (iconFibonacci != null) {
			StyleConstants.setIcon(s, imageScale(iconFibonacci));
		}

		s = doc.addStyle("iconConcha", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconConcha = createImageIcon("/img/conchaFibonacci.jpg", "Fibo2");
		if (iconConcha != null) {
			StyleConstants.setIcon(s, imageScale(iconConcha));
		}

		s = doc.addStyle("iconPar", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconPar = createImageIcon("/img/par.png", "par");
		if (iconPar != null) {
			StyleConstants.setIcon(s, iconPar);
		}

		s = doc.addStyle("iconImpar", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconImpar = createImageIcon("/img/impar.png", "par");
		if (iconImpar != null) {
			StyleConstants.setIcon(s, iconImpar);
		}

		s = doc.addStyle("iconQuadrado1", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconQuadrado1 = createImageIcon("/img/quadrado1.gif", "Quadrado1");
		if (iconQuadrado1 != null) {
			StyleConstants.setIcon(s, imageScale(iconQuadrado1));
		}

		s = doc.addStyle("iconQuadrado2", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconQuadrado2 = createImageIcon("/img/quadrado2.png", "Quadrado2");
		if (iconQuadrado2 != null) {
			StyleConstants.setIcon(s, iconQuadrado2);
		}

		s = doc.addStyle("iconQuadrante", regular);
		StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);

		ImageIcon iconQuadrante = createImageIcon("/img/quadrante.png", "quadrantes");
		if (iconQuadrante != null) {
			StyleConstants.setIcon(s, iconQuadrante);
		}

	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = FrameSobreAnalises.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private ImageIcon imageScale(ImageIcon icon) {
		Image image = icon.getImage(); // transform it
		Image newimg = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		icon = new ImageIcon(newimg); // transform it back
		return icon;
	}

	private void criaTopPanel() {

		topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topPanel.setBackground(Color.WHITE);

		criaTextPane();

		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING).addComponent(textScrollPane,
				GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE));
		gl_topPanel.setVerticalGroup(gl_topPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_topPanel.createSequentialGroup().addContainerGap().addComponent(textScrollPane,
						GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)));
		topPanel.setLayout(gl_topPanel);

	}

	private void criaJanela() {

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);// <- prevent closing
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Principal.frameSobreAnalisesEstaFechado(true);
				dispose();
			}
		});

		setSize(600, 450);
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((tela.width - this.getSize().width) / 2, (tela.height - this.getSize().height) / 2);
		setVisible(true);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup()
						.addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 594, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(topPanel,
				GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE));
		getContentPane().setLayout(groupLayout);
	}

	@Override
	public void run() {

		criaTopPanel();
		criaJanela();

		Principal.frameSobreAnalisesEstaFechado(false);
	}
}
