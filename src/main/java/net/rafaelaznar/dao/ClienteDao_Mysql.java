/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.rafaelaznar.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import net.rafaelaznar.data.MysqlData;
import net.rafaelaznar.helper.Conexion;
import net.rafaelaznar.helper.FilterBean;
import net.rafaelaznar.bean.ClienteBean;

public class ClienteDao_Mysql implements ClienteDao {

    private final MysqlData oMysql;
    private final Conexion.Tipo_conexion enumTipoConexion;

    public ClienteDao_Mysql(Conexion.Tipo_conexion tipoConexion) throws Exception {
        oMysql = new MysqlData();
        enumTipoConexion = tipoConexion;
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getPages("cliente", intRegsPerPag, hmFilter, hmOrder);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBean> hmFilter) throws Exception {
        int pages;
        try {
            oMysql.conexion(enumTipoConexion);
            pages = oMysql.getCount("cliente", hmFilter);
            oMysql.desconexion();
            return pages;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<ClienteBean> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBean> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<ClienteBean> arrCliente = new ArrayList<>();
        try {
            oMysql.conexion(enumTipoConexion);
            arrId = oMysql.getPage("cliente", intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                ClienteBean oClienteBean = new ClienteBean(iterador.next());
                arrCliente.add(this.get(oClienteBean));
            }
            oMysql.desconexion();
            return arrCliente;
        } catch (Exception e) {
            throw new Exception("ClienteDao.getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public ClienteBean get(ClienteBean oClienteBean) throws Exception {
        if (oClienteBean.getId() > 0) {
            try {
                oMysql.conexion(enumTipoConexion);
                if (!oMysql.existsOne("cliente", oClienteBean.getId())) {
                    oClienteBean.setId(0);
                } else {

                    Class yourClass = ClienteBean.class;
                    for (Method method : yourClass.getMethods()) {
                        if (!method.getName().substring(3).equalsIgnoreCase("id")) {
                            if (method.getName().substring(0, 3).equalsIgnoreCase("set")) {
                                method.invoke(oClienteBean, oMysql.getOne("cliente", method.getName().substring(3).toLowerCase(Locale.ENGLISH), oClienteBean.getId()));
                            }
                        }
                    }
//                        oClienteBean.setNombre(oMysql.getOne("cliente", "nombre", oClienteBean.getId()));
//                        oClienteBean.setApe1(oMysql.getOne("cliente", "ape1", oClienteBean.getId()));
//                        oClienteBean.setApe2(oMysql.getOne("cliente", "ape2", oClienteBean.getId()));
//                        oClienteBean.setEmail(oMysql.getOne("cliente", "email", oClienteBean.getId()));
                }
            } catch (Exception e) {
                throw new Exception("ClienteDao.getCliente: Error: " + e.getMessage());
            } finally {
                oMysql.desconexion();
            }
        } else {
            oClienteBean.setId(0);
        }
        return oClienteBean;
    }

    @Override
    public ClienteBean set(ClienteBean oClienteBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.initTrans();
            if (oClienteBean.getId() == 0) {
                oClienteBean.setId(oMysql.insertOne("cliente"));
            }
            oMysql.updateOne(oClienteBean.getId(), "cliente", "nombre", oClienteBean.getNombre());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "ape1", oClienteBean.getApe1());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "ape2", oClienteBean.getApe2());
            oMysql.updateOne(oClienteBean.getId(), "cliente", "email", oClienteBean.getEmail());
            oMysql.commitTrans();
        } catch (Exception e) {
            oMysql.rollbackTrans();
            throw new Exception("ClienteDao.setCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return oClienteBean;
    }

    @Override
    public void remove(ClienteBean oClienteBean) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            oMysql.removeOne(oClienteBean.getId(), "cliente");
            oMysql.desconexion();
        } catch (Exception e) {
            throw new Exception("ClienteDao.removeCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    public int borrarinicio(String campo, String valor) throws Exception {
        try {
            oMysql.conexion(enumTipoConexion);
            //oMysql.removeOne(oClienteBean.getId(), "cliente");
            int res=oMysql.borrarinicio("cliente",campo, valor);
            oMysql.desconexion();
            return res;
        } catch (Exception e) {
            throw new Exception("ClienteDao.removeCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
    }
    
    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            oMysql.conexion(enumTipoConexion);
            alColumns = oMysql.getColumnsName("cliente", Conexion.getDatabaseName());
            oMysql.desconexion();

        } catch (Exception e) {
            throw new Exception("ClienteDao.removeCliente: Error: " + e.getMessage());
        } finally {
            oMysql.desconexion();
        }
        return alColumns;
    }
}
