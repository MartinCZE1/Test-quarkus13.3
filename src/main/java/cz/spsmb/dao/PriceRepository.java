package cz.spsmb.dao;

import cz.spsmb.model.Price;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceRepository implements PanacheRepository<Price> {

    public Price listByName(String price) {
        return find("price", price).firstResult();
    }
}

