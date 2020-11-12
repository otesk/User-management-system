<#macro search roles searchFilter>
    <script src="/static/js/queryanalyzer.js"></script>
    <a class="btn btn-secondary" href="#collapseFilter" data-toggle="collapse" role="button" aria-expanded="false"
       aria-controls="collapseFilter">
        Use filters to search
    </a>
    <div class="collapse" id="collapseFilter">
        <form action="/user" id="searchForm">
            <input type="text" class="form-control" name="usernameForSearchFilter" id="usernameFilter"
                   <#if searchFilter.getUsernameForSearchFilter()??>value="${searchFilter.getUsernameForSearchFilter()}"</#if>
                   placeholder="username for search"
                   min="3" maxlength="16" pattern="^[a-zA-Z]+$" title="Use only latin letters">
            <span>
                <#list roles as role>
                    <label>
                        <input type="checkbox"
                               name="${role}" ${searchFilter.getRolesForSearchFilter()?seq_contains(role)?string("checked", "")}> ${role}
                    </label>
                </#list>
                <button class="btn btn-lg btn-info" type="submit">submit</button>
            </span>
        </form>
    </div>
</#macro>