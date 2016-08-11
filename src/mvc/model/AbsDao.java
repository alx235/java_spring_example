package mvc.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//àáñòðàêòíûé êëàññ ääÿ ñîçäàíèÿ entityManager
//è âûïîëíåíèÿ òðàíçàêöèé


public abstract class AbsDao{
	/*@Autowired
	private EntityManagerFactory entityManagerFactory;*/
	
	@PersistenceContext
	private EntityManager entityManager;//shared EntityManager
	
	public void store(Object obj) { 
		
		getStandartTransact(obj);
	}
	
	protected EntityManager getEntityManager()
	{
		return entityManager;
	}

	private void getStandartTransact(Object obj)
	{
	EntityManager entityManager  = getEntityManager();
	EntityTransaction tx = entityManager.getTransaction();
		try{
			tx.begin(); 

				entityManager.persist(obj); 
				entityManager.flush();
				
			tx.commit(); 
		}
		catch (RuntimeException e) { 
		tx.rollback(); 
		throw e; 
		} 
		finally { 
			entityManager.close();
		}
	}

	protected int execQuery(String query,List<?> obj_par_ar)
	{	
			return execQuery_(query,obj_par_ar);
	}
	
	protected int execQuery(String query)
	{	
			return execQuery_(query,null);
	}

	@SuppressWarnings("finally")
	protected int execQuery_(String query,List<?> obj_par_ar)
	{	
	EntityManager entityManager  = getEntityManager();
	int m = -1;
	Query query_ = entityManager.createNativeQuery(query);
	querySetParams(query_,obj_par_ar);
	EntityTransaction tx = entityManager.getTransaction();
		try{
			tx.begin(); 
			m = query_.executeUpdate();
			tx.commit(); 
		}
		catch (RuntimeException e) { 
		tx.rollback(); 
		throw e; 
		} 
		finally { 
			entityManager.close();
			return m;
		}
	}
	
	protected <T> List<?> selectQuery(String query,List<?> obj_par_ar,Class<T> cls)
	{	
			return selectQuery_(query,obj_par_ar,cls);
	}

	protected List<?> selectQuery(String query,List<?> obj_par_ar)
	{	
			return selectQuery_(query,obj_par_ar,null);
	}

	protected <T> List<?> selectQuery(String query,Class<T> cls)
	{	
			return selectQuery_(query,null,cls);
	}	
	
	protected List<?> selectQuery(String query)
	{	
			return selectQuery_(query,null,null);
	}	
	
	@SuppressWarnings("finally")
	protected <T> List<?> selectQuery_(String query,List<?> obj_par_ar,Class<T> cls)
	{	
	EntityManager entityManager  = getEntityManager();
	List<?> list = null;
	Query query_ = null;
	if (cls!=null)
		query_ = entityManager.createNativeQuery(query,cls);
	else
		query_ = entityManager.createNativeQuery(query);
	querySetParams(query_,obj_par_ar);
	EntityTransaction tx = entityManager.getTransaction();
		try{
			tx.begin(); 
			list = query_.getResultList();	
			tx.commit(); 
		}
		catch (RuntimeException e) { 
		//tx.rollback(); 
		throw e; 
		} 
		finally { 
			entityManager.close();
			return list;
		}
	}
	
	private void querySetParams(Query query_,List<?> obj_par_ar)
	{
		int n =-1;
		if (obj_par_ar!=null)
			n = obj_par_ar.size();
		for (int i=1;i<n+1;i++)
		{
			query_.setParameter(i, obj_par_ar.get(i-1));
		}
	}
	
	
}
