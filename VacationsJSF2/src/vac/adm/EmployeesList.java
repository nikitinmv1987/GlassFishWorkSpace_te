package vac.adm;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.sql.DataSource;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named; 

@Named
@RequestScoped
public class EmployeesList {
	@Resource(name="vacRes")
	private DataSource ds;
	private List<Employee> dataList;
	
	private HtmlDataTable dataTableEmployees;
	private Employee dataItem = new Employee();
	
	public List<Employee> getEmployeesList() throws SQLException { 		
		Connection conn = ds.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					"Use Vacations " +
					"SELECT " +
						"E.IdEmploees, " +
						"E.Name, " +
						"E.Position, " +
						"E.TabNumber, " +
						"E.Telephone, " +
						"E.Login, " +
						"E.Admin " +
					"FROM dbo.Employees E ORDER BY E.Name");
			
			dataList = new ArrayList<Employee>();			
			while (result.next()) {
				dataList.add(new Employee(
						result.getInt("IdEmploees"),
						result.getString("Name"),
						result.getString("Position"),
						result.getString("TabNumber"),
						result.getString("Telephone"),
						result.getString("Login"),
						result.getBoolean("Admin")						
						));
			}
						
			return dataList;
		} 
		
		finally
		{
			conn.close();
		}				 						
	}

	public String editEmployee() {
		//int index = dataTableEmployees.getRowIndex();
		//System.out.println(index);
		
		dataItem = (Employee) dataTableEmployees.getRowData();					
		System.out.println(dataItem.getIdEmploees());
		
		return "edit";
	}

	public HtmlDataTable getDataTableEmployees() {
		return dataTableEmployees;
	}

	public void setDataTableEmployees(HtmlDataTable dataTableEmployees) {
		this.dataTableEmployees = dataTableEmployees;
	}
}
