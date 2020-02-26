package db;

import java.util.ArrayList;

import useInfo.SearchBean;
import useInfo.UseBean;

public class UserDAO extends DBManager {
    
    public ArrayList<UseBean> userSearch(String a_strUserName, SearchBean bean) throws Exception {
        mysqlConnection();
        makeStatement();
        ArrayList<UseBean> userList = new ArrayList<UseBean>();
        
        try{
            String strQuery = "select * from user where name = '" + a_strUserName +"';";
            result = stmt.executeQuery(strQuery);
            
            StringBuilder strBirth = new StringBuilder();
            while(result.next()) {
                strBirth.append(result.getString("birth1")).append(".").append(result.getString("birth2")).append(".").append(result.getString("birth3"));
                
                bean.setStrName(result.getString("name"));
                bean.setStrID(result.getString("id"));
                bean.setBirth(strBirth.toString());

                UseBean useBean = new UseBean();
                useBean.setiPCNumber(result.getInt(1));
                useBean.setStrName(result.getString(2));
                useBean.setStrID(result.getString(3));
                useBean.setbSex(result.getBoolean(5));
                useBean.setStrEmail(result.getString(6));
                useBean.setbPaymentplan(result.getBoolean(8));
                useBean.setStrRemaintime(result.getString(9));
                useBean.setStrUsetime(result.getString(10));
                useBean.setiAccruemoney(result.getInt(11));
                useBean.setStrAccruetime(result.getString(12));
                useBean.setiBirth1(result.getInt(13));
                useBean.setiBirth2(result.getInt(14));
                useBean.setiBirth3(result.getInt(15));
                userList.add(useBean);
            }
        } catch(Exception e) {
            throw e;
        }
        
        return userList;
    }
    
    public UseBean userSearch(String a_strUserID) {
//      mysqlConnection();
//      makeStatement();
//      try{
//          String query = "select * from user where id = '" + a_strUserID +"';";
//          result = stmt.executeQuery(query);
//          
//          if (result.next()) {
//              useBean.setiPCNumber(result.getInt(1));
//              useBean.setStrName(result.getString(2));
//              useBean.setStrID(result.getString(3));
//              useBean.setbSex(result.getBoolean(5));
//              useBean.setStrEmail(result.getString(6));
//              useBean.setbPaymentplan(result.getBoolean(8));
//              useBean.setStrRemaintime(result.getString(9));
//              useBean.setStrUsetime(result.getString(10));
//              useBean.setiAccruemoney(result.getInt(11));
//              useBean.setStrAccruetime(result.getString(12));
//              useBean.setiBirth1(result.getInt(13));
//              useBean.setiBirth2(result.getInt(14));
//              useBean.setiBirth3(result.getInt(15));
//          }
//      } catch(Exception e) {
//          e.printStackTrace();
//      }
        
        useBean.setiPCNumber(1);
        useBean.setStrName("중구");
        useBean.setStrID("jgji");
        useBean.setbSex(true);
        useBean.setStrEmail("");
        useBean.setbPaymentplan(true);
        useBean.setStrRemaintime("");
        useBean.setStrUsetime("");
        useBean.setiAccruemoney(11000);
        useBean.setStrAccruetime("");
        useBean.setiBirth1(93);
        useBean.setiBirth2(12);
        useBean.setiBirth3(13);
        
        return useBean;
    }
    
    public void selectUser(String id) {
        mysqlConnection();
        makeStatement();
        try {
            String strUpdate = "update timetemp set id='"+id+"';";
            String strInsert = "insert into timetemp value('" + id + "',0);";
            String strResult = getTempId();
            
            if(strResult == null)
            {
                stmt.executeUpdate(strInsert);
            } else {
                stmt.executeUpdate(strUpdate);
            }
        }catch(Exception e) {
            System.out.println("DBManager클占쏙옙占쏙옙 test() 占쏙옙占쏙옙");
            e.printStackTrace();
        }
    }
    
    
}
