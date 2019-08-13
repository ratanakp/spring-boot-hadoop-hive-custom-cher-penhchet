package kr.co.cbnu.hive.hadoop;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class CopyFileToDHDFS {

	public static void main(String[] args) throws IOException, URISyntaxException {
		System.setProperty("hadoop.home.dir", "C:/winutils/hadoop-2.7.1");
		// 1. Get the instance of COnfiguration
		Configuration configuration = new Configuration();
		// 2. Create an InputStream to read the data from local file
		InputStream inputStream = new BufferedInputStream(new FileInputStream(
				"C:/Users/PENHCHET/Downloads/Compressed/Pluralsight.Introduction.To.Qt.A.C.Plus.Plus.Cross.Platform.Application.Framework_p30download.com.rar"));
		// 3. Get the HDFS instance
		FileSystem hdfs = FileSystem.get(new URI("hdfs://192.168.131.145:9000"), configuration);
		// 4. Open a OutputStream to write the data, this can be obtained from the FileSytem
		OutputStream outputStream = hdfs.create(
				new Path(
						"hdfs://192.168.131.145:9000/user/hadoop/Pluralsight.Introduction.To.Qt.A.C.Plus.Plus.Cross.Platform.Application.Framework_p30download.com.rar"),
				new Progressable() {
					@Override
					public void progress() {
						System.out.println("....");
					}
				});
		try {
			IOUtils.copyBytes(inputStream, outputStream, 4096, false);
		} finally {
			IOUtils.closeStream(inputStream);
			IOUtils.closeStream(outputStream);
		}
	}
}
