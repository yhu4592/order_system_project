package Ka;

import java.util.ArrayList;

public class OfficeSupplyOrderSystem{
	public ArrayList<Order> orderSystem;
	public OfficeSupplyOrderSystem() {
		orderSystem = new ArrayList<Order>();
	}
	
	public void addOrder(Order order) {
		orderSystem.add(order);
	}
	
	public void deleteOrder(int ID) {
		for(int i = 0; i < orderSystem.size();i++) {
			if(orderSystem.get(i).getOrderID()==ID) {
				orderSystem.remove(i);
				break;
			}
		}
	}
	
	public int getSize() {
		return orderSystem.size();
	}
	
	public Order getOrderByID(int ID){
		for( int i = 0; i < orderSystem.size(); i++) {
			if(orderSystem.get(i).getOrderID()==ID) {
				return orderSystem.get(i);
				
			}
		}
		return null;
		//This is to get rid of return error!
		
	}
	
	public void listAllOrders(int year,int month) throws DateInvalidException {
		for( Order o: orderSystem) {
			if((o.getOrderDate().getYear()==year)&&(o.getOrderDate().getMonth()==month)) {
				System.out.println(o);
			}
		}
	}
	
	public void listAllOrders(Date startDate, Date endDate) throws DateInvalidException {
		for(Order o:orderSystem) {
			if((o.getOrderDate().greaterOrEqual(startDate))&&(o.getOrderDate().lessOrEqual(endDate))) {
				System.out.println(o);
			}
		}
	}
	
	
	public void listAllOrders(String pID) {
		for(Order o: orderSystem) {
			if(o.getProductID()==pID) {
				System.out.println(o);
			}
		}
	}
	public double sumAmount(int year, int month) throws DateInvalidException {
		int total = 0;
		for(Order o: orderSystem) {
			if((o.getOrderDate().getYear()==year)&&(o.getOrderDate().getMonth()==month)) {
				total+=o.getAmount();
			}
		}
		return total;
	}
	public double sumAmount(Date startDate, Date endDate) throws DateInvalidException {
		int total = 0;
		for(Order o:orderSystem) {
			if((o.getOrderDate().greaterOrEqual(startDate))&&(o.getOrderDate().lessOrEqual(endDate))) {
				total += o.getAmount();
			}
		}
		return total;
	}
	public double sumAmount(String pID) {
		int total = 0;
		for(Order o: orderSystem){
			if(o.getProductID()==pID) {
				total+=o.getAmount();
			}
		}
		return total;
	}
	
	
}	


