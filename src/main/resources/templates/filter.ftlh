<#macro search roles searchFilter>
    <link rel="stylesheet" href="/static/css/filter.css"/>
    <a class="btn btn-secondary" href="#collapseFilter" data-toggle="collapse" role="button" aria-expanded="false"
       aria-controls="collapseFilter">
        Use filters to search
    </a>
    <div class="collapse" id="collapseFilter">
        <input type="text" class="form-control" name="usernameForSearchFilter" id="usernameFilter"
                <#if searchFilter.getUsernameForSearchFilter()??>
                    value="${searchFilter.getUsernameForSearchFilter()}"
                </#if>
               placeholder="username for search"
               min="3" maxlength="16" pattern="^[a-zA-Z]+$" title="Use only latin letters"/>
        <span>
                <#list roles as role>
                    <label>
                        <input type="checkbox"
                               name="${role}" checked="${searchFilter.getRolesForSearchFilter()?seq_contains(role)?string("checked", "")}"
                        /> ${role}
                    </label>
                </#list>
                <button class="btn btn-lg btn-info" type="submit">submit</button>
            </span>
    </div>
</#macro>