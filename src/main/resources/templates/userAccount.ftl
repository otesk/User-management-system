<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<@c.page>
    <script src="/static/js/checkbox.js"></script>
    <link rel="stylesheet" href="/static/css/userlist.css">
    <link rel="stylesheet" href="/static/css/checker.css">
    <h1 class="h3 mb-3 font-weight-normal">Information about
        <#list userAccounts as userAccount>
            ${userAccount.getUsername()}
        </#list>
    </h1>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Username</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Role</th>
            <th scope="col">Created at</th>
            <#if isAdmin>
                <th scope="col">Status</th>
                <th></th>
            </#if>
        </tr>
        </thead>
        <tbody>
        <tr>
            <#list userAccounts as userAccount>
                <td>${userAccount.getUsername()}</td>
                <td>${userAccount.getFirstName()}</td>
                <td>${userAccount.getLastName()}</td>
                <td>${userAccount.getRole()}</td>
                <td>${userAccount.getCreatedAt()}</td>
                <#if isAdmin>
                    <td>
                        <form method="post" action="/user/${userAccount.getId()}" id="changeStatusForm">
                    <span class="switch">
                        <label>
                            <input type="checkbox" id="statusCheckbox" name="status"
                                   <#if userAccount.getStatus().name() == "ACTIVE">checked</#if>
                            onclick="clickCheckbox()">
                            ${userAccount.getStatus()}
                        </label>
                    </span>
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                        </form>
                    </td>
                    <td><a href="/user/${userAccount.getId()}/edit">edit</a></td>
                </#if>
            </#list>
        </tr>
        </tbody>
    </table>
</@c.page>