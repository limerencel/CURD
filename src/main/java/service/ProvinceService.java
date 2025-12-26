package service;

import dao.ProvinceDao;
import entity.Province;

import java.util.List;

public class ProvinceService {
    public List<Province> getProvinceList() {
        return ProvinceDao.getProvinceList();
    }


}
