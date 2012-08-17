package org.cweili.wray.dao.mysql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;

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
	public List<Long> getIds(final Class<?> domain, long id) {
		String select = domain.equals(Item.class) ? "item_id" : "article_id";
		String where = domain.equals(Item.class) ? "article_id" : "item_id";
		return db.queryForList("SELECT " + select + " FROM relationship WHERE " + where + "=? AND stat>0",
				new Object[] { id }, Long.class);
	}

	/* (non-Javadoc)
	 * @see org.cweili.wray.dao.RelationshipDao#saveOrUpdate(java.lang.Class, long, java.util.List)
	 */
	@Override
	public int[] saveOrUpdate(Class<?> domain, long id, List<Long> relatedIds) {
		String where = domain.equals(Article.class) ? "article_id" : "item_id";
		String notIN = domain.equals(Article.class) ? "item_id" : "article_id";
		StringBuilder sql = new StringBuilder("DELETE FROM relationship WHERE ")
				.append(notIN).append(" NOT IN (");
		List<Integer> types = new ArrayList<Integer>();
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for(int i = 0; i < relatedIds.size(); ++i) {
			if(i != 0) {
				sql.append(",?");
			} else {
				sql.append("?");
			}
			types.add(Types.BIGINT);
			if(domain.equals(Article.class)) {
				batchArgs.add(new Long[]{ id, relatedIds.get(i) });
			} else {
				batchArgs.add(new Long[]{ relatedIds.get(i), id });
			}
			
		}
		sql.append(") AND ").append(where).append("=?");
		types.add(Types.BIGINT);
		List<Long> args = new ArrayList<Long>(relatedIds);
		args.add(id);
		db.update(sql.toString(), args.toArray(), types.toArray());
		return db.batchUpdate("REPLACE INTO relationship (article_id, item_id) VALUES (?,?)",
				batchArgs, new int[]{ Types.BIGINT, Types.BIGINT });
	}

}
