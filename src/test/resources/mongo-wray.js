/** config indexes **/
db.getCollection("config").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** config records **/
db.getCollection("config").insert({
  "_id": "attachFooter",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "<!-- footer -->"
});
db.getCollection("config").insert({
  "_id": "attachHeader",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "<!-- header -->"
});
db.getCollection("config").insert({
  "_id": "attachStat",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "<!-- stat -->"
});
db.getCollection("config").insert({
  "_id": "blogHost",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "http:\/\/localhost:8080\/wray"
});
db.getCollection("config").insert({
  "_id": "blogSubtitle",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "This is subtitle"
});
db.getCollection("config").insert({
  "_id": "blogTitle",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "Cweili Blog"
});
db.getCollection("config").insert({
  "_id": "htmlHead",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": ""
});
db.getCollection("config").insert({
  "_id": "metaDescription",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "Cweili Blog"
});
db.getCollection("config").insert({
  "_id": "metaKeywords",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "Cweili,Blog"
});
db.getCollection("config").insert({
  "_id": "topArticleLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "置顶"
});
db.getCollection("config").insert({
  "_id": "updatedLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "有更新"
});
db.getCollection("config").insert({
  "_id": "noticeBoard",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "<span style=\"line-height:24px;background-color:#00CC00;color:#003333;font-size:18px;\"><strong>公告栏<\/strong><\/span>"
});
db.getCollection("config").insert({
  "_id": "replyLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "回复"
});
db.getCollection("config").insert({
  "_id": "mailInvalidLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "邮箱不合法"
});
db.getCollection("config").insert({
  "_id": "nextPageLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": ">"
});
db.getCollection("config").insert({
  "_id": "previousPageLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "<"
});
db.getCollection("config").insert({
  "_id": "firstPageLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "«"
});
db.getCollection("config").insert({
  "_id": "",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": ""
});
db.getCollection("config").insert({
  "_id": "feedSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "inputErrorLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "填写错误"
});
db.getCollection("config").insert({
  "_id": "pageSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "recentCommentsSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "topHitArticlesSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "topCommentArticlesSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "10"
});
db.getCollection("config").insert({
  "_id": "mostUsedTagsSize",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "30"
});
db.getCollection("config").insert({
  "_id": "commentLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "评论"
});
db.getCollection("config").insert({
  "_id": "moreLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "继续阅读"
});
db.getCollection("config").insert({
  "_id": "mostCommentArticlesLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "热评文章"
});
db.getCollection("config").insert({
  "_id": "archiveLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "文章存档"
});
db.getCollection("config").insert({
  "_id": "lastPageLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "»"
});
db.getCollection("config").insert({
  "_id": "popTagsLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "热门标签"
});
db.getCollection("config").insert({
  "_id": "recentCommentsLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "最近评论"
});
db.getCollection("config").insert({
  "_id": "mostViewCountArticlesLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "热门点击"
});
db.getCollection("config").insert({
  "_id": "sumLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "共"
});
db.getCollection("config").insert({
  "_id": "commentLinkLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "链接"
});
db.getCollection("config").insert({
  "_id": "returnLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "返回"
});
db.getCollection("config").insert({
  "_id": "maxlengthErrorLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "长度最长为"
});
db.getCollection("config").insert({
  "_id": "commentEmailLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "E-mail"
});
db.getCollection("config").insert({
  "_id": "allTagsLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "标签墙"
});
db.getCollection("config").insert({
  "_id": "tagLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "标签"
});
db.getCollection("config").insert({
  "_id": "linkLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "友情链接"
});
db.getCollection("config").insert({
  "_id": "viewLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "次点击"
});
db.getCollection("config").insert({
  "_id": "pageLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "页"
});
db.getCollection("config").insert({
  "_id": "submmitCommentLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "提交评论"
});
db.getCollection("config").insert({
  "_id": "searchLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "搜索"
});
db.getCollection("config").insert({
  "_id": "skinDir",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "default"
});
db.getCollection("config").insert({
  "_id": "commentAuthorLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "用户名"
});
db.getCollection("config").insert({
  "_id": "requiredErrorLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "必须填写"
});
db.getCollection("config").insert({
  "_id": "noticeBoardLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "公告栏"
});
db.getCollection("config").insert({
  "_id": "atomLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "RSS\/ATOM"
});
db.getCollection("config").insert({
  "_id": "homeLabel",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "首页"
});
db.getCollection("config").insert({
  "_id": "adminNick",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "Cweili"
});
db.getCollection("config").insert({
  "_id": "adminName",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "Cweili"
});
db.getCollection("config").insert({
  "_id": "adminEmail",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "3weili@gmail.com"
});
db.getCollection("config").insert({
  "_id": "navigatorSwitch",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "1"
});
db.getCollection("config").insert({
  "_id": "adminPwd",
  "_class": "org.cweili.wray.domain.dto.Config",
  "value": "a773056e3ce4443f5529cb94a6b659eec8e408138c7c380f45249a8c1974c1ed"
});
