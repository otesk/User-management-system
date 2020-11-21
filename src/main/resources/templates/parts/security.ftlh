<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        userAccount = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name = userAccount.getUsername()
        isAdmin = (userAccount.authorities)?seq_contains("users:write")
    >
<#else>
    <#assign
        name = "guest"
        isAdmin = false
    >
</#if>