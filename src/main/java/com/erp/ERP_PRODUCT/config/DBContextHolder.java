package com.erp.ERP_PRODUCT.config;

import com.erp.ERP_PRODUCT.ENUM.DataSourceNamesENUM;

public class DBContextHolder {
	
	private static final ThreadLocal<DataSourceNamesENUM> contextHolder= new ThreadLocal<>();
	
	public static void setCurrentDb(DataSourceNamesENUM dbType)
	{
		contextHolder.set(dbType);
	}
	
	public static DataSourceNamesENUM getCurrentDb() {
		return contextHolder.get();
	}
	
	public static void clear() {
		contextHolder.remove();
	}
}
