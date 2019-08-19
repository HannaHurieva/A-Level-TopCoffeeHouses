<div class="card my-3">
        <form action="/places/location" method="post">
            <div class="form-group">
                <label for="locationFilter">Filter by location</label>
                <div class="col-sm-3">
                    <input type="text" name="locationFilter">
                    <input type="hidden" value="${_csrf.token}" name="_csrf" />
                </div>
                    <button type="submit">Find</button>
            </div>
        </form>
</div>
