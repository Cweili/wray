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
<!--link type="text/css" rel="stylesheet" href="/css/default-base$`{miniPostfix}.css" charset="utf-8" /-->
<link type="text/css" rel="stylesheet" href="${staticServePath}${skinDir}css/style.css" charset="utf-8" />
<link type="text/css" rel="stylesheet" href="include/editor/plugins/code/prettify.css" charset="utf-8" />
<link href="blog-articles-feed.do" title="ATOM" type="application/atom+xml" rel="alternate" />
<link rel="icon" type="image/png" href="favicon.png" />
${htmlHead}
</#macro>