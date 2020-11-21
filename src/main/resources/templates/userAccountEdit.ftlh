<#import "parts/common.ftlh" as c>
<@c.page>
    <link rel="stylesheet" href="/static/css/registration.css"/>
    <div class="calculator-container transition-fade">
        <form method="post" action="/user/${userAccount.id}/edit">
            <h1 class="h3 mb-3 font-weight-normal">Edit account of ${userAccount.username}</h1>
            <div class="form-label-group">
                <label for="inputUsername">
                    <input type="text" name="username" id="inputUsername"
                           class="form-control input-panel ${(usernameError??)?string('is-invalid', '')}"
                           placeholder="New user name" value="<#if userAccount??>${userAccount.username}</#if>"
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
                           placeholder="New first name" value="<#if userAccount??>${userAccount.firstName}</#if>"
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
                           placeholder="New last name" value="<#if userAccount??>${userAccount.lastName}</#if>"
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
                           placeholder="New password" required="required"
                           pattern="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$"
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
                           placeholder="Confirm new password" required="required"
                           pattern="^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$"
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
            <input type="hidden" name="_method" value="put"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
        </form>
        <form method="post" action="/user/${userAccount.id}" id="deleteForm">
            <input type="hidden" name="_method" value="delete"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button onclick="return confirm('Are you sure you want to delete?');" id="deleteButton"
                    class="btn btn-lg btn-danger btn-block mt-1">Delete
            </button>
        </form>
    </div>
</@c.page>