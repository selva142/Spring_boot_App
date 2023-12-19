package com.erp.ERP_PRODUCT.services.impl.Master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.erp.ERP_PRODUCT.dao.Master.Category_Master_DAO;
import com.erp.ERP_PRODUCT.entity.Master.Category_Master_Entity;
import com.erp.ERP_PRODUCT.services.Master.Category_Master_Service;

@Service
public class Category_Master_Service_Impl implements Category_Master_Service{
	
	@Autowired
	Category_Master_DAO objMCategoryDao;

	@Override
	public ResponseEntity<String> save(Category_Master_Entity objCategoryEntity) {
		objMCategoryDao.save(objCategoryEntity);
		return new ResponseEntity<>("Successfully Saved",HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> update(Category_Master_Entity objCategoryEntity) {

		if(objMCategoryDao.findById(objCategoryEntity.getCid()).isPresent()) {
			
			Category_Master_Entity objMCategoryEntity= objMCategoryDao.findById(objCategoryEntity.getCid()).get();
			objMCategoryEntity.setCid(objCategoryEntity.getCid());
			objMCategoryEntity.setCname(objCategoryEntity.getCname());
			objMCategoryEntity.setCtype(objCategoryEntity.getCtype());
			objMCategoryDao.save(objMCategoryEntity);
			return new ResponseEntity<>("Successfully Updated", HttpStatus.CREATED);
		}
		return new ResponseEntity<>("Updated Faild", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> delete(String strCategoryId) {
		if(objMCategoryDao.findById(strCategoryId).isPresent()) {
			objMCategoryDao.deleteById(strCategoryId);
			return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Record Not Found", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Category_Master_Entity> view(String strCategoryId) {
		
		return new ResponseEntity<>(objMCategoryDao.findById(strCategoryId).orElse(new Category_Master_Entity()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Category_Master_Entity>> viewAll() {
		return new ResponseEntity<>(objMCategoryDao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteAll() {
		if(objMCategoryDao.findAll().size()>0) {
			objMCategoryDao.deleteAll();
			return new ResponseEntity<>("Successfully All Categorys Deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Record Not Found", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Object>> dynamicDataAccess(String strTable, String strFields,String strCondition) {
		return null;//new ResponseEntity<>(objMCategoryDao.getDynamicAccessValues(strFields, strTable, strCondition),HttpStatus.OK);
	}

}
