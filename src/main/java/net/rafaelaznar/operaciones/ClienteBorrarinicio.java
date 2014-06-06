/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.operaciones;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.rafaelaznar.dao.ClienteDao_Mysql;
import net.rafaelaznar.helper.Conexion;

/**
 *
 * @author al037805
 */
public class ClienteBorrarinicio implements GenericOperation {
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        try {
            ClienteDao_Mysql oClienteDAO = new ClienteDao_Mysql(Conexion.getConection());                                    
            Map<String, String> data = new HashMap<>();
            if (request.getParameter("campo") != null && request.getParameter("iniciales") != null) {
                int res= oClienteDAO.borrarinicio(request.getParameter("campo"),request.getParameter("iniciales"));
                data.put("status", "200");
                data.put("message", "se han borrado " +  res + " registros");
            } else {
                data.put("status", "error");
                data.put("message", "error");
            }
            Gson gson = new Gson();
            String resultado = gson.toJson(data);
            return resultado;        
        } catch (Exception e) {
            throw new ServletException("ClienteBorraInicioJson: View Error: " + e.getMessage());
        }
    }
    
    
}
