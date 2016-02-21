package mvc.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

import javax.persistence.Query; 

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class TakenItemDaoImpl extends AbsDao implements TakenItemDao {

@SuppressWarnings("unchecked")
public List<Object[]> find_Taken(Long parentId) throws FileNotFoundException, IOException { 
//	SetEntityManager();
//Query query = entityManager.createNativeQuery("select select b.DiskName,b.id,b.UserId from"
//		+ " TakenItem a,Users c, Disks b where"
//		+ " a.UserId=c.id and a.DiskId=b.id and"
//		+ " (c.id=:a)").setParameter("a", ParentId); 
//return (List<Object[]>) query.getResultList();
	String query = "select b.DiskName,c.UserName,a.DiskId from TakenItems a,Users c, Disks b where"
			+ " a.UserId=c.id and a.DiskId=b.id and (c.id=?1)";
	List <Object> list= new ArrayList <Object>();
	list.add(parentId);
	return  (List<Object[]>) selectQuery(query, list);
} 

@SuppressWarnings("unchecked")
public List<Object[]> find_Received(Long userId) throws FileNotFoundException, IOException { 
	
//	SetEntityManager();
//Query query = entityManager.createNativeQuery("select b.DiskName,c.UserName,a.DiskId from"
//		+ " TakenItem a,Users c, Disks b where"
//		+ " a.UserId=c.id and a.DiskId=b.id and" 
//		+ " (UserId=:a)").setParameter("a", UserId); 
//return (List<Object[]>) query.getResultList(); 
	String query = "select b.DiskName,c.UserName,a.DiskId from TakenItems a,Users c, Disks b where"
			+ " a.UserId=c.id and a.DiskId=b.id and (UserId=?1)";
	List <Object> list= new ArrayList <Object>();
	list.add(userId);
	return  (List<Object[]>) selectQuery(query, list);
}

@SuppressWarnings("unchecked")
public  List<Object[]> find_free(Long id) throws FileNotFoundException, IOException{ 
//	SetEntityManager();
//	
//Query query = entityManager.createNativeQuery("select b.DiskName,b.id,b.UserId from"
//		+ " Users c, Disks b where"
//		+ " b.UserId=a.id"
//		+ " and not exist"
//		+ " (select a.DiskId from TakenItem a"
//		+ " where a.DiskId=b.id")
//		.setParameter("a", id);
//		
//return  (List<Object[]>) query.getResultList();
	String query = "select b.DiskName,b.id,b.UserId from Users c, Disks b where b.UserId=c.id"
			+ " and b.id<>?1 and not exists"
			+ " (select a.DiskId from TakenItems a where a.DiskId=b.id)";
	List <Object> list= new ArrayList <Object>();
	list.add(id);
	return  (List<Object[]>) selectQuery(query, list);
	
}

public  void backDisk(Long userId,Long diskId) throws FileNotFoundException, IOException{ 
//	SetEntityManager();
//	
//Query query = entityManager.createNativeQuery("delete from TakenItem a where (a.Userid=:a) " +
//		" and (a.DiskId=:b)").setParameter("a", Userid)
//		.setParameter("b", DiskId);
//query.executeUpdate();
	String query = "delete from TakenItems a where (a.Userid=?1) and (a.DiskId=?2)";
	List <Object> list= new ArrayList <Object>();
	list.add(userId);
	list.add(diskId);
	execQuery(query, list);
}

}

