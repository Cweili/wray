<#include "macro-head.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title="${errMsg} - ${blogTitle}">
        <meta name="keywords" content="${errMsg},${metaKeywords}"/>
        <meta name="description" content="${errMsg},${metaDescription}"/>
        <meta name="robots" content="noindex, follow"/>
        </@head>
    </head>
    <body>
        ${topBarReplacement?if_exists}
		<div id="wrapper" align="center">
			<#include "header.ftl">
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<!---->
					<div id="maincol">
						<div class="postwrap">
                            <h1 class="error-msg">${errMsg}</h1>
                            <a href="${staticServePath}">${returnLabel} ${blogTitle}</a>
                        </div>
						<div class="clr"></div>
					</div>
					<div class="clr"></div>
					<div class="copyr">
					&copy; ${year}&nbsp;<a href="http://${blogHost}">${blogTitle}</a>
					</div>
					<div class="clr16"></div>
					<!---->
				</div>
			</div>
			<#include "footer.ftl">
		</div>
    </body>
</html>