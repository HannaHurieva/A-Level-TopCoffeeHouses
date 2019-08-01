        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Address :</label>
            <div class="col-sm-6">
                <input type="text" name="address" value="<#if contact??>${contact.address}</#if>"
                class="form-control ${(addressError??)?string('is-invalid', '')}"
                placeholder="Address" />

                <small class="form-text text-muted">
                    * Input the address of place. Do not leave blank!
                </small>

                <#if addressError??>
                    <div class="invalid-feedback">
                    ${addressError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Location :</label>
            <div class="col-sm-6">
                <input type="text" name="location" value="<#if contact??>${contact.location}</#if>"
                       class="form-control ${(locationError??)?string('is-invalid', '')}"
                       placeholder="Location" />
            </div>
        </div>

        <div class="form-row">
            <label class="col-sm-2"></label>
            <div class="form-group col-md-3">
                <label> Phone :</label>
                <input type="text" name="phone" value="<#if contact??>${contact.phone}</#if>"
                       class="form-control"
                       placeholder="Phone" />
            </div>

            <div class="form-group col-md-3">
                <label> Web site :</label>
                <input type="text" name="website" value="<#if contact??>${contact.website}</#if>"
                       class="form-control"
                       placeholder="Web site" />
            </div>
        </div>
