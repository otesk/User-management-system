<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#import "parts/pager.ftl" as p>
<#import "filter.ftl" as f>

<@c.page>
    <link rel="stylesheet" href="/static/css/userlist.css">
    <@f.search roles searchFilter/>
    <div class="card" style="width: auto;">
        <div class="card-header text-center bg-dark text-light">
            <@p.pager url page/>
            <h1 class="h2 font-weight-normal">User list</h1>
            <@p.pagerElements url page/>
        </div>
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th scope="col">Username</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Role</th>
                <#if isAdmin>
                    <th></th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#list page.content as userAccount>
                <tr>
                    <td><a href="/user/${userAccount.id}">${userAccount.username}</a></td>
                    <td>${userAccount.firstName}</td>
                    <td>${userAccount.lastName}</td>
                    <td>${userAccount.getRole()}</td>
                    <#if isAdmin>
                        <td>
                            <form action="/user/${userAccount.id}/edit">
                            <button class="btn btn-lg btn-info" type="submit">edit</button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
        <div class="card-footer text-center bg-dark text-light">
        </div>
    </div>
</@c.page>