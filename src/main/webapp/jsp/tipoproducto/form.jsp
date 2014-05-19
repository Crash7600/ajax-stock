<%-- 
    Document   : form
    Created on : Jan 21, 2013, 10:24:17 AM
    Author     : rafa
--%>
<form class="form-horizontal" action="#" id="formulario" name="formulario">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="id">Id: </label>
            <div class="controls">
                <input id="id" name="id" type="text" size="30" maxlength="50" />
            </div>
        </div>        
        <div class="control-group">
            <label class="control-label" for="descripcion">Descripción: </label> 
            <div class="controls">
                <input id="descripcion" name="descripcion" type="text" size="30" maxlength="50" />
            </div>
        </div>           
        <div class="control-group">
            <div class="controls">
               <button type="submit" id="submitForm" class="btn submitForm">Submit</button>
            </div>
        </div>
    </fieldset>
</form>
