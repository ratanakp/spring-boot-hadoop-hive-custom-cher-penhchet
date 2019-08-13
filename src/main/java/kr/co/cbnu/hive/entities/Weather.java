package kr.co.cbnu.hive.entities;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class Weather {

    private String wban;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datetime;
    private String stationType;
    private String skyCondition;
    private int year;
    private String month;

    public Weather() {
    }

    public Weather(String data) throws Exception {

        super();
        List infoList = new ArrayList();
        StringTokenizer tokenizer = new StringTokenizer(data, ",");
        while (tokenizer.hasMoreTokens()) {
            infoList.add(tokenizer.nextToken());
        }

        Date date1 = new Date();
        if (infoList.get(0) != null)
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(infoList.get(0).toString());
        this.setDatetime(date1);
        this.setStationType(infoList.get(2).toString());
    }


	/*public Employee(String information) {
		super();
		List alist = new ArrayList();
		StringTokenizer st = new StringTokenizer(information, "|");
		while(st.hasMoreTokens()){
			alist.add(st.nextToken());
		}
		this.setName(alist.get(0));
		this.setAge(Integer.parseInt(alist.get(1)));
		this.setBirthdate(alist.get(3));
		this.setStatus(alist.get(2));
		this.setLocation(alist.get(4));
	}
*/

    public String getWban() {
        return wban;
    }

    public void setWban(String wban) {
        this.wban = wban;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getSkyCondition() {
        return skyCondition;
    }

    public void setSkyCondition(String skyCondition) {
        this.skyCondition = skyCondition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }


    @Override
    public String toString() {
        return "Weather{" +
                "wban='" + wban + '\'' +
                ", datetime='" + datetime + '\'' +
                ", stationType='" + stationType + '\'' +
                ", skyCondition='" + skyCondition + '\'' +
                ", year=" + year +
                ", month='" + month + '\'' +
                '}';
    }
}
