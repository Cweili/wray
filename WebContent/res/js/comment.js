var editorInit = {
	themeType : "default",
	height : "200px",
	wellFormatMode : true,
	indentChar : "",
	urlType : "relative",
	emoticonsPath : "res/image/emotion/",
	filterMode : true,
	allowFileManager : false,
	allowImageUpload : false,
	allowFlashUpload : false,
	allowMediaUpload : false,
	allowFileUpload : false,
	colorTable : [
			[ "#000000", "#CCFFFF", "#CCFFCC", "#CCFF99", "#CCFF66", "#CCFF33", "#CCFF00",
					"#66FF00", "#66FF33", "#66FF66", "#66FF99", "#66FFCC", "#66FFFF", "#00FFFF",
					"#00FFCC", "#00FF99", "#00FF66", "#00FF33", "#00FF00" ],
			[ "#333333", "#CCCCFF", "#CCCCCC", "#CCCC99", "#CCCC66", "#CCCC33", "#CCCC00",
					"#66CC00", "#66CC33", "#66CC66", "#66CC99", "#66CCCC", "#66CCFF", "#00CCFF",
					"#00CCCC", "#00CC99", "#00CC66", "#00CC33", "#00CC00" ],
			[ "#666666", "#CC99FF", "#CC99CC", "#CC9999", "#CC9966", "#CC9933", "#CC9900",
					"#669900", "#669933", "#669966", "#669999", "#6699CC", "#6699FF", "#0099FF",
					"#0099CC", "#009999", "#009966", "#009933", "#009900" ],
			[ "#999999", "#CC66FF", "#CC66CC", "#CC6699", "#CC6666", "#CC6633", "#CC6600",
					"#666600", "#666633", "#666666", "#666699", "#6666CC", "#6666FF", "#0066FF",
					"#0066CC", "#006699", "#006666", "#006633", "#006600" ],
			[ "#CCCCCC", "#CC33FF", "#CC33CC", "#CC3399", "#CC3366", "#CC3333", "#CC3300",
					"#663300", "#663333", "#663366", "#663399", "#6633CC", "#6633FF", "#0033FF",
					"#0033CC", "#003399", "#003366", "#003333", "#003300" ],
			[ "#FFFFFF", "#CC00FF", "#CC00CC", "#CC0099", "#CC0066", "#CC0033", "#CC0000",
					"#660000", "#660033", "#660066", "#660099", "#6600CC", "#6600FF", "#0000FF",
					"#0000CC", "#000099", "#000066", "#000033", "#000000" ],
			[ "#FF0000", "#FF00FF", "#FF00CC", "#FF0099", "#FF0066", "#FF0033", "#FF0000",
					"#990000", "#990033", "#990066", "#990099", "#9900CC", "#9900FF", "#3300FF",
					"#3300CC", "#330099", "#330066", "#330033", "#330000" ],
			[ "#00FF00", "#FF33FF", "#FF33CC", "#FF3399", "#FF3366", "#FF3333", "#FF3300",
					"#993300", "#993333", "#993366", "#993399", "#9933CC", "#9933FF", "#3333FF",
					"#3333CC", "#333399", "#333366", "#333333", "#333300" ],
			[ "#0000FF", "#FF66FF", "#FF66CC", "#FF6699", "#FF6666", "#FF6633", "#FF6600",
					"#996600", "#996633", "#996666", "#996699", "#9966CC", "#9966FF", "#3366FF",
					"#3366CC", "#336699", "#336666", "#336633", "#336600" ],
			[ "#FFFF00", "#FF99FF", "#FF99CC", "#FF9999", "#FF9966", "#FF9933", "#FF9900",
					"#999900", "#999933", "#999966", "#999999", "#9999CC", "#9999FF", "#3399FF",
					"#3399CC", "#339999", "#339966", "#339933", "#339900" ],
			[ "#00FFFF", "#FFCCFF", "#FFCCCC", "#FFCC99", "#FFCC66", "#FFCC33", "#FFCC00",
					"#99CC00", "#99CC33", "#99CC66", "#99CC99", "#99CCCC", "#99CCFF", "#33CCFF",
					"#33CCCC", "#33CC99", "#33CC66", "#33CC33", "#33CC00" ],
			[ "#000000", "#FF00FF", "#FF00CC", "#FF0099", "#FF0066", "#FF0333", "#FF0000",
					"#99FF00", "#99FF33", "#99FF66", "#99FF99", "#99FFCC", "#99FFFF", "#33FFFF",
					"#33FFCC", "#33FF99", "#33FF66", "#33FF33", "#33FF00" ], ],
	items : [ "formatblock", "fontsize", "forecolor", "hilitecolor", "|", "bold", "italic",
			"underline", "strikethrough", "superscript", "subscript", "|", "link", "unlink", "hr",
			"|", "image", "table", "emoticons", "code", "baidumap", "/", "undo", "redo", "|",
			"insertunorderedlist", "insertorderedlist", "indent", "outdent", "|", "justifyleft",
			"justifycenter", "justifyright", "justifyfull", "|", "selectall", "cut", "copy",
			"paste", "|", "insertmore", "quickformat", "removeformat", "clearhtml", "source",
			"preview", "fullscreen" ]
};

var commentFormValidation = {
	rules : {
		author : {
			required : true,
			maxlength : 200
		},
		email : {
			required : true,
			email : true,
			maxlength : 200
		},
		link : {
			url : true,
			maxlength : 200
		},
		content : {
			required : true
		},
		captcha : {
			required : true,
			maxlength : 6
		}
	}
};

var editor = null;

var updateCaptcha = function(id) {
	$(id).attr("src", "captcha?height=28&width=100&" + Math.random());
};

// 提交评论
var submitComment = function(id) {
	$(id).on("submit", function() {
		editor.sync();
		$(".captcha-error").hide();
		if ($(id).valid()) {
			$.post("comment", $(id).serialize(), function(data) {
				if (data == "captcha") {
					$(".captcha-error").show();
					$(id + " input[name=captcha]").focus();
					updateCaptcha("#captcha" + id.substring(12));
				} else if (data == "error") {
					alert('Error.');
				} else {
					window.location.href = data;
				}
			});
		}
		return false;
	});
};

// 回复评论
var replyTo = function(id, author) {
	// 移除其他回复框
	$("#replyForm").remove();

	// 添加新的回复框
	$("#comment-" + id).append(
			'<form id="commentForm' + id + '" action="comment" method="post">'
					+ '<table class="marginTop12 comment-form hide" id="replyForm">'
					+ $("#commentForm").children().html() + '</table></form>');

	// 添加AJAX提交
	submitComment("#commentForm" + id);

	// 重置编辑器
	$("#comment-content").empty();
	$("#comment-content").append(
			'<textarea id="textarea' + id + '" name="content" style="width:540px"></textarea>');
	editor = KindEditor.create("#textarea" + id, editorInit);

	// 重置验证码
	$("#comment-captcha").empty();
	$("#comment-captcha").append('<img id="captcha' + id + '" alt="validate" />');
	updateCaptcha("#captcha" + id);
	$("#captcha" + id).click(function() {
		updateCaptcha("#captcha" + id);
	});

	// 添加关闭按钮
	$("#commentForm" + id + " #closeCommentButton").on("click", function() {
		$("#replyForm").remove();
		$("#commentForm").slideDown(500);
		$("#commentForm input:eq(0)").focus();
	});
	$("#commentForm" + id + " #closeCommentButton").show();

	// 为父回复赋值
	$(".parentId").val(id);

	// 添加回复框验证
	$("#commentForm" + id).validate(commentFormValidation);

	// 显示回复框
	$("#replyForm").show(500);
	$("#commentForm" + id + " input:eq(0)").focus();

	// 关闭评论框
	$("#commentForm").slideUp(500);
};

// 显示评论
var showComment = function(it, id) {
	if ($("#commentRef" + id).length < 1) {
		var $refComment = $("#comment-" + id + " .comment-panel").clone();
		$refComment.removeClass().addClass("comment-body-ref").attr("id", "commentRef" + id);
		$refComment.css("padding-left", "5px");
		$refComment.find(".comment-info").width($(".comment-info:eq(0)").width());
		$("#comments").append($refComment);
	}
	var position = $(it).position();
	$("#commentRef" + id).hide();
	$("#commentRef" + id).css("top", (position.top + 18) + "px");
	$("#commentRef" + id).show(300);
};

// 隐藏评论
var hideComment = function(id) {
	$("#commentRef" + id).hide(300);
};

// $.validator.setDefaults({
// submitHandler: function(form) {
// if(null != editor) {
// editor.sync();
// $("#submitCommentButton").attr("disabled", true);
// }
// }
// });

$(function() {
	if ($("#comments div").length === 0) {
		$("#comments").removeClass("comments");
	}

	// 构造层叠评论
	$(".comment-body").each(function(i) {
		var $it = $(this);
		var parentId = $it.find(".comment-parent");
		if (parentId.length > 0) {
			var parent = $("#comment-" + parentId.text());
			parent.after($it);

			parentId.text($("#comment-" + parentId.text() + " .comment-author a").text());

			var left = parseInt(parent.find(".comment-panel").css("padding-left"));
			left = left < 121 ? left : 120; // 缩进最多 8 + 1 次，即层叠 10 级
			left += 15;
			$it.find(".comment-panel").css("padding-left", left + "px");
			$it.find(".comment-info").width($(".comment-info:eq(0)").width() - left);
		}
	});

	KindEditor.ready(function(K) {
		editor = K.create(".wysiwyg", editorInit);
	});

	$(".commentLink").focus(function() {
		$(".commentLinkLabel").hide();
		$(".commentLink").val("http://");
	});

	// $("#captcha").hide();

	$(".captcha-error").hide();

	// 更新验证码
	updateCaptcha("#captcha");

	// $("input[name=author]").one("focus", function() {
	// $("#captcha").show();
	// updateCaptcha();
	// });

	$("#captcha").click(function() {
		updateCaptcha("#captcha");
	});

	// 头像加载错误显示默认头像
	$(".comment-author-img").one("error", function() {
		$(this).attr("src", "res/image/user.png");
		return false;
	});

	// 添加评论提交
	submitComment("#commentForm");

	// 评论框添加验证
	$("#commentForm").validate(commentFormValidation);

});