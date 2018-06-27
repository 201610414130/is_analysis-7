package tools;


import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Tools {
	public static String getID(){  
        String refId=UUID.randomUUID().toString().replace("-","");
        return refId;
	}
	public static String getMD5Password(String message) {  
		MessageDigest messageDigest = null;  
        StringBuffer md5StrBuff = new StringBuffer();  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
            messageDigest.reset();  
            messageDigest.update(message.getBytes("UTF-8"));  
              
            byte[] byteArray = messageDigest.digest();  
            for (int i = 0; i < byteArray.length; i++)   
            {  
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
                else  
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
            }  
        } catch (Exception e) {  
            throw new RuntimeException();  
        }  
        return md5StrBuff.toString().toUpperCase();//字母大写
    }  
	public static Date getDateAfter(Date d,int day){  
		   Calendar now =Calendar.getInstance();  
		   now.setTime(d);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		   return now.getTime();  
	}  
	public static String getTimeNow(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
	}
	public static boolean isContainChinese(String str) {
		 Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
		 Matcher matcher = pattern.matcher(str);
		 if (matcher.find()) {
			 return true;
		 }
		 	return false;
		}
	public static String utilToJson(Object object){
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("{");
		Field[] fields=object.getClass().getDeclaredFields();
		for(int i=0;i<fields.length;i+=1)
		{
			try {
				fields[i].setAccessible(true);
				String key=fields[i].getName();
				Object value=fields[i].get(object);
				stringBuilder.append('"'+key+'"'+":"+'"'+value+'"'+",");
			} catch (Exception e) {
				System.out.println("Tools - utilToJson:转换Json失败");
				e.printStackTrace();
			} 
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
}
