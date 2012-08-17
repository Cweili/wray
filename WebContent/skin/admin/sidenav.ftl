<div id="sidebar"> <!-- Sidebar -->
	<div class="sidebox">
		<div id="navigation"> <!-- Navigation begins here -->
			<div class="sidenav"><!-- Sidenav -->
				<div class="navhead"><span>首页</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li><a href="${staticServePath}" title="博客首页" target="_blank">博客首页</a></li>
						<li<#if adminAction="dashboard"> id="current_nav"</#if>><a href="admin-dashboard.html">管理首页</a></li>
					</ul>
				</div>
				<div class="navhead"><span>文章管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="article-add"> id="current_nav"</#if>><a href="admin-article-add.html">新增文章</a></li>
						<li<#if adminAction="article-pub"> id="current_nav"</#if>><a href="admin-article-pub.html">已发布文章</a></li>
						<li<#if adminAction="article-draft"> id="current_nav"</#if>><a href="admin-article-draft.html">文章草稿</a></li>
						<li<#if adminAction="article-recycle"> id="current_nav"</#if>><a href="admin-article-recycle.html">文章回收站</a></li>
					</ul>
				</div>
				<div class="navhead"><span>评论管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="comment-add"> id="current_nav"</#if>><a href="admin-article-add.html">评论管理</a></li>
					</ul>
				</div>
				<div class="navhead"><span>分类管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="category-add"> id="current_nav"</#if>><a href="admin-category-add.html">新增分类</a></li>
						<li<#if adminAction="category"> id="current_nav"</#if>><a href="admin-category.html">分类管理</a></li>
					</ul>
				</div>
				<div class="navhead"><span>标签管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li><a href="#">新增标签</a></li>
						<li><a href="#">标签列表</a></li>
					</ul>
				</div>
				<div class="navhead"><span>页面管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="page-add"> id="current_nav"</#if>><a href="admin-page-add.html">新增页面</a></li>
						<li<#if adminAction="page-pub"> id="current_nav"</#if>><a href="admin-page-pub.html">公开页面</a></li>
						<li<#if adminAction="page-private"> id="current_nav"</#if>><a href="admin-page-private.html">私密页面</a></li>
						<li<#if adminAction="page-recycle"> id="current_nav"</#if>><a href="admin-page-recycle.html">页面回收站</a></li>
					</ul>
				</div>
				<div class="navhead"><span>链接管理</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="link-add"> id="current_nav"</#if>><a href="admin-link-add.html">新增链接</a></li>
						<li<#if adminAction="link"> id="current_nav"</#if>><a href="admin-link.html">链接管理</a></li>
					</ul>
				</div>
				<div class="navhead"><span>博客设置</span></div>
				<div class="subnav">
					<ul class="submenu">
						<li<#if adminAction="setup-basic"> id="current_nav"</#if>><a href="admin-setup-basic.html">基本设置</a></li>
						<li<#if adminAction="setup-account"> id="current_nav"</#if>><a href="admin-setup-account.html">管理帐户</a></li>
						<li<#if adminAction="setup-skin"> id="current_nav"</#if>><a href="admin-setup-skin.html">外观设置</a></li>
						<li<#if adminAction="setup-test"> id="current_nav"</#if>><a href="admin-setup-test.html">测试设置</a></li>
					</ul>
				</div>
			</div>
		</div> <!-- END Navigation -->
	</div>
	<#--
	<div class="sidebox">
		<span class="stitle">Additional Panel</span>
		<div id="datepicker"></div>

	</div>
	<div class="sidebox">
		<span class="stitle">Photo gallery</span>
		<div class="gallery">
			<img src="images/1.jpg" alt="" width="100" height="75" />
			<img src="images/2.jpg" alt="" width="100" height="75" />
			<img src="images/3.jpg" alt="" width="100" height="75" />
			<img src="images/4.jpg" alt="" width="100" height="75" />
			<img src="images/5.jpg" alt="" width="100" height="75" />
			<img src="images/6.jpg" alt="" width="100" height="75" />
		</div>
	</div>
	-->
	<div class="sidebox">
		<span class="stitle">关于 Wary</span>
		<p>作者: <b>Cweili</b></p>
		<p><b>213 products</b> sold yesterday.</p>
		<p><b>More information:</b></p>
	</div>
</div> <!-- END Sidebar -->