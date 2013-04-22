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
<!--link rel="stylesheet" href="/css/default-base$`{miniPostfix}.css" type="text/css" charset="utf-8" /-->
<link rel="stylesheet" type="text/css" href="${staticServePath}${skinDir}css/style.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="include/editor/plugins/code/prettify.css" />
<link rel="alternate" type="application/rss+xml" charset="utf-8" title="${blogTitle}" href="${staticServePath}feed" />
<link rel="alternate" type="application/atom+xml" charset="utf-8" title="${blogTitle}" href="${staticServePath}atom" />
<link rel="icon" type="image/png" href="favicon.png" />
<script type="text/javascript" src="include/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="include/js/functions.js"></script>
<script type="text/javascript" src="include/editor/plugins/code/prettify.js"></script>
${htmlHead}
</#macro>