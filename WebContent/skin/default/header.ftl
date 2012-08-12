			<div id="layouttop"></div>
			<div id="header">
				<div id="headerleft">
				<h1><a href="${staticServePath}" title="${blogTitle}">${blogTitle}</a></h1><h3>${blogSubtitle}</h3>
				</div>
				<div id="headerright">
					<div id="rssboxo">
						<a href="blog-articles-feed.do">${atomLabel}</a>
					</div>
				</div>
			</div>
			<div id="navouter">
				<div id="nav">
					<ul id="header-navi">
						<li><a href="${staticServePath}">${homeLabel}</a></li>
						<#list pageNavigations as page>
						<li>
							<a href="page/${page.permalink}/">
								${page.title}
							</a>
						</li>
						</#list>
						<li>
							<a href="/tags.html">$`{allTagsLabel}</a>
						</li>
						<li class="lastNavi">
							<a href="javascript:void(0);"></a>
						</li>
						<!--
						<li class="current_page_item"><a  href="javascript:void(0);"></a></li>
						-->
					</ul>            
				</div>
			</div>