package com.zhang.service.impl;

import java.util.List;

import com.zhang.dao.ProductTypeDao;
import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;

/**
 * ҵ���߼���ʵ������--��Ʒ����
 * @author Administrator
 *
 */
public class ProductTypeServiceImpl implements ProductTypeService {

	//�������ݿ���ʲ�
	private ProductTypeDao productTypedao=new ProductTypeDao();
	
	//��ѯһ������
	@Override
	public List<ProductType> findFirstType(){
		return productTypedao.findFirstType();
	}

	//��ѯ��������
	@Override
	public List<ProductType> findSecondType(String firstTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findSecondType(firstTypeId);
	}
	
	//�޸�һ����������
	@Override
	public boolean updateFirstType(ProductType p) {
		// TODO Auto-generated method stub
		return productTypedao.updateFirstType(p);
	}

	//�޸Ķ�����������
	@Override
	public boolean updateSecondType(ProductType p) {
		// TODO Auto-generated method stub
		return productTypedao.updateSecondType(p);
	}
	
	//��ѯһ����������
	@Override
	public Object FirstTypeName(String firstTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.firstTypeName(firstTypeName);
	}

	//��ѯ������������
	@Override
	public Object SecondTypeName(String secondTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.secondTypeName(secondTypeName);
	}

	//����µĶ���Ʒ��
	@Override
	public boolean addSecondTypes(String secondTypeName, String firstTypeId,String picture) {
		// TODO Auto-generated method stub
		return productTypedao.addSecondTypes(secondTypeName, firstTypeId,picture);
	}

	//���ݷ������Ʋ�ѯ����ID
	@Override
	public Object findByName(String firstTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.findByName(firstTypeName);
	}

	//ɾ���ڶ�Ʒ��
	@Override
	public boolean deleteSecondType(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.deleteSecondType(secondTypeId);
	}
	
	//����һ������ID��ѯ��������
	@Override
	public Object findById(String firstTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findById(firstTypeId);
	}

	//���ݶ�������ID��ѯ��������
	@Override
	public Object findBySecondId(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findBySecondId(secondTypeId);
	}

	@Override
	public Object findPicture(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findPicture(secondTypeId);
	}
	
}
