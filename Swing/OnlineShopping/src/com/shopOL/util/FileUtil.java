package com.shopOL.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FileUtil {

	/**
	 * ��ȡָ��Ŀ¼���ļ���
	 * 
	 * @param folder
	 * @return
	 */
	public static List<String> dirFile(String folder) {
		List<String> filelList = new ArrayList<String>();
		File file = null;

		file = new File(folder);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File floderFile : files) {
				if (floderFile.isFile()) {
					filelList.add(floderFile.getName());
				}
			}
		}
		return filelList;
	}

	/**
	 * ��ȡָ��Ŀ¼��Ŀ¼��
	 * 
	 * @param folder
	 * @return
	 */
	public static List<String> dirfolder(String folder) {
		List<String> folderlList = new ArrayList<String>();
		File file = null;

		file = new File(folder);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File floderFile : files) {
				if (floderFile.isDirectory()) {
					folderlList.add(floderFile.getName());
				}
			}
		}
		return folderlList;
	}

	/**
	 * ��ȡ�ļ��е�����
	 * 
	 * @param pathname
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map getProperties(String pathname) {
		Properties prop = new Properties();

		Reader reader = null;
		try {
			reader = new FileReader(pathname);
			prop.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	/**
	 * �����ݱ��浽�ļ���
	 * 
	 * @param pathname
	 * @param map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean saveProperties(String pathname, Map map) {
		Properties prop = new Properties();

		Writer writer = null;
		try {
			writer = new FileWriter(pathname);
			prop.putAll(map);
			prop.store(writer, "");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * ��ȡ�ļ����ݱ��浽list��һ��Ϊlist��һ�����ݣ�
	 * 
	 * @param pathname
	 * @return
	 */
	public static List<String> reader(String pathname) {
		List<String> data = new ArrayList<String>();

		BufferedReader bReader = null;
		try {
			bReader = new BufferedReader(new FileReader(pathname));

			String row = null;
			while ((row = bReader.readLine()) != null) {
				data.add(row);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

	/**
	 * ��list������д���ļ�
	 * 
	 * @param pathname
	 * @param data
	 * @param append
	 * @return
	 */
	private static boolean writer(String pathname, List<String> data,
			boolean append) {
		BufferedWriter bWriter = null;
		try {
			bWriter = new BufferedWriter(new FileWriter(pathname, append));

			for (String row : data) {
				bWriter.write(row);
				bWriter.newLine();
			}
			bWriter.flush();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bWriter != null) {
				try {
					bWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * ��һ������׷�ӽ��ļ�
	 * 
	 * @param pathname
	 * @param message
	 */
	public static void append(String pathname, String message) {
		List<String> data = new ArrayList<String>();
		data.add(message);
		writer(pathname, data, true);
	}

	/**
	 * ��list������׷�ӽ��ļ�
	 * 
	 * @param pathname
	 * @param data
	 */
	public static void append(String pathname, List<String> data) {
		writer(pathname, data, true);
	}

	/**
	 * ��list������д���ļ������ǣ�
	 * 
	 * @param pathname
	 * @param list
	 */
	public static void save(String pathname, List<String> data) {
		writer(pathname, data, false);
	}

	/**
	 * ɾ�������ļ�
	 * 
	 * @param sPath
	 *            ��ɾ���ļ����ļ���
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
