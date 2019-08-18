<#import "parts/common.ftl" as c>

<@c.page>
    <div class="mb-1">Create new place</div>

    <form action="/places/create" method="post">

    <div class="form-group row">
            <label class="col-sm-2 col-form-label">Title :</label>
            <div class="col-sm-6">
                <input type="text" name="title" value="<#if place??>${place.title}</#if>"
                class="form-control ${(titleError??)?string('is-invalid', '')}"
                placeholder="Title"/>

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
                          placeholder="Description" rows="3"></textarea>

                <#if descriptionError??>
                    <div class="invalid-feedback">
                    ${descriptionError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-row">
            <label class="col-sm-2 col-form-label"> Working time :</label>
            <div class="col-sm-3">
                <input type="number" name="timeOpening" value="<#if place??>${place.timeOpening}</#if>"
                       class="form-control ${(timeOpeningError??)?string('is-invalid', '')}"
                       placeholder="Time opening"/>

                <#if timeOpeningError??>
                    <div class="invalid-feedback">
                    ${timeOpeningError}
                    </div>
                </#if>
            </div>

            <div class="col-sm-3">
                <input type="number" name="timeClosing" value="<#if place??>${place.timeClosing}</#if>"
                       class="form-control ${(timeClosingError??)?string('is-invalid', '')}"
                       placeholder="Time closing"/>
                <#if timeClosingError??>
                    <div class="invalid-feedback">
                    ${timeClosingError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2"></label>
            <div class="col-sm-6">
                <small class="form-text text-muted">
                    ** Input the time in the integer format between 0 to 23. Do not leave blank!
                </small>
            </div>
        </div>

        <#include "parts/contacts.ftl">
        <#include "parts/cuisineTypes.ftl">
        <#include "parts/placeCategories.ftl">

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="col-sm-2"><input type="submit" value="Create"/></div>
    </form>
</@c.page>
