package mvc.model;

import java.io.FileNotFoundException;
import java.io.IOException;



public interface UserDao {
	
	public boolean check_account(String User) throws FileNotFoundException, IOException;
	public Long check_pass(String UserName, String pass) throws FileNotFoundException, IOException;
	public User findBy_name(String Username) throws FileNotFoundException, IOException ;
	public User findBy_id(Long id);
//	public void store(User user);
}