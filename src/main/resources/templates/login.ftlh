<#import "parts/common.ftlh" as c>
<@c.page>
    <link rel="stylesheet" href="/static/css/singin.css"/>
    <form class="form-signin transition-fade" method="post" action="/login">
        <img class="rounded mb-4" src="/static/images/logo.png" alt="" width="72" height="72"/>
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <div class="alert alert-danger" role="alert" id="alertDiv">
                <#if (Session.SPRING_SECURITY_LAST_EXCEPTION)?contains('locked')>
                    User account is locked.
                <#else>
                    Invalid username or password.
                </#if>
            </div>
        </#if>
        <label for="inputUsername" class="sr-only">User name</label>
        <input type="text" name="username" id="inputUsername" class="form-control" placeholder="User name"
               required="required" autofocus/>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password"
               required="required"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2020</p>
    </form>
</@c.page>