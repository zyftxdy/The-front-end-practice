package com.zhang.service;

import java.util.List;

import com.zhang.model.ProductType;

/**
 * ҵ���߼���--��Ʒ������Ϣ����
 * @author Administrator
 *
 */
public interface ProductTypeService {

	/**
	 * ��ѯһ������
	 * @return
	 */
	public List<ProductType> findFirstType();
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<ProductType> findSecondType();
}
