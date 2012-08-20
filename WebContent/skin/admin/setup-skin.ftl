<#include "header.ftl">
<div id="main"> <!-- Main, right side content -->
	<div id="content"> <!-- Content begins here -->
		<h2>外观设置</h2>
		<#if err="succ">
			<div class="succes">
				<div class="succes_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>设置保存成功!</span>
					<p>恭喜您，您的设置已成功保存。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		<#elseif err!="">
			<div class="err">
				<div class="err_icon"><!-- --></div>
				<a href="#" class="close" title="关闭">x</a>
				<div class="desc">
					<span>${err}</span>
					<p>实在抱歉，${err}, 请联系管理员。</p>
				</div>
			</div>
			<div class="clearboth"><!-- --></div>
		</#if>
		<form id="setupForm" action="admin-${adminAction}.html" method="post">
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
					<label for="topHitsArticlesSize">热门点击条数</label>
					<input class="smallfield" name="topHitsArticlesSize" type="text" value="${topHitsArticlesSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏热门点击</span>
				</div>
				<div class="input_field">
					<label for="topCommentArticlesSize">热评文章条数</label>
					<input class="smallfield" name="topCommentArticlesSize" type="text" value="${topCommentArticlesSize?if_exists}" />
					<span class="field_desc">输入0则不显示侧边栏热评文章</span>
				</div>
				<div class="input_field">
					<label for="topCommentArticlesSize">热热门标签数</label>
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
					<label for="atomLabel">RSS/ATOM链接</label>
					<input class="bigfield" name="atomLabel" type="text" value="${atomLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="homeLabel">导航栏首页链接</label>
					<input class="bigfield" name="homeLabel" type="text" value="${homeLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="commentLabel">“评论”</label>
					<input class="bigfield" name="commentLabel" type="text" value="${commentLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="moreLabel">“更多”链接</label>
					<input class="bigfield" name="moreLabel" type="text" value="${moreLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="tagLabel">“标签”</label>
					<input class="bigfield" name="tagLabel" type="text" value="${tagLabel?if_exists}" />
				</div>
				<div class="input_field">
					<label for="viewLabel">点击数</label>
					<input class="bigfield" name="viewLabel" type="text" value="${viewLabel?if_exists}" />
				</div>
			</fieldset>
			<fieldset><legend>保存设置</legend>
				<div class="input_field no_margin_bottom">
					<span class="form_line"><input class="submit" type="submit" value="保存设置" /></span>
				</div>
			</fieldset>
		</form>
		<script type="text/javascript">
			$("#skinDir").val("${currentSkinDir}");
			$("#setupForm").validate({
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