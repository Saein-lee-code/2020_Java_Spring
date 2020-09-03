package com.koreait.pjt;
import java.io.IOException;
import java.security.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class MyUtils {	
	public static int getIntParameter(HttpServletRequest request, String keyNum) {
		return parseStrToInt(request.getParameter(keyNum));
	}
	// return true : 로그인 안된 상태
	// return false : 로그인 된 상태
	public static boolean isLogout(HttpServletRequest request) throws IOException {		
		if(null == getLoginUser(request)) {
			return true;			
		}
		return false;
	}
	
	public static int parseStrToInt(String s) {
		return parseStrToInt(s, 0);
	}
	
	public static int parseStrToInt(String s, int n) {
		try {		
			return Integer.parseInt(s);
		}catch(Exception e) {
			return n;
		}
	}
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
    public static String encryptString(String str){	
       String sha = "";	
       try{
          MessageDigest sh = MessageDigest.getInstance("SHA-256");
          sh.update(str.getBytes());
          byte byteData[] = sh.digest();
          StringBuffer sb = new StringBuffer();
          for(int i = 0 ; i < byteData.length ; i++){
              sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
          }	
          sha = sb.toString();	
      }catch(NoSuchAlgorithmException e){
          //e.printStackTrace();
          System.out.println("Encrypt Error - NoSuchAlgorithmException");
          sha = null;
      }	
      return sha;
    }
}
