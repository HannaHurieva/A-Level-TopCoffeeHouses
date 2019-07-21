<#import "parts/common.ftl" as c>

<@c.page>
    User editor

    <form action="/user" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> User Name:</label>
        <div class="col-sm-6">
            <input type="text" name="username" value="${user.username}" />
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Roles:</label>
        <#list roles as role>
            <div>
            <label class="col-sm-6">
            <input type="checkbox" name="${role}"
            ${user.roles?seq_contains(role)?string("checked='checked'", "")} />${role}</label>
            </div>
        </#list>
    </div>

    <div class="form-group row">
        <label class="col-sm-2 col-form-label"> Status:</label>
            <div class="col-sm-6">
                <select name="status">
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="NOT_ACTIVE">NOT_ACTIVE</option>
                    <option value="DELETED">DELETED</option>
                </select>
            </div>
    </div>

    <input type="hidden" value="${user.id}" name="userId" />
    <input type="hidden" value="${_csrf.token}" name="_csrf" />
    <button type="submit">Save</button>
    </form>
</@c.page>