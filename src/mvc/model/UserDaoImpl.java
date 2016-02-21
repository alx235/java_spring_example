package mvc.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository; 
import org.springframework.stereotype.Service;

@Repository
public class UserDaoImpl extends AbsDao implements UserDao{

public boolean check_account(String user) throws FileNotFoundException, IOException{ 
//	SetEntityManager();
//Query query = entityManager.createNativeQuery("select c.UserName from User c where"
//			+ " (c.UserName=:a)").setParameter("a", User);
//
//return query.getResultList().isEmpty();
	String query = "select c.UserName from Users c where (c.UserName=?1)";
	List <Object> list= new ArrayList <Object>();
	list.add(user);
	return selectQuery(query, list).isEmpty();	
}

public Long check_pass(String userName, String pass) throws FileNotFoundException, IOException{ 
//	SetEntityManager();
//Query query = entityManager.createNativeQuery("select c.password from User c where"
//		+ " (c.password=:a) and (c.UserName=:b)").setParameter("a", pass)
//		.setParameter("b", UserName);
//return query.getResultList().isEmpty();
	String query = "select c.id from Users c where (c.UserName=?1) and (c.password=?2)";
	List <Object> list= new ArrayList <Object>();
	list.add(userName);
	list.add(pass);
	List<?> result = selectQuery(query, list);
	if (result.size()>0)
		return ((BigInteger) selectQuery(query, list).get(0)).longValue();
	return (long) -1;
}


@SuppressWarnings("unchecked")
public User findBy_name(String userName) throws FileNotFoundException, IOException {
	//SetEntityManager();
//	Query query = entityManager.createNativeQuery("select * from User c where"
//			+ " (c.UserName=:a)").setParameter("a", Username);
//	List <User> u = (List<User>) query.getResultList();
	String query = "select * from Users c where (c.UserName=?1)";
	List <Object> list= new ArrayList <Object>();
	list.add(userName);
	List <User> userList = (List<User>) selectQuery(query, list,User.class);	
	User user = null;
	if (userList.size()>0)
		user = userList.get(0);
	return user;
}

@SuppressWarnings("unchecked")
public User findBy_id(Long id) {
	//SetEntityManager();
	String query = "select * from Users c where (c.id=?1)";
	List <Object> list= new ArrayList <Object>();
	list.add(id);
	List <User> u = (List<User>) selectQuery(query, list,User.class);
	//Query query = entityManager.createNativeQuery("select * from User c where"
	//		+ " (c.id=:a)").setParameter("a", id);
	//List <User> u = (List<User>) query.getResultList();
	
	User user = u.get(0);
	
	return user;
}
} 


