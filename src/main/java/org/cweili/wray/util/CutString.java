package org.cweili.wray.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;

/**
 * 中文截取
 * 
 * @author Cweili
 * @version 2012-8-21 上午11:15:00
 * 
 */
public class CutString {

	/**
	 * 判断是否是一个中文汉字
	 * 
	 * @param c
	 *            字符
	 * @return true表示是中文汉字，false表示是英文字母
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static boolean isChineseChar(char c) {
		// 如果字节数大于1，是汉字
		// 以这种方式区别英文字母和中文汉字并不是十分严谨，但在这个题目中，这样判断已经足够了
		try {
			return String.valueOf(c).getBytes(Constant.CHARSET).length > 2;
		} catch (UnsupportedEncodingException e) {
			return false;
		}
	}

	/**
	 * 按字节截取字符串
	 * 
	 * @param orignal
	 *            原始字符串
	 * @param count
	 *            截取位数
	 * @return 截取后的字符串
	 * @throws UnsupportedEncodingException
	 *             使用了JAVA不支持的编码格式
	 */
	public static String substring(String orignal, int count) {
		// 原始字符不为null，也不是空字符串
		if (!StringUtils.isEmpty(orignal)) {
			// 要截取的字节数大于0，且小于原始字符串的字节数
			int length = 0;
			try {
				length = orignal.getBytes(Constant.CHARSET).length;
			} catch (UnsupportedEncodingException e1) {

			}
			if (count > 0 && count < length) {
				StringBuilder buff = new StringBuilder();
				for (int i = 0; i < count; ++i) {
					char c;
					try {
						c = orignal.charAt(i);
					} catch (StringIndexOutOfBoundsException e) {
						break;
					}
					buff.append(c);
					if (CutString.isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减2
						count = count - 2;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}

	public static void main(String[] args) {
		// 原始字符串
		String s = "爱中国爱China爱Java";
		System.out.println("原始字符串：" + s);
		System.out.println("截取前1位：" + CutString.substring(s, 1));
		System.out.println("截取前10位：" + CutString.substring(s, 10));
		System.out.println("截取前20位：" + CutString.substring(s, 20));
		System.out.println("截取前30位：" + CutString.substring(s, 30));
	}
}
