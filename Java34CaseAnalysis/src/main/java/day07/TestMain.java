package day07;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class TestMain {
	static Map<String,String> map = null;
	static{
		map = loadData();
	}
public static void main(String[] args) {
	try (
			BufferedReader br = new BufferedReader(new FileReader("D:\\Date\\bikes.log"));
			){
		String line =null;
		while((line=br.readLine())!=null){
			//System.out.println(line);
			BikeBean bean = JSON.parseObject(line, BikeBean.class);
			//获取经纬度以便查找位置
			double latitude = bean.getLatitude();
			double longitude = bean.getLongitude();
			//查找位置
			String addr = findAddr(latitude,longitude);
			//做相应的业务处理
			System.out.println(addr);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
}

/**
 * 查找位置
 * 1.从本地仓库查找
 * 2.从网络查找
 * @param latitude
 * @param longitude
 * @return
 */
private static String findAddr(double latitude, double longitude) {
	String addr = findAddrByLocal(latitude,longitude);
	if(addr==null){
		return addr = findAddrByNet(latitude,longitude);
	}
	return addr;
}

/**
 * 从网络查找位置
 * @param latitude
 * @param longitude
 * @return
 */
private static String findAddrByNet(double latitude, double longitude) {
	String jsonByNet = Utiles.getJsonByNet(latitude, longitude);	
	ResultBean resultBean = JSON.parseObject(jsonByNet, ResultBean.class);
	String geoHash = Utiles.getGeoHash(latitude, longitude);
	AddrList addrList = resultBean.getAddrList()[0];
	String province_city = addrList.getAdmName();
	String[] split = province_city.split(",");
	if(split.length>2){
		String province = split[0];
		String city = split[1];
		String district = split[2];
		String ret = geoHash+"\t"+province+"\t"+city+"\t"+district+"\t"+addrList.getAdmName();
		try (
				BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Date\\repostory.tsv",true));
				){
			bw.write(ret);
			bw.newLine();
			bw.flush();
			//System.err.println(ret);
			//更新本地仓库的数据
			map.put(geoHash, ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	return null;
}

/**
 * 从本地查找
 * @param latitude
 * @param longitude
 * @return
 */
private static String findAddrByLocal(double latitude, double longitude) {
	String geoHash = Utiles.getGeoHash(latitude, longitude);
	String addr = map.get(geoHash);
	return addr;
}
/**
 * 把本地的仓库的数据(tsv)加载过来，形成Map数据
 * @return
 */
public static Map<String,String> loadData(){
	HashMap<String, String> map = new HashMap<String, String>();
	try(
			BufferedReader br = new BufferedReader(new FileReader("D:\\Date\\repostory.tsv"));
			) {
		String line = null;
		while((line=br.readLine())!=null){
			//System.out.println(line);
			String geoHashCode = line.split("\t")[0];
			map.put(geoHashCode, line);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
	
	
}
}
