package com.zhang.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * �����������㷨
 * @author 12443
 *
 */
public class OrderNumber {

	public final static String getOrderNumber(String userid){
		//int machineId = 1;
		//int hashCodeV = UUID.randomUUID().toString().hashCode();
		//if(hashCodeV < 0) {
			//hashCodeV = - hashCodeV;
		//}
		//return machineId+ String.format("%015d", hashCodeV);	
		
		//SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		//String newDate=sdf.format(new Date());
		//String result="";
		//Random random=new Random();
		//for(int i=0;i<3;i++){
		//	result+=random.nextInt(10);
		//}
		//return newDate+result;
		long currentTime=System.currentTimeMillis();//ʮ��λʱ���
		String orderNo=""+(currentTime+currentTime%9);
		if(userid.length()==1){
			orderNo=orderNo+"0"+userid;
		}else{
			String id=userid.substring(userid.length()-2,userid.length());//��ȡID����λ
			orderNo=orderNo+id;
		}
		return orderNo;		       
	}
}
