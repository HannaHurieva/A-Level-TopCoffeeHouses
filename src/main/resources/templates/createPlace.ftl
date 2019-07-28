<#import "parts/common.ftl" as c>

<@c.page>
    <div class="mb-1">Create new place</div>

    <form action="/places/create" method="post">

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Title :</label>
            <div class="col-sm-6">
                <input type="text" name="title" value="<#if coffeeHouse??>${coffeeHouse.title}</#if>"
                class="form-control ${(titleError??)?string('is-invalid', '')}"
                placeholder="Title" />
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
                <input type="text" name="description" value="<#if coffeeHouse??>${coffeeHouse.description}</#if>"
                       class="form-control" placeholder="Description"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Time opening :</label>
            <div class="col-sm-6">
                <input type="number" name="timeOpening" value="<#if coffeeHouse??>${coffeeHouse.timeOpening}</#if>"
                       class="form-control" placeholder="Time opening"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Time closing :</label>
            <div class="col-sm-6">
                <input type="number" name="timeClosing" value="<#if coffeeHouse??>${coffeeHouse.timeClosing}</#if>"
                       class="form-control" placeholder="Time closing"/>
            </div>
        </div>



        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div><input type="submit" value="Create"/></div>
    </form>
</@c.page>
