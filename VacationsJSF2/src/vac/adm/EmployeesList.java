package vac.adm;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Named; 

@Named
@RequestScoped
public class EmployeesList {
	@Resource(name="vacRes")
	private DataSource ds;
	
	public ResultSet getEmployeesList() throws SQLException { 		
		Connection conn = ds.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					"Use Vacations "
					+ "SELECT " +
						"E.IdEmploees, " +
						"E.Name, " +
						"E.Position, " +
						"E.TabNumber, " +
						"E.Telephone, " +
						"E.Login, " +
						"E.Admin " +
					"FROM dbo.Employees E ORDER BY E.Name");
			
			CachedRowSet crs = new com.sun.rowset.CachedRowSetImpl();
			crs.populate(result);
			return crs;
		} 
		
		finally
		{
			conn.close();
		}				 						
	}	
}
