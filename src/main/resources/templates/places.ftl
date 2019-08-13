<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Welcome to CoffeeIN!</h5>
    <#include "parts/placesList.ftl">

    <#if isAdmin>
        <a href="/places/create">Create new place</a>
    </#if>

</@c.page>
