<#macro pager url page>
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#">Pages(${page.getTotalPages()})</a>
        </li>
        <#--        input adn hidden-->
        <li class="page-item <#if page.getNumber() lt 1>disabled</#if>">
            <form action="/user">
                <input type="hidden">
                <input type="hidden" name="page" value="0">
                <input type="hidden" name="size" value="${page.getSize()}">
                <button class="page-link" type="submit" tabindex="-1"><b>&laquo;</b></button>
            </form>
        </li>
        <li class="page-item <#if page.getNumber() lt 1>disabled</#if>">
            <form action="/user">
                <input type="hidden" name="page" value="${page.getNumber() - 1}">
                <input type="hidden" name="size" value="${page.getSize()}">
                <button class="page-link" type="submit" tabindex="-1"><b>&lsaquo;</b></button>
            </form>
        </li>
        <li class="page-item active">
            <a class="page-link" href="#" tabindex="-1">${page.getNumber() + 1}</a>
        </li>
        <li class="page-item <#if page.getNumber() gt (page.getTotalPages() - 2)>disabled</#if>">
            <form action="/user">
                <input type="hidden" name="page" value="${page.getNumber() + 1}">
                <input type="hidden" name="size" value="${page.getSize()}">
                <button class="page-link" type="submit" tabindex="-1"><b>&rsaquo;</b></button>
            </form>
        </li>
        <li class="page-item <#if page.getNumber() gt (page.getTotalPages() - 2)>disabled</#if>">
            <form action="/user">
                <input type="hidden" name="page" value="${page.getTotalPages() - 1}">
                <input type="hidden" name="size" value="${page.getSize()}">
                <button class="page-link" type="submit" tabindex="-1"><b>&raquo;</b></button>
            </form>
        </li>
    </ul>
</#macro>

<#macro pagerElements url page>
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
                    <form action="/user">
                        <input type="hidden" name="page" value="0">
                        <input type="hidden" name="size" value="${elements}">
                        <button class="page-link" type="submit" tabindex="-1">${elements}</button>
                    </form>
                </li>
            </#if>
        </#list>
    </ul>
</#macro>