package mvc.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.core.JdbcTemplate;

public class MetaLoad {
	
	private JdbcTemplate jdbcTemplate;
	
	private String table = "TAKENITEMS";
	
	@PostConstruct
	public void Load() throws IOException, SQLException
	{
		int baseExist = BaseExist();
		if (baseExist==2)
		{
			final String[] tmp_arr = ConfigReader.getInsallDbScript(
					getClass().getResource("/schema_install.sql").getFile());
			jdbcTemplate.batchUpdate(tmp_arr);
		}
		else
			if (baseExist==0)
					new Exception ("DB is not connected");
	}
	public MetaLoad(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	
	 private Connection getConnection() {
		 	Connection connection =null;
			try{
				connection = jdbcTemplate.getDataSource().getConnection();
	        }catch(SQLException e){
	        	System.out.println("DB is not connected: " + e.getMessage());
	        }
			if (connection!=null)
			{
				return connection;
			}
			else
			{
				return null;
			}
	    }
	 
		private int BaseExist () throws SQLException
		{	
			Connection connection = getConnection();
			
			if (connection==null)
			{
				return 0;
			}
			else
			{
				String[] type = {"TABLE"};
				ResultSet resultSet = connection.getMetaData().getTables(null, "PUBLIC", table, type);
				
				while (resultSet.next())
				{
					return 1;
				}
				return 2;
			}
		}
}
