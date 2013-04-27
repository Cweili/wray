/**
 * 
 */
package org.cweili.wray.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.recognition.NatureRecognition;
import org.apache.commons.lang3.StringUtils;

/**
 * 中文分词
 * 
 * @author cweili
 * @version 2013-4-26 下午12:38:51
 * 
 */
public class ChineseSegment {

	public static final Set<Character> useLessNature = new HashSet<Character>(5);

	static {
		useLessNature.add('c');
		useLessNature.add('e');
		useLessNature.add('u');
		useLessNature.add('w');
		useLessNature.add('p');
	}

	/**
	 * 获取关键词数组
	 * 
	 * @param string
	 *            原文
	 * @return 关键词数组
	 */
	public static String[] segmentToArray(String string) {
		Set<String> stringSet = segmentToSet(string);
		String[] strings = new String[stringSet.size()];
		return stringSet.toArray(strings);
	}

	/**
	 * 获取关键词集合
	 * 
	 * @param string
	 *            原文
	 * @return 关键词集合
	 */
	public static Set<String> segmentToSet(String string) {
		List<Term> terms = ToAnalysis.paser(string);
		new NatureRecognition(terms).recognition();

		Set<String> result = new HashSet<String>(terms.size());
		for (Term term : terms) {
			if (null != StringUtils.stripToNull(term.getNatrue().natureStr)
					&& ("en".equals(term.getNatrue().natureStr) || !useLessNature.contains(term
							.getNatrue().natureStr.charAt(0)))) {

				String part = StringUtils.trimToNull(term.getName().replaceAll(
						"[\\s\\p{Blank}\\p{Cntrl}\\p{Punct}]", ""));
				if (null != part) {
					result.add(term.getName().toLowerCase());
				}
			}
		}

		return result;
	}
}
