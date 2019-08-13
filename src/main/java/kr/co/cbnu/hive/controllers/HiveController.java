package kr.co.cbnu.hive.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.cbnu.hive.entities.Weather;
import kr.co.cbnu.hive.services.WeatherService;

@RestController
@RequestMapping(value="/v1/api/hive")
public class HiveController {
	
	@Autowired
	private WeatherService weatherService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Weather>> findAllWeathers(){
		return new ResponseEntity<List<Weather>>(weatherService.findAll(), HttpStatus.OK);
	}
}
