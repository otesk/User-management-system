<#macro pager page>
    <script src="/static/js/pager.js"></script>
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#">Pages(${page.getTotalPages()})</a>
        </li>
        <input type="hidden" name="page" value="0" id="pageNumber"/>
        <input type="hidden" name="size" value="${page.getSize()}" id="pageSize"/>
        <li class="page-item <#if page.getNumber() lt 1>disabled</#if>">
            <button onclick="changePage(0)" class="page-link" type="submit" tabindex="-1"><b>&#171;</b>
            </button>
        </li>
        <li class="page-item <#if page.getNumber() lt 1>disabled</#if>">
            <button onclick="changePage(${page.getNumber() - 1})" class="page-link" type="submit" tabindex="-1"><b>&#8249;</b>
            </button>
        </li>
        <li class="page-item active">
            <a class="page-link" href="#" tabindex="-1">${page.getNumber() + 1}</a>
        </li>
        <li class="page-item <#if page.getNumber() gt (page.getTotalPages() - 2)>disabled</#if>">
            <button onclick="changePage(${page.getNumber() + 1})" class="page-link" type="submit" tabindex="-1"><b>&#8250;</b>
            </button>
        </li>
        <li class="page-item <#if page.getNumber() gt (page.getTotalPages() - 2)>disabled</#if>">
            <button onclick="changePage(${page.getTotalPages() - 1})" class="page-link" type="submit" tabindex="-1"><b>&#187;</b>
            </button>
        </li>
    </ul>
</#macro>

<#macro pagerElements page>
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Elements</a>
        </li>
        <#list [1, 5, 10, 15] as elements>
            <#if elements == page.getSize()>
                <li class="page-item active">
                    <a class="page-link" href="#" tabindex="-1">${elements}</a>
                </li>
            <#else>
                <li class="page-item">
                    <button onclick="changeSize(${elements})" class="page-link" type="submit"
                            tabindex="-1">${elements}</button>
                </li>
            </#if>
        </#list>
    </ul>
</#macro>