<div class="form-group">
    <label class="col-sm-2 col-form-label">Place Categories :</label>
    <#list 0..placeCategories?size-9 as i>
        <div class="form-row">
        <label class="col-sm-2"></label>
        <label class="col-sm-2">
        <input type="checkbox" name="${placeCategories[i].placeCategory}"/> ${placeCategories[i].placeCategory}</label>
        <label class="col-sm-2">
        <input type="checkbox" name="${placeCategories[i+4].placeCategory}"/> ${placeCategories[i+4].placeCategory}</label>
        <label class="col-sm-2">
        <input type="checkbox" name="${placeCategories[i+8].placeCategory}"/> ${placeCategories[i+8].placeCategory}</label>
        </div>
    </#list>
</div>
