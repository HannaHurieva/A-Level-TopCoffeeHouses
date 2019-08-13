<#import "parts/common.ftl" as c>

<@c.page>
    <div class="card-columns">
    <#list reviews as review>
        <div class="card my-3">
            <div class="m-2">
                <span>${review.text}</span><br/>
                <i>${review.lastModifiedDate}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user/reviews/${review.author.id}">${review.authorName}</a>
            </div>
        </div>
    <#else>
        No review
    </#list>
    </div>
</@c.page>
