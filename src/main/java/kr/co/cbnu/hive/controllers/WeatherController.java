package kr.co.cbnu.hive.controllers;

import kr.co.cbnu.hive.entities.Weather;
import kr.co.cbnu.hive.services.WeatherService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {

    private WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/write/{data}")
    public ResponseEntity<String> writeDataToDB(@PathVariable("data") Long data) throws Exception {

        System.setProperty("hadoop.home.dir", "/usr/local/hadoop-2.9.2");
        Configuration configuration = new Configuration();
        URI uri = new URI("hdfs://210.115.182.181:9000/sqoop-mysql-import/weather/part-m-00000");
        FileSystem hdfs = FileSystem.get(uri, configuration);
        InputStream inputStream = null;
        try {
            Path path = new Path(uri);
            inputStream = hdfs.open(path, 4096);

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line = reader.readLine();

            List<Weather> weathers = new ArrayList<>();

            int count = 0;
            while (count <= data) {
                line = reader.readLine();
                weathers.add(new Weather(line));
                count++;
            }

            if (this.weatherService.saveBatch(weathers)) {
                return ResponseEntity.ok("Looking good!!!");
            }

        } finally {
            IOUtils.closeStream(inputStream);
        }

        return new ResponseEntity<>("Ort kert te", HttpStatus.BAD_REQUEST);


    }
}
