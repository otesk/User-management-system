<#import "parts/common.ftlh" as c>
<@c.page>
    <link rel="stylesheet" href="/static/css/registration.css"/>
    <form class="calculator-container transition-fade" method="post" action="/user/new">
        <h1 class="h3 mb-3 font-weight-normal">Please fill the fields to create a new user account</h1>
        <div class="form-label-group">
            <label for="inputUsername">
                <input type="text" name="username" id="inputUsername"
                       class="form-control input-panel ${(usernameError??)?string('is-invalid', '')}"
                       value="<#if userAccount??>${userAccount.getUsername()}</#if>" placeholder="User name"
                       required="required" pattern="^[a-zA-Z]+$" title="Use only latin letters"
                       minlength="3" maxlength="16"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </label>
        </div>
        <div class="form-label-group">
            <label for="inputFirstName">
                <input type="text" name="firstName" id="inputFirstName"
                       class="form-control input-panel ${(firstNameError??)?string('is-invalid', '')}"
                       value="<#if userAccount??>${userAccount.getFirstName()}</#if>" placeholder="First name"
                       required="required" pattern="^[a-zA-Z]+$" title="Use only latin letters"
                       minlength="1" maxlength="16"/>
                <#if firstNameError??>
                    <div class="invalid-feedback">
                        ${firstNameError}
                    </div>
                </#if>
            </label>
        </div>
        <div class="form-label-group">
            <label for="inputLastName">
                <input type="text" name="lastName" id="inputLastName"
                       class="form-control input-panel ${(lastNameError??)?string('is-invalid', '')}"
                       value="<#if userAccount??>${userAccount.getLastName()}</#if>" placeholder="Last name"
                       required="required" pattern="^[a-zA-Z]+$" title="Use only latin letters"
                       minlength="1" maxlength="16"/>
                <#if lastNameError??>
                    <div class="invalid-feedback">
                        ${lastNameError}
                    </div>
                </#if>
            </label>
        </div>
        <div class="form-label-group">
            <label for="inputPassword">
                <input type="password" name="password" id="inputPassword"
                       class="form-control input-panel ${(passwordError??)?string('is-invalid', '')}"
                       value="<#if userAccount??>${userAccount.getPassword()}</#if>" placeholder="Password"
                       required="required" pattern="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$"
                       title="Use only latin letters (1 minimum) and arabic numerals (1 minimum)"
                       minlength="3" maxlength="16"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </label>
        </div>
        <div class="form-label-group">
            <label for="inputConfirmPassword">
                <input type="password" name="confirmPassword" id="inputConfirmPassword"
                       class="form-control input-panel ${(confirmPasswordError??)?string('is-invalid', '')}"
                       value="<#if userAccount??>${userAccount.getConfirmPassword()}</#if>"
                       placeholder="Confirm password" required="required" pattern="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$"
                       title="Use only latin letters (1 minimum) and arabic numerals (1 minimum)"
                       minlength="3" maxlength="16"/>
                <#if confirmPasswordError??>
                    <div class="invalid-feedback">
                        ${confirmPasswordError}
                    </div>
                </#if>
            </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-label-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
        </div>
    </form>
</@c.page>