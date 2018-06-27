package db.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import db.DBH;

@Service("dbh")
@Transactional
public class DBHImpl implements DBH {
	/* (non-Javadoc)
	 * @see db.DBHi#excuteQueryPage(java.lang.String, java.util.Map, java.lang.Class)
	 */
	@Override
	public List excuteQueryPage(String prepSql,Map param, Class objectClass){
//		Session session = HibernateUntil.getSession();
//		Transaction tx = null;
//		List<Inventory> list = null;
//		try{
//			tx = session.beginTransaction();
//			SQLQuery sqlQuery = session.createSQLQuery(prepSql).addEntity(objectClass);
//			if(param != null){
//				for(int i = 0;i < param.size(); i += 1){
//					sqlQuery.setParameter(i, param.get(i));
//				}
//			}
//			tx.commit();
//			list = sqlQuery.list();
//			return list;
//
//		} catch (Exception e){
//			tx.rollback();
//			throw new RuntimeException(e);
//		}finally{
//			HibernateUntil.closeSession();
//		}
		
//		List<Object> list = null;
//		SQLQuery sqlQuery = getSession().createSQLQuery(prepSql).addEntity(objectClass);
//		if(param != null){
//			for(int i = 0;i < param.size(); i += 1){
//				sqlQuery.setParameter(i, param.get(i));
//			}
//		}
//		list = sqlQuery.list();
//		return list;
		
//		 HibernateTemplate tmpl = getHibernateTemplate();  
//		 return tmpl.execute(new HibernateCallback<List<Object[]>>() {  
//		     @SuppressWarnings("unchecked")  
//		     @Override  
//		     public List<Object[]> doInHibernate(Session session)  
//		         throws HibernateException  {  
//		         SQLQuery query = session.createSQLQuery(prepSql).addEntity(objectClass);  
//		         for(int i = 0;i < param.size(); i += 1){
//		        	 query.setParameter(i, param.get(i));
//					}
//		         List<Object[]> results = query.list();  //因为只有查询一个列，所以返回的List是List<Object>  
//		         return results;  
//		     }  
//		 }); 
		List<Object> list = null;
		SQLQuery sqlQuery = getSession().createSQLQuery(prepSql).addEntity(objectClass);
		if(param != null){
			for(int i = 0;i < param.size(); i += 1){
				sqlQuery.setParameter(i, param.get(i));
			}
		}
		list = sqlQuery.list();
		return list;
	}
	
	/* (non-Javadoc)
	 * @see db.DBHi#excuteQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public void excuteQuery(String prepSql,Map param){
//		Session session = HibernateUntil.getSession();
//		Transaction tx = null;
//		Class object = Inventory.class;
//		try{
//			tx = session.beginTransaction();
//			SQLQuery sqlQuery = session.createSQLQuery(prepSql);
//			if(param != null){
//				for(int i = 0;i < param.size(); i += 1){
//					sqlQuery.setParameter(i, param.get(i));
//				}
//			}
//			tx.commit();
//
//		} catch (Exception e){
//			tx.rollback();
//			throw new RuntimeException(e);
//		}finally{
//			HibernateUntil.closeSession();
//		}
		SQLQuery sqlQuery = getSession().createSQLQuery(prepSql);
		if(param != null){
			for(int i = 0;i < param.size(); i += 1){
				sqlQuery.setParameter(i, param.get(i));
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see db.DBHi#getPageCount(java.lang.String, java.util.Map)
	 */
	@Override
	public int getPageCount(String prepSql,Map param){
//		Session session = HibernateUntil.getSession();
//		Transaction tx = null;
//		List<Object> list = null;
//		Class object = Inventory.class;
//		try{
//			tx = session.beginTransaction();
//			SQLQuery sqlQuery = session.createSQLQuery(prepSql);
//			if(param != null){
//				for(int i = 0;i < param.size(); i += 1){
//					sqlQuery.setParameter(i, param.get(i));
//				}
//			}
//			tx.commit();
//			list = sqlQuery.list();
//			return Integer.valueOf(list.get(0).toString());
//		} catch (Exception e){
//			tx.rollback();
//			throw new RuntimeException(e);
//		}finally{
//			HibernateUntil.closeSession();
//		}
		List<Object> list = null;
		
		
		SQLQuery sqlQuery = getSession().createSQLQuery(prepSql);;
		if(param != null){
			for(int i = 0;i < param.size(); i += 1){
				sqlQuery.setParameter(i, param.get(i));
			}
		}
		list = sqlQuery.list();
		return Integer.valueOf(list.get(0).toString());
	}
	
	/* (non-Javadoc)
	 * @see db.DBHi#getUsersById(java.lang.String, java.lang.Class)
	 */
	@Override
	public Object getUsersById(String id, Class objectClass){
//		Session session = HibernateUntil.getSession();
//		Transaction tx = null;
//		try{
//			tx = session.beginTransaction();
//			Object object = session.get(objectClass, id);
//			
//			tx.commit();
//			return object;
//		} catch (Exception e){
//			tx.rollback();
//			throw new RuntimeException(e);
//		}finally{
//			HibernateUntil.closeSession();
//		}
		Object object = getSession().get(objectClass, id);
		return object;
	}
	
	/* (non-Javadoc)
	 * @see db.DBHi#update(java.lang.Object)
	 */
	@Override
	public void update(Object object){
//		Session session = HibernateUntil.getSession();
//		Transaction tx = null;
//		try{
//			tx = session.beginTransaction();
//			session.update(object);
//			tx.commit();
//		} catch (Exception e){
//			tx.rollback();
//			throw new RuntimeException(e);
//		}finally{
//			HibernateUntil.closeSession();
//		}
		getSession().update(object);
	}
	//通过ID删除
		/* (non-Javadoc)
		 * @see db.DBHi#delete(java.lang.String, java.lang.Class)
		 */
		@Override
		public void delete(String id, Class objectClass){
//			Session session = HibernateUntil.getSession();
//			Transaction tx = null;
//			try{
//				tx = session.beginTransaction();
//				session.delete(session.get(objectClass, id));
//				tx.commit();
//			} catch (Exception e){
//				tx.rollback();
//				throw new RuntimeException(e);
//			}finally{
//				HibernateUntil.closeSession();
//			}
			getSession().delete(getSession().get(objectClass, id));
	}
		/* (non-Javadoc)
		 * @see db.DBHi#save(java.lang.Object)
		 */
		@Override
		public boolean save(Object object)
		{
//			Session session = HibernateUntil.getSession();
//			Transaction tx = null;
//			try{
//				tx = session.beginTransaction();
//				session.save(object);
//				tx.commit();
//			} catch (Exception e){
//				tx.rollback();
//				throw new RuntimeException(e);
//			}finally{
//				HibernateUntil.closeSession();
//			}
			getSession().save(object);
			return true;
		}
		
		public DBHImpl() {
			super();
			// TODO Auto-generated constructor stub
		}

		/*Spring和Hibernate整合后*/ 
		//放在属性上面，就不会调用set方法，使用
	    //反射注进来，所以可以把set方法干掉了
		@Resource
	    private SessionFactory sessionFactory; 
	      
	    //当需要使用sessoinFactory的时候，Spring会将sessionFactory注入进来  
//	    public void setSessionFactory(SessionFactory sessionFactory) {  
//	        this.sessionFactory = sessionFactory;  
//	    }    
	    protected Session getSession() {
	        //从当前线程获取session，如果没有则创建一个新的session  
	        return sessionFactory.getCurrentSession();  
	    } 
	    
	    
	    
	    
//	    @Autowired    
//	    public void setSessionFactoryOverride(SessionFactory sessionFactory)    
//	    {    
//	    
//	        super.setSessionFactory(sessionFactory);    
//	    }   
//	    public Session getSession(){  
//	        Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();  
//	        return session;  
//	    } 
	    
}
