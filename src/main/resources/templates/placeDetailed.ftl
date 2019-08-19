<#import "parts/common.ftl" as c>

<@c.page>
        <div class="m-2"><h5>${place.title}</h5></div>
        <div>${place.description!''}</div>
                <div class="form-group row m-2">
                    <label class="col-sm-2 col-form-label"><i>Business hours : </i></label>
                    <div class="col-sm-6">from ${place.timeOpening} to ${place.timeClosing} </div>
                </div>

                <div class="form-group row m-2">
                    <label class="col-sm-2 col-form-label"><i>Cuisine types : </i></label>
                    <div class="col-sm-6">
                        <#list place.cuisineTypes as cuisineType>${cuisineType.cuisineType}<#sep>, </#list>
                    </div>
                </div>

                <div class="form-group row m-2">
                    <label class="col-sm-2 col-form-label"><i>Place categories : </i></label>
                    <div class="col-sm-6">
                        <#list place.placeCategories as placeCategory>${placeCategory.placeCategory}<#sep>, </#list>
                    </div>
                </div>

                <div class="card-footer text-muted">
                    <label><b>Contacts : </b></label>
                </div>
                    <div class="m-4">
                        ${place.contact.address}
                        <div>${place.contact.location!''}</div>
                        <div><i>${place.contact.phone!''}</i></div>
                        <div><a href="${place.contact.website!''}">${place.contact.website}</a></div>
                    </div>

            <div class="card-footer text-muted">
                <a href="/places/reviews/${place.id}">Reviews</a>
            </div>

            <div class="card-footer text-muted">
                <a href="/places/reviews/${place.id}/create">Write a feedback</a>
            </div>

</@c.page>
