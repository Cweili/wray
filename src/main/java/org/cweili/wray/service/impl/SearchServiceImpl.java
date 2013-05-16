package org.cweili.wray.service.impl;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.cweili.wray.domain.dto.Article;
import org.cweili.wray.service.SearchService;
import org.cweili.wray.util.Captcha;
import org.cweili.wray.util.ChineseSegment;
import org.cweili.wray.util.Function;
import org.springframework.stereotype.Service;

/**
 * 搜索 Service 实现
 * 
 * @author Cweili
 * @version 2013-4-27 下午3:12:49
 * 
 */
@Service("searchService")
public class SearchServiceImpl extends BaseService implements SearchService {

	@Override
	public String[] segmentKeyword(String keyword) {
		return ChineseSegment.segmentToArray(keyword);
	}

	@Override
	public Article[] findByKeyword(String[] keywords) {
		Map<Article, Integer> articleMap = new LinkedHashMap<Article, Integer>();
		String[] replacements = new String[keywords.length];
		for (int i = 0; i < keywords.length; ++i) {
			for (Article article : articleDao.findBykeywordAndStat(keywords[i],
					Article.STAT_PUBLISHED)) {
				if (articleMap.containsKey(article)) {
					articleMap.put(article, articleMap.get(article) + 1);
				} else {
					articleMap.put(article, 1);
				}
			}

			// 关键字高亮
			Color background = Captcha.getRandomColor(false);
			String backgroundStr = StringUtils.join(
					new Integer[] { background.getRed(), background.getGreen(),
							background.getBlue() }, ',');
			Color color = Captcha.getRandomColor(true);
			String colorStr = StringUtils.join(new Integer[] { color.getRed(), color.getGreen(),
					color.getBlue() }, ',');
			replacements[i] = "<span style=\"background-color:rgb(" + backgroundStr
					+ ");color:rgb(" + colorStr + ");\">" + keywords[i] + "</span>";
		}
		List<Entry<Article, Integer>> entryList = Function.sortMapByValue(articleMap);
		Article[] articles = new Article[entryList.size()];
		for (int i = 0; i < articles.length; ++i) {
			articles[i] = dealArticleContent(entryList.get(i).getKey());
			articles[i].setContent(StringUtils.replaceEach(articles[i].getContent(), keywords,
					replacements));
			articles[i].setTitle(StringUtils.replaceEach(articles[i].getTitle(), keywords,
					replacements));
		}
		return articles;
	}

}
