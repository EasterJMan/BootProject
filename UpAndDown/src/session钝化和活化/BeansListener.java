package session钝化和活化;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

public class BeansListener implements HttpSessionActivationListener,Serializable {
	private Integer num;
	private String user;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	// 刚刚进行了活化
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("session被活化了");
	}

	// 监听时刻:即将钝化之前
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("session即将被钝化");
	}

}
