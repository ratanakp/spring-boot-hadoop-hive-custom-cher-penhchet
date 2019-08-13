package kr.co.cbnu.hive.hadoop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class TestConnection {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Configuration conf = new Configuration();
		FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.131.145:9000"), conf);
		if (fileSystem instanceof DistributedFileSystem) {
			System.out.println("HDFS is the underlying filesystem");
		} else {
			System.out.println("Other type of file system " + fileSystem.getClass());
		}
	}
}
