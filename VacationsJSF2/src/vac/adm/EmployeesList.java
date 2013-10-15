package vac.adm;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private HtmlInputHidden dataItemId = new HtmlInputHidden();
	
	private int rowIndex;
	
	
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

	public String editEmployee() throws SQLException {		
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
		String result ="editEmployee?faces-redirec=true"; 
		
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			dataItem = dataList.get(this.rowIndex - 1);			
	        System.out.println(rowIndex);
	        System.out.println(dataItem.getName());
	    } else {
	    	System.out.println("Не выбрана строка");
	    	result = "";
	    }
		
		dataItemId.setValue(dataItem.getIdEmploees());
		
		return result;
	}

	public HtmlDataTable getDataTableEmployees() {
		return dataTableEmployees;
	}

	public void setDataTableEmployees(HtmlDataTable dataTableEmployees) {
		this.dataTableEmployees = dataTableEmployees;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public HtmlInputHidden getDataItemId() {
		return dataItemId;
	}

	public void setDataItemId(HtmlInputHidden dataItemId) {
		this.dataItemId = dataItemId;
	}
	
	public String saveDataItem() throws SQLException {
        dataItem.setIdEmploees(Integer.parseInt((String) dataItemId.getValue()));

        Connection conn = ds.getConnection();
        try {
			PreparedStatement updateEmployee = conn.prepareStatement(
					"UPDATE Employees " +
					"SET Name = ?, " +
					     "Position = ?, " +
					     "TabNumber = ?, " +
					     "Telephone = ?, " +
					     "Login = ?, " +
					     "Admin = ? " +
					"WHERE IdEmploees = ?");
			updateEmployee.setString(1, dataItem.getName());
			updateEmployee.setString(2, dataItem.getPosition());
			updateEmployee.setString(3, dataItem.getTabNumber());
			updateEmployee.setString(4, dataItem.getTelephone());
			updateEmployee.setString(5, dataItem.getLogin());
			updateEmployee.setBoolean(6, dataItem.getAdmin());
			updateEmployee.setInt(7, dataItem.getIdEmploees());
			
			updateEmployee.execute();
			
		}
		finally {
			conn.close();
		}

        return "employees"; // Navigation case.
    }
	
	public Employee getDataItem() {
		return dataItem;
	}

	public void setDataItem(Employee dataItem) {
		this.dataItem = dataItem;
	}
}
