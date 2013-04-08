<#if paginationOn>
<!-- The pagination -->
<ul class="pagination">
	<#if 1 != paginationCurrentPageNum>
		<li><a href="admin/${adminAction}/">${firstPageLabel}</a></li>
		<li><a href="admin/${adminAction}/?page=${paginationPreviousPageNum?c}">${previousPageLabel}</a></li>
	<#else>
		<li><a>${firstPageLabel}</a></li>
		<li><a>${previousPageLabel}</a></li>
	</#if>
	<#list paginationPageNums as paginationPageNum>
		<#if paginationPageNum == paginationCurrentPageNum>
			<li class="current"><a>${paginationCurrentPageNum}</a></li>
		<#else>
			<li><a href="admin/${adminAction}/?page=${paginationPageNum?c}">${paginationPageNum?c}</a></li>
		</#if>
	</#list>
	<#if paginationPageNums?last != paginationCurrentPageNum>
		<li><a href="admin/${adminAction}/?page=${paginationNextPageNum?c}">${nextPageLabel}</a></li>
		<li><a href="admin/${adminAction}/?page=${paginationPageCount?c}">${lastPageLabel}</a></li>
	<#else>
		<li><a>${nextPageLabel}</a></li>
		<li><a>${lastPageLabel}</a></li>
	</#if>
	<li>${sumLabel} ${paginationPageCount?c} ${pageLabel}</li>
</ul>
<!-- Pagination end -->
</#if>