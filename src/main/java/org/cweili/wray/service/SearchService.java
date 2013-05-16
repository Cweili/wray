package org.cweili.wray.service;

import org.cweili.wray.domain.dto.Article;

/**
 * 搜索 Service
 * 
 * @author Cweili
 * @version 2013-4-3 下午3:38:14
 * 
 */
public interface SearchService {

	public String[] segmentKeyword(String keyword);

	public Article[] findByKeyword(String[] keywords);
}
