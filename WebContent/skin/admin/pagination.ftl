<#if paginationOn>
<!-- The pagination -->
<ul class="pagination">
	<#if 1 != paginationCurrentPageNum>
		<li><a href="admin-${adminAction}.html">${firstPageLabel}</a></li>
		<li><a href="admin-${adminAction}.html?page=${paginationPreviousPageNum?c}">${previousPageLabel}</a></li>
	<#else>
		<li><a>${firstPageLabel}</a></li>
		<li><a>${previousPageLabel}</a></li>
	</#if>
	<#list paginationPageNums as paginationPageNum>
		<#if paginationPageNum == paginationCurrentPageNum>
			<li class="current"><a>${paginationCurrentPageNum}</a></li>
		<#else>
			<li><a href="admin-${adminAction}.html?page=${paginationPageNum?c}">${paginationPageNum?c}</a></li>
		</#if>
	</#list>
	<#if paginationPageNums?last != paginationPageCount>
		<li><a href="admin-${adminAction}.html?page=${paginationNextPageNum?c}">${nextPagePabel}</a></li>
		<li><a href="admin-${adminAction}.html?page=${paginationPageCount?c}">${lastPageLabel}</a></li>
	<#else>
		<li><a>${nextPagePabel}</a></li>
		<li><a>${lastPageLabel}</a></li>
	</#if>
	<li>${sumLabel} ${paginationPageCount?c} ${pageLabel}</li>
</ul>
<!-- Pagination end -->
</#if>