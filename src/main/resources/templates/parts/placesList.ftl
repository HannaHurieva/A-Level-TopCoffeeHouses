<div class="card-columns">
    <#list places as place>
        <div class="card my-3">
        <div class="m-2">
            <a href="/places/${place.id}"><span>${place.title}</span><br/></a>
            <span>${place.description}</span>
        </div>
        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}">Reviews</a>
        </div>
        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}/create">Write a feedback</a>
        </div>
        </div>
    <#else>
        No message
    </#list>
</div>
