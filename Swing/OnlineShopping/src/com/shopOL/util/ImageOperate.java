package com.shopOL.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageOperate {
	@SuppressWarnings("resource")
	public static void copyImg(String mudi, String yuan) {
		try {
			FileOutputStream fos = new FileOutputStream(mudi);
			FileInputStream fis = new FileInputStream(yuan);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}

	}
}
