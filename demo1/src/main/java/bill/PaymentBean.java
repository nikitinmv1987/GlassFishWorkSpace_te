package bill;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;

	public PaymentBean() {
			
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
}
