
/** article indexes **/
db.getCollection("article").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** article indexes **/
db.getCollection("article").ensureIndex({
  "stat": NumberInt(1),
  "isPage": NumberInt(1)
},[
  
]);

/** article indexes **/
db.getCollection("article").ensureIndex({
  "permalink": NumberInt(1),
  "isPage": NumberInt(1)
},{
  "unique": true
});

/** articlecontent indexes **/
db.getCollection("articlecontent").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** comment indexes **/
db.getCollection("comment").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** comment indexes **/
db.getCollection("comment").ensureIndex({
  "articleId": NumberInt(1)
},[
  
]);

/** config indexes **/
db.getCollection("config").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** item indexes **/
db.getCollection("item").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** item indexes **/
db.getCollection("item").ensureIndex({
  "stat": NumberInt(1)
},[
  
]);

/** relationship indexes **/
db.getCollection("relationship").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** relationship indexes **/
db.getCollection("relationship").ensureIndex({
  "articleId": NumberInt(1)
},[
  
]);

/** relationship indexes **/
db.getCollection("relationship").ensureIndex({
  "itemId": NumberInt(1)
},[
  
]);

/** article records **/
db.getCollection("article").insert({
  "_id": "1UOkYZ",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章一",
  "permalink": "ceshiwenzhangyi",
  "tag": "",
  "createTime": ISODate("2013-04-07T08:02:31.838Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(8),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "1UOkbj",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章二",
  "permalink": "ceshiwenzhanger",
  "tag": "测试标签一,测试标签二",
  "createTime": ISODate("2013-04-07T08:05:47.725Z"),
  "stat": NumberInt(2),
  "hits": NumberInt(3),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Etzapq",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章三",
  "permalink": "ceshiwenzhangsan",
  "tag": "测试标签二,测试标签3",
  "createTime": ISODate("2013-04-07T08:36:13.496Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(22),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "EtzkpB",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章四",
  "permalink": "ceshiwenzhangsi",
  "tag": "",
  "createTime": ISODate("2013-04-07T09:40:13.397Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(2),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Etzlic",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章五",
  "permalink": "ceshiwenzhangwu",
  "tag": "",
  "createTime": ISODate("2013-04-07T09:45:57.14Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(3),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Eu3IlF",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章六",
  "permalink": "ceshiwenzhangliu",
  "tag": "",
  "createTime": ISODate("2013-04-08T09:09:16.998Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(58),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Eu3fQA",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章七",
  "permalink": "ceshiwenzhangqi",
  "tag": "",
  "createTime": ISODate("2013-04-08T11:34:27.481Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(2),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Eu62nu",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章八",
  "permalink": "ceshiwenzhangba",
  "tag": "",
  "createTime": ISODate("2013-04-09T03:18:41.511Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(20),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjyhi3",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章九",
  "permalink": "ceshiwenzhangjiu",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:20:19.550Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjyhxC",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十",
  "permalink": "ceshiwenzhangshi",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:21:56.410Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjyh_H",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十一",
  "permalink": "ceshiwenzhangshiyi",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:22:22.579Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjyidX",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十二",
  "permalink": "ceshiwenzhangshier",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:26:40.141Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjyimT",
  "_class": "org.cweili.wray.domain.Article",
  "title": "2013-04-10 15:27:37",
  "permalink": "2013-04-10-15-27-37",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:27:37.314Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjyjUv",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十三",
  "permalink": "ceshiwenzhangshisan",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:32:34.542Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjyj-0",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十四",
  "permalink": "ceshiwenzhangshisi",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:35:53.683Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjykgJ",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十六",
  "permalink": "ceshiwenzhangshiliu",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:40:37.134Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjykyc",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十七",
  "permalink": "ceshiwenzhangshiqi",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:42:34.224Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjylA0",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十八",
  "permalink": "ceshiwenzhangshiba",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:44:00.27Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(1),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "CjylGS",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章十九",
  "permalink": "ceshiwenzhangshijiu",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:44:41.210Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(0),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjylsl",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章二十",
  "permalink": "ceshiwenzhangershi",
  "tag": "",
  "createTime": ISODate("2013-04-10T07:48:46.376Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(7),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});
db.getCollection("article").insert({
  "_id": "Cjyzfs",
  "_class": "org.cweili.wray.domain.Article",
  "title": "测试文章二十一",
  "permalink": "ceshiwenzhangershiyi",
  "tag": "",
  "createTime": ISODate("2013-04-10T09:22:58.297Z"),
  "stat": NumberInt(4),
  "hits": NumberInt(1),
  "commentCount": NumberInt(0),
  "commentStatus": NumberInt(1),
  "isPage": NumberInt(0)
});

/** articlecontent records **/
db.getCollection("articlecontent").insert({
  "_id": "1UOkYZ",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章一"
});
db.getCollection("articlecontent").insert({
  "_id": "1UOkbj",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试<a name=\"more\"><\/a>内容二"
});
db.getCollection("articlecontent").insert({
  "_id": "Etzapq",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试内容三 测试内容三"
});
db.getCollection("articlecontent").insert({
  "_id": "EtzkpB",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<span style=\"background-color:#003300;color:#CC00FF;font-size:32px;\">测试内<a name=\"more\"><\/a>容四<\/span>"
});
db.getCollection("articlecontent").insert({
  "_id": "Eu3IlF",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<span style=\"font-size:24px;color:#FF3366;\">测试文<a name=\"more\"><\/a>章六 a<\/span>"
});
db.getCollection("articlecontent").insert({
  "_id": "Eu3fQA",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<p>\r\n文章七文章<a name=\"more\"><\/a>七\r\n<\/p>\r\n<p>\r\n<img src=\"..\/..\/upload\/Eu3gcT\/cweili.png\" alt=\"\" \/>\r\n<\/p>"
});
db.getCollection("articlecontent").insert({
  "_id": "Eu62nu",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<p>\r\n<img src=\"upload\/Eu6eVZ\/cweili.png\" alt=\"\" \/>\r\n<\/p>"
});
db.getCollection("articlecontent").insert({
  "_id": "Cjyhi3",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章九"
});
db.getCollection("articlecontent").insert({
  "_id": "CjyhxC",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章十"
});
db.getCollection("articlecontent").insert({
  "_id": "Cjyh_H",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章十一"
});
db.getCollection("articlecontent").insert({
  "_id": "CjyidX",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "CjyimT",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "CjyjUv",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章十三"
});
db.getCollection("articlecontent").insert({
  "_id": "Cjyj-0",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "CjykgJ",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "Cjykyc",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "CjylGS",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": ""
});
db.getCollection("articlecontent").insert({
  "_id": "Cjylsl",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<p>\r\n二十二四阿德大啊\r\n<\/p>"
});
db.getCollection("articlecontent").insert({
  "_id": "Cjyzfs",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "文章二十一二十一二十二"
});
db.getCollection("articlecontent").insert({
  "_id": "CjylA0",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "测试文章十八"
});
db.getCollection("articlecontent").insert({
  "_id": "Etzlic",
  "_class": "org.cweili.wray.domain.ArticleContent",
  "content": "<span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><span style=\"background-color:#006600;color:#CC99FF;font-size:24px;font-family:SimHei;\">很长很长很长<\/span><span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span><\/span>"
});

/** comment records **/
db.getCollection("comment").insert({
  "_id": "Eu03s6",
  "_class": "org.cweili.wray.domain.Comment",
  "articleId": "1UOkYZ",
  "author": "a",
  "email": "ad@s.c",
  "link": "http:\/\/sdad.com",
  "ip": "0:0:0:0:0:0:0:1",
  "postDate": ISODate("2013-04-07T11:42:15.34Z"),
  "agent": "Mozilla\/5.0 (X11; Linux x86_64) AppleWebKit\/537.13 (KHTML, like Gecko) Ubuntu\/12.10 Chromium\/27.0.1425.0 Chrome\/27.0.1425.0 Safari\/537.13",
  "content": "alert(1);",
  "parrentId": NumberLong(0),
  "stat": NumberInt(2)
});
db.getCollection("comment").insert({
  "_id": "Eu3J4C",
  "_class": "org.cweili.wray.domain.Comment",
  "articleId": "Eu3IlF",
  "author": "sadsad",
  "email": "swa@dad.cn",
  "link": "",
  "ip": "127.0.0.1",
  "postDate": ISODate("2013-04-08T09:11:14.423Z"),
  "agent": "Mozilla\/5.0 (X11; Linux x86_64) AppleWebKit\/537.13 (KHTML, like Gecko) Ubuntu\/12.10 Chromium\/27.0.1425.0 Chrome\/27.0.1425.0 Safari\/537.13",
  "content": "大师的",
  "parrentId": NumberLong(0),
  "stat": NumberInt(2)
});

/** config records **/
db.getCollection("config").insert({
  "_id": "adminEmail",
  "_class": "org.cweili.wray.domain.Config",
  "value": "3weili@gmail.com"
});
db.getCollection("config").insert({
  "_id": "adminName",
  "_class": "org.cweili.wray.domain.Config",
  "value": "Cweili"
});
db.getCollection("config").insert({
  "_id": "adminNick",
  "_class": "org.cweili.wray.domain.Config",
  "value": "Cweili"
});
db.getCollection("config").insert({
  "_id": "adminPwd",
  "_class": "org.cweili.wray.domain.Config",
  "value": "1"
});
db.getCollection("config").insert({
  "_id": "archiveLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "文章存档"
});
db.getCollection("config").insert({
  "_id": "atomLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "RSS\/ATOM"
});
db.getCollection("config").insert({
  "_id": "attachFooter",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<!-- footer -->"
});
db.getCollection("config").insert({
  "_id": "attachHeader",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<!-- header -->"
});
db.getCollection("config").insert({
  "_id": "attachStat",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<!-- stat -->"
});
db.getCollection("config").insert({
  "_id": "blogHost",
  "_class": "org.cweili.wray.domain.Config",
  "value": "http:\/\/localhost:8080\/wray"
});
db.getCollection("config").insert({
  "_id": "blogSubtitle",
  "_class": "org.cweili.wray.domain.Config",
  "value": "This is subtitle"
});
db.getCollection("config").insert({
  "_id": "blogTitle",
  "_class": "org.cweili.wray.domain.Config",
  "value": "Cweili Blog"
});
db.getCollection("config").insert({
  "_id": "commentLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "评论"
});
db.getCollection("config").insert({
  "_id": "firstPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "&laquo;"
});
db.getCollection("config").insert({
  "_id": "homeLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "首页"
});
db.getCollection("config").insert({
  "_id": "htmlHead",
  "_class": "org.cweili.wray.domain.Config",
  "value": ""
});
db.getCollection("config").insert({
  "_id": "lastPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "&raquo;"
});
db.getCollection("config").insert({
  "_id": "limit",
  "_class": "org.cweili.wray.domain.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "linkLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "友情链接"
});
db.getCollection("config").insert({
  "_id": "metaDescription",
  "_class": "org.cweili.wray.domain.Config",
  "value": "Cweili Blog"
});
db.getCollection("config").insert({
  "_id": "metaKeywords",
  "_class": "org.cweili.wray.domain.Config",
  "value": "Cweili,Blog"
});
db.getCollection("config").insert({
  "_id": "mostCommentArticlesLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "热评文章"
});
db.getCollection("config").insert({
  "_id": "mostUsedTagsSize",
  "_class": "org.cweili.wray.domain.Config",
  "value": "20"
});
db.getCollection("config").insert({
  "_id": "mostViewCountArticlesLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "热门点击"
});
db.getCollection("config").insert({
  "_id": "nextPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "&gt;"
});
db.getCollection("config").insert({
  "_id": "noticeBoardLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "公告栏"
});
db.getCollection("config").insert({
  "_id": "pageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "页"
});
db.getCollection("config").insert({
  "_id": "popTagsLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "热门标签"
});
db.getCollection("config").insert({
  "_id": "previousPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "&lt;"
});
db.getCollection("config").insert({
  "_id": "recentCommentsLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "最近评论"
});
db.getCollection("config").insert({
  "_id": "returnLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "返回"
});
db.getCollection("config").insert({
  "_id": "skinDir",
  "_class": "org.cweili.wray.domain.Config",
  "value": "default"
});
db.getCollection("config").insert({
  "_id": "sumLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "共"
});
db.getCollection("config").insert({
  "_id": "tagLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "标签"
});
db.getCollection("config").insert({
  "_id": "topArticleLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "置顶"
});
db.getCollection("config").insert({
  "_id": "topCommentArticlesSize",
  "_class": "org.cweili.wray.domain.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "topHitsArticlesSize",
  "_class": "org.cweili.wray.domain.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "updatedLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "有更新"
});
db.getCollection("config").insert({
  "_id": "viewLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "次点击"
});
db.getCollection("config").insert({
  "_id": "noticeBoard",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<span style=\"line-height:24px;background-color:#00CC00;color:#003333;font-size:18px;\"><strong>公告栏<\/strong><\/span>"
});
db.getCollection("config").insert({
  "_id": "replyLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "回复"
});
db.getCollection("config").insert({
  "_id": "mailInvalidLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "邮箱不合法"
});
db.getCollection("config").insert({
  "_id": "commentAuthorLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "用户名"
});
db.getCollection("config").insert({
  "_id": "commentEmailLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "E-mail"
});
db.getCollection("config").insert({
  "_id": "commentLinkLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "链接"
});
db.getCollection("config").insert({
  "_id": "submmitCommentLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "提交评论"
});
db.getCollection("config").insert({
  "_id": "urlErrorLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "链接填写错误"
});
db.getCollection("config").insert({
  "_id": "maxlengthErrorLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "长度最长为"
});
db.getCollection("config").insert({
  "_id": "emailErrorLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "E-mail填写错误"
});
db.getCollection("config").insert({
  "_id": "requiredErrorLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "必须填写"
});
db.getCollection("config").insert({
  "_id": "moreLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "继续阅读"
});

/** item records **/
db.getCollection("item").insert({
  "_id": "1UOkZS",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "日记1",
  "permalink": "diary",
  "description": "",
  "count": NumberInt(1),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(0),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "1UOkZb",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "编程",
  "permalink": "coding",
  "description": "",
  "count": NumberInt(1),
  "itemOrder": NumberInt(1),
  "itemType": NumberInt(0),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "1UOkaI",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "测试",
  "permalink": "test",
  "description": "",
  "count": NumberInt(7),
  "itemOrder": NumberInt(2),
  "itemType": NumberInt(0),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "1UOkbk",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "测试标签一",
  "permalink": "",
  "description": "",
  "count": NumberInt(1),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(1),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "1UOkbl",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "测试标签二",
  "permalink": "",
  "description": "",
  "count": NumberInt(2),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(1),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "EtzYph",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "Cweili Alpha",
  "permalink": "",
  "description": "http:\/\/cweili.sinaapp.com",
  "count": NumberInt(0),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(2),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "Etzapr",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "测试标签3",
  "permalink": "",
  "description": "",
  "count": NumberInt(1),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(1),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "Cjyrdj",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "日记2",
  "permalink": "riji2",
  "description": "",
  "count": NumberInt(0),
  "itemOrder": NumberInt(0),
  "itemType": NumberInt(0),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});
db.getCollection("item").insert({
  "_id": "Cjys1N",
  "_class": "org.cweili.wray.domain.Item",
  "itemName": "测试2",
  "permalink": "ceshi2",
  "description": "",
  "count": NumberInt(1),
  "itemOrder": NumberInt(12),
  "itemType": NumberInt(0),
  "parrentId": NumberLong(0),
  "stat": NumberInt(1)
});

/** relationship records **/
db.getCollection("relationship").insert({
  "_id": "1UOkbm",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "1UOkbj",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "1UOkbn",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "1UOkbj",
  "itemId": "1UOkbk"
});
db.getCollection("relationship").insert({
  "_id": "1UOkbo",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "1UOkbj",
  "itemId": "1UOkbl"
});
db.getCollection("relationship").insert({
  "_id": "Etzaps",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Etzapq",
  "itemId": "1UOkZS"
});
db.getCollection("relationship").insert({
  "_id": "Etzapt",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Etzapq",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Etzapu",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Etzapq",
  "itemId": "1UOkbl"
});
db.getCollection("relationship").insert({
  "_id": "Etzapv",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Etzapq",
  "itemId": "Etzapr"
});
db.getCollection("relationship").insert({
  "_id": "EtzkpC",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "EtzkpB",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Etzlid",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Etzlic",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Eu3IlG",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Eu3IlF",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Eu62nw",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Eu62nu",
  "itemId": "1UOkZb"
});
db.getCollection("relationship").insert({
  "_id": "Eu62nx",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Eu62nu",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Cjyhi4",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Cjyhi3",
  "itemId": "1UOkaI"
});
db.getCollection("relationship").insert({
  "_id": "Cjyzft",
  "_class": "org.cweili.wray.domain.Relationship",
  "articleId": "Cjyzfs",
  "itemId": "Cjys1N"
});

/** system.indexes records **/
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.upload.files",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "filename": NumberInt(1),
    "uploadDate": NumberInt(1)
  },
  "ns": "wray.upload.files",
  "name": "filename_1_uploadDate_1"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.upload.chunks",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "files_id": NumberInt(1),
    "n": NumberInt(1)
  },
  "unique": true,
  "ns": "wray.upload.chunks",
  "name": "files_id_1_n_1"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.config",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.comment",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "articleId": NumberInt(1)
  },
  "ns": "wray.comment",
  "name": "articleId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.article",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "stat": NumberInt(1),
    "isPage": NumberInt(1)
  },
  "ns": "wray.article",
  "name": "index_stat_isPage",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "permalink": NumberInt(1),
    "isPage": NumberInt(1)
  },
  "unique": true,
  "ns": "wray.article",
  "name": "index_permalink_isPage",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.item",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "stat": NumberInt(1)
  },
  "ns": "wray.item",
  "name": "stat",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.relationship",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "articleId": NumberInt(1)
  },
  "ns": "wray.relationship",
  "name": "articleId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "itemId": NumberInt(1)
  },
  "ns": "wray.relationship",
  "name": "itemId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "wray.articlecontent",
  "name": "_id_"
});
