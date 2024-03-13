package cz.spsmb.dao;

import cz.spsmb.model.Car;
import cz.spsmb.model.Color;
import cz.spsmb.model.Price;
import io.quarkus.hibernate.orm.panache.Panache;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class PriceRepository implements PanacheRepository<Price> {

    public Optional<Price> listByName(int price) {
        return find("price", price).singleResultOptional();
    }
}
