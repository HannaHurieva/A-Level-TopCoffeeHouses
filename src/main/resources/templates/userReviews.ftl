<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <#--<#include "parts/reviewEdit.ftl">-->

    <div class="card-columns">
    <#list reviews as review>
        <div class="card my-3">
            <div class="m-2">
                <span>${review.text}</span><br/>
                <i>${review.creationDate}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user/reviews/${review.author.id}">${review.authorName}</a>
                <#if review.author.id == currentUserId>
                <a class="btn btn-primary" href="/user/reviews/${review.author.id}?review=${review.id}">
                Edit
                </a>
                </#if>
            </div>
        </div>
    <#else>
        No review
    </#list>
    </div>

    <div><a href="/user/profile/${currentUserId}">Profile</div>
</@c.page>