package org.cweili.wray.test;

import static org.junit.Assert.assertEquals;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cweili.wray.util.ChineseSegment;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSeg {

	private static final Log log = LogFactory.getLog(TestSeg.class);

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
		String str = "歧义和同义词:研究生命起源，混合词: 做B超检查身体，本质是X射线，单位和全角: 2009年８月６日开始大学之旅，中文数字: 四分之三的人都交了六十五块钱班费，那是一九九八年前的事了，四川麻辣烫很好吃，五四运动留下的五四精神。笔记本五折包邮亏本大甩卖。人名识别: 我是陈鑫，也是jcesg的作者，三国时期的诸葛亮是个天才，我们一起给刘翔加油，罗志高兴奋极了因为老吴送了他一台笔记本。配对标点: 本次『畅想杯』黑客技术大赛的得主为电信09-2BF的张三，奖励C++程序设计语言一书和【畅想网络】的『PHP教程』一套。特殊字母: 【Ⅰ】（Ⅱ），英文数字: bug report chenxin619315@gmail.com or visit http://code.google.com/p/jcseg, 15% of the day's time i will be there.特殊数字: ① ⑩ ⑽ ㈩.";
		ToAnalysis.paser("");
		long _start = System.nanoTime();

		log.info(ToAnalysis.paser(str));

		log.info(ChineseSegment.segmentToSet(str));

		log.info(System.nanoTime() - _start);
		assertEquals(1, 1);
	}

}
