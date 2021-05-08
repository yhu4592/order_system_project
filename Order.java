package Ka;

import java.util.InputMismatchException;

public class Order {
	// Static class field used to generate unique order IDs (incremented in the constructor
	// Because it is static it will be the same across all objects of class Order
	private static int nextOrderID = 1;
	
	// Non-static class fields (don't need to be private, can be protected)
	// default or public access modifiers are also OK, but inferior in this case
	private final int orderID;	// final because you cannot change order IDs (have to be unique!), solutions when it can be changed are acceptable
	private String customerID;
	private String productID;
	private Date orderDate; 	// uses Date class from Lab #3, but can be String
	private double amount;		// can be integers too
	
	// Non-parametrized constructor (just to replace default constructor - but will create 'useless' blank orders)
	public Order() throws DateInvalidException, NegativeOrderAmountException{
	
		
			// First let's set the order to 0 to mark it as "useless"
			this.orderID = 0;
			
			// Now, let's use class methods to populate class fields
			this.setCustomerID("");
			this.setProductID("");
			this.setOrderDate(new Date());
			this.setAmount(0.0);		
		}
	
	
	// Parametrized constructor
	public Order(String newCustomerID, String newProductID, Date newOrderDate, double newAmount) throws NegativeOrderAmountException, DateInvalidException {
		// First let's set the order ID and update static order ID counter (nextOrderID
		this.orderID = Order.nextOrderID;
		Order.nextOrderID++;
		
		// Now, let's use class methods to populate class fields
		this.setCustomerID(newCustomerID);
		this.setProductID(newProductID);
		this.setOrderDate(newOrderDate);
		this.setAmount(newAmount);
	}
	 
	// Copy constructor
	public Order(Order otherOrder) throws DateInvalidException {
		// First, let's make sure we will not run into any null pointer exception
		if (otherOrder != null) {
			// Copy the fields now.
			this.orderID = otherOrder.getOrderID();
			this.setCustomerID(otherOrder.getCustomerID());
			this.setProductID(otherOrder.getProductID());
			this.setOrderDate(otherOrder.getOrderDate());
			this.setAmount(otherOrder.getAmount());
		} else
			
					// if otherOrder is null fall back on non-parametrized constructor behavior
					// First let's set the order to 0 to mark it as "useless"
					this.orderID = 0;
					
					// Now, let's use class methods to populate class fields
					this.setCustomerID("");
					this.setProductID("");
					this.setOrderDate(new Date());
					this.setAmount(0.0);
		}
		
	
	
	// orderID field accessor method
	public int getOrderID(){
		// orderID is a primitive data type and not a class-based object
		// we can return it directly
		return this.orderID;
	}
	
	// customerID field accessor method
	public String getCustomerID(){
		// customerID is an object of String data type, we need to "copy it" instead of passing reference only
		return new String(this.customerID);
	}

	// productID field accessor method
	public String getProductID(){
		// productID is an object of String data type, we need to "copy it" instead of passing reference only
		return new String(this.productID);
	}

	// orderDate field accessor method
	public Date getOrderDate() throws DateInvalidException {
		try {
			// orderDate is an object of Date data type, we need to "copy it" instead of passing reference only
			return new Date(this.orderDate.getMonth(), this.orderDate.getDay(), this.orderDate.getYear());
		}
		catch(DateInvalidException e) {
			this.orderDate.setDay(1); this.orderDate.setMonth(1); this.orderDate.setYear(2000);
			return new Date(this.orderDate.getMonth(), this.orderDate.getDay(), this.orderDate.getYear());
		}
	
	}
	// orderDate field accessor method
	public double getAmount(){
		// orderID is a primitive data type and not a class-based object
		// we can return it directly
		return this.amount;
	}	
	
	// customerID mutator method
	public void setCustomerID(String newCustomerID){
		// Let's make a copy of newCustomerID and "store it"
		this.customerID = new String(newCustomerID);
	}
	
	// productID mutator method
	public void setProductID(String newProductID){
		// Let's make a copy of newProductID and "store it"
		this.productID = new String(newProductID);
	}
	
	// orderDate mutator method
	public void setOrderDate(Date newOrderDate) throws DateInvalidException {
		try {
			// Some null pointer exception avoidance here:
			if (newOrderDate != null){
				// Let's make a copy of newOrderDate and "store it"
				this.orderDate = new Date(newOrderDate.getMonth(), newOrderDate.getDay(), newOrderDate.getYear());
			} else {
				// else it will have to be a date Object with default date
				this.orderDate = new Date();
			}
		}
	
	catch (DateInvalidException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	// amount mutator method
	public void setAmount(double newAmount){
		// Some input validation:
		try {
			if(newAmount<0) {
				throw new NegativeOrderAmountException("");
			}
			amount = newAmount;
			}
		
		catch(NegativeOrderAmountException e) {
			System.out.println("You cannot enter a negative order amount!");
			amount = 0;
		}
	}
	
	// toString Method
	public String toString() {
		return 	"O=" + this.orderID + " | C=" + this.customerID + " | P=" + this.productID + " | D=" + this.orderDate + " | A=" + this.amount;
	}
	
	
}