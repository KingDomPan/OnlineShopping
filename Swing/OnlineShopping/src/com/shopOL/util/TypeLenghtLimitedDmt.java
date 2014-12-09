/** 
 * �����ı������� ����
 */

package com.shopOL.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

/**
 * 
 * @author Administrator ������������(���ȣ�����)
 */

@SuppressWarnings("serial")
public class TypeLenghtLimitedDmt extends PlainDocument {

	/**
	 * ����Ϊ������ 0~9
	 */
	public static final int TYPE_NUM_ONLY = 0;

	/**
	 * ����Ϊ���֡���ĸ���
	 */
	public static final int TYPE_letter_NUM = 1;

	/**
	 * ����Ϊ��ĸ�����ֻ��
	 */
	public static final int TYPE_letter_Hanzi = 2;

	/**
	 * ����Ϊ���֡���ĸ�����ֻ��
	 */
	public static final int TYPE_ALL = 3;

	/**
	 * ����Ϊmoney
	 */
	public static final int TYPE_MONEY = 4;
	/**
	 * ����Ϊmoney
	 */
	public static final int ANY_TYPE = 5;

	private int limitedLength;// ����
	private int type;

	public TypeLenghtLimitedDmt(int limitedLength, int type) {
		this.limitedLength = limitedLength;
		this.type = type;
	}

	/**
	 * ��дinsertString()����
	 */
	public void insertString(int offs, String str, AttributeSet attr) {
		try {
			if (str == null) {// ����Ϊ�գ�ֱ�ӷ���
				return;
			}
			/*
			 * Ϊ�޶����С�����õ�
			 */
			boolean dian = true;
			if (this.getLength() > 0) {
				char[] charArray2 = this.getText(0, this.getLength())
						.toCharArray();
				if (charArray2[this.getLength() - 1] == '.') {
					dian = false;
					limitedLength = 100;
				} else if (this.getLength() > 1) {
					if (charArray2[this.getLength() - 2] == '.') {
						dian = false;
						limitedLength = this.getLength() + 1;
					}
				}
			}
			if ((this.getLength() + str.length()) <= limitedLength) {// ԭ�е��ַ�������������ַ�������С�����Ƴ���
				char[] charArray = str.toCharArray();// ���������ַ���ת��Ϊ�ֽ�����

				int length = 0;
				for (int i = 0; i < charArray.length; i++) {
					switch (type) {

					// ������
					case TYPE_NUM_ONLY:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// ɸѡ������
							charArray[length++] = charArray[i];
						}
						break;
					// ���֡���ĸ���
					case TYPE_letter_NUM:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// ɸѡ������
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'a' && charArray[i] <= 'z') {// ɸѡ��Сд��ĸ
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// ɸѡ����д��ĸ
							charArray[length++] = charArray[i];
						}
						break;
					// ��ĸ������
					case TYPE_letter_Hanzi:
						if (charArray[i] >= 'a' && charArray[i] <= 'z') {// ɸѡ��Сд��ĸ
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// ɸѡ����д��ĸ
							charArray[length++] = charArray[i];
						} else {// ɸѡ������
							String regEx = "[\\u4e00-\\u9fa5]";
							Pattern p = Pattern.compile(regEx);
							Matcher m = p.matcher(str);
							if (m.find()) {
								charArray[length++] = charArray[i];
							}
						}
						break;
					// ȫ��
					case TYPE_ALL:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// ɸѡ������
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'a' && charArray[i] <= 'z') {// ɸѡ��Сд��ĸ
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// ɸѡ����д��ĸ
							charArray[length++] = charArray[i];
						} else {// ɸѡ������
							String regEx = "[\\u4e00-\\u9fa5]";
							Pattern p = Pattern.compile(regEx);
							Matcher m = p.matcher(str);
							if (m.find()) {
								charArray[length++] = charArray[i];
							}
						}
						break;
					// moneyר�ã��޳��ȣ������ϡ�����
					case TYPE_MONEY:
						if (dian) {
							if (charArray[i] >= '0' && charArray[i] <= '9') {// ɸѡ������
								charArray[length++] = charArray[i];
							} else if (charArray[i] == '.') {
								charArray[length++] = charArray[i];
							}
						} else {
							if (charArray[i] >= '0' && charArray[i] <= '9') {// ɸѡ������
								charArray[length++] = charArray[i];
							}
						}
						break;
					case ANY_TYPE:
						charArray[length++] = charArray[i];
						break;
					}

				}
				super.insertString(offs, new String(charArray, 0, length), attr);// ���������������ַ���

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}