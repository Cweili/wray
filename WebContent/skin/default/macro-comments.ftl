<#macro comments commentList articleId permalink>
<div class="comments" id="comments">
	<#list commentList as comment>
	<div id="comment-${comment.commentId}" class="comment-body">
		<div class="comment-panel">
			<div class="comment-author">
				<div>
					<img class="comment-author-img" alt="${comment.author}"
						src="http://www.gravatar.com/avatar.php?gravatar_id=${comment.email}&amp;size=32"
					/>
				</div>
				<#if "http:/" == comment.link>
				<a>${comment.author}</a>
				<#else>
				<a href="${comment.link}" target="_blank" rel="nofollow">${comment.author}</a>
				</#if>
			</div>
			<div class="comment-info">
				<div class="right">
					<#if comment.parentId?has_content>
						<button onclick="window.location.href='article/${permalink}#comment-${comment.parentId}'"
							onmouseover="showComment(this, '${comment.parentId}');"
							onmouseout="hideComment('${comment.parentId}')">
							@<span class="comment-parent">${comment.parentId}</span>
						</button>
					</#if>
					<button onclick="replyTo('${comment.commentId}');">${replyLabel}</button>
				</div>
				<div class="">
					${comment.postDate?string("yyyy-MM-dd HH:mm:ss")}
				</div>
				<div class="clr"></div>
				<div class="comment-content">
					${comment.content}
				</div>
			</div>
			<div class="clr"></div>
		</div>
	</div>
	</#list>
</div>
<form id="commentForm" action="comment" method="post">
	<table class="comment-form">
		<tbody>
			<tr>
				<th width="58px">
					${commentAuthorLabel}
				</th>
				<td colspan="2" width="450px">
					<input type="text" class="normalInput" name="author"/>
				</td>
				<td width="30px">
					<button id="closeCommentButton" class="hide">X</button>
				</td>
			</tr>
			<tr>
				<th>
					${commentEmailLabel}
				</th>
				<td colspan="3">
					<input type="text" class="normalInput" name="email"/>
				</td>
			</tr>
			<tr>
				<th>
					${commentLinkLabel}
				</th>
				<td colspan="3">
					<div id="commentLinkLabel">
						http://
					</div>
					<input type="text" id="commentLink" name="link"/>
				</td>
			</tr>
			<tr>
				<td id="comment-content" colspan="4">
					<textarea class="wysiwyg" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" width="205px">
					<input type="text" class="normalInput" name="captcha"/>
				</td>
				<td colspan="2" width="335px">
					<img id="captcha" alt="validate" src="/captcha.do"></img>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="right">
					<button id="submitCommentButton">${submmitCommentLabel}</button>
				</td>
			</tr>
			<input class="parentId" type="hidden" name="parentId" value="" />
			<input type="hidden" name="articleId" value="${articleId}" />
			<input type="hidden" name="permalink" value="${permalink}" />
		</tbody>
	</table>
</form>
</#macro>

<#macro comment_script>
<script src="include/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="include/editor/kindeditor-min.js" type="text/javascript"></script>
<script src="include/editor/zh_CN.js" type="text/javascript"></script>
<script src="include/js/comment.js" type="text/javascript"></script>
<script type="text/javascript">
	$.extend($.validator.messages, {
		required: "${requiredErrorLabel}",
		email: "${emailErrorLabel}",
		url: "${urlErrorLabel}",
		maxlength: $.validator.format("${maxlengthErrorLabel}" + " {0}")
	});
</script>
</#macro>