<#include "macro-head.ftl">
<#include "macro-footer.ftl">
<!DOCTYPE html>
<html>
    <head>
        <@head title="${exception?if_exists} - ${blogTitle?if_exists}">
        <meta name="keywords" content="${exception?if_exists},${metaKeywords?if_exists}"/>
        <meta name="description" content="${exception?if_exists},${metaDescription?if_exists}"/>
        <meta name="robots" content="noindex, follow"/>
        </@head>
    </head>
    <body>
		<div id="wrapper" align="center">
			<@header></@header>
			<div id="outerwrapper">
				<div id="innerwrapper">
					<div id="rightcol">
						<#include "side.ftl">
					</div>
					<!---->
					<div id="maincol">
						<div class="postwrap">
                            <h1 class="error-msg">${exception?if_exists}</h1>
                            <a href="${staticServePath?if_exists}">${returnLabel?if_exists} ${blogTitle?if_exists}</a>
                        </div>
						<div class="clr"></div>
					</div>
					<@copyright></@copyright>
				</div>
			</div>
			<@footer></@footer>
		</div>
    </body>
</html>