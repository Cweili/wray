package org.cweili.wray.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.util.Function;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author cweili
 * @version 2013-4-11 上午9:51:42
 * 
 */
public class TestSha256 {

	private static final Log log = LogFactory.getLog(TestSha256.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		String s = "a";
		log.info(Function.sha256(s) + "////" + Function.sha256(s).length());
		assertEquals("ca978112ca1bbdcafac231b39a23dc4da786eff8147c4e72b9807785afee48bb",
				Function.sha256(s));
		log.info(Function.md5(s));
		assertEquals("0cc175b9c0f1b6a831c399e269772661", Function.md5(s));
	}
}
