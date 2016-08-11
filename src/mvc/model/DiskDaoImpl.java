package mvc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class DiskDaoImpl extends AbsDao implements DiskDao  {
	
	@SuppressWarnings("unchecked")
	public Disk findBy_id(Long id) {
		String query = "select * from Disks c where (c.id=:a)";
		List <Object> list= new ArrayList <Object>();
		list.add(id);
		selectQuery(query, list);
		//List <Disk> u = (List<Disk>) query.getResultList();
		List <Disk> u = (List<Disk>) selectQuery(query, list);
		Disk disk = u.get(0);
		
		return disk;
	}

} 
