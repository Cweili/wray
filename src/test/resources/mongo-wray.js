
/** config indexes **/
db.getCollection("config").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

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
  "value": "this is subtitle"
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
  "_id": "moreLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "更多"
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
  "_id": "noticeBoard",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<p>\r\n<span style=\"background-color:#CC0066;color:#66FF00;font-size:16px;\">这是公告栏<\/span> \r\n<\/p>\r\n<p align=\"center\">\r\n嘿嘿\r\n<\/p>"
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
