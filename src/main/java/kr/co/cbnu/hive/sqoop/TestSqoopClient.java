package kr.co.cbnu.hive.sqoop;


import org.apache.sqoop.client.SqoopClient;

public class TestSqoopClient {


    public void test() {

        String url = "http://localhost:12000/sqoop/";
        SqoopClient client = new SqoopClient(url);


    }

}
