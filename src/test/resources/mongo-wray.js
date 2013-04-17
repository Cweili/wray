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
  "value": "»"
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
db.getCollection("config").insert({
  "_id": "recentCommentsSize",
  "_class": "org.cweili.wray.domain.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "nextPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": ">"
});
db.getCollection("config").insert({
  "_id": "previousPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "<"
});
db.getCollection("config").insert({
  "_id": "firstPageLabel",
  "_class": "org.cweili.wray.domain.Config",
  "value": "«"
});
