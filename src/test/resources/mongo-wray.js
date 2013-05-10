/** article indexes * */
db.getCollection("article").ensureIndex({
	"_id" : NumberInt(1)
}, [

]);

/** article indexes * */
db.getCollection("article").ensureIndex({
	"stat" : NumberInt(1),
	"isPage" : NumberInt(1)
}, [

]);

/** article indexes * */
db.getCollection("article").ensureIndex({
	"permalink" : NumberInt(1),
	"isPage" : NumberInt(1)
}, {
	"unique" : true
});

/** article indexes * */
db.getCollection("article").ensureIndex({
	"keyword" : NumberInt(1)
}, [

]);

/** comment indexes * */
db.getCollection("comment").ensureIndex({
	"_id" : NumberInt(1)
}, [

]);

/** comment indexes * */
db.getCollection("comment").ensureIndex({
	"articleId" : NumberInt(1)
}, [

]);

/** config indexes * */
db.getCollection("config").ensureIndex({
	"_id" : NumberInt(1)
}, [

]);

/** item indexes * */
db.getCollection("item").ensureIndex({
	"_id" : NumberInt(1)
}, [

]);

/** item indexes * */
db.getCollection("item").ensureIndex({
	"stat" : NumberInt(1)
}, [

]);

/** item indexes * */
db.getCollection("item").ensureIndex({
	"permalink" : NumberInt(1),
	"itemType" : NumberInt(1)
}, {
	"unique" : true
});

/** item indexes * */
db.getCollection("item").ensureIndex({
	"itemType" : NumberInt(1),
	"stat" : NumberInt(1)
}, [

]);

/** relationship indexes * */
db.getCollection("relationship").ensureIndex({
	"_id" : NumberInt(1)
}, [

]);

/** relationship indexes * */
db.getCollection("relationship").ensureIndex({
	"articleId" : NumberInt(1)
}, [

]);

/** relationship indexes * */
db.getCollection("relationship").ensureIndex({
	"itemId" : NumberInt(1)
}, [

]);

/** relationship indexes * */
db.getCollection("relationship").ensureIndex({
	"articleId" : NumberInt(1),
	"itemId" : NumberInt(1)
}, {
	"unique" : true
});

/** article records * */
db.getCollection("article").insert({
	"_id" : "It935H",
	"_class" : "org.cweili.wray.domain.dto.Article",
	"title" : "测试文章七",
	"permalink" : "ceshiwenzhangqi",
	"content" : "",
	"tag" : "测试标签三,测试标签一",
	"createTime" : ISODate("2013-04-27T05:56:28.458Z"),
	"stat" : NumberInt(4),
	"hit" : NumberInt(0),
	"commentCount" : NumberInt(0),
	"commentStatus" : NumberInt(1),
	"isPage" : NumberInt(0),
	"keyword" : [ "三", "标签", "文章", "一", "七", "测试" ]
});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It8mOP",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "关于我",
					"permalink" : "about",
					"content" : "<span style=\"background-color:#6600CC;font-size:32px;color:#FFCCFF;\">关于我<\/span>",
					"tag" : "关于我的关键字",
					"createTime" : ISODate("2013-04-27T04:39:08.221Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(1),
					"keyword" : [ "我", "关键字" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It8sqe",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章一",
					"permalink" : "ceshiwenzhangyi",
					"content" : "<span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一<\/span><span style=\"font-size:32px;color:#009966;\">测试文章一测试文章一<\/span>",
					"tag" : "测试标签一",
					"createTime" : ISODate("2013-04-27T05:07:20.861Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "文章", "一", "测试", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It8v2j",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章二",
					"permalink" : "ceshiwenzhanger",
					"content" : "<span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span><span style=\"font-size:24px;color:#9999FF;\">测试文章二<\/span>",
					"tag" : "测试标签一,测试标签二",
					"createTime" : ISODate("2013-04-27T05:16:58.730Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "一", "测试", "二", "文章", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It8wIY",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章三",
					"permalink" : "ceshiwenzhangsan",
					"content" : "<span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试<a name=\"more\"><\/a>文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"color:#CC3333;font-size:24px;\">测试文章三<\/span><span style=\"font-size:18px;\"><\/span>",
					"tag" : "测试标签一,测试标签二",
					"createTime" : ISODate("2013-04-27T05:22:25.662Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "文章", "三", "一", "测试", "标签", "二" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It8wWd",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章四",
					"permalink" : "ceshiwenzhangsi",
					"content" : "测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四测试文章四",
					"tag" : "测试标签三,测试标签一",
					"createTime" : ISODate("2013-04-27T05:23:23.384Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(2),
					"commentCount" : NumberInt(1),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "三", "一", "测试", "文章", "四", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It92yg",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章五",
					"permalink" : "ceshiwenzhangwu",
					"content" : "测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五测试文章五",
					"tag" : "测试标签一,测试标签二",
					"createTime" : ISODate("2013-04-27T05:55:57.337Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "一", "测试", "五", "二", "文章", "标签" ]
				});
db.getCollection("article").insert({
	"_id" : "It931V",
	"_class" : "org.cweili.wray.domain.dto.Article",
	"title" : "测试文章六",
	"permalink" : "ceshiwenzhangliu",
	"content" : "",
	"tag" : "测试标签三,测试标签一",
	"createTime" : ISODate("2013-04-27T05:56:12.934Z"),
	"stat" : NumberInt(4),
	"hit" : NumberInt(0),
	"commentCount" : NumberInt(0),
	"commentStatus" : NumberInt(1),
	"isPage" : NumberInt(0),
	"keyword" : [ "三", "标签", "文章", "一", "测试", "六" ]
});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It93Dh",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章八",
					"permalink" : "ceshiwenzhangba",
					"content" : "测试文章八测试<span style=\"color:#CC0066;font-size:16px;\">文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八<\/span><span style=\"color:#CC0066;font-size:16px;\">测试文章八测<\/span>试文章八测试文章八",
					"tag" : "测试标签一,测试标签二",
					"createTime" : ISODate("2013-04-27T05:57:02.963Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "一", "测试", "八", "二", "文章", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It93aK",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章十一",
					"permalink" : "ceshiwenzhangshiyi",
					"content" : "测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十测试文章十",
					"tag" : "测试标签三,测试标签一",
					"createTime" : ISODate("2013-04-27T05:58:39.716Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(9),
					"commentCount" : NumberInt(1),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "三", "一", "十一", "测试", "十", "文章", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "ItAI9T",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章十二",
					"permalink" : "ceshiwenzhangshier",
					"content" : "<img src=\"upload\/ItAHgg\/3770.jpg\" alt=\"\" height=\"375\" width=\"600\" \/><br \/>",
					"tag" : "",
					"createTime" : ISODate("2013-04-27T11:41:54.950Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(4),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "十二", "文章", "测试" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It93M9",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章九",
					"permalink" : "ceshiwenzhangjiu",
					"content" : "<span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测<\/span><span style=\"font-size:24px;background-color:#FF0066;\">试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九<\/span><span style=\"font-size:24px;background-color:#FF0066;\">测试文章九测试文章<\/span><span style=\"background-color:#FF0066;\">九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九<\/span><span style=\"background-color:#FF0066;\">测试文章九测试文章九<\/span>",
					"tag" : "测试标签三,测试标签一",
					"createTime" : ISODate("2013-04-27T05:57:37.579Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "九", "三", "一", "测试", "文章", "标签" ]
				});
db
		.getCollection("article")
		.insert(
				{
					"_id" : "It93Sn",
					"_class" : "org.cweili.wray.domain.dto.Article",
					"title" : "测试文章十",
					"permalink" : "ceshiwenzhangshi",
					"content" : "测试文章十测试<span style=\"color:#FF33FF;font-size:24px;\">文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span><span style=\"color:#FF33FF;font-size:24px;\">测试文章十<\/span>测试文章十测试文章十测试文章十测试文章十测试文章九测试文章九测试文章九测试文章九",
					"tag" : "测试标签三,测试标签一",
					"createTime" : ISODate("2013-04-27T05:58:04.782Z"),
					"stat" : NumberInt(4),
					"hit" : NumberInt(0),
					"commentCount" : NumberInt(0),
					"commentStatus" : NumberInt(1),
					"isPage" : NumberInt(0),
					"keyword" : [ "文章", "九", "三", "一", "测试", "十", "标签" ]
				});

/** comment records * */
db
		.getCollection("comment")
		.insert(
				{
					"_id" : "ItDBWD",
					"_class" : "org.cweili.wray.domain.dto.Comment",
					"articleId" : "It8wWd",
					"author" : "david2003mn",
					"email" : "adfa@163.com",
					"link" : "",
					"ip" : "192.168.104.134",
					"postDate" : ISODate("2013-04-28T01:11:44.797Z"),
					"agent" : "Mozilla\/5.0 (Windows NT 5.1) AppleWebKit\/537.22 (KHTML, like Gecko) Chrome\/25.0.1364.152 Safari\/537.22",
					"content" : "asdfasdfadfa",
					"parentId" : "",
					"stat" : NumberInt(2)
				});
db.getCollection("comment").insert({
	"_id" : "ItDt9E",
	"_class" : "org.cweili.wray.domain.dto.Comment",
	"articleId" : "It93aK",
	"author" : "Cweili",
	"email" : "ads@dad.cd",
	"link" : "",
	"ip" : "127.0.0.1",
	"postDate" : ISODate("2013-04-28T04:26:48.79Z"),
	"agent" : "Mozilla\/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko\/20100101 Firefox\/21.0",
	"content" : "sdsdsdsadad",
	"parentId" : "",
	"stat" : NumberInt(2)
});

/** config records * */
db.getCollection("config").insert({
	"_id" : "attachHeader",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "<!-- header -->"
});
db.getCollection("config").insert({
	"_id" : "attachStat",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "<!-- stat -->"
});
db.getCollection("config").insert({
	"_id" : "blogHost",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "http:\/\/localhost:8080\/wray"
});
db.getCollection("config").insert({
	"_id" : "blogSubtitle",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "This is subtitle"
});
db.getCollection("config").insert({
	"_id" : "blogTitle",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "Cweili Blog"
});
db.getCollection("config").insert({
	"_id" : "htmlHead",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : ""
});
db.getCollection("config").insert({
	"_id" : "metaDescription",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "Cweili Blog"
});
db.getCollection("config").insert({
	"_id" : "metaKeywords",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "Cweili,Blog"
});
db.getCollection("config").insert({
	"_id" : "topArticleLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "置顶"
});
db.getCollection("config").insert({
	"_id" : "updatedLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "有更新"
});
db.getCollection("config").insert({
	"_id" : "replyLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "回复"
});
db.getCollection("config").insert({
	"_id" : "nextPageLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : ">"
});
db.getCollection("config").insert({
	"_id" : "previousPageLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "<"
});
db.getCollection("config").insert({
	"_id" : "firstPageLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "«"
});
db.getCollection("config").insert({
	"_id" : "feedSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "10"
});
db.getCollection("config").insert({
	"_id" : "inputErrorLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "填写错误"
});
db.getCollection("config").insert({
	"_id" : "pageSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "10"
});
db.getCollection("config").insert({
	"_id" : "recentCommentsSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "10"
});
db.getCollection("config").insert({
	"_id" : "topHitArticlesSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "10"
});
db.getCollection("config").insert({
	"_id" : "topCommentArticlesSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "10"
});
db.getCollection("config").insert({
	"_id" : "mostUsedTagsSize",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "30"
});
db.getCollection("config").insert({
	"_id" : "commentLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "评论"
});
db.getCollection("config").insert({
	"_id" : "moreLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "继续阅读"
});
db.getCollection("config").insert({
	"_id" : "archiveLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "文章存档"
});
db.getCollection("config").insert({
	"_id" : "lastPageLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "»"
});
db.getCollection("config").insert({
	"_id" : "popTagsLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "热门标签"
});
db.getCollection("config").insert({
	"_id" : "recentCommentsLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "最近评论"
});
db.getCollection("config").insert({
	"_id" : "mostViewCountArticlesLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "热门点击"
});
db.getCollection("config").insert({
	"_id" : "sumLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "共"
});
db.getCollection("config").insert({
	"_id" : "commentLinkLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "链接"
});
db.getCollection("config").insert({
	"_id" : "returnLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "返回"
});
db.getCollection("config").insert({
	"_id" : "maxlengthErrorLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "长度最长为"
});
db.getCollection("config").insert({
	"_id" : "commentEmailLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "E-mail"
});
db.getCollection("config").insert({
	"_id" : "allTagsLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "标签墙"
});
db.getCollection("config").insert({
	"_id" : "tagLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "标签"
});
db.getCollection("config").insert({
	"_id" : "linkLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "友情链接"
});
db.getCollection("config").insert({
	"_id" : "viewLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "次点击"
});
db.getCollection("config").insert({
	"_id" : "pageLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "页"
});
db.getCollection("config").insert({
	"_id" : "submmitCommentLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "提交评论"
});
db.getCollection("config").insert({
	"_id" : "searchLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "搜索"
});
db.getCollection("config").insert({
	"_id" : "skinDir",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "default"
});
db.getCollection("config").insert({
	"_id" : "commentAuthorLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "用户名"
});
db.getCollection("config").insert({
	"_id" : "requiredErrorLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "必须填写"
});
db.getCollection("config").insert({
	"_id" : "noticeBoardLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "公告栏"
});
db.getCollection("config").insert({
	"_id" : "atomLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "RSS\/ATOM"
});
db.getCollection("config").insert({
	"_id" : "homeLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "首页"
});
db.getCollection("config").insert({
	"_id" : "adminNick",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "Cweili"
});
db.getCollection("config").insert({
	"_id" : "adminName",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "Cweili"
});
db.getCollection("config").insert({
	"_id" : "adminEmail",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "3weili@gmail.com"
});
db.getCollection("config").insert({
	"_id" : "navigatorSwitch",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "0"
});
db.getCollection("config").insert({
	"_id" : "attachFooter",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "<!-- footer -->"
});
db
		.getCollection("config")
		.insert(
				{
					"_id" : "noticeBoard",
					"_class" : "org.cweili.wray.domain.dto.Config",
					"value" : "<span style=\"line-height:24px;background-color:#00CC00;color:#003333;font-size:18px;\"><strong>公告栏<\/strong><\/span>"
				});
db.getCollection("config").insert({
	"_id" : "mostCommentArticlesLabel",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "热评文章"
});
db.getCollection("config").insert({
	"_id" : "adminPwd",
	"_class" : "org.cweili.wray.domain.dto.Config",
	"value" : "a773056e3ce4443f5529cb94a6b659eec8e408138c7c380f45249a8c1974c1ed"
});

/** item records * */
db.getCollection("item").insert({
	"_id" : "It8hFV",
	"_class" : "org.cweili.wray.domain.dto.Item",
	"itemName" : "日记",
	"permalink" : "diary",
	"description" : "日记的关键词",
	"count" : NumberInt(1),
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(0),
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_id" : "It8hKB",
	"_class" : "org.cweili.wray.domain.dto.Item",
	"itemName" : "测试",
	"permalink" : "test",
	"description" : "测试的关键词",
	"count" : NumberInt(15),
	"itemOrder" : NumberInt(1),
	"itemType" : NumberInt(0),
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_class" : "org.cweili.wray.domain.dto.Item",
	"_id" : "It8v2k",
	"count" : NumberInt(7),
	"description" : "",
	"itemName" : "测试标签二",
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(1),
	"permalink" : "It8v2l",
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_class" : "org.cweili.wray.domain.dto.Item",
	"_id" : "It8sqf",
	"count" : NumberInt(14),
	"description" : "",
	"itemName" : "测试标签一",
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(1),
	"permalink" : "It8sqf",
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_class" : "org.cweili.wray.domain.dto.Item",
	"_id" : "It8wWe",
	"count" : NumberInt(6),
	"description" : "",
	"itemName" : "测试标签三",
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(1),
	"permalink" : "It8wWf",
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_id" : "ItDaow",
	"_class" : "org.cweili.wray.domain.dto.Item",
	"itemName" : "测试",
	"permalink" : "ItDaow",
	"description" : "啊",
	"count" : NumberInt(0),
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(2),
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_id" : "ItEUIf",
	"_class" : "org.cweili.wray.domain.dto.Item",
	"itemName" : "自定义导航1",
	"permalink" : "ItEUIf",
	"description" : "自定义导航1",
	"count" : NumberInt(0),
	"itemOrder" : NumberInt(0),
	"itemType" : NumberInt(3),
	"stat" : NumberInt(1)
});
db.getCollection("item").insert({
	"_id" : "ItEUpC",
	"_class" : "org.cweili.wray.domain.dto.Item",
	"itemName" : "自定义导航2",
	"permalink" : "ItEUpC",
	"description" : "自定义导航2",
	"count" : NumberInt(0),
	"itemOrder" : NumberInt(1),
	"itemType" : NumberInt(3),
	"stat" : NumberInt(1)
});

/** relationship records * */
db.getCollection("relationship").insert({
	"_id" : "It8sqg",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8sqe",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8sqh",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8sqe",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8v2m",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v2j",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8v2u",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v2j",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8v2v",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v2j",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It8v52",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v51",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8v53",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v51",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8v54",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v51",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It8v9S",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v9R",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8v9T",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v9R",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8v9U",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8v9R",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It8vT7",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8vT6",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8vT8",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8vT6",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8vT9",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8vT6",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It8wIZ",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wIY",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8wI_",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wIY",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It8wIa",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wIY",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It8wWg",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wWd",
	"itemId" : "It8hFV"
});
db.getCollection("relationship").insert({
	"_id" : "It8wWh",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wWd",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It8wWi",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wWd",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It8wWj",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It8wWd",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It92yh",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It92yg",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It92yi",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It92yg",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It92yk",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It92yg",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It931W",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It931V",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It931X",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It931V",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It931Y",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It931V",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It935I",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It935H",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It935J",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It935H",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It935K",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It935H",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It93Di",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Dh",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It93Dj",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Dh",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It93Dk",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Dh",
	"itemId" : "It8v2k"
});
db.getCollection("relationship").insert({
	"_id" : "It93MA",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93M9",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It93MB",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93M9",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It93MC",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93M9",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It93So",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Sn",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It93Sp",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Sn",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It93Sq",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93Sn",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "It93aL",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93aK",
	"itemId" : "It8hKB"
});
db.getCollection("relationship").insert({
	"_id" : "It93aM",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93aK",
	"itemId" : "It8wWe"
});
db.getCollection("relationship").insert({
	"_id" : "It93aN",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "It93aK",
	"itemId" : "It8sqf"
});
db.getCollection("relationship").insert({
	"_id" : "ItAI9U",
	"_class" : "org.cweili.wray.domain.dto.Relationship",
	"articleId" : "ItAI9T",
	"itemId" : "It8hKB"
});

/** system.indexes records * */
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.upload.files",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"filename" : NumberInt(1),
		"uploadDate" : NumberInt(1)
	},
	"ns" : "wray.upload.files",
	"name" : "filename_1_uploadDate_1"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.upload.chunks",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"files_id" : NumberInt(1),
		"n" : NumberInt(1)
	},
	"unique" : true,
	"ns" : "wray.upload.chunks",
	"name" : "files_id_1_n_1"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.config",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.comment",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"articleId" : NumberInt(1)
	},
	"ns" : "wray.comment",
	"name" : "articleId",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.article",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"stat" : NumberInt(1),
		"isPage" : NumberInt(1)
	},
	"ns" : "wray.article",
	"name" : "index_stat_isPage",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"permalink" : NumberInt(1),
		"isPage" : NumberInt(1)
	},
	"unique" : true,
	"ns" : "wray.article",
	"name" : "index_permalink_isPage",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.item",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"stat" : NumberInt(1)
	},
	"ns" : "wray.item",
	"name" : "stat",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"_id" : NumberInt(1)
	},
	"ns" : "wray.relationship",
	"name" : "_id_"
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"articleId" : NumberInt(1)
	},
	"ns" : "wray.relationship",
	"name" : "articleId",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"itemId" : NumberInt(1)
	},
	"ns" : "wray.relationship",
	"name" : "itemId",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"keyword" : NumberInt(1)
	},
	"ns" : "wray.article",
	"name" : "keyword",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"permalink" : NumberInt(1),
		"itemType" : NumberInt(1)
	},
	"unique" : true,
	"ns" : "wray.item",
	"name" : "index_permalink_itemType",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"articleId" : NumberInt(1),
		"itemId" : NumberInt(1)
	},
	"unique" : true,
	"ns" : "wray.relationship",
	"name" : "index_article_item",
	"dropDups" : false,
	"sparse" : false
});
db.getCollection("system.indexes").insert({
	"v" : NumberInt(1),
	"key" : {
		"itemType" : NumberInt(1),
		"stat" : NumberInt(1)
	},
	"ns" : "wray.item",
	"name" : "index_itemType_stat",
	"dropDups" : false,
	"sparse" : false
});
