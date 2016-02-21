package mvc.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class DefaultDaoImpl extends AbsDao implements DefaultDao {
	
	String table = "TAKENITEMS";
	
	String error;
	
	public String get_error()
	{
		return error;
	}
	
	public DefaultDaoImpl()
	{
		System.out.println("Dao created");
	}
	
	public int CheckBase () throws SQLException
	{	
		EntityManager entityManager  = getEntityManager();
		Connection connection = getConnection(entityManager);
		
		if (connection==null)
		{
			return 0;
		}
		else
		{
			String[] type = {"TABLE"};
			ResultSet resultSet = null;
			try{
				resultSet = connection.getMetaData().getTables(null, "PUBLIC", table, type);
			}
			finally{
				entityManager.close();
			}
			while (resultSet.next())
			{
				if (check_Users())
				{
					return 2;
				}
				else
				{
					return 1;
				}
			}
				return 0;
		}
	}
	
	 private Connection getConnection(EntityManager entityManager) {
		//setEntityManager();
        Session session = (Session) entityManager.getDelegate();
        SessionFactoryImpl sessionFactory = (SessionFactoryImpl) session.getSessionFactory();
        Connection connection = null;
		try{
            connection = sessionFactory.getConnectionProvider().getConnection();
        }catch(SQLException e){
        	error = "DB is not connectd: " + e.getMessage();
        }
		if (error==null)
		{
			return connection;
		}
		else
		{
			return null;
		}
    }
	 
	 public boolean check_Users(){ 
		 //SetEntityManager();
//	 Query query = entityManager.createQuery("select c.UserName from Users c where"
//	 		+ " not c.UserName is null");
//	 return query.getResultList().isEmpty();
		 String query = "select c.UserName from Users c where not c.UserName is null";
		 return selectQuery(query).isEmpty();
	 }	

}
