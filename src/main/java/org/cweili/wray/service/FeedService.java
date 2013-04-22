package org.cweili.wray.service;

import org.cweili.feed.atom.Atom;
import org.cweili.feed.rss.RSS;

/**
 * 
 * @author cweili
 * @version 2013-4-3 下午3:38:14
 * 
 */
public interface FeedService {

	public String getRss();

	public String getAtom();

	public void setRss(RSS rss);

	public void setAtom(Atom atom);

	public void clearFeedCache();
}
