<#macro head title>
<meta charset="utf-8" />
<title>${title}</title>
<#nested>
<base href="${staticServePath}" />
<meta name="author" content="wray" />
<meta name="generator" content="wray" />
<meta name="copyright" content="wray" />
<meta name="revised" content="wray" />
<meta http-equiv="Window-target" content="_top" />
<!--link rel="stylesheet" href="/css/default-base$`{miniPostfix}.css" type="text/css" charset="utf-8" /-->
<link rel="stylesheet" href="${staticServePath}${skinDir}css/style.css" type="text/css" charset="utf-8" />
<link rel="stylesheet" href="include/editor/plugins/code/prettify.css" type="text/css" charset="utf-8" />
<link href="blog-articles-feed.do" title="ATOM" type="application/atom+xml" rel="alternate" />
<link rel="icon" type="image/png" href="favicon.png" />
<script src="include/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="include/js/functions.js" type="text/javascript"></script>
<script src="include/editor/plugins/code/prettify.js" type="text/javascript"></script>
${htmlHead}
</#macro>