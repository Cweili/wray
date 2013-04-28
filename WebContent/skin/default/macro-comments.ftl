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
				<th style="width:104px">${commentAuthorLabel}</th>
				<td>
					<input type="text" name="author" tabindex="1" />
				</td>
				<td style="width:30px">
					<button id="closeCommentButton" class="hide">X</button>
				</td>
			</tr>
			<tr>
				<th>${commentEmailLabel}</th>
				<td colspan="2">
					<input type="text" name="email" tabindex="2" />
				</td>
			</tr>
			<tr>
				<th>${commentLinkLabel}
				</th>
				<td colspan="2">
					<div class="commentLinkLabel">http://</div>
					<input class="commentLink" type="text" name="link" tabindex="3" />
				</td>
			</tr>
			<tr>
				<td id="comment-content" colspan="3">
					<textarea class="wysiwyg" name="content" tabindex="4" ></textarea>
				</td>
			</tr>
			<tr>
				<th id="comment-captcha">
					<img id="captcha" alt="validate" />
				</th>
				<td colspan="2" style="height:44px">
					<input type="text" name="captcha" tabindex="5" />
					<label for="captcha" class="error captcha-error">${inputErrorLabel}</label>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="right">
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
<script src="res/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="res/editor/kindeditor-min.js" type="text/javascript"></script>
<script src="res/editor/zh_CN.js" type="text/javascript"></script>
<script src="res/js/comment.js" type="text/javascript"></script>
<script type="text/javascript">
	$.extend($.validator.messages, {
		required: "${requiredErrorLabel}",
		email: "${inputErrorLabel}",
		url: "${inputErrorLabel}",
		maxlength: $.validator.format("${maxlengthErrorLabel}" + " {0}")
	});
</script>
</#macro>