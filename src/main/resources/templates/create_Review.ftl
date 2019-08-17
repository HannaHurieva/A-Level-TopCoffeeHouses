<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
<div class="mb-1">Create new review</div>

<form action="/places/reviews/${place.id}/create" method="post">

    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Message :</label>
        <div class="col-sm-6">
            <input type="text" name="text" value="<#if review??>${review.text}</#if>"
                   class="form-control ${(textError??)?string('is-invalid', '')}"
                   placeholder="Input the text" />
            <#if textError??>
            <div class="invalid-feedback">
                ${textError}
            </div>
        </#if>
    </div>
    </div>

    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Create"/></div>
</form>
</@c.page>
