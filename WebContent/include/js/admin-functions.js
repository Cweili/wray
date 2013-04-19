// JQUERY CONFIGURATION FILE FOR BLACKADMINV2 

var editor = null;
var slideToggleDelay = 6000;

$(function() {

 //NAVIGATION MENU
	$(".submenu li").each(function (i) {
		if (i < $(".submenu li").length - 1) {
			var $it = $(this),
			locationURL = window.location.pathname + window.location.search;
//			if (i === 0 && (locationURL === "/")) {
//					$it.addClass("current_nav");
//					return;
//			}
			if (locationURL.indexOf($it.find("a").attr("href")) > -1) {// && i !== 0) {
					$it.addClass("current_nav");
			}
		}
	});
	
	$("div.sidenav:eq(0)> div.subnav").hide();
	$("div.sidenav:eq(0)> div.navhead").click(function() {
	$(this).parent().find("div.subnav").slideUp("normal");
	$(this).parent().find("div.navhead").removeClass("selected");
	$(this).next().slideToggle("slow");
	$(this).toggleClass("selected");
//	location.href=$(this).next().children().children(":eq(0)").children().attr("href");
	});
	
	$(".current_nav").parent().parent().slideToggle("slow");
	$(".current_nav").parent().parent().prev().toggleClass("selected");
	
 //DATE PICKER
	$("#datepicker").datepicker();


 //CLOSE NOTIFICATIONS BUTTON
	$(".close").click(
		function () {
			$(this).parent().fadeTo(400, 0, function () { // Links with the class "close" will close parent
				$(this).slideUp(400);
			});
			return false;
		}
	);
	
	if (window.location.hash == "#succ") {
		$(".succes").slideToggle();
		setTimeout('$(".succes").slideToggle()', slideToggleDelay);
	}
	
/*
 //Initialize WYSIWYG editor

	$("#wysiwyg").wysiwyg({
		initialContent: "",
		
			controls: {
				cut: { visible: true },
					copy: { visible: true },
					paste: { visible: true },
					increaseFontSize: { visible: true },
					decreaseFontSize: { visible: true },
					html: { visible : true }
//					createLink: { visible : false },
//					unLink: { visible : false },
//					insertImage: { visible : false },
//					insertTable: { visible : false }
			}
	});
*/
 // Check all the checkboxes when the head one is selected:
		
	$(".checkall").click(
		function(){
			$(this).parent().parent().parent().parent().find('input[type="checkbox"]').attr("checked", $(this).is(":checked"));	 
		}
	);
	
//	$("input.submit").click(
//		function(){
//			$(this).attr("disabled",true);
//			$("form").submit();
//		}
//	);
	
//	$.validator.setDefaults({
//		 submitHandler: function(form) {
//			 $(".submit").attr("disabled", true);
//			 submitForm($("#editForm"));
//		}
//	});
	
	$("#editForm").on("submit", function() {
		if($("#editForm").valid()) {
			submitForm($("#editForm"));
		}
		return false;
	});
	
	if($("#main").height() < 570) {
		$("#main").css({
			height: "570px"
		});
	}
	
	reSizeMain();
	
	KindEditor.plugin('insertmore', function(K) {
		var editor = this;
		editor.clickToolbar("insertmore", function() {
			editor.insertHtml('<a name="more"></a>');
		});
	});
	
	
	
	KindEditor.ready(function(K) {
		editor = K.create(".wysiwyg", {
			themeType: "default",
			wellFormatMode: true,
			indentChar: "",
			urlType: "relative",
			emoticonsPath: "include/image/emotion/",
			uploadJson: "upload-json?a=" + (authority ? authority : 0) + "&t=" + (authorityTime ? authorityTime : 0),
			//fileManagerJson: "include/editor/jsp/file_manager_json.jsp",
			allowFileManager : false,
			allowImageUpload: true,
			allowFlashUpload: true,
			allowMediaUpload: true,
			allowFileUpload: true,
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
				"formatblock", "fontname", "fontsize", "forecolor", "hilitecolor", "|", "bold", "italic", "underline", "strikethrough", "superscript", "subscript", "|",
				"link", "unlink", "anchor", "hr", "|", "image", "multiimage", "flash", "media", "insertfile", "table", "emoticons", "code", "baidumap", "/",
				"undo", "redo", "|", "insertunorderedlist", "insertorderedlist", "indent", "outdent", "|",
				"justifyleft", "justifycenter", "justifyright", "justifyfull", "|", "selectall", "cut", "copy", "paste", "plainpaste", "wordpaste", "|",
				"insertmore", "quickformat", "removeformat", "clearhtml", "source", "preview", "fullscreen", "about"
			]
		});
	});
});

$(window).resize(function() {
	reSizeMain();
});

var submitForm = function(form) {
	if(editor != null) {
		editor.sync();
	}
	$(".submit").attr("disabled", true);
	$.post(form.attr("action"), form.serialize(), function(data) {
		if (data == "success") {
			$(".err").hide();
			$(".succes").slideToggle();
			setTimeout('$(".succes").slideToggle()', slideToggleDelay);
		} else if(data != "") {
			window.location.href = data + "#succ";
		} else {
			$(".err").slideToggle();
			setTimeout('$(".err").slideToggle()', slideToggleDelay);
		}
		$(".submit").attr("disabled", false);
	});
};

var deleteSingle = function(id) {
	$("#deleteId").val(id);
	$("#deleteForm").submit();
};

var reSizeMain = function() {
	var containerwidth = $(window).width();
	containerwidth = containerwidth > 996 ? containerwidth : 996;
	var mainwidth = containerwidth - 285;
	
	$("#container").css({
		width: containerwidth + "px"
	});
	$("#main").css({
		width: mainwidth + "px",
	});
};

$.extend($.validator.messages, {
	required: "必须填写",
	remote: "请修正此处",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入数字",
	digits: "请输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "两次输入的密码不同",
	accept: "扩展名不合法",
	maxlength: $.validator.format("长度最长为 {0}"),
	minlength: $.validator.format("长度最短为 {0}"),
	rangelength: $.validator.format("长度在 {0} 和 {1} 之间"),
	range: $.validator.format("数值范围 {0} 到 {1}"),
	max: $.validator.format("数值最大为 {0}"),
	min: $.validator.format("数值最小为 {0}")
});

/*$(function(){ prettyPrint(); });*/
