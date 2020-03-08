package userInfoView;

import org.apache.ibatis.type.Alias;

@Alias("userInfo")
public class UserInfoVO {
	private String userID;
	private int firstMoney;
	private String strRemainTime;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(int firstMoney) {
		this.firstMoney = firstMoney;
	}
	
	public String getstrRemainTime()
	{
		return strRemainTime;
	}
	
	public void setstrRemainTime(String strRemainTime)
	{
		this.strRemainTime = strRemainTime;
	}
}