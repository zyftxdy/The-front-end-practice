package com.zhang.serviceImpl;

import java.util.List;

import com.zhang.dao.ProductTypeDao;
import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;

/**
 * ҵ���߼���ʵ������--��Ʒ������Ϣ����
 * @author Administrator
 *
 */
public class ProductTypeServiceImpl implements ProductTypeService {

	//���ݿ���ʲ����
	private ProductTypeDao ptd=new ProductTypeDao();
	
	//��ѯһ������
	@Override
	public List<ProductType> findFirstType() {
		// TODO Auto-generated method stub
		return ptd.findFirstType();
	}

	//��ѯ��������
	@Override
	public List<ProductType> findSecondType() {
		// TODO Auto-generated method stub
		return ptd.findSecondType();
	}

}
