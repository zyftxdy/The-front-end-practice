package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Product;
import com.zhang.model.ProductDatils;

/**
 * ҵ���߼���--��Ʒ��Ϣ����
 * @author Administrator
 *
 */
public interface ProductService {

	//�����Ʒ
	public boolean addProduct(Product p);
	//�����Ʒ����
	public boolean addDatils(ProductDatils pd);
	//����ID�޸���Ʒ��Ϣ
	public boolean updateProduct(Product p);
	//����ID�޸���Ʒ����
	public boolean updateDatils1(ProductDatils pd);
	//���������޸���Ʒ����
	public boolean updateDatils(ProductDatils pd);
	//�޸���Ʒ״̬
	public boolean updateStatus(String productid);
	//�ָ���Ʒ״̬
	public boolean recoverStatus(String productid);
	//���������¼���Ʒ����
	public boolean deleteDatils(String datilsId);
	//����ID��ѯ��Ʒ��Ϣ
	public Product findById(String productid);
	//��������ҳ��ѯ��Ʒ��Ϣ
	public List<Map<String,Object>> FindProducts(int currPage,int perPageRecords,Object... parameters);
	//��ѯ���ܼ�¼��
	public int getRows(Object... parameters);
	//��������ҳ��ѯ��Ʒ����
	public List<Map<String,Object>> FindDatils(int currPage,int perPageRecords,String productid,Object... parameters);
	//��ѯ�������ܼ�¼��
	public int getDatilsRows(String productid,Object... parameters);
	//��ѯ��Ʒ����ɫ����
	public int getColor(String productid);
	//��ѯ��Ʒ�ĳ������
	public int getClothing(String productid);
	//��ѯ��Ʒ��Ь�����
	public int getShot(String productid);

}
