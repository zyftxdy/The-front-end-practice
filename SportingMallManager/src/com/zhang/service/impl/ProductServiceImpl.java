package com.zhang.service.impl;



import java.util.List;
import java.util.Map;

import com.zhang.dao.ProductDao;
import com.zhang.model.Product;
import com.zhang.model.ProductDatils;
import com.zhang.service.ProductService;

/**
 * ҵ���߼���ʵ������--��Ʒ����
 * @author Administrator
 *
 */
public class ProductServiceImpl implements ProductService{

	//���ݿ���ʲ����
	private ProductDao productDao=new ProductDao();
	
	//�����Ʒ
	@Override
	public boolean addProduct(Product p) {
		// TODO Auto-generated method stub
		return productDao.addProduct(p);
	}
	
	//�����Ʒ����
	@Override
	public boolean addDatils(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.addProductDatils(pd);
	}
	
	//����ID�޸���Ʒ��Ϣ
	@Override
	public boolean updateProduct(Product p) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(p);
	}
	
	//�޸���Ʒ״̬
	@Override
	public boolean updateStatus(String productid) {
		// TODO Auto-generated method stub
		return productDao.updateStatus(productid);
	}
	
	//�ָ���Ʒ״̬
	@Override
	public boolean recoverStatus(String productid) {
		// TODO Auto-generated method stub
		return productDao.recoverStatus(productid);
	}
	
	//���������¼���Ʒ����
	@Override
	public boolean deleteDatils(String datilsId) {
		// TODO Auto-generated method stub
		return productDao.deleteDatils(datilsId);
	}
	
	//����ID��ѯ��Ʒ��Ϣ
	@Override
	public Product findById(String productid) {
		// TODO Auto-generated method stub
		return productDao.findById(productid);
	}
	
	//��������ҳ��ѯ��Ʒ
	@Override
	public List<Map<String, Object>> FindProducts(int currPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.find(currPage, perPageRecords, parameters);
	}

	//��ȡ���ܼ�¼��
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getRows(parameters);
	}

	//��������ҳ��ѯ��Ʒ����
	@Override
	public List<Map<String, Object>> FindDatils(int currPage, int perPageRecords, String productid,
			Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findDatils(currPage, perPageRecords, productid, parameters);
	}

	//��ѯ��Ʒ�������ܼ�¼��
	@Override
	public int getDatilsRows(String productid, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getDatilsRows(productid, parameters);
	}

	@Override
	public int getColor(String productid) {
		// TODO Auto-generated method stub
		return productDao.getColorRows(productid);
	}

	@Override
	public int getClothing(String productid) {
		// TODO Auto-generated method stub
		return productDao.getClothRows(productid);
	}

	@Override
	public int getShot(String productid) {
		// TODO Auto-generated method stub
		return productDao.getShotRows(productid);
	}

	//�޸���Ʒ����
	@Override
	public boolean updateDatils(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.updateDatils(pd);
	}

	@Override
	public boolean updateDatils1(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.updateDatils1(pd);
	}




}
