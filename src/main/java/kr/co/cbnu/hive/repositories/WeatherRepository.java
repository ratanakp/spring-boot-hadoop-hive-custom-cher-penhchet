package kr.co.cbnu.hive.repositories;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import kr.co.cbnu.hive.entities.Weather;

@Repository
public interface WeatherRepository {

	@Select("SELECT wban, datetime, stationtype, stationtype, year, month FROM weather WHERE month ='02' LIMIT 1000")
	@Results({
		@Result(property="wban", column="wban"),
		@Result(property="datetime", column="datetime"),
		@Result(property="stationType", column="stationtype"),
		@Result(property="skyCondition", column="stationtype"),
		@Result(property="year", column="year"),
		@Result(property="month", column="month")
	})
	List<Weather> findAll();



	@Insert({"<script>" ,
			"insert into tb_weather(datetime) " ,
			"values" ,
			"<foreach collection='weathers' item='weather' " ,
			"index='weather_index' separator=',' >" ,
			"(" ,
			"#{weather.datetime}",
			")",
			"</foreach>" ,
			"</script>"})
	boolean saveBatch(@Param("weathers") List<Weather> weathers);


}
