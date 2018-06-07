package day07;

public class AddrList {
	//"addrList":[{"type":"poi","status":1,"name":"中发社区","id":"ANB01C30KGB8",
		//"admCode":"230103","admName":"黑龙江省,哈尔滨市,南岗区,","addr":"先锋路591号",
		//"nearestPoint":[126.67066,45.76838],"distance":92.477}]
	private String name;
	private String admName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	@Override
	public String toString() {
		return "AddrList [name=" + name + ", admName=" + admName + "]";
	}
	
}
