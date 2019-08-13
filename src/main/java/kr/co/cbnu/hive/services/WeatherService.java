package kr.co.cbnu.hive.services;

import java.util.List;

import kr.co.cbnu.hive.entities.Weather;

public interface WeatherService {

	List<Weather> findAll();

	boolean saveBatch(List<Weather> weathers);
}
