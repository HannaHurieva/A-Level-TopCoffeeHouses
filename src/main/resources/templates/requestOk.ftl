<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${message!''}</h5>

    <#if isAdmin>
        <a href="/places">List of places</a>
    <#else>
        <a href="/user/reviews/${currentUserId}">My reviews</a>
    </#if>
</@c.page>