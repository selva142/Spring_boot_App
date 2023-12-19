package com.erp.ERP_PRODUCT.controller.Master;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.ERP_PRODUCT.entity.Master.Category_Master_Entity;
import com.erp.ERP_PRODUCT.services.Master.Category_Master_Service;

@CrossOrigin
@RestController
@RequestMapping("mcategory")
public class Category_Master_Controller {

	@Autowired
	Category_Master_Service objMCategoryService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createNewCategory(@RequestBody Category_Master_Entity objMCategoryEntity){
		return objMCategoryService.save(objMCategoryEntity);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody Category_Master_Entity objMCategoryEntity){
		return objMCategoryService.update(objMCategoryEntity);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCategory(@RequestParam("cid") String strCategoryId){
		return objMCategoryService.delete(strCategoryId);
	}
	
	@GetMapping("/view")
	public ResponseEntity<Category_Master_Entity> viewCategory(@RequestParam("cid") String strCategoryId){
		return objMCategoryService.view(strCategoryId);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Category_Master_Entity>> viewAllCategory(){
		return objMCategoryService.viewAll();
	}
	
	@DeleteMapping("/deleteall")
	public ResponseEntity<String> deleteAllCategory(){
		return objMCategoryService.deleteAll();
	}
	
	@GetMapping("/dynamicaccess")
	public ResponseEntity<List<Object>> getDynamicTableAccessValues(
		@RequestParam("tables") String strTables,
		@RequestParam("fields") String strFields,
		@RequestParam("condition") String strCondition
	){
		return objMCategoryService.dynamicDataAccess(strTables, strFields, strCondition);
	}
	
	
}
