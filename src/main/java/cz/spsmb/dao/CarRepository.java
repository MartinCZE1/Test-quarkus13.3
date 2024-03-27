package cz.spsmb.dao;

import cz.spsmb.model.Car;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    public Optional<Car> listByName(String type) {
        return find("type", type).singleResultOptional();
    }

    public List<Car> findByTypeName(String typeName) {
        return Panache.getEntityManager()
                .createQuery("SELECT t FROM Type t join fetch t.type c where c.name = :typeName", Car.class)
                .setParameter("typeName", typeName)
                .getResultList();
    }
}