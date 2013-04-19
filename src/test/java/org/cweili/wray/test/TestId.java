package org.cweili.wray.test;

import java.sql.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.cweili.wray.util.Function;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author cweili
 * @version 2013-4-11 上午9:51:49
 * 
 */
public class TestId {

	private static final Log log = LogFactory.getLog(TestId.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		log.info(new Date(Function.timestamp() + 4000000000000L).toString());
	}

	@After
	public void tearDown() throws Exception {
		log.info(new ObjectId("514beeed00457ae92c1171a8"));
	}

	@Test
	public void test() {
		for (int i = 0; i < 20; ++i) {
			String id = Function.generateId();
			log.info(Function.timestamp() + "//" + id + "//" + (Function.decode(id) << 6));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info(Function.encode(-9223372036854775808L));
	}

}
