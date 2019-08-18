<#import "parts/common.ftl" as c>

<@c.page>
        <div class="m-2"><h5>${place.title}</h5>
        <div>${place.description!''}</div>
            <div class="m-2">
            <div>Business hours : from ${place.timeOpening} to ${place.timeClosing}</div>
                <div class="card-footer text-muted">
                Contacts:
                </div>
            <div class="m-2">
                <span>${place.contact.address}</span></br>
                <span>${place.contact.location!''}</span></br>
                <span>${place.contact.phone!''}</span></br>
                <a href="http://${place.contact.website!''}">${place.contact.website}</a>
            </div>
         </div>
        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}">Reviews</a>
        </div>
        <div class="card-footer text-muted">
            <a href="/places/reviews/${place.id}/create">Write a feedback</a>
        </div>
    </div>
</@c.page>
