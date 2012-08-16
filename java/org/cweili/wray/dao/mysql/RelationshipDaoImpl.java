package org.cweili.wray.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 *
 * @author Cweili
 * @version 2012-8-16 下午10:56:49
 *
 */
public class RelationshipDaoImpl extends BaseDaoSupport<long[]> implements RelationshipDao {

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#save(java.lang.Object)
	 */
	@Override
	public long save(long[] t) {
		return db.update("INSERT INTO relationship (article_id, item_id) VALUES (?,?)",
				t, new int[]{ Types.BIGINT, Types.BIGINT });
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#update(java.lang.Object)
	 */
	@Override
	public int update(long[] t) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.BaseDao#remove(java.lang.Object)
	 */
	@Override
	public int remove(long[] t) {
		return db.update("DELETE FROM relationship WHERE article_id=? AND item_id=?", t, new int[]{ Types.BIGINT, Types.BIGINT });
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.RelationshipDao#getIds(java.lang.Class, long)
	 */
	@Override
	public List<Long> getIds(final Class domain, long id) {
		String select = domain.equals(Item.class) ? "item_id" : "article_id";
		String where = domain.equals(Item.class) ? "article_id" : "item_id";
		return db.queryForList("SELECT " + select + " FROM relationship WHERE " + where + "=? AND stat>0",
				new Object[] { id }, Long.class);
	}

}
