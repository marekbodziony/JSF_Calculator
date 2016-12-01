package atj;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

@ManagedBean (name="calc", eager=true)
@SessionScoped
public class Calculator {
	
	private String a = "0";
	private String b = "0";
	private String result = "0";
	private String action = "";
	private String hide = "false";
	private String pageBackgroundColor = "silver";	

	
	public String getPageBackgroundColor() {
		return pageBackgroundColor;
	}

	public void setPageBackgroundColor(String pageBackgroundColor) {
		this.pageBackgroundColor = pageBackgroundColor;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		if (this.a.length() == 1 && this.a.substring(0, 1).equals("0") && !a.equals(".")) {this.a = "";}	// delete first "0" from String
		if (a.equals(".") && this.a.contains(".")) {a = "";}												// if user select "," second time do nothing
		if (action.equals("equals")){
			this.a = "";
			action = "";
		}
		this.a += a;
		result = this.a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	// cancel 
	public void cancel(){
		a = "0";
		b = "0";
		result = "0";
		action = "";
		hide = "false";
	}
	// add 
	public void add(){
		if (action.equals("equals") || !action.equals("")){ a = result;}
		action="add";
		b = a;
		result = a;
		a = "0";
	}
	// subtraction
	public void sub(){
		if (action.equals("equals") || !action.equals("")){ a = result;}
		action="sub";
		b = a;
		result = a;
		a = "0";
	}
	// multiplication
	public void multi(){
		if (action.equals("equals") || !action.equals("")){ a = result;}
		action="multi";
		b = a;
		result = a;
		a = "0";
	}
	// division
	public void divi(){
		if (action.equals("equals") || !action.equals("")){ a = result;}
		action="divi";
		b = a;
		result = a;
		a = "0";
	}
	// sqrt
	public void sqrt(){
		if (action.equals("equals") || !action.equals("")){ a = result;}
		action = "sqrt";
		equals();
	}
	// change sign
	public void sign(){
		if (action.equals("equals")){ a = result;}
		if (!action.equals("")){
			float s = Float.parseFloat(a) * (-1);
			a = "" + s;
			result = a;
		}
		else{
			action = "sign";
			equals();
		}		
	}
	// percent
	public void percent(){
		if (action.equals("multi")){
			float p = Float.parseFloat(b) * Float.parseFloat(a) * 0.01f;
			result = "" + p;
			action = "equals";
		}
	}
	// equals
	public void equals(){
		if (action.equals("add")){
			float sum = Float.parseFloat(b) + Float.parseFloat(a);
			result = ""+sum;
		}
		else if (action.equals("sub")){
			float sub = Float.parseFloat(b) - Float.parseFloat(a);
			result = ""+sub;
		}
		else if (action.equals("multi")){
			float multi = Float.parseFloat(b) * Float.parseFloat(a);
			result = ""+multi;
		}
		else if (action.equals("divi")){
			if (a.equals("0")){
				error();
				}
			else{
				float divi = Float.parseFloat(b) / Float.parseFloat(a);
				result = ""+divi;
			}
		}
		else if (action.equals("sqrt")){
			double a = Double.parseDouble(this.a);
			if (a < 0) {error();}
			else{
				result = "" + Math.sqrt(a);
			}
		}
		else if (action.equals("sign")){
			float s = Float.parseFloat(a) * (-1);
			result = ""+ s;
		}
		action = "equals";
	}

	public String getHide() {
		return hide;
	}

	public void setHide(String hide) {
		this.hide = hide;
	}
	// error
	private void error(){
		hide = "true";
		result = "ERR";
	}

}
