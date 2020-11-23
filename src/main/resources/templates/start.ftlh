<#include "parts/security.ftlh">
<#import "parts/common.ftlh" as c>
<@c.page>
    <link rel="stylesheet" href="/static/css/start.css"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
    <div class="wrapper transition-fade">
        <div class="about animate__animated animate__fadeInLeftBig">
            <h1>Hello, ${name}!</h1>
            <div>
                We are glade to welcome you! UMS is a simple user management application.
            </div>
            <#if !known>
                <div>
                    To continue please <a href="/login">sign in</a>.
                </div>
            <#else>
                Actions available to you:
                <ul>
                    <li><a href="/user">View user list</a>;</li>
                    <li><a href="/user">View any user's details</a><#if !isAdmin>.<#else>;</#if></li>
                    <#if isAdmin>
                        <li><a href="/user/new">Create a new user</a>;</li>
                        <li><a href="/user">Editing an existing user</a>;</li>
                        <li><a href="/user">User lock/unlock</a>.</li>
                    </#if>
                </ul>
            </#if>
        </div>
        <div class="animate__animated animate__fadeInRight">
            <img src="/static/images/users.png" alt="" width="600"/>
        </div>
    </div>
</@c.page>