package mvc.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TakenItemDao {
	
	public List<Object[]> find_Taken(Long ParentId) throws FileNotFoundException, IOException; 
	public List<Object[]> find_Received(Long UserId) throws FileNotFoundException, IOException; 
	public List<Object[]> find_free(Long id) throws FileNotFoundException, IOException;
	public  void backDisk(Long Userid,Long DiskId) throws FileNotFoundException, IOException;
//	public void store(TakenItem takenItem);
}