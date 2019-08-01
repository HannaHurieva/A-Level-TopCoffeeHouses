<div class="form-group row">
    <label class="col-sm-2 col-form-label"> Cuisine Types:</label>
    <#list cuisineTypes as cuisine>
        <div>
        <label class="col-sm-6">
        <input type="checkbox" name="${cuisine}"/>${cuisine}</label>
        </div>
    </#list>
</div>
