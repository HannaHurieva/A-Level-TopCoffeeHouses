<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <h5>...here will be your reviews :-)</h5>

    <#include "parts/security.ftl">
    <div class="card-columns">
        <#list reviews as review>
            <div class="card my-3">
                <div class="m-2">
                    <span>${review.text}</span><br/>
                </div>
                <div class="card-footer text-muted">
                    <a href="/user/review/">${review.authorName}</a>
                </div>
            </div>
        <#else>
            No review
        </#list>
    </div>

    <div class="m-2"><a href="/user/profile">Profile</div>
</@c.page>