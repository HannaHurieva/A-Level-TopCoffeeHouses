<div class="card my-3">
        <form action="/places/cuisine" method="post">
            <div class="form-group">

                <label for="cuisineTypeFilter">Filter by cuisine type</label>
                    <select name="cuisineFilter" class="form-control" id="cuisineTypeFilter">
                        <option></option>
                        <option value="American">American</option>
                        <option value="Argentinean">Argentinean</option>
                        <option value="Armenian">Armenian</option>
                        <option value="Asian">Asian</option>
                        <option value="Caucasian">Caucasian</option>
                        <option value="Chinese">Chinese</option>
                        <option value="European">European</option>
                        <option value="French">French</option>
                        <option value="Georgian">Georgian</option>
                        <option value="German">German</option>
                        <option value="Italian">Italian</option>
                        <option value="Japanese">Japanese</option>
                        <option value="Mexican">Mexican</option>
                        <option value="Russian">Russian</option>
                        <option value="Turkish">Turkish</option>
                        <option value="Ukrainian">Ukrainian</option>
                        <option value="Uzbek">Uzbek</option>
                        <option value="Vietnamese">Vietnamese</option>
                    </select>
                <input type="hidden" value="${_csrf.token}" name="_csrf" />
                <input type="submit" value="Find"/>
            </div>
        </form>
</div>
