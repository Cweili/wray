<#macro comments commentList articleId permalink>
<div class="comments" id="comments">
    <#list commentList as comment>
    <div id="${comment.commentId}" class="comment-body">
        <div class="comment-panel">
            <div class="left comment-author">
                <div>
                    <img alt="${comment.author}" src="" onerror="this.src='include/image/pencil_48.png'"/>
                </div>
                <#if "http://" == comment.link>
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
<form id="commentForm" action="comment/" method="post">
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
<script src="include/js/jquery-ui-1.8.22.custom.min.js" type="text/javascript"></script>
<script src="include/editor/kindeditor-min.js" type="text/javascript"></script>
<script src="include/editor/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
	KindEditor.ready(function(K) {
		K.create(".wysiwyg", {
			themeType: "default",
			height: "200px",
			wellFormatMode: true,
			indentChar: "",
			urlType: "relative",
			emoticonsPath: "${staticServePath}include/image/emotion/",
			filterMode: true,
			allowFileManager : false,
			allowImageUpload: false,
			allowFlashUpload: false,
			allowMediaUpload: false,
			allowFileUpload: false,
			colorTable : [
				['#000000', '#CCFFFF', '#CCFFCC', '#CCFF99', '#CCFF66', '#CCFF33', '#CCFF00', '#66FF00', '#66FF33', '#66FF66', '#66FF99', '#66FFCC', '#66FFFF', '#00FFFF', '#00FFCC', '#00FF99', '#00FF66', '#00FF33', '#00FF00'],
				['#333333', '#CCCCFF', '#CCCCCC', '#CCCC99', '#CCCC66', '#CCCC33', '#CCCC00', '#66CC00', '#66CC33', '#66CC66', '#66CC99', '#66CCCC', '#66CCFF', '#00CCFF', '#00CCCC', '#00CC99', '#00CC66', '#00CC33', '#00CC00'],
				['#666666', '#CC99FF', '#CC99CC', '#CC9999', '#CC9966', '#CC9933', '#CC9900', '#669900', '#669933', '#669966', '#669999', '#6699CC', '#6699FF', '#0099FF', '#0099CC', '#009999', '#009966', '#009933', '#009900'],
				['#999999', '#CC66FF', '#CC66CC', '#CC6699', '#CC6666', '#CC6633', '#CC6600', '#666600', '#666633', '#666666', '#666699', '#6666CC', '#6666FF', '#0066FF', '#0066CC', '#006699', '#006666', '#006633', '#006600'],
				['#CCCCCC', '#CC33FF', '#CC33CC', '#CC3399', '#CC3366', '#CC3333', '#CC3300', '#663300', '#663333', '#663366', '#663399', '#6633CC', '#6633FF', '#0033FF', '#0033CC', '#003399', '#003366', '#003333', '#003300'],
				['#FFFFFF', '#CC00FF', '#CC00CC', '#CC0099', '#CC0066', '#CC0033', '#CC0000', '#660000', '#660033', '#660066', '#660099', '#6600CC', '#6600FF', '#0000FF', '#0000CC', '#000099', '#000066', '#000033', '#000000'],
				['#FF0000', '#FF00FF', '#FF00CC', '#FF0099', '#FF0066', '#FF0033', '#FF0000', '#990000', '#990033', '#990066', '#990099', '#9900CC', '#9900FF', '#3300FF', '#3300CC', '#330099', '#330066', '#330033', '#330000'],
				['#00FF00', '#FF33FF', '#FF33CC', '#FF3399', '#FF3366', '#FF3333', '#FF3300', '#993300', '#993333', '#993366', '#993399', '#9933CC', '#9933FF', '#3333FF', '#3333CC', '#333399', '#333366', '#333333', '#333300'],
				['#0000FF', '#FF66FF', '#FF66CC', '#FF6699', '#FF6666', '#FF6633', '#FF6600', '#996600', '#996633', '#996666', '#996699', '#9966CC', '#9966FF', '#3366FF', '#3366CC', '#336699', '#336666', '#336633', '#336600'],
				['#FFFF00', '#FF99FF', '#FF99CC', '#FF9999', '#FF9966', '#FF9933', '#FF9900', '#999900', '#999933', '#999966', '#999999', '#9999CC', '#9999FF', '#3399FF', '#3399CC', '#339999', '#339966', '#339933', '#339900'],
				['#00FFFF', '#FFCCFF', '#FFCCCC', '#FFCC99', '#FFCC66', '#FFCC33', '#FFCC00', '#99CC00', '#99CC33', '#99CC66', '#99CC99', '#99CCCC', '#99CCFF', '#33CCFF', '#33CCCC', '#33CC99', '#33CC66', '#33CC33', '#33CC00'],
				['#000000', '#FF00FF', '#FF00CC', '#FF0099', '#FF0066', '#FF0333', '#FF0000', '#99FF00', '#99FF33', '#99FF66', '#99FF99', '#99FFCC', '#99FFFF', '#33FFFF', '#33FFCC', '#33FF99', '#33FF66', '#33FF33', '#33FF00'],
			],
			items: [
				"formatblock", "fontsize", "forecolor", "hilitecolor", "|", "bold", "italic", "underline", "strikethrough", "superscript", "subscript", "|",
				"link", "unlink", "hr", "|", "image", "flash", "media", "table", "emoticons", "code", "baidumap", "/",
				"undo", "redo", "|", "insertunorderedlist", "insertorderedlist", "indent", "outdent", "|",
				"justifyleft", "justifycenter", "justifyright", "justifyfull", "|", "selectall", "cut", "copy", "paste", "|",
				"insertmore", "quickformat", "removeformat", "clearhtml", "source", "preview", "fullscreen"
			]
		});
	});
	$.validator.setDefaults({
		 submitHandler: function(form) {
			 $("#submitCommentButton").attr("disabled",true);
			 form.submit();
		}
	});
	$.extend($.validator.messages, {
		required: "${requiredErrorLabel}",
		email: "${emailErrorLabel}",
		url: "${urlErrorLabel}",
		maxlength: $.validator.format("${maxlengthErrorLabel}" + " {0}")
	});
	$("#commentForm").validate({
		rules: {
			author: {
				required:true,
				maxlength:200
			},
			email: {
				required:true,
				email:true,
				maxlength:200
			},
			link: {
				url:true,
				maxlength:200
			},
			content: {
				required:true
			}
		}
	});
</script>
</#macro>