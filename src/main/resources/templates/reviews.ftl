<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div class="card-columns">
    <#list reviews as review>
        <div class="card my-3">
            <div class="m-2">
                <span>${review.text}</span><br/>
                <i>${review.creationDate}</i>
            </div>
        </div>
    <#else>
        No review
    </#list>
    </div>
</@c.page>
