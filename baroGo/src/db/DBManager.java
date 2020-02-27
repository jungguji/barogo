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
import useInfo.UseBean;
import userInfoView.userInfoBean;

/**
 * @author 지중구, 강지은
 *		프로그램에서 사용되는 모든 쿼리문이 실행되는 클래스
 */

public class DBManager {
	final static String strDriverName	= "com.mysql.cj.jdbc.Driver";
	String strDbURL			= "jdbc:mysql://localhost:3306/barogo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
	Connection db_conn		= null;
	Statement stmt			= null;
	ResultSet result		= null;
	
	UseBean useBean			= new UseBean();
	int count = 0;
	
	public void mysqlConnection() {
	    System.out.println("mysqlConnection()");
		try {
			Class.forName(strDriverName);
			db_conn = DriverManager.getConnection(strDbURL, "root", "qwe123");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void makeStatement() {
	    System.out.println("makeStatement()");
		try{
			stmt = db_conn.createStatement();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login_query(String strID,String strPW, boolean isAdmin)
	{
		mysqlConnection();
		makeStatement();
		try {
			
			String strQuery = "select id from admins where id='" + strID + "' and pw='" + strPW + "';";
			String strQuery2 = "select id from user where id='" + strID + "' and pw='" + strPW + "';";
			
			if(isAdmin) {
				result = stmt.executeQuery(strQuery);
			} else {
				result = stmt.executeQuery(strQuery2);
			}
			
			if(result.next()) {
				System.out.println("로그인완료");
				return true;
			} else {
				System.out.println("로그인실패");
				return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
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
			System.out.println("DBManager클래스 search_temp() 에러");
			e.printStackTrace();
		}
	}
	
	public String search_temp_print()
	{
//		mysqlConnection();
//		makeStatement();
//		String strResult = null;
//		try {
//			String strQuery = "select * from searchtemp";
//			result = stmt.executeQuery(strQuery);
//			
//			if(result.next()) {
//				strResult = result.getString(1);
//			}
//		}catch(Exception e) {
//			System.out.println("DBManager클래스 search_temp_print() 에러");
//			e.printStackTrace();
//		}
		return "jgji";
	}
	
	
	// 테이블뷰에서 선택한 애가 누구인지
	public void select_user(String id)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strUpdate = "update timetemp set id='"+id+"';";
			String strInsert = "insert into timetemp value('" + id + "',0);";
			String strResult = getTempId();
			if(strResult == null) {
				stmt.executeUpdate(strInsert);
			} else {
				stmt.executeUpdate(strUpdate);
			}
		}catch(Exception e) {
			System.out.println("DBManager클래스 test() 에러");
			e.printStackTrace();
		}
	}
	
	public String getTempId() {
		mysqlConnection();
		makeStatement();
//		String strResult = null;
//		try {
//			String strQuery = "select id from timetemp;";
//			result =  stmt.executeQuery(strQuery);
//			if (result.next())  {
//				strResult = result.getString(1);
//			}
//			
//		}catch(Exception e) {
//			System.out.println("DBManager클래스 temp_id_print() 에러");
//			e.printStackTrace();
//		}
		
		return "jgji";
	}
	
	public int temp_time_print(String a_strID)
	{
		mysqlConnection();
		makeStatement();
		int iResult = 0;
		try {
			String strQuery = "select add_time from timetemp where id='"+ a_strID + "';";
			result =  stmt.executeQuery(strQuery);
			while(result.next()) 
			{
				iResult = result.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("DBManager클래스 temp_time_print() 에러");
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
			System.out.println("DBManager클래스 temp_delete() 에러");
			e.printStackTrace();
		}
	}
	
	public void time_temp_insert(int a_iAddTime)
	{
		mysqlConnection();
		makeStatement();
		try {
			System.out.println("time_temp_insert = " + a_iAddTime);
			String strQuery = "update timetemp set add_time="+ a_iAddTime +";";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManager클래스 time_temp_insert() 에러");
			e.printStackTrace();
		}
	}
	
	// 남은시간 업데이트
	public void updateUserRemaintimeById(String time, String userId)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "update user set remaintime='" + time + "' where id='" + userId +"';";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManager클래스 user_remaintime_update() 에러");
			e.printStackTrace();
		}
	}
	
	// 메인뷰에서 남은시간 업데이트
	public void view_user_remaintime_update(String a_strUpdate, String a_PCNum)
	{
		mysqlConnection();
		makeStatement();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@22   :  " + a_strUpdate );
		try {
			String strQuery = "update user set remaintime='" + a_strUpdate + "' where pcnumber='" + a_PCNum +"';";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManager클래스 user_remaintime_update() 에러");
			e.printStackTrace();
		}
	}
	
	public void user_passwd_change(String a_strPw)
	{
		mysqlConnection();
		makeStatement();
		String strResult = getTempId();
		try {
			String strQuery = "update user set pw='" + a_strPw + "' where id='" + strResult +"';";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManager클래스 user_passwd_change() 에러");
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
			
			// 생년월일을 끊어서 저장
			strBirth1 = a_strUserBirtday.substring(0, 2);
			strBirth2 = a_strUserBirtday.substring(2, 4);
			strBirth3 = a_strUserBirtday.substring(4, 6);
			
			String strQuery = "insert into user(pcnumber,name, id, pw, sex, email, birth1, birth2, birth3)" +
							" value(0, '" + a_strUserName +"', '" + a_strUserID + "', '" + a_strUserPW + "', " +
							a_bUserSex + ", '"+ a_UserEmail + "', '" + strBirth1 + "', '" + strBirth2 + "', '" + strBirth3 + "');";
			stmt.executeUpdate(strQuery);
		}catch(Exception e) {
			System.out.println("DBManager클래스 membership_insert() 에러");
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
				System.out.println("로그인완료");
				strRes = result.getString(1);
			} else {
				System.out.println("로그인실패");
			}
		} catch(Exception e) {
			System.out.println("login_Calculate() 오류 ");
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
			
			// 시간 판 값 상품 판값 수량 선택하는 select 문
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
			System.out.println("DBManager클래스 insert_stats_data() 에러");
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
			System.out.println("DBManager클래스 stats_overlap_chk() 에러");
			e.printStackTrace();
		}
		return isResult;
	}
	public String findRemainTime(String strID) {
	    String remainTime = "";
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "select remaintime from user where id='" + strID + "';";
			result = stmt.executeQuery(strQuery);

			if (result.next()) {
			    remainTime = result.getString(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return remainTime;
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
		
		StatsBean statsBean;
		ArrayList<StatsBean> statsList = new ArrayList<StatsBean>();
		
		try
		{
			String strQuery = "select * from stats where date like '" + year + "-" + month + "%' order by date asc";
			result = stmt.executeQuery(strQuery);
			
			while (result.next())
			{
				String date = result.getString("date");
				String day = date.substring(date.length() - 2, date.length());

				statsBean = new StatsBean();

				statsBean.setDay(day);
				statsBean.setSales(result.getInt("sales"));
				statsBean.setProfit(result.getInt("profit"));

				statsList.add(statsBean);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return statsList;
	}
	
	public ArrayList<StatsBean> stats_query(String year)
	{
		mysqlConnection();
		makeStatement();

		StatsBean statsBean;
		ArrayList<StatsBean> statsList = new ArrayList<StatsBean>();

		int Msales[] = new int[12];
		int Mprofit[] = new int[12];

		try 
		{
			String strQuery = "select * from stats where date like '" + year + "%' order by date asc";
			result = stmt.executeQuery(strQuery);

			while (result.next()) 
			{
				String date = result.getString("date");
				String Month = date.substring(date.length() - 5, date.length() - 3);

				switch (Month) 
				{
				case "01":
					Msales[0] += result.getInt("sales");
					Mprofit[0] += result.getInt("profit");
					break;
				case "02":
					Msales[1] += result.getInt("sales");
					Mprofit[1] += result.getInt("profit");
					break;
				case "03":
					Msales[2] += result.getInt("sales");
					Mprofit[2] += result.getInt("profit");
					break;
				case "04":
					Msales[3] += result.getInt("sales");
					Mprofit[3] += result.getInt("profit");
					break;
				case "05":
					Msales[4] += result.getInt("sales");
					Mprofit[4] += result.getInt("profit");
					break;
				case "06":
					Msales[5] += result.getInt("sales");
					Mprofit[5] += result.getInt("profit");
					break;
				case "07":
					Msales[6] += result.getInt("sales");
					Mprofit[6] += result.getInt("profit");
					break;
				case "08":
					Msales[7] += result.getInt("sales");
					Mprofit[7] += result.getInt("profit");
					break;
				case "09":
					Msales[8] += result.getInt("sales");
					Mprofit[8] += result.getInt("profit");
					break;
				case "10":
					Msales[9] += result.getInt("sales");
					Mprofit[9] += result.getInt("profit");
					break;
				case "11":
					Msales[10] += result.getInt("sales");
					Mprofit[10] += result.getInt("profit");
					break;
				case "12":
					Msales[11] += result.getInt("sales");
					Mprofit[11] += result.getInt("profit");
					break;
				}
			}
			for (int i = 0; i < Msales.length; i++) 
			{
				String Month = "" + (i + 1);

				statsBean = new StatsBean();

				statsBean.setDay(Month);
				statsBean.setSales(Msales[i]);
				statsBean.setProfit(Mprofit[i]);

				statsList.add(statsBean);
			}
		} 
		catch (Exception e) 
		{
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
			System.out.println("DBManager클래스 user_data_save() 에러");
			e.printStackTrace();
		}
	}
	
	public void user_pcnum_reset(String a_strID)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strUpdate = "update user set pcnumber=0 where id = '" + a_strID + "';";
			System.out.println(strUpdate);
			stmt.executeUpdate(strUpdate);
		}catch(Exception e) {
			System.out.println("DBManager클래스 user_pcnum_reset() 에러");
			e.printStackTrace();
		}
	}
	
	
	// 메인뷰에서 할때
	public void view_user_add_time(String a_PCNum, int a_iAddTime)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "select remaintime from user where pcnumber = '" + a_PCNum +"';";
			result = stmt.executeQuery(strQuery);
			
			if(result.next()) 
			{
				// 남은 시간을 가져옴
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
				view_user_remaintime_update(strUpdate,a_PCNum);
				
			}
		} catch (Exception e) {
			System.out.println("DBManager클래스 view_user_add_time() 에러");
			e.printStackTrace();
		}
	}
	
	public String user_id_return(String a_strPCNum)
	{
		String strID = null;
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "select id from user where pcnumber = '" + a_strPCNum +"';";
			result = stmt.executeQuery(strQuery);
			if(result.next())
			{
				strID = result.getString(1);
			}
		}catch(Exception e)
		{
			System.out.println("DBManager클래스 user_id_return() 에러");
			e.printStackTrace();
		}
		return strID;
	}
	
	public void pcNumber_query(String strID, String strPW, int pcNumber) 
	   {
	      mysqlConnection();
	      makeStatement();
	      
	      ArrayList<Integer> num = new ArrayList<Integer>();
	      
	      try 
	      {
	         String strQuery = "select pcnumber from user;";
	         result = stmt.executeQuery(strQuery);
	         while (result.next()) 
	         {
	            num.add(result.getInt("pcnumber"));
	         }

	         while (true) 
	         {
	            pcNumber = (1 + (int) (Math.random() * 10));
	            if (!num.contains(pcNumber)) 
	            {
	               strQuery = "update user set pcnumber = '" + pcNumber + "' where id='" + strID + "' and pw='" + strPW + "';";
	               stmt.executeUpdate(strQuery);
	               break;
	            }
	         }

	      } 
	      catch (Exception e) 
	      {
	         e.printStackTrace();
	      }
	   }
	   
	   public int pcNumber_print(String strID, String strPW) 
	   {
	      mysqlConnection();
	      makeStatement();
	      
	      int pcNumber = 0;
	      
	      try {
	         String strQuery = "select pcnumber from user where id='" + strID + "' and pw='" + strPW + "';";
	         result = stmt.executeQuery(strQuery);
	         
	         if(result.next())
	         {
	            pcNumber = result.getInt("pcnumber");
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return pcNumber;
	   }
	   
	   
	   public ArrayList<SaleInfoBean> SearchReceipt(String receiptNo, LocalDate date)
		{
			SaleInfoBean saleInfoBean;
			ArrayList<SaleInfoBean> Bean = new ArrayList<SaleInfoBean>();
			
			mysqlConnection();
			makeStatement();
			
			try {
				String strQuery = "select productName,salesCount,price from uploadGoods where "
						+ "date = '" + date + "' and receiptNo = '" + receiptNo + "';";
				result = stmt.executeQuery(strQuery);

				while (result.next())
				{
					saleInfoBean = new SaleInfoBean();
					saleInfoBean.setProductName(result.getString("productName"));
					saleInfoBean.setCount(result.getInt("salesCount"));
					saleInfoBean.setPrice(result.getInt("price"));
					Bean.add(saleInfoBean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Bean;
		}
	   
	   public void ReturnGoods(String receiptNo, LocalDate Date)
		{
			mysqlConnection();
			makeStatement();
			try {
				String strDelete = "delete from uploadGoods where receiptNo = '" + receiptNo + "' and date = '" + Date + "';";
				stmt.executeUpdate(strDelete);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   public void Return_query(String receiptNo, LocalDate Date)
		{
			mysqlConnection();
			makeStatement();
			try {
				String strQuery = "update receipt set salesType='1' where receiptNo = '" + receiptNo + "' and date = '" + Date + "';";
				stmt.executeUpdate(strQuery);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   
	   public ArrayList<SaleInfoBean> Frequencyofsale_query(String year, String month)
		{
			mysqlConnection();
			makeStatement();
			
			SaleInfoBean saleInfoBean;
			ArrayList<SaleInfoBean> Bean = new ArrayList<SaleInfoBean>();
			
			try {
				String strQuery = "select productName,sum(salesCount) as salesCount from uploadGoods where date like '" + year + "-" + month + "%' group by productName order by salesCount desc limit 10;";
				result = stmt.executeQuery(strQuery);
				
				while(result.next())
				{
					saleInfoBean = new SaleInfoBean();
					saleInfoBean.setProductName(result.getString("productName"));
					saleInfoBean.setCount(result.getInt("salesCount"));
					Bean.add(saleInfoBean);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Bean;
		}
	   
	   public ArrayList<SaleInfoBean> Frequencyofsale_query(String year)
		{
			mysqlConnection();
			makeStatement();
			
			SaleInfoBean saleInfoBean;
			ArrayList<SaleInfoBean> Bean = new ArrayList<SaleInfoBean>();
			
			try {
				String strQuery = "select productName,sum(salesCount) as salesCount from uploadGoods where date like '" + year + "%' group by productName order by salesCount desc limit 10;";
				result = stmt.executeQuery(strQuery);
				
				while(result.next())
				{
					saleInfoBean = new SaleInfoBean();
					saleInfoBean.setProductName(result.getString("productName"));
					saleInfoBean.setCount(result.getInt("salesCount"));
					Bean.add(saleInfoBean);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Bean;
		}   
	   
	public void startTime_query(String Date, String startTime)
	{
		mysqlConnection();
		makeStatement();
		try
		{
			String strInsert = "insert into starttime value('" + Date + "','" + startTime + "');";
			stmt.executeUpdate(strInsert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int selectReceiptNo()
	{
		mysqlConnection();
		makeStatement();
		int selectReceiptNo = 0;
		try {
			String strQuery = "select max(receiptNo) from receipt where date like(select max(date) from receipt);";
			result = stmt.executeQuery(strQuery);
			if(result.next())
			{
				selectReceiptNo = result.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectReceiptNo;
	}
	
	public String selectDate()
	{
		mysqlConnection();
		makeStatement();
		String selectDate = null;
		try {
			String strQuery = "select max(date) from receipt";
			result = stmt.executeQuery(strQuery);
			if(result.next())
			{
				selectDate = result.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectDate;
	}
	
	public void uploadGoods(int receiptNo, String productName, int salesCount, int salesPrice, String date, String time)
	{
		mysqlConnection();
		makeStatement();
		try {
			
			String strInsert = "insert into  uploadGoods value('" + receiptNo + "','" + productName + "','" 
								+ salesCount + "','" + salesPrice + "','" + date + "','" + time + "');";
				stmt.executeUpdate(strInsert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Sales_query(int receiptNo, String date, String time, int salesType, int snack, int noodle, int drink, int salesPrice)
	{
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "insert into  receipt value('"+ receiptNo + "','" + date + "','" + time + "','" + salesType + "','" 
					+ snack + "','" + noodle + "','" + drink + "','" + salesPrice +"');";
			System.out.println(strQuery);
			stmt.executeUpdate(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void m​ovementSeat_query(String strID, String strPW, String remaintime) {
		mysqlConnection();
		makeStatement();
		try {
			String strQuery = "update user set remaintime = remaintime +'" + remaintime + "' where id='" + strID + "' and pw='"
					+ strPW + "';";
			stmt.executeUpdate(strQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> RateOfSalesByTime(String year, String month)
	{
		mysqlConnection();
		makeStatement();
		
		ArrayList<String> time = new ArrayList<String>();
		
		try {
			String strQuery = "select time from starttime where date like '" + year + "-" + month + "%';";
			result = stmt.executeQuery(strQuery);
			while(result.next())
			{
				time.add(result.getString("time"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}
	
	public int[] RateOfSalesByQuarter(String year)
	{
		mysqlConnection();
		makeStatement();

		int Quarter[] = new int[4];
		
		try 
		{
			String strQuery = "select * from stats where date like '" + year + "%';";
			result = stmt.executeQuery(strQuery);

			while (result.next()) 
			{
				String date = result.getString("date");
				String Month = date.substring(date.length() - 5, date.length() - 3);

				switch (Month) 
				{
				case "01":
				case "02":
				case "03":
					Quarter[0] += result.getInt("profit");
					break;
				case "04":
				case "05":
				case "06":
					Quarter[1] += result.getInt("profit");
					break;
				case "07":
				case "08":
				case "09":
					Quarter[2] += result.getInt("profit");
					break;
				case "10":
				case "11":
				case "12":
					Quarter[3] += result.getInt("profit");
					break;
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return Quarter;
	}
	
	public ArrayList<Integer> RateOfSalesByCategory(String year, String month)
	{
		mysqlConnection();
		makeStatement();
		
		ArrayList<Integer> category = new ArrayList<Integer>();
		
		try {
			String strQuery = "select sum(snack),sum(noodle),sum(drink) from receipt where date like '" + year + "-" + month + "%' and salesType = 0;";
			result = stmt.executeQuery(strQuery);
			while(result.next())
			{
				category.add(result.getInt(1));
				category.add(result.getInt(2));
				category.add(result.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public ArrayList<Integer> RateOfSalesByCategory(String year)
	{
		mysqlConnection();
		makeStatement();
		
		ArrayList<Integer> category = new ArrayList<Integer>();
		
		try {
			String strQuery = "select sum(snack),sum(noodle),sum(drink) from receipt where date like '" + year + "%' and salesType = 0;";
			result = stmt.executeQuery(strQuery);
			while(result.next())
			{
				category.add(result.getInt(1));
				category.add(result.getInt(2));
				category.add(result.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public boolean id_overlap_chk(String userid)
	{
		mysqlConnection();
		makeStatement();

		try {
			String strQuery =	"select id from user where id='" + userid + "';";
			System.out.println(strQuery);
			
			result = stmt.executeQuery(strQuery);
			
			if(result.next())
			{			
				System.out.println("아이디 중복!");
				return true;
			} else {
				System.out.println("아이디 사용가능!");
				return false;
			}
		}catch(Exception e) {
			System.out.println("DBManager클래스 id_overlap_chk() 에러");
			e.printStackTrace();
		}
		return false;
	}

	
}