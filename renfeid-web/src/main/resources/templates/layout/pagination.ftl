<#macro paginationLayout paginationList>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <#if paginationList?? && (paginationList?size > 0)>
                <#list paginationList as pagination>
                    <li class="page-item${pagination.active?string(" active","")}">
                        <a class="page-link" href="${pagination.link?html}">${pagination.page!}</a>
                    </li>
                </#list>
            </#if>
        </ul>
    </nav>
</#macro>