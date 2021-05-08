package Ka;

public class RepeatedOrder extends Order {
	// Non-static class fields (don't need to be private, can be protected)
	// default or public access modifiers are also OK, but inferior in this case
	private int period;
	private Date endDate;
	
	// Non-parametrized constructor (just to replace default constructor - but will create 'useless' blank orders)
	public RepeatedOrder() throws DateInvalidException, NegativeOrderAmountException{
		super(); // First let's call the corresponding superclass constructor
		
		// Now, let's use class methods to populate non-superclass fields
		this.setPeriod(0);
		this.setEndDate(new Date());	
	}
	
	// Parametrized constructor
	public RepeatedOrder(String newCustomerID, String newProductID, Date newOrderDate, double newAmount, int newPeriod, Date newEndDate) throws DateInvalidException, NegativeOrderAmountException {
		super(newCustomerID, newProductID, newOrderDate, newAmount); // First let's call the corresponding superclass constructor
		
		// Now, let's use class methods to populate class fields
		this.setPeriod(newPeriod);
		this.setEndDate(newEndDate);	
	}
	 
	// Copy constructor
	public RepeatedOrder(RepeatedOrder otherOrder) throws DateInvalidException {
		super(otherOrder); // First let's call corresponding superclass constructor
		// First, let's make sure we will not run into any null pointer exception
		if (otherOrder != null) {
			// Not null? Great - let's copy the object fields		
			// Copy the non-superclass fields now.
			this.setPeriod(otherOrder.getPeriod());
			this.setEndDate(otherOrder.getEndDate());
		} else {	
			// if otherOrder was null superclass constructor should have taken care of its defaults already
			// Now, let's use class methods to populate non-superclass fields
			this.setPeriod(0);
			this.setEndDate(new Date());	
		}
	}
	
	// period field accessor method
	public int getPeriod(){
		// period is a primitive data type and not a class-based object
		// we can return it directly
		return this.period;
	}
	
	// endDate field accessor method
	public Date getEndDate() throws DateInvalidException{
		// orderDate is an object of Date data type, we need to "copy it" instead of passing reference only
		return new Date(this.endDate.getMonth(), this.endDate.getDay(), this.endDate.getYear());
	}	
	
	// period mutator method
	public void setPeriod(int newPeriod){
		// Some input validation:
		if (newPeriod > 0) {
			this.period = newPeriod;
		} else {
			this.period = 0;
		}
	}
	// endDate mutator method
	public void setEndDate(Date newEndDate) throws DateInvalidException{
		// Some null pointer exception avoidance here:
		if (newEndDate != null){
			// Let's make a copy of newEndDate and "store it"
			this.endDate = new Date(newEndDate.getMonth(), newEndDate.getDay(), newEndDate.getYear());
		} else {
			// else it will have to be a date Object with default date
			this.endDate = new Date();
		}
	}
	public String toString() {
		return 	super.toString() + " | Period: "+ period + " | ED: " + this.endDate;
	}
}