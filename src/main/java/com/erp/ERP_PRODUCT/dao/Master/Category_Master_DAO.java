package com.erp.ERP_PRODUCT.dao.Master;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.ERP_PRODUCT.entity.Master.Category_Master_Entity;

@Repository
public interface Category_Master_DAO extends JpaRepository<Category_Master_Entity, String>   {

//	@Query(nativeQuery = true,value="select :fields from :tables where :condition")
//	List<Object> getDynamicAccessValues(
//			@Param("fields") String strFields,
//			@Param("tables") String strTables,
//			@Param("condition") String strCondition);
	
}
