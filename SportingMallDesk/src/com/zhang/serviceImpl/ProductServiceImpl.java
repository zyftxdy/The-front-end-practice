package com.zhang.serviceImpl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.ProductDao;
import com.zhang.service.ProductService;

/**
 * ҵ���߼���ʵ������--��Ʒ��Ϣ����
 * @author 12443
 *
 */
public class ProductServiceImpl implements ProductService {

	//�������ݿ���ʶ���
	private ProductDao productDao=new ProductDao();
	
	//��ѯ��Ʒ�б�
	@Override
	public List<Map<String, Object>> findProducts(int currentPage,int perPageRecords,int Sorting,Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findProducts(currentPage,perPageRecords,Sorting,parameters);
	}

	//��Ʒ����
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getRows(parameters);
	}

	//����ID��ѯ��Ʒ��ͼ۸�
	@Override
	public double getMinPrice(String productId) {
		// TODO Auto-generated method stub
		return productDao.getMinPrice(productId);
	}

	//����ID��ѯ��Ʒ��߼۸�
	@Override
	public double getMaxPrice(String productId) {
		// TODO Auto-generated method stub
		return productDao.getMaxPrice(productId);
	}

	//��ѯ��Ʒ�ܿ��
	@Override
	public int getNums(String productId) {
		// TODO Auto-generated method stub
		return productDao.getNums(productId);
	}

	//��ѯ��Ʒ��ɫ�б�
	@Override
	public List<String> getColour(String productId) {
		// TODO Auto-generated method stub
		return productDao.getColour(productId);
	}

	//��ѯ��ƷЬ���б�
	@Override
	public List<String> getShot(String productid) {
		// TODO Auto-generated method stub
		return productDao.getShot(productid);
	}

	//��ѯ��Ʒ�����б�
	@Override
	public List<String> getClothing(String productid) {
		// TODO Auto-generated method stub
		return productDao.getClothing(productid);
	}

	//�����������²�ѯ��Ʒ�۸�
	@Override
	public double getPriceByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getPriceByCd(productid, color, parameters);
	}

	//�����������²�ѯ��ƷͼƬ
	@Override
	public String getPictureByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getPictureByCd(productid, color, parameters);
	}

	//�����������²�ѯ��Ʒ���
	@Override
	public int getNumsByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getNumsByCd(productid, color, parameters);
	}

	// ����������ѯ��Ʒ����
	@Override
	public int CheckCount(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.CheckCount(productid, color, parameters);
	}

	//��ѯ��Ʒ����ID
	@Override
	public String findDatilsId(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findDatilsId(productid, color, parameters);
	}

	//��ѯ��Ʒ����
	@Override
	public List<Map<String, Object>> findComment(int currentPage,int perPageRecords,String productid) {
		// TODO Auto-generated method stub
		return productDao.findComment(currentPage,perPageRecords,productid);
	}

	//��ѯ��Ʒ��������
	@Override
	public int getCommentRows(String productId) {
		// TODO Auto-generated method stub
		return productDao.getCommentRows(productId);
	}

}
