<div class="card my-3">
        <form action="/places/category" method="post">
            <div class="form-group">
                <label for="categoryFilter">Filter by place category</label>
                    <select name="categoryFilter" class="form-control" id="categoryFilter">
                        <option></option>
                        <option value="Anticafe">Anticafe</option>
                        <option value="Art cafe">Art cafe</option>
                        <option value="Bakery">Bakery</option>
                        <option value="Bar">Bar</option>
                        <option value="Cafe">Cafe</option>
                        <option value="Coffee house">Coffee house</option>
                        <option value="Coffee to go">Coffee to go</option>
                        <option value="Pizzeria">Pizzeria</option>
                        <option value="Pub">Pub</option>
                        <option value="Restaurant">Restaurant</option>
                        <option value="Sushi bar">Sushi bar</option>
                        <option value="Tea Room">Tea Room</option>
                    </select>
            <input type="hidden" value="${_csrf.token}" name="_csrf" />
            <input type="submit" value="Find"/>
            </div>
        </form>
</div>
