/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;
import net.rafaelaznar.bean.EntregaBean;

/**
 *
 * @author al037805
 */
public class EntregaDao_Mysql {
    
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
                } else {
                    oEntregaBean.setCodigo(oMysql.getOne("producto", "codigo", oProductoBean.getId()));
                    oEntregaBean.setDescripcion(oMysql.getOne("producto", "descripcion", oProductoBean.getId()));
                    oEntregaBean.setPrecio(Double.parseDouble(oMysql.getOne("producto", "precio", oProductoBean.getId())));
                    oEntregaBean.setId_tipoProducto(Integer.parseInt(oMysql.getOne("producto", "id_tipoproducto", oProductoBean.getId())));
                }
            } catch (Exception e) {
                throw new Exception("ProductoDao.getProducto: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oProductoBean.setId(0);
        }
        return oProductoBean;
    }

    @Override
    public ProductoBean set(ProductoBean oProductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oProductoBean.getId() == 0) {
                oProductoBean.setId(oMysql.insertOne("producto"));
            }
            oMysql.updateOne(oProductoBean.getId(), "producto", "codigo", oProductoBean.getCodigo());
            oMysql.updateOne(oProductoBean.getId(), "producto", "descripcion", oProductoBean.getDescripcion());
            oMysql.updateOne(oProductoBean.getId(), "producto", "precio", oProductoBean.getPrecio().toString());            
            oMysql.updateOne(oProductoBean.getId(), "producto", "id_tipoproducto", oProductoBean.getId_tipoProducto().toString());                       
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ProductoDao.setProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oProductoBean;
    }

    @Override
    public void remove(ProductoBean oProductoBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oProductoBean.getId(), "producto");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ProductoDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns=null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns=oMysql.getColumnsName("producto", Conexion.getDatabaseName());
            oMysql.desconexion();
            
        } catch (Exception e) {
            throw new Exception("ProductoDao.removeProducto: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }
    
}
