package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.StringTokenizer;

import adminCalculate.CalcBean;
import adminSales.SaleInfoBean;
import adminStats.StatsBean;
import useInfo.SearchBean;
import useInfo.UseBean;
import userInfoView.userInfoBean;

public class DBManager {
	private final static String DRIVER_NAME	= "com.mysql.jdbc.Driver";
	private final static String DB_URL			= "jdbc:mysql://localhost:3306/barogo?useUnicode=true&characterEncoding=UTF-8";
	private static Connection db_conn		= null;
	public static Statement stmt			= null;
	public static ResultSet result		= null;
	
	UseBean useBean			= new UseBean();
	public static void mysqlConnection() {
		try {
			Class.forName(DRIVER_NAME);
			db_conn = DriverManager.getConnection(DB_URL, "love", "love99");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void makeStatement() {
		try{
			stmt = db_conn.createStatement();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void search_temp(String a_strName)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strInsert = "insert into searchtemp value('" + a_strName + "');";
			String strUpdate = "update searchtemp set id='"+a_strName+"';";
			String strResult = search_temp_print();
			if(strResult == null)
			{
				stmt.executeUpdate(strInsert);
			} else {
				stmt.executeUpdate(strUpdate);
			}
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� search_temp() ����");
			e.printStackTrace();
		}
	}
	
	public String search_temp_print()
	{
		mysqlConnection();
		makeStatement();
		String strResult = null;
		try {
			String strQuery = "select * from searchtemp";
			result = stmt.executeQuery(strQuery);
			
			if(result.next())
			{
				strResult = result.getString(1);
			}
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� search_temp_print() ����");
			e.printStackTrace();
		}
		return strResult;
	}
	
	/*
	 *  ���̺� �信�� ������ ����� ���� �ð�(remiantime)�� �߰��ϴ� �޼ҵ�
	 */
	public void user_add_time(String a_strID, int a_iAddTime)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "select remaintime from user where id = '" + a_strID +"';";
			result = stmt.executeQuery(strQuery);
			
			if(result.next()) 
			{
				// ���� �ð��� ������
				String strRemainTime = result.getString(1);
				String strUpdate = null;
				if(strRemainTime == null || strRemainTime.length() == 0)
				{
					strUpdate = String.valueOf(a_iAddTime) + ":" + "00";
				} 
				else 
				{
					StringTokenizer strToken = new StringTokenizer(strRemainTime, ":");
					String[] strSubMsg = new String[5];
					
					int i = 0;
					while(strToken.hasMoreElements())
					{
						strSubMsg[i] = strToken.nextToken();
						i++;
					}
					
					int iTempHour = Integer.parseInt(strSubMsg[0]) + a_iAddTime;
					String strHour = String.valueOf(iTempHour);
					strUpdate = strHour + ":" + strSubMsg[1];
				}
				user_remaintime_update(strUpdate,a_strID);
				
			}
		} catch (Exception e) {
			System.out.println("DBManagerŬ���� user_add_time() ����");
			e.printStackTrace();
		}
	}
	
	// ���̺�信�� ������ �ְ� ��������
	
	
	public static String temp_id_print()
	{
		mysqlConnection();
		makeStatement();
		String strResult = null;
		try {
			String strQuery = "select id from timetemp;";
			result =  stmt.executeQuery(strQuery);
			while(result.next()) 
			{
				strResult = result.getString(1);
			}
			
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� temp_id_print() ����");
			e.printStackTrace();
		}
		return strResult;
	}
	
	public int temp_time_print(String a_strID)
	{
		mysqlConnection();
		makeStatement();
		int iResult = 0;
		try {
			String strQuery = "select addtime from timetemp where id='"+ a_strID + "';";
			result =  stmt.executeQuery(strQuery);
			while(result.next()) 
			{
				iResult = result.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� temp_time_print() ����");
			e.printStackTrace();
		}
		return iResult;
	}
	
	public void temp_delete()
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "delete from timetemp;";
			String strQuery2 = "delete from searchtemp;";
			stmt.executeUpdate(strQuery);
			stmt.executeUpdate(strQuery2);
			
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� temp_delete() ����");
			e.printStackTrace();
		}
	}
	
	public void time_temp_insert(int a_iAddTime)
	{
		mysqlConnection();
		makeStatement();
		try {
			System.out.println("time_temp_insert = " + a_iAddTime);
			String strQuery = "update timetemp set addtime="+ a_iAddTime +";";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� time_temp_insert() ����");
			e.printStackTrace();
		}
	}
	
	// �����ð� ������Ʈ
	public void user_remaintime_update(String a_strUpdate, String a_strID)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "update user set remaintime='" + a_strUpdate + "' where id='" + a_strID +"';";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� user_remaintime_update() ����");
			e.printStackTrace();
		}
	}
	
	public void user_passwd_change(String a_strPw)
	{
		mysqlConnection();
		makeStatement();
		String strResult = temp_id_print();
		try {
			String strQuery = "update user set pw='" + a_strPw + "' where id='" + strResult +"';";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� user_passwd_change() ����");
			e.printStackTrace();
		}
	}
	//db.membership_insert(userName, userbirtday, bUserSex, userID, userPW, userEmail);
	public void membership_insert(String a_strUserName, String a_strUserBirtday, boolean a_bUserSex, 
					String a_strUserID, String a_strUserPW, String a_UserEmail) 
	{
		mysqlConnection();
		makeStatement();
		try {
			
			String strBirth1, strBirth2, strBirth3;
			
			// ���� �ð� �߿� '��' �κ��� �߶� ���������� �ٲٰ� �߰��� �ð��� ����
			strBirth1 = a_strUserBirtday.substring(0, 2);
			strBirth2 = a_strUserBirtday.substring(2, 4);
			strBirth3 = a_strUserBirtday.substring(4, 6);
			
			String strQuery = "insert into user(name, id, pw, sex, email, birth1, birth2, birth3)" +
							" value('" + a_strUserName +"', '" + a_strUserID + "', '" + a_strUserPW + "', " +
							a_bUserSex + ", '"+ a_UserEmail + "', '" + strBirth1 + "', '" + strBirth2 + "', '" + strBirth3 + "');";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� membership_insert() ����");
			e.printStackTrace();
		}
	}
	
	public String login_Calculate(String strID,String strPW)
	{
		mysqlConnection();
		makeStatement();
		String strRes = null;
		try {
				String strQuery = "select name from admins where id='" + strID + "' and pw='" + strPW + "';";
				result = stmt.executeQuery(strQuery);
				System.out.println(strQuery);
			if(result.next()) {
				System.out.println("�α��οϷ�");
				strRes = result.getString(1);
			} else {
				System.out.println("�α��ν���");
			}
		} catch(Exception e) {
			System.out.println("login_Calculate() ���� ");
			e.printStackTrace();
		}
		return strRes;
	}
	
	public CalcBean salse_total(LocalDate date)
	{
		mysqlConnection();
		makeStatement();
		CalcBean calcBean = new CalcBean();
		ResultSet res;
		
		try {
			
			// �ð� �� �� ��ǰ �ǰ� ���� �����ϴ� select ��
			String strQuery		= "select count(date), sum(price) from timesales where date='"+ date + "'" +
									"union " +
									"select count(date), sum(salesPrice) from receipt where date='"+ date +"';";
			String strQuery2	= "select count(salesPrice), sum(salesPrice) from receipt where date='" + date + "' and salesType=1;";
			
			result 	= stmt.executeQuery(strQuery);
			
			int iCount = 0;
			int iSum = 0;
			while(result.next())
			{
				iCount	+= 	result.getInt(1);
				iSum 	+=	result.getInt(2);
			}
			
			calcBean.setiCount(iCount);
			calcBean.setiSales(iSum);
			
			res		= stmt.executeQuery(strQuery2);

			if(res.next())
			{
				calcBean.setiReturnCount(res.getInt(1));
				calcBean.setiReturnSales(res.getInt(2));
			}
	} catch(Exception e) {
		e.printStackTrace();
	}
		return calcBean;
	}
	
	public void insert_stats_data(LocalDate date, int a_iSales, int a_iProfit)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strInsert = "insert into stats value('" + date + "'," + a_iSales +", " + a_iProfit + ");";
			
			stmt.executeUpdate(strInsert);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� insert_stats_data() ����");
			e.printStackTrace();
		}
	}
	
	public boolean stats_overlap_chk(LocalDate a_date)
	{
		mysqlConnection();
		makeStatement();
		String strDate = null;
		boolean isResult = false;
		try {
			String strQuery =	"select date "
							+	"from stats "
							+ 	"where date='" + a_date + "';";
			System.out.println(strQuery);
			result = stmt.executeQuery(strQuery);
			
			if(result.next())
			{
				strDate = result.getString(1);
				if(strDate != null)
				{
					isResult = true;
					return isResult;
				}
				
			}
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� stats_overlap_chk() ����");
			e.printStackTrace();
		}
		return isResult;
	}
	public boolean paycheck_query(String strID, String strPW) {
		mysqlConnection();
		makeStatement();
		String p = null;
		try {
			String strQuery = "select remaintime from user where id='" + strID + "' and pw='" + strPW + "';";
			result = stmt.executeQuery(strQuery);

			while (result.next()) {
				p = result.getString("remaintime");
			}

			if (p == null || p.length() == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<SaleInfoBean> sale_query(String category) {
		SaleInfoBean SaleInfoBean;
		ArrayList<SaleInfoBean> Bean = new ArrayList<SaleInfoBean>();
		String strQuery = "select name,price from product where category='" + category + "';";
		try {
			mysqlConnection();
			makeStatement();
			result = stmt.executeQuery(strQuery);

			while (result.next()) {
				SaleInfoBean = new SaleInfoBean();
				SaleInfoBean.setProductName(result.getString("name"));
				SaleInfoBean.setPrice(result.getInt("price"));
				Bean.add(SaleInfoBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Bean;
	}

	public void paymentPlanInsert_query(int paymentPlan, String strID, String strPW) {
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "update user set paymentplan = '" + paymentPlan + "' where id='" + strID + "' and pw='"
					+ strPW + "';";
			stmt.executeUpdate(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int paymentPlanOutput_query(String strID, String strPW) {
		mysqlConnection();
		makeStatement();
		int payPlan = 0;
		try {
			String strQuery = "select paymentPlan from user where id='" + strID + "' and pw='" + strPW + "';";
			result = stmt.executeQuery(strQuery);
			while (result.next()) {
				payPlan = result.getInt("paymentPlan");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payPlan;
	}

	public void userTemp_query(String strID, String strPW) {
		mysqlConnection();
		makeStatement();
		try {
			String strUpdate = "update usertemp set id='" + strID + "',pw='" + strPW + "';";
			String strInsert = "insert into usertemp value('" + strID + "','" + strPW + "');";
			String strResult = userTempPrint_query();
			if (strResult == null) {
				stmt.executeUpdate(strInsert);
			} else {
				stmt.executeUpdate(strUpdate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String userTempPrint_query() {
		mysqlConnection();
		makeStatement();
		String strResult = null;
		try {
			String strQuery = "select id,pw from usertemp;";
			result = stmt.executeQuery(strQuery);
			while (result.next()) {
				strResult = result.getString(1) + "," + result.getString(2);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strResult;
	}

	public userInfoBean actioninit_query(int paymentPlan, String strID, String strPW) {
		mysqlConnection();
		makeStatement();
		userInfoBean beanUserInfo = new userInfoBean();
		try {
			String strQuery = "select id,firstmoney,remaintime from user where id='" + strID + "' and pw='" + strPW + "';";
			String strQuery2 = "select id from user where id='" + strID + "' and pw='" + strPW + "';";
			if (paymentPlan == 0) {
				result = stmt.executeQuery(strQuery);
				while (result.next()) {
					beanUserInfo.setUserID(result.getString("id"));
					beanUserInfo.setFirstMoney(result.getInt("firstmoney"));
					beanUserInfo.setstrRemainTime(result.getString("remaintime"));
				}
			} else if(paymentPlan == 1) {
				result = stmt.executeQuery(strQuery2);
				while (result.next()) {
					beanUserInfo.setUserID(result.getString("id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return beanUserInfo;
	}
	
	public ArrayList<StatsBean> stats_query(String year, String month)
	{
		mysqlConnection();
		makeStatement();
		
		StatsBean statsBean = new StatsBean();
		ArrayList<StatsBean> statsList = new ArrayList<StatsBean>();
		String day = null;
		int i = 0;
		
		try {
			String strQuery = "select * from stats";
			result = stmt.executeQuery(strQuery);
			while(result.next())
			{
				StringTokenizer st = new StringTokenizer(result.getString("date"), "-");

				day = st.nextToken();
				statsBean.setDay(day);
				statsBean.setSales(result.getInt("sales"));
				statsBean.setProfit(result.getInt("profit"));
				statsList.add(i,statsBean);
				i++;
				System.out.println(" statsBean.getDay() " +statsBean.getDay());
				System.out.println(" statsBean.getSales() " +statsBean.getSales());
				System.out.println(" statsBean.getProfit() " +statsBean.getProfit());
				System.out.println(" statsList " +statsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statsList;
	}
	
	public void user_data_save(String a_strID, String a_strRemainTime)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strUpdate = "update user set remaintime='" + a_strRemainTime + "' where id = '" + a_strID + "';";
			System.out.println(strUpdate);
			stmt.executeUpdate(strUpdate);
		}catch(Exception e) {
			System.out.println("DBManagerŬ���� user_data_save() ����");
			e.printStackTrace();
		}
	}
	
}