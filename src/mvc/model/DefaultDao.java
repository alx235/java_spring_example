package mvc.model;

import java.sql.SQLException;

public interface DefaultDao{
	
	public int CheckBase() throws SQLException;
	public String get_error();
	public boolean check_Users();
}
