package org.cweili.wray.dao.mysql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.cweili.wray.dao.RelationshipDao;
import org.cweili.wray.domain.Article;
import org.cweili.wray.domain.Item;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cweili
 * @version 2012-8-16 下午10:56:49
 *
 */
@Repository("relationshipDao")
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
	 * @see org.cweili.wray.dao.RelationshipDao#getRelatedIds(java.lang.Object)
	 */
	@Override
	public List<Long> getRelatedIds(Object o) {
		long id;
		String select, where;
		if(o instanceof Article) {
			id = ((Article)o).getArticleId();
			select = "item_id";
			where = "article_id";
		} else if(o instanceof Item) {
			id = ((Item)o).getItemId();
			select = "article_id";
			where = "item_id";
		} else return null;
		return db.queryForList("SELECT " + select + " FROM relationship WHERE " + where + "=?",
				new Object[] { id }, Long.class);
	}

	@Override
	public int[] saveOrUpdate(Object o, List<Long> relatedIds) {
		long id;
		String notIN, where;
		if(o instanceof Article) {
			id = ((Article)o).getArticleId();
			notIN = "item_id";
			where = "article_id";
		} else if(o instanceof Item) {
			id = ((Item)o).getItemId();
			notIN = "article_id";
			where = "item_id";
		} else return null;
		StringBuilder sql = new StringBuilder("DELETE FROM relationship WHERE ")
				.append(notIN).append(" NOT IN (");
		List<Object[]> batchArgs = new ArrayList<Object[]>();
		for(int i = 0; i < relatedIds.size(); ++i) {
			if(i != 0) {
				sql.append(",?");
			} else {
				sql.append("?");
			}
			if(o instanceof Article) {
				batchArgs.add(new Long[]{ id, relatedIds.get(i) });
			} else {
				batchArgs.add(new Long[]{ relatedIds.get(i), id });
			}
			
		}
		sql.append(") AND ").append(where).append("=?");
		List<Long> args = new ArrayList<Long>(relatedIds);
		args.add(id);
		db.update(sql.toString(), args.toArray());
		return db.batchUpdate("REPLACE INTO relationship (article_id, item_id) VALUES (?,?)",
				batchArgs, new int[]{ Types.BIGINT, Types.BIGINT });
	}

}
