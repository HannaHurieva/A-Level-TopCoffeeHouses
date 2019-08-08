<div class="form-group">
    <label class="col-sm-2 col-form-label">Cuisine Types :</label>
    <#list 0..cuisineTypes?size-13 as i>
        <div class="form-row">
        <label class="col-sm-2"></label>
        <label class="col-sm-2">
        <input type="checkbox" name="${cuisineTypes[i].cuisineType}"/>
            ${cuisineTypes[i].cuisineType!""}</label>
        <label class="col-sm-2">
        <input type="checkbox" name="${cuisineTypes[i+6].cuisineType}"/>
            ${cuisineTypes[i+6].cuisineType!""}</label>
        <label class="col-sm-2">
        <input type="checkbox" name="${cuisineTypes[i+12].cuisineType}"/>
            ${cuisineTypes[i+12].cuisineType!""}</label>
        </div>
    </#list>
</div>
