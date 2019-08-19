<#include "parts/security.ftl">
<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Welcome to CoffeeIN!</h5>
    <div class="card-columns">
        <#include "parts/filterByCuisine.ftl">
        <#include "parts/filterByPlaceCategory.ftl">
        <#include "parts/filterByLocation.ftl">
    </div>

    <#include "parts/placesList.ftl">
</@c.page>
