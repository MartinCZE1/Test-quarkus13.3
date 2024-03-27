package cz.spsmb.view;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.ColorRepository;
import cz.spsmb.dao.PriceRepository;
import cz.spsmb.dao.TypeRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Color;
import cz.spsmb.model.Price;
import cz.spsmb.model.Type;
import cz.spsmb.rest.CarResource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Named
@ApplicationScoped
public class CarView {

    @Inject
    TypeRepository typeRepository;
    @Inject
    CarRepository carRepository;
    @Inject
    ColorRepository colorRepository;
    @Inject
    PriceRepository priceRepository;

    List<Type> types;
    List<Car> cars;
    CarDTO newCar;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        String typeName = params.get("type");

        System.out.println(typeName);

        types = typeRepository.listAll();
        System.out.println(types);

        Optional<Type> type = typeRepository.listByName(typeName);
        if (type.isPresent()) {
            cars = carRepository.findByTypeName(typeName);
        } else {
            cars = carRepository.listAll();
        }
        newCar = new CarDTO();
    }


    public CarDTO getNewCar() {
        return newCar;
    }

    public void setNewCar(CarDTO newCar) {
        this.newCar = newCar;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public TypeRepository getTypeRepository() {
        return typeRepository;
    }

    public void setTypeRepository(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ColorRepository getColorRepository() {
        return colorRepository;
    }

    public void setColorRepository(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public PriceRepository getPriceRepository() {
        return priceRepository;
    }

    public void setPriceRepository(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    @Transactional
    public Response saveCar() {
        if (CarResource.validateInput(newCar)) {
            Car car = new Car();
            Optional<Type> typeOptional = typeRepository.listByName(newCar.getType());
            Optional<Color> colorOptional = colorRepository.listByName(newCar.getColor());
            Optional<Price> priceOptional = Optional.ofNullable(priceRepository.listByName(newCar.getPrice()));

            if (typeOptional.isPresent()) {
                car.setType(typeOptional.get());
            } else {
                Type type = new Type();
                type.setType(newCar.getType());
                type.getCar().add(car);
                typeRepository.persist(type);
                car.setType(type);
            }

            if (colorOptional.isPresent()) {
                car.setColor(colorOptional.get());
            } else {
                Color color = new Color();
                color.setColor(newCar.getColor());
                color.getCarList().add(car);
                colorRepository.persist(color);
                car.setColor(color);
            }

            if (priceOptional.isPresent()) {
                car.setPrice(priceOptional.get());
            } else {
                Price price = new Price();
                price.setPrice(newCar.getPrice());
                price.setCar(car);
                priceRepository.persist(price);
                car.setPrice(price);
            }
            carRepository.persist(car);

            init();

            return Response.ok().entity("ok").build();


        }
        return Response.status(400).entity("Invalid inputs").build();
    }
}
