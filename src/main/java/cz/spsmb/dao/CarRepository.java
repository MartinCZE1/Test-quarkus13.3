package cz.spsmb.dao;

import cz.spsmb.model.Car;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    public Optional<Car> listByName(String type) {
        return find("type", type).singleResultOptional();
    }
}