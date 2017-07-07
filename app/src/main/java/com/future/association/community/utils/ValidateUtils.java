package com.future.association.community.utils;


public class ValidateUtils {

	private ValidateUtils(){}
	
	/** 
	 * 验证电话号码是否合法
	 */  
	public static boolean isMobileNO(String mobiles) {  
		/**
		 * 电信、移动、联通（13、14、15、17、18）
		 */
	    String telRegex = "[1][34578]\\d{9}";
	    if (TextUtil.isEmpty(mobiles)) return false;  
	    else return mobiles.matches(telRegex);  
	   }  
	/**
	 * 验证密码是否合法
	 * @param pwd
	 * @return
	 */
	public static boolean isPwdValid(String pwd){
		String pwdRegex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";//必须是有数字和字母组成
		//String pwdRegex = "^[\\w]{6,12}$";//6-12位下划线、数字、字母组成
//		String pwdRegex = "^[0-9A-Za-z]{6,16}$";
		if(TextUtil.isEmpty(pwd)) return false ;
		else return pwd.matches(pwdRegex) ;
	}
	/**
	 * 验证验证码
	 * 
	 * @param validateCode
	 * @return
	 */
	public static boolean isValidCode(String validateCode) {

		String reg = "^[0-9]{4}$";
		if (TextUtil.isEmpty(validateCode))
			return false;
		else
			return validateCode.matches(reg);
	}
	/**
	 * 验证昵称是否合法
	 * @param nickName
	 * @return
	 */
	public static boolean isNickValid(String nickName){
		String pwdRegex = "^([A-Za-z]|[\u4E00-\u9FA5])+$";
		if(TextUtil.isEmpty(nickName)) return false ;
		else return nickName.matches(pwdRegex) ;
	}
	/**
	 * 验证身份证号码
	 * @param idCard
	 * @return
	 */
	public static boolean isIdCardValid(String idCard){
//		String idCard15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$" ;//第一代身份证 15位
//		String idCard15 = "^[1-9]\\d{7}((0\\[1-9])|(1[0-2]))(([0\\[1-9]|1\\d|2\\d])|3[0-1])\\d{2}([0-9]|x|X){1}$" ;//第一代身份证 15位
//		String idCard18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$" ;//第二代身份18位
		String idCards = "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
                                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
                                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))" ;
		
		if(TextUtil.isEmpty(idCards)){
			return false ;
		}else{
			return idCard.matches(idCards) ;
		}
	}
	/**
	 * 验证军官证
	 * @param officerId
	 * @return
	 */
	public static boolean isOfficerIdCardValid(String officerId){
		String officerCard  = "^[a-zA-Z0-9]{7,21}$" ;
		if(TextUtil.isEmpty(officerId)){
			return false ;
		}else{
			return officerId.matches(officerCard) ;
		}
	}
}
