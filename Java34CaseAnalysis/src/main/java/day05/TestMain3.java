package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

/**
 * 3.根据给的手机号段运营商规则，计算出总流量最高的运营商Top3
 * @author Administrator
 *
 */
public class TestMain3 {
public static void main(String[] args) {
	Map<String, String> map = getMap();
	Map<String,Long> ispMap = new HashMap<>();
	//1.读取数据
	try (
			BufferedReader br = new BufferedReader(new FileReader("E:\\小牛学堂\\Javase34\\java案例分析\\day04-电影排行\\day05-流量统计\\http.log"));
			){
		String line;
		while((line=br.readLine())!=null){
			String pNumber = line.split("\t")[0].substring(0,7);
			String[] split = line.split("\t")[1].split(" ");
			long up = Long.parseLong(split[1]);
			long down = Long.parseLong(split[2]);
			String isp = map.get(pNumber);
			Long sum = ispMap.getOrDefault(isp, 0L);
			sum=sum+up+down;
			//将数据存入到pMap中
			ispMap.put(isp, sum);
		}
		//遍历pMap，并对其进行按照sum的进行降序排序
				Set<Entry<String,Long>> entrySet = ispMap.entrySet();
				List<Entry<String, Long>> list = new ArrayList<Entry<String,Long>>(entrySet);
				//按照sum对pMap进行降序排序
				SortUtiles.sortByLong(list);
				//System.out.println(list);
				//保存输出结果
				for (int i=0;i<3;i++) {
					System.out.println("Top"+(i+1)+":"+list.get(i));
				}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
}
/**
 * 读取phone数据，存入到map中Map<手机号，省份>
 * @return
 */
private static Map<String,String> getMap() {
	Map<String,String> map = new HashMap<>();
	try (
			BufferedReader br = new BufferedReader(new FileReader("E:\\小牛学堂\\Javase34\\java案例分析\\day04-电影排行\\day05-流量统计\\phone.txt"));
			){
		//2.相应的切分数据手机号、流量的数据
		String line;
		while((line=br.readLine())!=null){
			//System.out.println(line);
			String[] split = line.split("\\s");
			String pNum = split[1];
			String isp = split[4];
			//System.out.println(isp);
			map.put(pNum, isp);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return map;
}
}
