package org.cweili.wray.service.impl;

import org.cweili.feed.atom.Atom;
import org.cweili.feed.rss.RSS;
import org.cweili.wray.service.FeedService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author cweili
 * @version 2012-8-16 下午5:23:30
 * 
 */
@Service("feedService")
public class FeedServiceImpl extends BaseService implements FeedService {

	@Override
	public String getRss() {
		return rss;
	}

	@Override
	public String getAtom() {
		return atom;
	}

	@Override
	public void setRss(RSS rssNew) {
		rss = rssNew.toString();
	}

	@Override
	public void setAtom(Atom atomNew) {
		atom = atomNew.toString();
	}

	@Override
	public void clearFeedCache() {
		rss = null;
		atom = null;
	}

}
