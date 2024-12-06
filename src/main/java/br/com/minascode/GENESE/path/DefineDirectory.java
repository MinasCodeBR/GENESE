/**
 * GENESE - Gerador de Números e Estatísticas para Mega-Sena
 * Copyright (C) 2018-2024 MinasCode
 *
 * Autor: Rafael Teixeira
 * Email: rafaelfst@live.com
 * Versão: 0.0.1
 * Licença: GNU General Public License v3.0
 * 
 * Este programa é um software livre: você pode redistribuí-lo e/ou
 * modificá-lo sob os termos da Licença Pública Geral GNU, conforme
 * publicada pela Free Software Foundation, seja a versão 3 da
 * Licença, ou (a seu critério) qualquer versão posterior.
 * 
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem mesmo a garantia implícita de
 * COMERCIALIZAÇÃO ou ADEQUAÇÃO A UM DETERMINADO FIM. Veja a
 * Licença Pública Geral GNU para mais detalhes.
 * 
 * Você deve ter recebido uma cópia da Licença Pública Geral GNU
 * junto com este programa. Caso contrário, veja <https://www.gnu.org/licenses/>.
 */

package br.com.minascode.GENESE.path;

import java.io.File;
import java.io.IOException;

public final class DefineDirectory {

	private static String rootDirectory;
	private static String cefDirectory;
	private static String combinationsDirectory;
	private static String megaSenaCombinationDir;
	private static String lotoFacilCombinationDir;
	private static String arffDirectory;
	private static String filterDirectory;
	private static String megaSenafilterDir;
	private static String lotoFacilfilterDir;
	private static String serialDirectory;

	private static final String USER_PROFILE = System.getenv("USERPROFILE");
	private static final String USER_HOME = System.getProperty("user.home");
	private static final String OS_NAME = System.getProperty("os.name");

	public DefineDirectory() {
	}

	public static String getRootDirectory() {
		try {
			setRootDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootDirectory + File.separator;
	}

	private static void setRootDirectory() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			rootDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				rootDirectory = (USER_HOME + "/.genese/CEF");
			}
		}
		File targetDir = new File(rootDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getSerialDirectory() {
		try {
			setSerialDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return serialDirectory + File.separator;
	}

	private static void setSerialDirectory() throws IOException {
		if (OS_NAME.toLowerCase().contains("windows")) {
			serialDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				serialDirectory = (USER_HOME + "/.genese/serial");
			}
		}
		File targetDir = new File(serialDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getCefDirectory() {
		try {
			setCefDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cefDirectory + File.separator;
	}

	private static void setCefDirectory() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			cefDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE\\CEF");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				cefDirectory = (USER_HOME + "/.genese/CEF");
			}
		}
		File targetDir = new File(cefDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getCombinationsDirectory() {
		try {
			setCombinationsDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return combinationsDirectory + File.separator;
	}

	private static void setCombinationsDirectory() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			combinationsDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\cmb");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				combinationsDirectory = (USER_HOME + "/.genese/serial/cmb");
			}
		}
		File targetDir = new File(combinationsDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}
	
	public static String getMegaSenaCombinationDir() {
		try {
			setMegaSenaCombinationDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return megaSenaCombinationDir + File.separator;
	}

	private static void setMegaSenaCombinationDir() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			megaSenaCombinationDir = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\cmb\\megasena");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				megaSenaCombinationDir = (USER_HOME + "/.genese/serial/cmb/megasena");
			}
		}
		File targetDir = new File(megaSenaCombinationDir);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}
	
	public static String getLotoFacilCombinationDir() {
		try {
			setLotoFacilCombinationDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lotoFacilCombinationDir + File.separator;
	}

	private static void setLotoFacilCombinationDir() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			lotoFacilCombinationDir = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\cmb\\lotofacil");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				lotoFacilCombinationDir = (USER_HOME + "/.genese/serial/cmb/lotofacil");
			}
		}
		File targetDir = new File(lotoFacilCombinationDir);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}
	
	public static String getArffDirectory() {
		try {
			setArffDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arffDirectory + File.separator;
	}

	private static void setArffDirectory() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			arffDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\arff");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				arffDirectory = (USER_HOME + "/.genese/serial/arff");
			}
		}
		File targetDir = new File(arffDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	public static String getFilterDirectory() {

		try {
			setFilterDirectory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filterDirectory + File.separator;
	}

	private static void setFilterDirectory() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			filterDirectory = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\filtro");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				filterDirectory = (USER_HOME + "/.genese/serial/filtro");
			}
		}
		File targetDir = new File(filterDirectory);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}
	
	public static String getMegaSenaFilterDir() {

		try {
			setMegaSenaFilterDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return megaSenafilterDir + File.separator;
	}

	private static void setMegaSenaFilterDir() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			megaSenafilterDir = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\filtro\\megasena");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				megaSenafilterDir = (USER_HOME + "/.genese/serial/filtro/megasena");
			}
		}
		File targetDir = new File(megaSenafilterDir);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}
	
	public static String getLotoFacilFilterDir() {

		try {
			setLotoFacilFilterDir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lotoFacilfilterDir + File.separator;
	}

	private static void setLotoFacilFilterDir() throws IOException {

		if (OS_NAME.toLowerCase().contains("windows")) {
			lotoFacilfilterDir = (USER_PROFILE + "\\AppData\\Local\\GENESE\\serial\\filtro\\lotofacil");
		} else {
			if (OS_NAME.toLowerCase().contains("linux")) {
				lotoFacilfilterDir = (USER_HOME + "/.genese/serial/filtro/lotofacil");
			}
		}
		File targetDir = new File(lotoFacilfilterDir);
		if (!buildDirectory(targetDir)) {
			throw new IOException("Não foi possível criar o diretório: " + targetDir);
		}
	}

	private static boolean buildDirectory(File file) {
		return file.exists() || file.mkdirs();
	}

	private static int getFolderSize(String directory) {
		File folder = new File(directory);
		int size = 0;
		if (folder.isDirectory()) {
			String[] dirList = folder.list();
			if (dirList != null) {
				for (int i = 0; i < dirList.length; i++) {
					String fileName = dirList[i];
					File f = new File(directory, fileName);
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

	public static boolean isDirectoryEmpty(String directory) {
		File dir = new File(directory);
		String[] children = dir.list();

		if (children.length < 501) {
			return true;
		} else {
			return children.length != 501;
		}
	}
}
