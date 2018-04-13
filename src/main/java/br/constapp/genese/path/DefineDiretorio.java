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

package br.constapp.genese.path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import br.constapp.genese.gui.panels.PainelResultadoPalpites;
import br.constapp.genese.util.DownloadAndUnzip;

public final class DefineDiretorio implements Runnable {

	private static String diretorioRaiz;
	private static String diretorioCEF;
	private static String diretorioCombinacoes;
	private static String diretorioFiltro;
	private static final String USERPROFILE = System.getenv("USERPROFILE");
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String OS_NAME = System.getProperty("os.name");

	public DefineDiretorio() {
	}

	public static String getDiretorioRaiz() {
		try {
			setDiretorioRaiz();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diretorioRaiz + File.separator;
	}

	private static void setDiretorioRaiz() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			diretorioRaiz = (USERPROFILE + "\\AppData\\Local\\GENESE");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				diretorioRaiz = (USER_HOME + "/.genese/CEF");
			}
		}
		File targetDir = new File(diretorioRaiz);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getDiretorioCEF() {
		try {
			setDiretorioCEF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diretorioCEF + File.separator;
	}

	private static void setDiretorioCEF() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			diretorioCEF = (USERPROFILE + "\\AppData\\Local\\GENESE\\CEF");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				diretorioCEF = (USER_HOME + "/.genese/CEF");
			}
		}
		File targetDir = new File(diretorioCEF);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getDiretorioCombinacoes() {
		try {
			setDiretorioCombinacoes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diretorioCombinacoes + File.separator;
	}

	private static void setDiretorioCombinacoes() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			diretorioCombinacoes = (USERPROFILE + "\\AppData\\Local\\GENESE\\serial\\cmb");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				diretorioCombinacoes = (USER_HOME + "/.genese/serial/cmb");
			}
		}
		File targetDir = new File(diretorioCombinacoes);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getDiretorioFiltro() {

		try {
			setDiretorioFiltro();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return diretorioFiltro + File.separator;
	}

	private static void setDiretorioFiltro() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			diretorioFiltro = (USERPROFILE + "\\AppData\\Local\\GENESE\\serial\\filtro");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				diretorioFiltro = (USER_HOME + "/.genese/serial/filtro");
			}
		}
		File targetDir = new File(diretorioFiltro);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}

	}

	private static boolean buildDirectory(File file) {
		return file.exists() || file.mkdirs();
	}

	private static int getFolderSize(String diretorio) {
		File folder = new File(diretorio);
		int size = 0;
		if (folder.isDirectory()) {
			String[] dirList = folder.list();
			if (dirList != null) {
				for (int i = 0; i < dirList.length; i++) {
					String fileName = dirList[i];
					File f = new File(diretorio, fileName);
					if (f.isDirectory()) {
						String filePath = f.getPath();
						size += getFolderSize(filePath);
						continue;
					}
					size += f.length();
				}
			}
		}
		return size;
	}

	public static boolean isDiretorioVazio(String diretorio) {
		File dir = new File(diretorio);
		String[] children = dir.list();

		if (children.length < 51 || getFolderSize(diretorio) < 260000000) {
			return true;
		} else {
            return children.length != 51 || getFolderSize(diretorio) <= 260000000;
        }
	}

	@Override
	public void run() {

		if (isDiretorioVazio(getDiretorioFiltro())) {

			PainelResultadoPalpites.setProgressBar(true);
			PainelResultadoPalpites.getBtnEstouComSorte().setEnabled(false);
			PainelResultadoPalpites.painelCfgHabilitado(false);
			PainelResultadoPalpites.setLblDisplay("Copiando listas...");

			for (int i = 1; i <= 51; i++) {

				String nomeArquivo = "lista_" + i + ".ser";

				InputStream file = getClass().getClassLoader().getResourceAsStream("res/serial/filtro/" + nomeArquivo);
				File outPutFile = new File(DefineDiretorio.getDiretorioFiltro() + nomeArquivo);

				try {
					DownloadAndUnzip.copyInputStream(file, new FileOutputStream(outPutFile));
					PainelResultadoPalpites.setLblDisplay("Lista " + i + " copiada com sucesso!");

				} catch (FileNotFoundException e) {
					System.err.println("Falha ao copiar lista - " + e.toString());
				} catch (IOException e) {
					System.err.println("Falha ao copiar lista - " + e.toString());
				}
			}
			PainelResultadoPalpites.getBtnEstouComSorte().setEnabled(true);
			PainelResultadoPalpites.painelCfgHabilitado(true);
			PainelResultadoPalpites.setProgressBar(false);
			Thread.currentThread().interrupt();
			PainelResultadoPalpites.iniciaThreadGerador();
		}

	}
}
