package service;

import dao.CityDao;
import entity.City;

import java.util.List;

public class CityService {

    public List<City> findAllCities() {
        return CityDao.findAllCities();
    }

    public List<City> findCitiesByProvinceId(String id) {
        return CityDao.findCitiesByProvinceId(id);
    }
}
