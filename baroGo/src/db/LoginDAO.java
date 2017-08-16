package db;

public class LoginDAO extends DBManager {
    
    public static boolean guestLogin(String id,String pw)
    {
        mysqlConnection();
        makeStatement();
        try {
            
            StringBuilder query = new StringBuilder();
            query.append("    SELECT               ");
            query.append("        ID               ");
            query.append("    FROM                 ");
            query.append("        user             ");
            query.append("    WHERE                ");
            query.append("        id = '" +id +"'  ");
            query.append("        AND              ");
            query.append("        pw = '" + pw +"' ");
            query.append("    ;                    ");
            
            result = stmt.executeQuery(query.toString());
            
            if(result.next()) {
                System.out.println("Login Complete");
                return true;
            } else {
                System.out.println("Login Fail");
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean adminLogin(String id,String pw)
    {
        mysqlConnection();
        makeStatement();
        try {
            
            StringBuilder query = new StringBuilder();
            query.append("    SELECT               ");
            query.append("        ID               ");
            query.append("    FROM                 ");
            query.append("        admins           ");
            query.append("    WHERE                ");
            query.append("        id = '" +id +"'  ");
            query.append("        AND              ");
            query.append("        pw = '" + pw +"' ");
            query.append("    ;                    ");
            
            result = stmt.executeQuery(query.toString());
            
            if(result.next()) {
                System.out.println("Login Complete");
                return true;
            } else {
                System.out.println("Login Fail");
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
