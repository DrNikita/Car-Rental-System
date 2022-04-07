package by.epam.lab.mvc.service;

import java.util.Optional;

import by.epam.lab.entity.Brand;
import by.epam.lab.exceptions.ServiceLayerException;

public interface IBrandService extends IService<Brand> {

	Optional<Brand> getEntityByBrandModel(String brand, String model) throws ServiceLayerException;

	boolean addBrand(Brand brand) throws ServiceLayerException;
}
