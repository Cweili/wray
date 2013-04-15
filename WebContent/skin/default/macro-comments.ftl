<#macro comments commentList articleId permalink>
<div class="comments" id="comments">
    <#list commentList as comment>
    <div id="comment-${comment.commentId}" class="comment-body">
        <div class="comment-panel">
            <div class="left comment-author">
                <div>
                    <img alt="${comment.author}" src="" onerror="this.src='include/image/pencil_48.png'"/>
                </div>
                <#if "http:/" == comment.link>
                <a>${comment.author}</a>
                <#else>
                <a href="${comment.link}" target="_blank" rel="nofollow">${comment.author}</a>
                </#if>
            </div>
            <div class="left comment-info">
                <div class="left">
                    ${comment.postDate?string("yyyy-MM-dd HH:mm:ss")}
                    <#if (comment.parrentId > 0)>
                    @
                    <a href="${permalink}#${comment.parrentId}"
                       onmouseover="showComment(this, '${comment.parrentId}');"
                       onmouseout="hideComment('${comment.parrentId}')">${comment.parrentId}</a>
                    </#if>
                </div>
                <div class="right">
                    <a class="no-underline" href="javascript:replyTo('${comment.commentId}');">${replyLabel}</a>
                </div>
                <div class="clear"></div>
                <div class="comment-content">
                    ${comment.content}
                </div>
            </div>
            <div class="clear"></div>
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
	            <td colspan="2" width="550px">
	                <input type="text" class="normalInput" name="author"/>
	            </td>
	        </tr>
	        <tr>
	            <th>
	                ${commentEmailLabel}
	            </th>
	            <td colspan="2">
	                <input type="text" class="normalInput" name="email"/>
	            </td>
	        </tr>
	        <tr>
	            <th>
	                ${commentLinkLabel}
	            </th>
	            <td colspan="2">
	                <div id="commentLinkLabel">
	                    http://
	                </div>
	                <input type="text" id="commentLink" name="link"/>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="3">
	                <textarea class="wysiwyg" name="content"></textarea>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                <input type="text" class="normalInput" name="captcha"/>
	            </td>
	            <td>
	                <img id="captcha" alt="validate" src="/captcha.do"></img>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="3" align="right">
	                <button id="submitCommentButton">${submmitCommentLabel}</button>
	            </td>
	        </tr>
	        <input type="hidden" name="articleId" value="${articleId}">
	        <input type="hidden" name="permalink" value="${permalink}">
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