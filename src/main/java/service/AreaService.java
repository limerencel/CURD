package service;

import dao.AreaDao;
import entity.City;

import java.util.List;

public class AreaService {

    public List<City> findAreaByCityId(String id) {
        return AreaDao.findAreaByCityId(id);
    }
}
