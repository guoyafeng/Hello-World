package day07;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import ch.hsr.geohash.GeoHash;

public class Utiles {
/**
 * 经纬度转换为hash值	
 * @param latitude
 * @param longitude
 * @return
 */
public static String getGeoHash(double latitude,double longitude){
	String base32 = GeoHash.withCharacterPrecision(latitude, longitude, 8).toBase32();
	return base32;
	}

/**
 * 通过经纬度信息获取json信息
 * @param latitude
 * @param longitude
 * @return
 */
public static String getJsonByNet(double latitude,double longitude){
	HttpClient client = new HttpClient();
	String uri = "http://gc.ditu.aliyun.com/regeocoding?l=" + latitude + "," + longitude+"&type=010";
	try {
		HttpMethod method = new GetMethod(uri);
		client.executeMethod(method);
		return method.getResponseBodyAsString();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return null;
	
}
}
