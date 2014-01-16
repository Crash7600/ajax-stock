/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;
import net.rafaelaznar.bean.EntregaBean;

/**
 *
 * @author al037805
 */
public class EntregaDao_Mysql implements EntregaDao{
    
    private final MysqlData oMysql;
    private final Conexion.Tipo_conexion enumTipoConexion;

    public EntregaDao_Mysql(Conexion.Tipo_conexion tipoConexion) throws Exception {
        oMysql = new MysqlData();
        enumTipoConexion = tipoConexion;
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("entrega", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ProductoDao.getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("entrega", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("EntregaDao.getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<EntregaBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<EntregaBean> arrEntrega = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("entrega", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                EntregaBean oEntregaBean = new EntregaBean(iterador.next());
                arrEntrega.add(this.get(oEntregaBean));
            }
            oMysql.desconexion();
            return arrEntrega;
        } catch (Exception e) {
            throw new Exception("EntregaDao.getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public EntregaBean get(EntregaBean oEntregaBean) throws Exception {
        if (oEntregaBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("entrega", oEntregaBean.getId())) {
                    oEntregaBean.setId(0);
                } else {      // REVISAR TODO ESTE BLOQUE DE CODIGO!!!!!!!
                    oEntregaBean.setIdDocumento(Integer.parseInt(oMysql.getOne("entrega", "documento", oEntregaBean.getId())));
                    oEntregaBean.getIdActividad(oMysql.getOne("entrega", "actividad", oEntregaBean.getId()));
                    oEntregaBean.setNota(Integer.parseInt(oMysql.getOne("entrega", "nota", oEntregaBean.getId())));
                    String string = oMysql.getOne("entrega", "fecha", oEntregaBean.getId());
                    SimpleDateFormat date = new SimpleDateFormat("yyyy MMMM d", Locale.ENGLISH);
                    oEntregaBean.setFecha(date.parse(string));
                }  // HASTA AQUI!!!!
            } catch (Exception e) {
                throw new Exception("ProductoDao.getProducto: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oEntregaBean.setId(0);
        }
        return oEntregaBean;
    }

    @Override
    public EntregaBean set(EntregaBean oEntregaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oEntregaBean.getId() == 0) {
                oEntregaBean.setId(oMysql.insertOne("producto"));
            }
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "iddocumento", oEntregaBean.getIdDocumento().toString());
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "idactividad", oEntregaBean.getIdActividad().toString());
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "fecha", oEntregaBean.getFecha().toString());            
            oMysql.updateOne(oEntregaBean.getId(), "entrega", "nota", oEntregaBean.getNota().toString());                       
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ProductoDao.setProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oEntregaBean;
    }

    @Override
    public void remove(EntregaBean oEntregaBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oEntregaBean.getId(), "entrega");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("EntregaDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns=null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns=oMysql.getColumnsName("entrega", Conexion.getDatabaseName());
            oMysql.desconexion();
            
        } catch (Exception e) {
            throw new Exception("EntregaDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }
    
}
