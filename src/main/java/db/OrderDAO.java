package db;

import db.entities.OrderEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class OrderDAO extends AbstractDAO<OrderEntity> {
    public OrderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
