<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>

    <#if isCurrentUser>
        <#include "parts/reviewEdit.ftl">
    </#if>

    <div class="card-columns">
    <#list reviews as review>
        <div class="card my-3">
            <div class="m-2">
                <span>${review.text}</span><br/>
                <i>${review.lastModifiedDate}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/places/${review.place.id}">
                ${review.place.title!''}
                </a>
            </div>
            <div class="card-footer text-muted">
                <div class="form-group row">
                    ${review.authorName}
                    <#if review.author.id == currentUserId>
                        <div class="col-sm-2">
                            <a class="btn btn-primary" href="/user/reviews/${review.author.id}?review=${review.id}">
                            Edit
                            </a>
                        </div>
                        <div class="col-sm-2">
                            <form action="/user/reviews/${review.author.id}/delete/${review.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <input class="btn btn-primary" type="submit" value="Delete"/>
                            </form>
                        </div>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No review
    </#list>
    </div>

    <#if isCurrentUser>
        <div><a href="/user/profile/${currentUserId}">Profile</div>
    </#if>
</@c.page>
