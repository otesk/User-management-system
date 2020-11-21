<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>
<#import "filter.ftlh" as f>

<@c.page>
    <link rel="stylesheet" href="/static/css/useraccountlist.css"/>
    <div class="card <#if !RequestParameters.page??>transition-fade</#if>" style="width: auto;">
        <div class="card-header text-center bg-dark text-light">
            <div>
                <h1 class="h2 font-weight-normal">User list</h1>
            </div>
            <form action="/user" class="custom-header">
                <@f.search roles searchFilter/>
                <@p.pager page/>
                <@p.pagerElements page/>
            </form>
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
            <tbody class="transition-fade" id="userList">
            <#list page.content as userAccount>
                <tr>
                    <td><a href="/user/${userAccount.id}">${userAccount.username}</a></td>
                    <td>${userAccount.firstName}</td>
                    <td>${userAccount.lastName}</td>
                    <td>${userAccount.role}</td>
                    <#if isAdmin>
                        <td>
                            <form method="get" action="/user/${userAccount.id}/edit">
                                <button class="btn btn-lg btn-info" type="submit">edit</button>
                            </form>
                        </td>
                    </#if>
                </tr>
            </#list>
            </tbody>
        </table>
        <div class="card-footer text-center bg-dark text-light">
            <#if page.content?size == 0>
                No such elements.
            </#if>
        </div>
    </div>
</@c.page>