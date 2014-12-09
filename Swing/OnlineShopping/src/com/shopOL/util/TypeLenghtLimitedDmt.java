/** 
 * 限制文本框类型 长度
 */

package com.shopOL.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

/**
 * 
 * @author Administrator 限制输入条件(长度，类型)
 */

@SuppressWarnings("serial")
public class TypeLenghtLimitedDmt extends PlainDocument {

	/**
	 * 类型为仅数字 0~9
	 */
	public static final int TYPE_NUM_ONLY = 0;

	/**
	 * 类型为数字、字母混合
	 */
	public static final int TYPE_letter_NUM = 1;

	/**
	 * 类型为字母、汉字混合
	 */
	public static final int TYPE_letter_Hanzi = 2;

	/**
	 * 类型为数字、字母、汉字混合
	 */
	public static final int TYPE_ALL = 3;

	/**
	 * 类型为money
	 */
	public static final int TYPE_MONEY = 4;
	/**
	 * 类型为money
	 */
	public static final int ANY_TYPE = 5;

	private int limitedLength;// 长度
	private int type;

	public TypeLenghtLimitedDmt(int limitedLength, int type) {
		this.limitedLength = limitedLength;
		this.type = type;
	}

	/**
	 * 重写insertString()方法
	 */
	public void insertString(int offs, String str, AttributeSet attr) {
		try {
			if (str == null) {// 输入为空，直接返回
				return;
			}
			/*
			 * 为限定金额小数点用的
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
			if ((this.getLength() + str.length()) <= limitedLength) {// 原有的字符串和新输入的字符串长度小于限制长度
				char[] charArray = str.toCharArray();// 将新输入字符串转换为字节数组

				int length = 0;
				for (int i = 0; i < charArray.length; i++) {
					switch (type) {

					// 仅数字
					case TYPE_NUM_ONLY:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// 筛选出数字
							charArray[length++] = charArray[i];
						}
						break;
					// 数字、字母混合
					case TYPE_letter_NUM:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// 筛选出数字
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'a' && charArray[i] <= 'z') {// 筛选出小写字母
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// 筛选出大写字母
							charArray[length++] = charArray[i];
						}
						break;
					// 字母、汉字
					case TYPE_letter_Hanzi:
						if (charArray[i] >= 'a' && charArray[i] <= 'z') {// 筛选出小写字母
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// 筛选出大写字母
							charArray[length++] = charArray[i];
						} else {// 筛选出汉字
							String regEx = "[\\u4e00-\\u9fa5]";
							Pattern p = Pattern.compile(regEx);
							Matcher m = p.matcher(str);
							if (m.find()) {
								charArray[length++] = charArray[i];
							}
						}
						break;
					// 全部
					case TYPE_ALL:
						if (charArray[i] >= '0' && charArray[i] <= '9') {// 筛选出数字
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'a' && charArray[i] <= 'z') {// 筛选出小写字母
							charArray[length++] = charArray[i];
						} else if (charArray[i] >= 'A' && charArray[i] <= 'Z') {// 筛选出大写字母
							charArray[length++] = charArray[i];
						} else {// 筛选出汉字
							String regEx = "[\\u4e00-\\u9fa5]";
							Pattern p = Pattern.compile(regEx);
							Matcher m = p.matcher(str);
							if (m.find()) {
								charArray[length++] = charArray[i];
							}
						}
						break;
					// money专用，无长度（理论上……）
					case TYPE_MONEY:
						if (dian) {
							if (charArray[i] >= '0' && charArray[i] <= '9') {// 筛选出数字
								charArray[length++] = charArray[i];
							} else if (charArray[i] == '.') {
								charArray[length++] = charArray[i];
							}
						} else {
							if (charArray[i] >= '0' && charArray[i] <= '9') {// 筛选出数字
								charArray[length++] = charArray[i];
							}
						}
						break;
					case ANY_TYPE:
						charArray[length++] = charArray[i];
						break;
					}

				}
				super.insertString(offs, new String(charArray, 0, length), attr);// 插入满足条件的字符串

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}