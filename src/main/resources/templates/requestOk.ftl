<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>${message!''}</h5>

    <a href="/user/reviews/${currentUserId}">My reviews</a>
</@c.page>