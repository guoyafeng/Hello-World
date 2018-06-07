package day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * 把现有的位置信息转换为本地仓库
 * @author Administrator
 *
 */
public class Repostory {
public static void main(String[] args) {
	try {
		BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Date\\repostory.tsv"));
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Date\\bj-poi-1.csv"), "gbk"));
		String line = null;
		while((line=br.readLine())!=null){
			try {
				System.out.println(line);
				String[] split = line.split(",");
				String addr = split[0];
				Double localx = Double.parseDouble(split[2]);
				Double localy = Double.parseDouble(split[3]);
				String city = split[5];
				String district = split[7];
				String geoHashCode = Utiles.getGeoHash(localy, localx);
				String ret = geoHashCode+"\t"+"北京市"+"\t"+city+"\t"+district+"\t"+addr;
				bw.write(ret);
				bw.newLine();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		bw.flush();
		bw.close();
		br.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
