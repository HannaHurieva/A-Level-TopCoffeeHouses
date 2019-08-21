<#import "parts/common.ftl" as c>

<@c.page>
    <#if isCurrentUser>
    <h5>${username!''}</h5>

    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">First Name:</label>
            <div class="col-sm-6">
                <input type="text" name="firstName" value="${firstName!''}"
                        class="form-control" placeholder="First Name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Last Name:</label>
            <div class="col-sm-6">
                <input type="text" name="lastName" value="${lastName!''}"
                       class="form-control" placeholder="Last Name" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
            <input type="password" name="password" class="form-control" placeholder="Password" />
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email:</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="${email!''}"
                    class="form-control ${(emailEmptyError??)?string('is-invalid', '')}"
                    placeholder="some@some.com" />
                <#if emailEmptyError??>
                <div class="invalid-feedback">
                    ${emailEmptyError}
                </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
    </#if>
</@c.page>
