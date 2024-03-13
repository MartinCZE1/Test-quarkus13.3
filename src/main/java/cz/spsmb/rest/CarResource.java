package cz.spsmb.rest;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.ColorRepository;
import cz.spsmb.dao.PriceRepository;
import cz.spsmb.dao.TypeRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Color;
import cz.spsmb.model.Price;
import cz.spsmb.model.Type;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/car")
public class CarResource {
    @Inject
    CarRepository carRepository;
    @Inject
    ColorRepository colorRepository;
    @Inject
    TypeRepository typeRepository;
    @Inject
    PriceRepository priceRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response list() {
        List<Car> jokes = carRepository.listAll();
        return Response.ok().entity(jokes).build();
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response getById(@PathParam("id") Long id) {
        Car cars = carRepository.findById(id);
        return Response.ok().entity(cars).build();
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        carRepository.deleteById(id);
        return Response.ok().entity("ok").build();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CarDTO carDTO) {
        if (validateInput(carDTO)) {
            Car car = new Car();

            Optional<Price> priceOptional = priceRepository.listByName(carDTO.getPrice());
            if (priceOptional.isPresent()) {
                car.setPrice(priceOptional.get());
            } else {
                Price price = new Price();
                price.setPrice(carDTO.getPrice());

                priceRepository.persist(price);
                car.setPrice(price);
            }

            Optional<Color> colorOptional = colorRepository.listByName(carDTO.getColor());
            if (colorOptional.isPresent()) {
                car.setColor(colorOptional.get());
            } else {
                Color color = new Color();
                color.setColor(carDTO.getColor());
                color.getCarList().add(car);
                colorRepository.persist(color);
                car.setColor(color);
            }

            Optional<Type> typeOptional = typeRepository.listByName(carDTO.getType());
            if (typeOptional.isPresent()) {
                car.setType(typeOptional.get());
            } else {
                Type type = new Type();
                type.setType(carDTO.getType());
                type.getCar().add(car);
                typeRepository.persist(type);
                car.setType(type);
            }
            carRepository.persist(car);
            return Response.ok().entity("ok").build();
        }
        return Response.status(400).entity("Invalid inputs").build();
    }

    public static boolean validateInput(CarDTO carDTO) {
        return !(carDTO.getColor().isEmpty() || carDTO.getType().isEmpty());
    }
}