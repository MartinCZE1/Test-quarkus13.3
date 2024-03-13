package cz.spsmb.dao;

import cz.spsmb.model.Color;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ColorRepository implements PanacheRepository<Color> {
    public Optional<Color> listByName(String color) {
        return find("color", color).singleResultOptional();
    }
}
