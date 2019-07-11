package com.sxt.sso.testdate;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;

public class Test02 {
	
	public static void main(String[] args) throws ParseException {
		
		/*long maxMemory = Runtime.getRuntime().maxMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("maxMemory = "+ maxMemory +"(字节)、"+(maxMemory /(double)1024 /1024)+"MB");
		System.out.println("maxMemory = "+ totalMemory +"(字节)、"+(totalMemory /(double)1024 /1024)+"MB");*/
		/*String ss = "2018-01-01 19:30".replace(" ", "-").replace(":", "-");
		String ss11 = "2018-01-01 17:00".replace(" ", "-").replace(":", "-");
		String[] ss2 = ss.split("-");
		String[] ss22 = ss11.split("-");
		Duration duration= Duration.between(LocalDateTime.of(Integer.valueOf(ss22[0]),Integer.valueOf(ss22[1]),
				Integer.valueOf(ss22[2]),Integer.valueOf(ss22[3]),Integer.valueOf(ss22[4])),LocalDateTime.of(Integer.valueOf(ss2[0]),Integer.valueOf(ss2[1]),
				Integer.valueOf(ss2[2]),Integer.valueOf(ss2[3]),Integer.valueOf(ss2[4])));
		
		long hours = duration.toMinutes();
		double we = (double)hours / 60;
		if(we < 2.4) {
			System.out.println("服务记录中服务机时不能大于服务开始时间到服务结束时间之间的小时数;");
		}*/
		String value = "http://samp.cas.cn/sams//sams/int/client/apparatus/clientShowApparatusImg.jsp?fileId=2147023959&file_suffix=jpg";
		int len = 150;
		//验证图片： 文件功能简述： 描述一个URL或图片地址是否有效
		checkPicture(value,len);

	}
	private static boolean checkPicture(String value, int len) {
		if (len != -1 && value.length() > len) {
			return false;
		}
//		if(checkUrl(value, len)){//先验证下url
			if (value.startsWith("http") && (value.lastIndexOf("/")>0 || value.lastIndexOf("\\")>0)) 
			{
//				if(!isImage(value)){ 
				if(isConnec(value) == null){
					return false;
				}
				return true;
			}
//		}
		return false;
	}
	/** 
	* 功能描述 : 检测当前URL是否可连接或是否有效, 
	* 最多连接网络 3 次, 如果3 次都不成功说明该地址不存在或视为无效地址. 
	* @param url 
	* 指定URL网络地址 
	* @return String 
	*///使用URLDecoder.decode(str,"UTF-8")解码，但是只能解决一部分，若路径中含有+，也是不能解决的，
	//原因 是URL并不是完全用URLEncoder.encode(str,"UTF-8")编码的,+号被解码后，却变成了空格。
	private synchronized static String isConnec(String urlStr){
		String success = null;
		if(null == urlStr || urlStr.length()<=0){
			return null;
		}
		int count = 0,state = -1;
		while(count<3){
			try {
				String full = changeChineseToURIEncode(urlStr);// 对空字符串进行处理);
				URL url = new URL(full.replaceAll(" ", "%20"));//encode后替换  解决空格问题 ; 其中%20是空格在UTF-8下的编码 
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				// 设置连接超时时间为10秒
				connection.setConnectTimeout(10 * 1000);
				// 防止屏蔽程序抓取而返回403错误
				connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
				state = connection.getResponseCode();
				// 打开的连接读取的输入流。
 				InputStream in = connection.getInputStream();
				if(null == in){
					return null;
				}
				if(state == HttpURLConnection.HTTP_OK || state == HttpURLConnection.HTTP_MOVED_TEMP){
					success = connection.getURL().toString();
				}
				connection.disconnect();
				break;
			} catch (Exception e) {
				e.printStackTrace();
				count++;
				System.out.println(count);
				continue;
			}
		}
		if(null == success){
			return null;
		}
		return success;
	}
	
    // 完整的判断中文汉字和符号
    private static String changeChineseToURIEncode(String strName) {
    	String result = "";
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            String c = String.valueOf(ch[i]);
            if (isChinese(ch[i])) {
            	try {
					c = URLEncoder.encode(c, "utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "";
				}
            }
            result += c;
        }
        return result;
    }

	// 根据Unicode编码完美的判断中文汉字和符号
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
}
