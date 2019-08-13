    <a class="btn btn-primary" data-toggle="collapse"
       href="#collapseExample" role="button"
       aria-expanded="false" aria-controls="collapseExample">
        Review editor</a>
    <div class="collapse <#if review??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
    <form method="post" enctype="multipart/form-data">

        <div class="form-group">
            <input type="text" class="form-control ${(textEmptyError??)?string('is-invalid', '')}"
            value="<#if review??>${review.text}</#if>" name="text" placeholder="Input the review" />
                <#if textEmptyError??>
                    <div class="invalid-feedback">
                    ${textEmptyError}
                    </div>
                </#if>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="hidden" name="id" value="<#if review??>${review.id}</#if>" />
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Save review</button>
        </div>
    </form>
    </div>
    </div>
