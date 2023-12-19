package com.erp.ERP_PRODUCT.entity.Master;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="MCATEGORY")
public class Category_Master_Entity {
	
	@Id
	@Column(name="cid", nullable = false)
	private String cid;
	private String cname;
	private String ctype;
}
