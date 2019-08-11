package com.zhang.service;

import java.util.List;

import com.zhang.model.ProductType;

/**
 * ҵ���߼���--��Ʒ��Ϣ����
 * @author Administrator
 *
 */
public interface ProductTypeService {

	//��ѯһ������
	public List<ProductType> findFirstType();
	//��ѯ��������
	public List<ProductType> findSecondType(String firstTypeId);
	//�޸�һ����������
	public boolean updateFirstType(ProductType p);
	//�޸Ķ�����������
	public boolean updateSecondType(ProductType p);
	//��ѯһ���������Ƹ���
	public Object FirstTypeName(String firstTypeName);
	//��ѯ�����������Ƹ���
	public Object SecondTypeName(String secondTypeName);
	//����µĶ�����������
	public boolean addSecondTypes(String secondTypeName,String firstTypeId,String picture);
	//���ݷ������Ʋ�ѯ����ID
	public Object findByName(String firstTypeName);
	//����һ������ID��ѯ��������
	public Object findById(String firstTypeId);
	//���ݶ�������ID��ѯ��������
	public Object findBySecondId(String secondTypeId);
	//���ݶ�������ID��ѯ��������
	public Object findPicture(String secondTypeId);
	//ɾ���ڶ�Ʒ��
	public boolean deleteSecondType(String secondTypeId);
	
}
