package com.nov.hotel.dao.impl;

import com.nov.hotel.dao.abstr.CrudDaoAbstrObject;
import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.ServiceInvoice;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("serviceInvoiceDao")
public class ServiceInvoiceDaoImpl extends CrudDaoAbstrObject<Object, ServiceInvoice> {
    {
        nameDataBase = "inv_service";
        sqlInsert = "INSERT INTO inv_service (invs_invoice_id_fk, invs_serv_id_fk, invs_quantity_n," +
                "invs_det_price_n) VALUES (:idInvoice, :idService, :quantity, :price)";

        sqlDelete = "DELETE FROM inv_service WHERE invs_invoice_id_fk = :idInvoice AND invs_serv_id_fk= :idService";;
        sqlSelectSome = "SELECT * FROM inv_service WHERE invs_invoice_id_fk";
        sqlSelectAll = "SELECT * FROM inv_service";
    }

    @Override
    protected MapSqlParameterSource getParams(ServiceInvoice elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("idInvoice",elem.getIdInvoice());
        params.addValue("idService",elem.getService().getId());
        params.addValue("quantity",elem.getQuantity());
        params.addValue("price",elem.getPrice());
        return params;
    }
    @Override
    public void update(ServiceInvoice elem) {
        throw new UnsupportedOperationException("update");
    }


    @Override
    public ServiceInvoice getRow(Object id) {
        throw new UnsupportedOperationException("getRow");
    }

    private static final RowMapper<ServiceInvoice> rowMapper = new RowMapper<ServiceInvoice>() {
        @Override
        public ServiceInvoice mapRow(ResultSet rs, int rowNum) throws SQLException {
            ServiceDaoImpl serviceDao = new ServiceDaoImpl();
            Service service = serviceDao.getRowMapper().mapRow(rs, rowNum);

            ServiceInvoice serviceInvoice = new ServiceInvoice();
            serviceInvoice.setIdInvoice(rs.getLong("invs_invoice_id_fk"));
            serviceInvoice.setService(service);
            serviceInvoice.setQuantity(rs.getFloat("invs_quantity_n"));
            serviceInvoice.setPrice(rs.getFloat("invs_det_price_n"));
            return serviceInvoice;
        }
    };

    @Override
    public  RowMapper<ServiceInvoice> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(ServiceInvoice elem) {

    }

}
