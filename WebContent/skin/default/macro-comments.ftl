<#macro comments commentList permalink>
<div class="comments" id="comments">
    <#list commentList as comment>
    <div id="${comment.id}" class="comment-body">
        <div class="comment-panel">
            <div class="left comment-author">
                <div>
                    <img alt="${comment.author}" src="" onerror="this.src=''"/>
                </div>
                <#if "http://" == comment.link>
                <a>${comment.author}</a>
                <#else>
                <a href="${comment.link}" target="_blank" rel="nofollow">${comment.author}</a>
                </#if>
            </div>
            <div class="left comment-info">
                <div class="left">
                    ${comment.postTime?string("yyyy-MM-dd HH:mm:ss")}
                    <#if (comment.parrentId > 0)>
                    @
                    <a href="${permalink}#${comment.parrentId}"
                       onmouseover="showComment(this, '${comment.parrentId}');"
                       onmouseout="page.hideComment('${comment.parrentId}')">${comment.parrentId}</a>
                    </#if>
                </div>
                <div class="right">
                    <a class="no-underline" href="javascript:replyTo('${comment.id}');">${replyLabel}</a>
                </div>
                <div class="clear"></div>
                <div class="comment-content">
                    ${comment.content}
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    </#list>
</div>
<table id="commentForm" class="comment-form">
    <tbody>
        <tr>
            <td width="208px">
                <input type="text" class="normalInput" id="commentName"/>
            </td>
            <td colspan="2" width="400px">
                ${commentAuthorLabel}
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" class="normalInput" id="commentEmail"/>
            </td>
            <td colspan="2">
                ${commentEmailLabel}
            </td>
        </tr>
        <tr>
            <td>
                <div id="commentURLLabel">
                    http://
                </div>
                <input type="text" id="commentURL"/>
            </td>
            <td colspan="2">
                ${commentLinkLabel}
            </td>
        </tr>
        <tr>
            <td id="emotions" colspan="3">
                <span class="em00" title="${em00Label?if_exists}"></span>
                <span class="em01" title="${em01Label?if_exists}"></span>
                <span class="em02" title="${em02Label?if_exists}"></span>
                <span class="em03" title="${em03Label?if_exists}"></span>
                <span class="em04" title="${em04Label?if_exists}"></span>
                <span class="em05" title="${em05Label?if_exists}"></span>
                <span class="em06" title="${em06Label?if_exists}"></span>
                <span class="em07" title="${em07Label?if_exists}"></span>
                <span class="em08" title="${em08Label?if_exists}"></span>
                <span class="em09" title="${em09Label?if_exists}"></span>
                <span class="em10" title="${em10Label?if_exists}"></span>
                <span class="em11" title="${em11Label?if_exists}"></span>
                <span class="em12" title="${em12Label?if_exists}"></span>
                <span class="em13" title="${em13Label?if_exists}"></span>
                <span class="em14" title="${em14Label?if_exists}"></span>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <textarea rows="10" cols="96" id="comment"></textarea>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" class="normalInput" id="commentValidate"/>
            </td>
            <td>
                <img id="captcha" alt="validate" src="/captcha.do"></img>
            </td>
            <th align="right">
                <span class="error-msg" id="commentErrorTip"/>
            </th>
        </tr>
        <tr>
            <td colspan="3" align="right">
                <button id="submitCommentButton" onclick="page.submitComment();">${submmitCommentLabel}</button>
            </td>
        </tr>
    </tbody>
</table>
</#macro>