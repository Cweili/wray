package org.cweili.wray.test;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.util.Constant;
import org.cweili.wray.util.Function;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Cweili
 * @version 2013-4-11 上午9:51:27
 * 
 */
public class TestRegex {

	private static final Log log = LogFactory.getLog(TestRegex.class);

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
		String html = "<script type=\"text/javascript\">alert();</script>";
		String expected = "alert();";
		log.info(html.replaceAll("<[//]?" + "script" + "[^>]*>", ""));
		assertEquals(expected, html.replaceAll("<[//]?" + "script" + "[^>]*>", ""));
		log.info(Function.stripTags(html, Constant.DANGEROUS_TAGS));
		assertEquals(expected, Function.stripTags(html, Constant.DANGEROUS_TAGS));
	}

}
