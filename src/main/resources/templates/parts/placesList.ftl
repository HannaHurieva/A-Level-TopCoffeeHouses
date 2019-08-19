<div class="card-columns">
    <#list places as place>
        <div class="card my-3">
        <div class="m-2">
            <a href="/places/${place.id}"><span>${place.title}</span><br/></a>
            <span>${place.description}</span>
        </div>

        <#if isAdmin>
            <div class="col-sm-2">
            <form action="/places/delete/${place.id}" method="post">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn btn-primary" type="submit" value="Delete"/>
            </form>
            </div>
        </#if>

        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}">Reviews</a>
        </div>
        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}/create">Write a feedback</a>
        </div>
        </div>
    <#else>
        No places
    </#list>
</div>
