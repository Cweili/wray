package org.cweili.wray.test;

import java.sql.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.cweili.wray.util.Function;

public class TestObjectId {

	private static final Log log = LogFactory.getLog(TestObjectId.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info(new Date(Function.timestamp() + 4000000000000L).toString());
		for (int i = 0; i < 20; i++) {
			String id = Function.generateId();
			log.info(Function.timestamp() + "//" + id + "//" + Function.decode(id));
		}
		log.info(new ObjectId("514beeed00457ae92c1171a8"));
	}

}
