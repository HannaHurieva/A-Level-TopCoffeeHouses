<div class="form-group">
    <label class="col-sm-2 col-form-label">Cuisine Types:</label>
    <#list 0..cuisineTypes?size-13 as i>
        <div class="form-row">
            <label class="col-sm-2"></label>
                <label class="col-sm-2">
                <input type="checkbox" name="${cuisineTypes[i]}"/> ${cuisineTypes[i]}</label>
                <label class="col-sm-2">
                <input type="checkbox" name="${cuisineTypes[i+6]}"/> ${cuisineTypes[i+6]}</label>
                <label class="col-sm-2">
                <input type="checkbox" name="${cuisineTypes[i+12]}"/> ${cuisineTypes[i+12]}</label>
        </div>
    </#list>
</div>
