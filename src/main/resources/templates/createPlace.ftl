<#import "parts/common.ftl" as c>

<@c.page>
    <div class="mb-1" xmlns="http://www.w3.org/1999/html">Create new place</div>

    <form action="/places/create" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Title :</label>
            <div class="col-sm-6">
                <input type="text" name="title" value="<#if coffeeHouse??>${coffeeHouse.title}</#if>"
                class="form-control ${(titleError??)?string('is-invalid', '')}"
                placeholder="Title" />

                <small class="form-text text-muted">
                    * Input the title of place
                </small>

                <#if titleError??>
                    <div class="invalid-feedback">
                    ${titleError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Description :</label>
            <div class="col-sm-6">
                <textarea name="description"
                          class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                          placeholder="Description" rows="3">
    <#if coffeeHouse??>${coffeeHouse.description} </textarea>
<#else>
            </textarea>
</#if>

                <#if descriptionError??>
                    <div class="invalid-feedback">
                    ${descriptionError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Time opening :</label>
            <div class="col-sm-6">
                <input type="number" name="timeOpening" value="<#if coffeeHouse??>${coffeeHouse.timeOpening}</#if>"
                       class="form-control ${(timeOpeningError??)?string('is-invalid', '')}"
                       placeholder="Time opening"/>

                <small class="form-text text-muted">
                    ** Input the time in the integer format between 0 to 23. Do not leave blank!
                </small>

                <#if timeOpeningError??>
                    <div class="invalid-feedback">
                    ${timeOpeningError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Time closing :</label>
            <div class="col-sm-6">
                <input type="number" name="timeClosing" value="<#if coffeeHouse??>${coffeeHouse.timeClosing}</#if>"
                       class="form-control ${(timeClosingError??)?string('is-invalid', '')}"
                       placeholder="Time closing"/>
                <#if timeClosingError??>
                    <div class="invalid-feedback">
                    ${timeClosingError}
                    </div>
                </#if>
            </div>
        </div>

        <#include "parts/createContact.ftl">

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Create"/></div>
    </form>
</@c.page>
