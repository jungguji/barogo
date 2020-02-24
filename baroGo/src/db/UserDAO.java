package db;

import java.util.ArrayList;

import useInfo.SearchBean;
import useInfo.UseBean;

public class UserDAO extends DBManager {
    
    public static ArrayList<UseBean> user_search(String a_strUserName, SearchBean bean) throws Exception {
        mysqlConnection();
        makeStatement();
        ArrayList<UseBean> arUseBean = new ArrayList<UseBean>();
        try{
            String strQuery = "select * from user where name = '" + a_strUserName +"';";
            result = stmt.executeQuery(strQuery);
            
            StringBuilder strBirth = new StringBuilder();
            while(result.next()) {
                strBirth.append(result.getString("birth1")).append(".").append(result.getString("birth2")).append(".").append(result.getString("birth3"));
                
                bean.setStrName(result.getString("name"));
                bean.setStrID(result.getString("id"));
                bean.setBirth(strBirth.toString());

                // 정보 창에 나올 정보들 계속 새로 생성해야?
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
                arUseBean.add(useBean);
            }
        } catch(Exception e) {
            throw e;
        }
        return arUseBean;
    }
    
    public UseBean user_search(String a_strUserID) throws Exception {
        mysqlConnection();
        makeStatement();
        
        UseBean useBean = new UseBean();
        try{
            String strQuery = "select * from user where id = '" + a_strUserID +"';";
            result = stmt.executeQuery(strQuery);
            
            while(result.next()) 
            {
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
            }
        } catch(Exception e) {
            throw e;
        }
        
        return useBean;
    }
    
    public static void select_user(String id) {
        mysqlConnection();
        makeStatement();
        try {
            String strUpdate = "update timetemp set id='"+id+"';";
            String strInsert = "insert into timetemp value('" + id + "',0);";
            String strResult = temp_id_print();
            
            if(strResult == null)
            {
                stmt.executeUpdate(strInsert);
            } else {
                stmt.executeUpdate(strUpdate);
            }
        }catch(Exception e) {
            System.out.println("DBManagerŬ���� test() ����");
            e.printStackTrace();
        }
    }
    
    
}