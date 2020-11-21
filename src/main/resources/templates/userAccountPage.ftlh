<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <script src="/static/js/checkbox.js"></script>
    <link rel="stylesheet" href="/static/css/useraccountlist.css"/>
    <link rel="stylesheet" href="/static/css/checker.css"/>
    <div class="">
        <h1 class="h3 mb-3 font-weight-normal">Information about ${user.username}</h1>
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
            <tbody id="userPage">
            <tr>
                <td>${user.username}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.role}</td>
                <td>${user.createdAt}</td>
                <#if isAdmin>
                    <td width="150px">
                        <form method="post" action="/user/${user.id}" id="changeStatusForm">
                    <span class="switch">
                        <label>
                            <input type="checkbox" id="statusCheckbox" name="status"
                                   <#if user.status.name() == "ACTIVE">checked</#if>
                            onclick="clickCheckbox()"/>
                            ${user.status}
                        </label>
                    </span>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <input type="hidden" name="_method" value="put"/>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="/user/${user.id}/edit">
                            <button class="btn btn-lg btn-info" type="submit">edit</button>
                        </form>
                    </td>
                </#if>
            </tr>
            </tbody>
        </table>
    </div>
</@c.page>