package kr.co.cbnu.hive.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.cbnu.hive.entities.Weather;
import kr.co.cbnu.hive.repositories.WeatherRepository;
import kr.co.cbnu.hive.services.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	public List<Weather> findAll() {
		return weatherRepository.findAll();
	}

	@Override
	public boolean saveBatch(List<Weather> weathers) {
		return this.weatherRepository.saveBatch(weathers);
	}
}
