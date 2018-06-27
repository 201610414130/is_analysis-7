package db;

import java.util.List;
import java.util.Map;

public interface DBH {

	List excuteQueryPage(String prepSql, Map param, Class objectClass);

	void excuteQuery(String prepSql, Map param);

	int getPageCount(String prepSql, Map param);

	Object getUsersById(String id, Class objectClass);

	void update(Object object);

	//Í¨¹ýIDÉ¾³ý
	void delete(String id, Class objectClass);

	boolean save(Object object);

}