<#macro head title>
	<meta charset="utf-8" />
	<title>${title}</title>
	<#nested>
	<base href="${staticServePath}" />
	<meta name="author" content="${adminNick}" />
	<meta name="generator" content="wray" />
	<meta name="copyright" content="${blogHost}" />
	<meta name="revised" content="wray" />
	<meta http-equiv="Window-target" content="_top" />
	<link rel="stylesheet" type="text/css" href="${staticServePath}${skinDir}css/style.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="res/editor/plugins/code/prettify.css" />
	<link rel="alternate" type="application/rss+xml" charset="utf-8" title="${blogTitle}" href="${staticServePath}feed" />
	<link rel="alternate" type="application/atom+xml" charset="utf-8" title="${blogTitle}" href="${staticServePath}atom" />
	<link rel="icon" type="image/png" href="favicon.png" />
	<script type="text/javascript" src="res/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="res/js/functions.js"></script>
	<script type="text/javascript" src="res/editor/plugins/code/prettify.js"></script>
	${attachHeader}
</#macro>

<#macro header>
	<div id="layouttop"></div>
	<div id="header">
		<div id="headerleft">
		<h1><a href="${staticServePath}" title="${blogTitle}">${blogTitle}</a></h1><h3>${blogSubtitle}</h3>
		</div>
		<div id="headerright">
			<div id="rssboxo">
				<a href="feed">${atomLabel}</a>
			</div>
		</div>
	</div>
	<div id="navouter">
		<div id="nav">
			<ul id="header-navi">
				<#if navigators?exists>
					<#list navigators as navigator>
						<li><a href="${navigator.description}">${navigator.itemName}</a></li>
					</#list>
				<#else>
					<li><a href="${staticServePath}">${homeLabel}</a></li>
					<#list categories as category>
						<li><a href="category/${category.permalink}">${category.itemName}</a></li>
					</#list>
					<#list pageNavigations as page>
						<li><a href="page/${page.permalink}">${page.title}</a></li>
					</#list>
					<#nested>
					<li><a href="tags">${allTagsLabel}</a></li>
				</#if>
				<li class="lastNavi"><a href="javascript:void(0);"></a></li>
				<!--
				<li class="current_page_item"><a  href="javascript:void(0);"></a></li>
				-->
			</ul>            
		</div>
	</div>
</#macro>