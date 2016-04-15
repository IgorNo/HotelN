package com.nov.hotel.dao.impl;

import com.nov.hotel.collections.impl.ClientCollection;
import com.nov.hotel.collections.impl.InvoiceCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractLong;
import com.nov.hotel.entities.Client;
import com.nov.hotel.entities.Invoice;
import com.nov.hotel.entities.Office;
import com.nov.hotel.entities.User;
import com.nov.hotel.entities.interfaces.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends CrudDaoAbstractLong<Invoice> {

//    @Autowired
//    private static OfficeDaoImpl officeDao;

    {
        nameDataBase = "invoices";

        sqlInsert = "INSERT INTO invoices (inv_datetime_dt, inv_number_s, inv_date_d, inv_amount_n, " +
                "inv_client_fk, inv_office_fk, inv_user_fk) " +
                "VALUES (:createTime, :invoiceN, :invoiceDate, :amount, :client, :office, :user)";

        sqlUpdate = "UPDATE invoices SET inv_number_s= :invoiceN, inv_date_d= :invoiceDate, inv_amount_n= :amount, " +
                "inv_client_fk= :client, inv_office_fk= :office, inv_user_fk= :user " +
                "WHERE inv_id_n = :id";

        sqlDelete = "DELETE FROM invoices WHERE inv_id_n = :id";
        sqlSelectSingle = "SELECT * FROM invoices_view WHERE inv_id_n";
        sqlSelectSome = "SELECT * FROM invoices_view WHERE inv_date_d";
        sqlSelectAll = "SELECT * FROM invoices_view";
    }

    @Override
    protected MapSqlParameterSource getParams(Invoice elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", elem.getId());
        params.addValue("createTime", Timestamp.valueOf(elem.getCreateTime()));
        params.addValue("invoiceN", elem.getInvoiceN());
        params.addValue("invoiceDate", Date.valueOf(elem.getInvoiceDate()));
        params.addValue("amount", elem.getAmount());
        params.addValue("user",elem.getUser().getId());
        String className = elem.getCustomer().getClass().getSimpleName();
        if (className.equals("Office") ) {
            Office office = (Office) elem.getCustomer();
            params.addValue("office", office.getId());
            params.addValue("client", null);
        } else
            if (className.equals("Client") ) {
                Client client = (Client) elem.getCustomer();
                params.addValue("office", null);
                params.addValue("client", client.getId());
            }
        return params;
    }
    private static final RowMapper<Invoice> rowMapper = new RowMapper<Invoice>() {
        @Override
        public Invoice mapRow(ResultSet rs, int rowNum) throws SQLException {

            Customer customer = null;
            if (rs.getObject("inv_client_fk") == null){
//                Office office = officeDao.getRowMapper().mapRow(rs, rowNum);
//                customer = OfficeCollection.getInstance().putValue(office);
            }
            if (rs.getObject("inv_office_fk") == null){
                ClientDaoImpl clientDao = new ClientDaoImpl();
                Client client = clientDao.getRowMapper().mapRow(rs, rowNum);
                customer = ClientCollection.getInstance().putValue(client);
            }

            UserDaoImpl userDao = new UserDaoImpl();
            User user = userDao.getRowMapper().mapRow(rs, rowNum);

            Invoice invoice = new Invoice();
            invoice.setId(rs.getLong("inv_id_n"));
            invoice.setCreateTime(rs.getTimestamp("inv_datetime_dt").toLocalDateTime());
            invoice.setInvoiceN(rs.getString("inv_number_s"));
            invoice.setInvoiceDate(rs.getDate("inv_date_d").toLocalDate());
            invoice.setAmount(rs.getFloat("inv_amount_n"));
            invoice.setCustomer(customer);
            invoice.setUser(user);

            return InvoiceCollection.getInstance().putValue(invoice);
        }
    };

    @Override
    protected RowMapper<Invoice> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Invoice elem) {

    }

}
