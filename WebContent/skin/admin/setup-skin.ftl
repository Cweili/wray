<#include "macro-form.ftl">
<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>外观设置</h2>
		<form id="editForm" action="admin-${adminAction}" method="post">
			<fieldset><legend>外观选项</legend>
				<div class="input_field">
					<label for="skinDir">博客皮肤</label>
					<select id="skinDir" name="skinDir">
						<#list skinDirs as dir>
							<option value="${dir}">${dir}</option>
						</#list>
					</select>
					<span class="field_desc">将皮肤文件夹放在Web目录的skin目录下</span>
				</div>
				<div class="input_field">
					<label for="limit">文章列表条数</label>
					<input class="smallfield" name="limit" type="text" value="${limit?if_exists}" />
					<span class="field_desc">文章列表每页显示的文章数</span>
				</div>
				<div class="input_field">
					<label for="recentCommentsSize">最近评论条数</label>
					<input class="smallfield" name="recentCommentsSize" type="text" value="${recentCommentsSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏最近评论</span>
				</div>
				<div class="input_field">
					<label for="topHitArticlesSize">热门点击条数</label>
					<input class="smallfield" name="topHitArticlesSize" type="text" value="${topHitArticlesSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏热门点击</span>
				</div>
				<div class="input_field">
					<label for="topCommentArticlesSize">热评文章条数</label>
					<input class="smallfield" name="topCommentArticlesSize" type="text" value="${topCommentArticlesSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏热评文章</span>
				</div>
				<div class="input_field">
					<label for="mostUsedTagsSize">热门标签数</label>
					<input class="smallfield" name="mostUsedTagsSize" type="text" value="${mostUsedTagsSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏热门标签</span>
				</div>
			</fieldset>
			<fieldset><legend>分页条文本</legend>
				<div class="input_field">
					<label for="firstPageLabel">第一页</label>
					<input class="bigfield" name="firstPageLabel" type="text" value="${firstPageLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="lastPageLabel">最后一页</label>
					<input class="bigfield" name="lastPageLabel" type="text" value="${lastPageLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="previousPageLabel">上一页</label>
					<input class="bigfield" name="previousPageLabel" type="text" value="${previousPageLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="nextPageLabel">下一页</label>
					<input class="bigfield" name="nextPageLabel" type="text" value="${nextPageLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="sumLabel">总共</label>
					<input class="bigfield" name="sumLabel" type="text" value="${sumLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="pageLabel">页</label>
					<input class="bigfield" name="pageLabel" type="text" value="${pageLabel?if_exists}" />
				</div>
			</fieldset>
			<fieldset><legend>侧边栏栏目文本</legend>
				<div class="input_field">
					<label for="noticeBoardLabel">公告栏</label>
					<input class="bigfield" name="noticeBoardLabel" type="text" value="${noticeBoardLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="recentCommentsLabel">最近评论</label>
					<input class="bigfield" name="recentCommentsLabel" type="text" value="${recentCommentsLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="mostCommentArticlesLabel">热评文章</label>
					<input class="bigfield" name="mostCommentArticlesLabel" type="text" value="${mostCommentArticlesLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="mostViewCountArticlesLabel">热门点击</label>
					<input class="bigfield" name="mostViewCountArticlesLabel" type="text" value="${mostViewCountArticlesLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="popTagsLabel">热门标签</label>
					<input class="bigfield" name="popTagsLabel" type="text" value="${popTagsLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="archiveLabel">文章存档</label>
					<input class="bigfield" name="archiveLabel" type="text" value="${archiveLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="linkLabel">友情链接</label>
					<input class="bigfield" name="linkLabel" type="text" value="${linkLabel?if_exists}" />
				</div>
			</fieldset>
			<fieldset><legend>界面元素文本</legend>
				<div class="input_field">
					<label for="atomLabel">RSS/ATOM</label>
					<input class="bigfield" name="atomLabel" type="text" value="${atomLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="homeLabel">导航“首页”</label>
					<input class="bigfield" name="homeLabel" type="text" value="${homeLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="allTagsLabel">导航“标签表”</label>
					<input class="bigfield" name="allTagsLabel" type="text" value="${allTagsLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="commentLabel">评论</label>
					<input class="bigfield" name="commentLabel" type="text" value="${commentLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="moreLabel">“更多”链接</label>
					<input class="bigfield" name="moreLabel" type="text" value="${moreLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="moreLabel">返回</label>
					<input class="bigfield" name="returnLabel" type="text" value="${returnLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="tagLabel">标签</label>
					<input class="bigfield" name="tagLabel" type="text" value="${tagLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="searchLabel">搜索</label>
					<input class="bigfield" name="searchLabel" type="text" value="${searchLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="viewLabel">点击数</label>
					<input class="bigfield" name="viewLabel" type="text" value="${viewLabel?if_exists}" />
				</div>
			</fieldset>
			<fieldset><legend>评论框提示文本</legend>
				<div class="input_field">
					<label for="commentAuthorLabel">评论者用户名</label>
					<input class="bigfield" name="commentAuthorLabel" type="text" value="${commentAuthorLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="commentEmailLabel">评论者E-mail</label>
					<input class="bigfield" name="commentEmailLabel" type="text" value="${commentEmailLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="commentLinkLabel">评论者链接</label>
					<input class="bigfield" name="commentLinkLabel" type="text" value="${commentLinkLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="submmitCommentLabel">提交评论</label>
					<input class="bigfield" name="submmitCommentLabel" type="text" value="${submmitCommentLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="requiredErrorLabel">必须填写提示</label>
					<input class="bigfield" name="requiredErrorLabel" type="text" value="${requiredErrorLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="inputErrorLabel">填写错误提示</label>
					<input class="bigfield" name="inputErrorLabel" type="text" value="${inputErrorLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="maxlengthErrorLabel">超长错误提示</label>
					<input class="bigfield" name="maxlengthErrorLabel" type="text" value="${maxlengthErrorLabel?if_exists}" />
				</div>
			</fieldset>
			<@message type="设置">
				恭喜您，您的设置已成功保存。
			</@message>
			<fieldset><legend>保存设置</legend>
				<div class="input_field no_margin_bottom">
					<@submit>保存设置</@submit>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#skinDir").val("${currentSkinDir}");
			$("#editForm").validate({
				rules: {
					<#list labels as lab>
					${lab}: {
						required:true,
						maxlength:64
					},
					</#list>
					limit: {
						required:true,
						digits:true,
						range:[1,100]
					},
					recentCommentsSize: {
						required:true,
						digits:true,
						range:[0,100]
					},
					topHitsArticlesSize: {
						required:true,
						digits:true,
						range:[0,100]
					},
					topCommentArticlesSize: {
						required:true,
						digits:true,
						range:[0,100]
					},
					mostUsedTagsSize: {
						required:true,
						digits:true,
						range:[0,200]
					}
				}
			});
		</script>
	</div> <!-- END Content -->
</div> 	
<#include "footer.ftl">