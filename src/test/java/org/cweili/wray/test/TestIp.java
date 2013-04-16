/**
 * 
 */
package org.cweili.wray.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.ipseeker.IPSeeker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Cweili
 * @version 2013-4-16 下午2:52:52
 * 
 */
public class TestIp {

	private static final Log log = LogFactory.getLog(TestIp.class);

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		log.info(IPSeeker.getInstance().getCountry("110.90.119.97"));
		log.info(this.getClass().getResource("/").getPath());
	}

}
