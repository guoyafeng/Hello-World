package day07;

import java.util.Arrays;

public class ResultBean {
	//{"queryLocation":[45.767525,126.67032],
		//"addrList":[{"type":"poi","status":1,"name":"中发社区","id":"ANB01C30KGB8",
		//"admCode":"230103","admName":"黑龙江省,哈尔滨市,南岗区,","addr":"先锋路591号",
		//"nearestPoint":[126.67066,45.76838],"distance":92.477}]}	
	private double[] queryLocation;
	private AddrList[] addrList;
	public double[] getQueryLocation() {
		return queryLocation;
	}
	public void setQueryLocation(double[] queryLocation) {
		this.queryLocation = queryLocation;
	}
	public AddrList[] getAddrList() {
		return addrList;
	}
	public void setAddrList(AddrList[] addrList) {
		this.addrList = addrList;
	}
	@Override
	public String toString() {
		return "ResultBean [queryLocation=" + Arrays.toString(queryLocation)
				+ ", addrList=" + Arrays.toString(addrList) + "]";
	}
	
}
