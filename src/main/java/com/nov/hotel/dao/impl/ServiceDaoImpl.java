package com.nov.hotel.dao.impl;
import com.nov.hotel.collections.impl.ServiceCollection;
import com.nov.hotel.dao.abstr.CrudDaoAbstractInt;
import com.nov.hotel.entities.Service;
import com.nov.hotel.entities.ServiceType;
import com.nov.hotel.entities.ServiceUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;


@Repository("serviceDao")
public class ServiceDaoImpl extends CrudDaoAbstractInt<Service> {

    @Autowired
    ServiceTypeDaoImpl serviceTypeDao;
    @Autowired
    ServiceUnitDaoImpl serviceUnitDao;
    {
        nameDataBase = "services";

        sqlInsert = "INSERT INTO services (service_name_s, service_price_n, " +
                "service_type_fk, service_unit_fk) VALUES (:name, :price, :serviceType, :serviceUnit) ";

        sqlUpdate = "UPDATE services SET service_name_s=:name,  service_price_n=:price, " +
                "service_type_fk=:serviceType, service_unit_fk=:serviceUnit WHERE service_id_n = :id";

        sqlDelete = "DELETE FROM services WHERE service_id_n = :id";

        sqlSelectSingle = "SELECT * FROM service_view WHERE service_id_n";

        sqlSelectSome = "SELECT * FROM service_view WHERE service_name_s";

        sqlSelectAll = "SELECT * FROM service_view";
    }

    @Override
    protected MapSqlParameterSource getParams(Service elem) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",elem.getId());
        params.addValue("name", elem.getName());
        params.addValue("price", elem.getPrice());
        params.addValue("serviceType", elem.getServiceType().getId());
        params.addValue("serviceUnit", elem.getServiceUnit().getId());
        return params;
    }

    private static final RowMapper<Service> rowMapper = new RowMapper<Service>() {
        @Override
        public Service mapRow(ResultSet rs, int rowNum) throws SQLException {

            ServiceTypeDaoImpl typeDao = new ServiceTypeDaoImpl();
            ServiceUnitDaoImpl unitDao = new ServiceUnitDaoImpl();

            ServiceType type = typeDao.getRowMapper().mapRow(rs,rowNum);
            ServiceUnit unit = unitDao.getRowMapper().mapRow(rs,rowNum);

            Service service = new Service();
            service.setId(rs.getInt("service_id_n"));
            service.setName(rs.getString("service_name_s"));
            service.setPrice(rs.getFloat("service_price_n"));
            service.setServiceType(type);
            service.setServiceUnit(unit);
            return ServiceCollection.getInstance().putValue(service);
        }
    };

    @Override
    public  RowMapper<Service> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected void checkId(Service elem) {
        if (elem.getServiceType().getId() == 0)
            serviceTypeDao.insert(elem.getServiceType());
        if (elem.getServiceUnit().getId() == 0)
            serviceUnitDao.insert(elem.getServiceUnit());
    }

}
