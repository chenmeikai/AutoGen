package www.chenmeikai.com.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    /**
     * 
     * isEmpty:判断字符串是否为空. <br/>
     *
     * @author qiyongkang
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 
     * subBySplit:根据分隔符截取字符串. <br/>
     *
     * @author qiyongkang
     * @param split
     * @return
     * @since JDK 1.6
     */
    public static String subBySplit(String str, String split) {
        String sub = null;
        if (!isEmpty(str) && str.lastIndexOf(split) > -1) {
            sub = str.substring(str.lastIndexOf(split) + 1);
        }
        
        return sub;
    }
    
    /**
     * upperFirst: 将字符串的首字母转换为大写. <br/>
     * 
     * @author qiyongkang
     * @param str
     *            要转换的字符串
     * @return 转换之后的字符串
     */
    public static String upperFirst(String str) {
        if (isEmpty(str)) {
            return null;
        }
        if (1 == str.length()) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    /**
     * 
     * 将字符串拆分为list
     * @param str
     * @param regex
     * @return
     */
    public static List<String> splitStr2List(String str, String regex) {
        List<String> list = new ArrayList<String>();
        String[] strs = str.split(regex);
        for (String s : strs) {
            list.add(s.trim());
        }
        return list;
    }
    
    /**
     * 将数据库表名，转为类名
     * TODO Add comments here.
     * @param fieldName
     * @return
     */
    public static String convertTableTName(String tableName) {
    	
    	//截去前缀
    	String prefix =ProUtils.getProperty("cut.table.prefix");
    	if (!"".equals(prefix)&&tableName.indexOf(prefix)==0) {
    		tableName =tableName.substring(prefix.length());
    	}
        String propName = "";
        String[] strs = tableName.split("_");
        for (int i = 0; i < strs.length; i++) {
            if(i == 0) {
                propName = strs[0];
            } else {
                propName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
            }
        }
        return propName;
    }
    
    /**
     * 将数据库字段名，转为属性名
     * TODO Add comments here.
     * @param fieldName
     * @return
     */
    public static String convertAttributeName(String fieldName) {
        String propName = "";
        String[] strs = fieldName.split("_");
        for (int i = 0; i < strs.length; i++) {
            if(i == 0) {
                propName = strs[0];
            } else {
                propName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
            }
        }
        return propName;
    }
    
    /**
     * TODO 数据库类型转换java类型
     * @param dbType
     * @return java類型
     */
    public static String changeDbType(String dbType) {  
        dbType = dbType.toUpperCase();  
        switch(dbType){  
            case "VARCHAR":  
            case "VARCHAR2":  
            case "CHAR": 
            case "LONGTEXT":
            case "TINYTEXT":
            case "TEXT":
                return "String";  
            case "NUMBER":  
            case "DECIMAL":  
                return "BigDecimal";  
            case "INT":  
            case "SMALLINT":  
            case "INTEGER":  
                return "Integer";  
            case "BIGINT":  
                return "Long";  
            case "DATETIME":  
            case "TIMESTAMP":  
            case "DOUBLE":  
                return "Double";  
            case "FLOAT":  
            	return "Float";  
            case "DATE":  
            	return "Date";  
            default:  
                return "String";  
        }  
    }  
    
}
