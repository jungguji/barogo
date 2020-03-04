package db;

import java.util.ArrayList;

import useInfo.SearchDTO;
import useInfo.UserVO;

public class UserDAO extends DBManager {
    
    public ArrayList<UserVO> userSearch(String a_strUserName, SearchDTO bean) throws Exception {
        mysqlConnection();
        makeStatement();
        ArrayList<UserVO> userList = new ArrayList<UserVO>();
        
        try{
            String strQuery = "select * from user where name = '" + a_strUserName +"';";
            result = stmt.executeQuery(strQuery);
            
            StringBuilder strBirth = new StringBuilder();
            while(result.next()) {
                strBirth.append(result.getString("birth1")).append(".").append(result.getString("birth2")).append(".").append(result.getString("birth3"));
                
                bean.setStrName(result.getString("name"));
                bean.setStrID(result.getString("id"));
                bean.setBirth(strBirth.toString());

                UserVO UserVO = new UserVO();
                UserVO.setiPCNumber(result.getInt(1));
                UserVO.setStrName(result.getString(2));
                UserVO.setStrID(result.getString(3));
                UserVO.setbSex(result.getBoolean(5));
                UserVO.setStrEmail(result.getString(6));
                UserVO.setbPaymentplan(result.getBoolean(8));
                UserVO.setStrRemaintime(result.getString(9));
                UserVO.setStrUsetime(result.getString(10));
                UserVO.setiAccruemoney(result.getInt(11));
                UserVO.setStrAccruetime(result.getString(12));
                UserVO.setiBirth1(result.getInt(13));
                UserVO.setiBirth2(result.getInt(14));
                UserVO.setiBirth3(result.getInt(15));
                userList.add(UserVO);
            }
        } catch(Exception e) {
            throw e;
        }
        
        return userList;
    }
    
    public UserVO userSearch(String a_strUserID) {
//      mysqlConnection();
//      makeStatement();
//      try{
//          String query = "select * from user where id = '" + a_strUserID +"';";
//          result = stmt.executeQuery(query);
//          
//          if (result.next()) {
//              UserVO.setiPCNumber(result.getInt(1));
//              UserVO.setStrName(result.getString(2));
//              UserVO.setStrID(result.getString(3));
//              UserVO.setbSex(result.getBoolean(5));
//              UserVO.setStrEmail(result.getString(6));
//              UserVO.setbPaymentplan(result.getBoolean(8));
//              UserVO.setStrRemaintime(result.getString(9));
//              UserVO.setStrUsetime(result.getString(10));
//              UserVO.setiAccruemoney(result.getInt(11));
//              UserVO.setStrAccruetime(result.getString(12));
//              UserVO.setiBirth1(result.getInt(13));
//              UserVO.setiBirth2(result.getInt(14));
//              UserVO.setiBirth3(result.getInt(15));
//          }
//      } catch(Exception e) {
//          e.printStackTrace();
//      }
        
        UserVO vo = new UserVO();
        vo.setiPCNumber(1);
        vo.setStrName("중구");
        vo.setStrID("jgji");
        vo.setbSex(true);
        vo.setStrEmail("");
        vo.setbPaymentplan(true);
        vo.setStrRemaintime("");
        vo.setStrUsetime("");
        vo.setiAccruemoney(11000);
        vo.setStrAccruetime("");
        vo.setiBirth1(93);
        vo.setiBirth2(12);
        vo.setiBirth3(13);
        
        return vo;
    }
}
