<#macro head title>
<meta charset="utf-8" />
<title>${title}</title>
<#nested>
<base href="${staticServePath}" />
<meta name="author" content="B3log Team" />
<meta name="generator" content="B3log" />
<meta name="copyright" content="B3log" />
<meta name="revised" content="B3log" />
<meta http-equiv="Window-target" content="_top" />
<!--link type="text/css" rel="stylesheet" href="/css/default-base$`{miniPostfix}.css" charset="utf-8" /-->
<link type="text/css" rel="stylesheet" href="${staticServePath}${skinDir}css/style.css" charset="utf-8" />
<link href="blog-articles-feed.do" title="ATOM" type="application/atom+xml" rel="alternate" />
<link rel="icon" type="image/png" href="favicon.png" />
${htmlHead}
</#macro>