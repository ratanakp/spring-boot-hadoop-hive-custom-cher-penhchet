package kr.co.cbnu.hive.hadoop;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class ReadFileDataToConsole {

	public static void main(String[] args) throws IOException, URISyntaxException {
		System.setProperty("hadoop.home.dir", "C:/winutils/hadoop-2.7.1");
		// 1. Get the instance of Configuration
		Configuration configuration = new Configuration();
		// 2. URI of the file to be read
		URI uri = new URI("hdfs://192.168.131.145:9000/user/hadoop/Hadoop_File1.txt");
		// 3. Get the instance of the HDFS
		FileSystem hdfs = FileSystem.get(uri, configuration);
		// 4. A reference to hold the InputStream
		InputStream inputStream = null;
		try {
			// 5. Prepare the Path, i.e similar to File class in Java, Path
			// represents file in HDFS
			Path path = new Path(uri);
			// 6. Open a Input Stream to read the data from HDFS
			inputStream = hdfs.open(path);
			// 7. Use the IOUtils to flush the data from the file to console
			IOUtils.copyBytes(inputStream, System.out, 4096, false);
		} finally {
			// 8. Close the InputStream once the data is read
			IOUtils.closeStream(inputStream);
		}
	}
}
