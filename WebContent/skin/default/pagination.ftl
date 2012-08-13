<#if paginationOn>
	<div id="nextprevious">
		<div class="nav">
			<div class="wp-pagenavi">
				<#if 1 != paginationCurrentPageNum><!--!= paginationPageNums?first-->
					<a href="${path}page-1/">${firstPageLabel}</a>
					<a id="previousPage" href="${path}page-${paginationPreviousPageNum?c}/">${previousPageLabel}</a>
				<#else>
					<span style="color: #889;">${firstPageLabel}</span>
					<span style="color: #889;">${previousPageLabel}</span>
				</#if>
				<#list paginationPageNums as paginationPageNum>
					<#if paginationPageNum == paginationCurrentPageNum>
						<span style="color: #889;">${paginationPageNum?c}</span>
					<#else>
						<a href="${path}page-${paginationPageNum?c}/">${paginationPageNum?c}</a>
					</#if>
				</#list>
				<#if paginationPageNums?last != paginationPageCount>
					<a id="nextPage" href="${path}page-${paginationNextPageNum?c}/">${nextPageLabel}</a>
					<a href="${path}page-${paginationPageCount?c}/">${lastPageLabel}</a>
				<#else>
					<span style="color: #889;">${nextPageLabel}</span>
					<span style="color: #889;">${lastPageLabel}</span>
				</#if>
				&nbsp;${sumLabel} ${paginationPageCount?c} ${pageLabel}
			</div>
		</div>
		<div class="clr"></div>
	</div>
	<div class="clr"></div>
</#if>