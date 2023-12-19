package com.erp.ERP_PRODUCT.services.Master;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.erp.ERP_PRODUCT.entity.Master.Category_Master_Entity;

public interface Category_Master_Service {

	ResponseEntity<String> save(Category_Master_Entity objCategoryEntity);
	ResponseEntity<String> update(Category_Master_Entity objCategoryEntity);
	ResponseEntity<String> delete(String strCategoryId);
	ResponseEntity<Category_Master_Entity> view(String strCategoryId);
	ResponseEntity<List<Category_Master_Entity>> viewAll();
	ResponseEntity<String> deleteAll();
	
	ResponseEntity<List<Object>> dynamicDataAccess(String strTable, String strFields, String strCondition);
}
