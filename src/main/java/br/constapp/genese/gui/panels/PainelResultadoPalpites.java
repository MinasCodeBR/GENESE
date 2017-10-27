package br.constapp.genese.gui.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class PainelResultadoPalpites extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static JPanel painelResultadoPalpites;
	
	public PainelResultadoPalpites() {
		preparaPainelResultadoPalpites();		
	}

	private static void preparaPainelResultadoPalpites() {
		
		painelResultadoPalpites = new JPanel();
		painelResultadoPalpites.setFont(new Font("Tahoma", Font.PLAIN, 12));
		painelResultadoPalpites.setBorder(new TitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, null, null));
		painelResultadoPalpites.setBackground(Color.WHITE);	
		
		
	}



	public static JPanel getPainelResuldatoPalpites() {
		return painelResultadoPalpites;
	}
}
