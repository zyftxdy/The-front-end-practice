package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * ҵ���߼���--��Ʒ��Ϣ����
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * ��ѯ��Ʒ�б�
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProducts(int currentPage,int perPageRecords,int Sorting,Object... parameters);
	
	/**
	 * ��ѯ��Ʒ����
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * ������ƷID��ѯ��Ʒ��ͼ۸�
	 * @param productId
	 * @return
	 */
	public double getMinPrice(String productId);
	
	/**
	 * ������ƷID��ѯ��Ʒ��ͼ۸�
	 * @param productId
	 * @return
	 */
	public double getMaxPrice(String productId);
	
	/**
	 * ��ѯ��Ʒ�ܿ��
	 * @param productId
	 * @return
	 */
	public int getNums(String productId);
	
	/**
	 * ��ѯ��Ʒ��ɫ�б�
	 * @param productId
	 * @return
	 */
	public List<String> getColour(String productId);
	
	/**
	 * ��ѯ��ƷЬ���б�
	 * @param productid
	 * @return
	 */
	public List<String> getShot(String productid);
	
	 /**
	  * ��ѯ��Ʒ�����б�
	 * @param productid
	 * @return
	 */
	public List<String> getClothing(String productid);
	
	/**
	 *����������ѯ��Ʒ�ļ۸�
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public double getPriceByCd(String productid,String color,Object...parameters);
	
	/**
	 *����������ѯ��Ʒ��ͼƬ
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public String getPictureByCd(String productid,String color,Object...parameters);
	
	/**
	 * ����������ѯ��Ʒ��ʣ����
	 * @param productid
	 * @param parameters
	 * @return
	 */
	public int getNumsByCd(String productid,String color,Object...parameters);
	
	/**
	 * ����������ѯ��Ʒ����
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public int CheckCount(String productid,String color,Object...parameters);
	
	/**
	 * ��ѯ��Ʒ����ID
	 * @param datilsid
	 * @param userid
	 * @param productname
	 * @param number
	 * @return
	 */
	public String findDatilsId(String productid,String color,Object...parameters);
	
	/**
	 * ��ѯ��Ʒ����
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,String productid);
	
	/**
	 * ��ѯ��Ʒ��������
	 * @param productId
	 * @return
	 */
	public int getCommentRows(String productId);
}
