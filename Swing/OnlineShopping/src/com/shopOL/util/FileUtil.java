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
	 * 获取指定目录下文件名
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
	 * 获取指定目录下目录名
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
	 * 获取文件中的数据
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
	 * 将数据保存到文件中
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
	 * 读取文件数据保存到list（一行为list中一个数据）
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
	 * 将list中数据写入文件
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
	 * 将一行数据追加进文件
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
	 * 将list中数据追加进文件
	 * 
	 * @param pathname
	 * @param data
	 */
	public static void append(String pathname, List<String> data) {
		writer(pathname, data, true);
	}

	/**
	 * 将list中数据写入文件（覆盖）
	 * 
	 * @param pathname
	 * @param list
	 */
	public static void save(String pathname, List<String> data) {
		writer(pathname, data, false);
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
