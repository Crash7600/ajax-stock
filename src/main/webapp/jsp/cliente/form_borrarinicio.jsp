<%-- 
    Document   : form
    Created on : Jan 21, 2013, 10:24:17 AM
    Author     : rafa
--%>
<form class="form-horizontal" action="#" id="formulario" name="formulario">
    <h2>Cliente</h2>
    <!--<div class="control-group">
        <label class="control-label" for="inputId">Id:</label>
        <div class="controls">
            <input type="text" id="id" name="id" placeholder="id" />
        </div>
    </div>-->
    <div class="control-group">
        <label class="control-label"  for="inputNombre">Letra inicio:</label>
        <div class="controls">
            <input type="text" id="iniciales" name="iniciales" size="15" placeholder="iniciales" />
        </div>
    </div>
    <div class="control-group">
        <label class="control-label"  for="inputNombre">Campo busqueda:</label>
        <div class="controls">
            <select name="campo">    
                <option id="nombre" value="nombre" selected="nombre">nombre</option>
                <option id="ape1" value="ape1">ape1</option>
                <option id="ape2" value="ape2">ape2</option>
             
            </select>
        </div>
    </div>
    <div class="control-group">
        <div class="controls">
            <button type="submit" id="submitForm" class="btn submitForm">Submit</button>
        </div>
    </div>
</form>
