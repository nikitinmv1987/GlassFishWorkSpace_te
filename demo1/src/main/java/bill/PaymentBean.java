package bill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private String txt1;
	
	List<String> list = new ArrayList<String>();  

	public PaymentBean() {
		
		list.add("Предоставление");
		list.add("Предоставление");
		list.add("Размещение");
		list.add("Упоминание");
		list.add("Возможность размещения");
		list.add("Использование");
		list.add("Трансляция");
		list.add("Приоритетное право");
			
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList() {

	}

	public String createPayment() {		
		System.out.println("PaymentBean.createPayment()");
		return "success";		
	}
	
	public String returnToMain() {		
		System.out.println("PaymentBean.returnToMain()");
		return "success";
	}
	
	public String save() {
		System.out.println("PaymentBean.save()");
		FacesContext context = FacesContext.getCurrentInstance();           
        context.addMessage(null, new FacesMessage("Сохранено", "Esc - вернутся"));
        
        return "success";
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}
	
	 public List<String> complete(String query) {  
	        List<String> results = new ArrayList<String>();
	               
	        for (String cur : list) {
				if (cur.toLowerCase().matches(query.toLowerCase() + "(.*)")) {
					results.add(cur);
				}
			}	        
	        return results;  
	    }  
}
