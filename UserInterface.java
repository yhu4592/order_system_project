package Ka;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class UserInterface{
	public static void main (String [] args) throws InputMismatchException, DateInvalidException, FileNotFoundException, NegativeOrderAmountException{
		OfficeSupplyOrderSystem test = new OfficeSupplyOrderSystem();
		Scanner scan = new Scanner(System.in);
		int input = 0;
		System.out.println("Press 1 to load file."+ "\nPress 2 to add order or repeated order." + "\nPress 3 to delete an order by ID."+"\nPress 4 to display list of orders."+"\nPress 5 to calculate inventory report."+"\nPress 6 to exit." + "\nWhat would you like to do?");
		input = scan.nextInt();
		//treats userinput as "buttons"
		while(input!=6) {
			
			if(input==1) {
				try{
					//adds orders by file
					Scanner fName = new Scanner(System.in);
					System.out.println("Please enter the order file name.");
					String name = fName.nextLine();
					Scanner oFile = new Scanner(new File(name));
					String[] list;
					String[] date;
					String line;
					String[] date2;
					while(oFile.hasNextLine()) {
						line = oFile.nextLine();
						list = line.split(",");
						//splits each line by commas, companies names with the ", Inc." gets tricky
						if(list[0].equals("O")) {
							boolean inc = true;
							if(list[3].indexOf("/")>=0) {
								inc = false;
							}
						
							if(inc) {
								//if a company name has the ", Inc." since it has a comma, it get split
								date = list[4].split("/");
								int day = Integer.parseInt(date[1]);
								int month = Integer.parseInt(date[0]);
								int year = Integer.parseInt(date[2]);
								int a = Integer.parseInt(list[5]);
								test.addOrder(new Order(list[1]+list[2], list[3], new Date(month,day,year), a));
								System.out.println("Order added.");
							}
							else {
								//if it does not have a ", Inc." with the comma
								date = list[3].split("/");
							
							
							int day = Integer.parseInt(date[0]);
							
							int month = Integer.parseInt(date[1]);
							int year = Integer.parseInt(date[2]);
							int a = Integer.parseInt(list[4]);
								test.addOrder(new Order(list[1], list[2], new Date(month,day,year), a));
								System.out.println("Order added");
							}
						}
						else if(list[0].equals("R")){
								//above documentation
								boolean inc = true;
								if(list[3].indexOf("/")>=0) {
									inc = false;
								}
							
							if(inc) {
							date = list[4].split("/");
							int day = Integer.parseInt(date[1]);
							int month = Integer.parseInt(date[0]);
							int year = Integer.parseInt(date[2]);
							int a = Integer.parseInt(list[5]);
							int p = Integer.parseInt(list[6]);
							date2 = list[7].split("/");
							int day2 = Integer.parseInt(date2[1]);
							int month2 = Integer.parseInt(date[0]);
							int year2 = Integer.parseInt(date[2]);
							test.addOrder(new RepeatedOrder(list[1]+list[2], list[3], new Date(month,day,year), a, p, new Date(month2, day2, year2)));
							System.out.println("Repeated order added.");
							}
							else {
								date = list[3].split("/");
								int day = Integer.parseInt(date[0]);
								int month = Integer.parseInt(date[1]);
								int year = Integer.parseInt(date[2]);
								int a = Integer.parseInt(list[4]);
								int p = Integer.parseInt(list[5]);
								date2 = list[6].split("/");
								int day2 = Integer.parseInt(date2[1]);
								int month2 = Integer.parseInt(date[0]);
								int year2 = Integer.parseInt(date[2]);
								test.addOrder(new RepeatedOrder(list[1], list[2], new Date(month,day,year), a, p, new Date(month2, day2, year2)));
								System.out.println("Repeated order added.");
							}
						}
					}
				}
				catch(FileNotFoundException e) {
					System.out.println("File not found.");
				}
				
				
			}
			else if(input ==2) {
				System.out.print("Press 1 to add order. Press 2 to add repeated order.");
				//choose what type of order, UI will ask information for parameters to create instance of order/repeated order
				int oInput = scan.nextInt();
				scan.nextLine();
				if(oInput == 1) {
					System.out.println("Enter the CustomerID.");
					String cID = scan.nextLine();
					System.out.println("Enter the ProductID.");
					String pID = scan.nextLine();
					System.out.println("Enter the day.");
					int d = scan.nextInt();
					System.out.println("Enter the month.");
					int m = scan.nextInt();
					System.out.println("Enter the year.");
					int y = scan.nextInt();
					System.out.println("Enter the order amount.");
					double a = scan.nextDouble();
					test.addOrder(new Order(cID, pID, new Date(m,d,y), a));
				}
				else if (oInput ==2) {
					System.out.println("Enter the CustomerID.");
					String cID = scan.nextLine();
					System.out.println("Enter the ProductID.");
					String pID = scan.nextLine();
					System.out.println("Enter the day.");
					int d = scan.nextInt();
					System.out.println("Enter the month.");
					int m = scan.nextInt();
					System.out.println("Enter the year.");
					int y = scan.nextInt();
					System.out.println("Enter the order amount.");
					Double a = scan.nextDouble();
					System.out.println("Enter the repeated order period.");
					int period = scan.nextInt();
					System.out.println("Enter the ending day.");
					int rd = scan.nextInt();
					System.out.println("Enter the ending month.");
					int rm = scan.nextInt();
					System.out.println("Enter the ending year.");
					int ry = scan.nextInt();
					test.addOrder(new RepeatedOrder(cID, pID, new Date( m, d,y), a, period, new Date(rm, rd, ry)));
				}
				System.out.println("Order Added.");
				
			}
			else if(input ==3) {
				//delete order by ORDER ID
				if(test.getSize()==0) {
					System.out.println("There are no orders.");
					
				}
				else {
					System.out.println("Enter the order ID to delete.");
					int d = scan.nextInt();
					test.deleteOrder(d);
					System.out.println("Order deleted.");
				}
				
			}
			else if(input ==4) {
				//a arraylist of orders with the CustomerID is created, it is then sorted by date
				scan.nextLine();
				System.out.println("Enter a CustomerID to list orders (without the comma).");
				String IC = scan.nextLine();
				ArrayList<Order> list = new ArrayList<Order>();
				for(int i = 0; i < test.getSize(); i++) {
					if(test.orderSystem.get(i).getCustomerID().equals(IC)||test.orderSystem.get(i).getCustomerID().equals(IC+" Inc.")){
						list.add(test.orderSystem.get(i));
					}
				}
				int j; Order temp;
				for ( int i = 1; i < list.size(); i++ )
				{
					j = i;
					temp = list.get(i);
					while ( j != 0 && list.get(j - 1).getOrderDate().greaterOrEqual(temp.getOrderDate()) == true )
					{
					
						list.set(j,list.get(j - 1));
						j--;
					}
					list.set(j,temp);
				}
				for(int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
				}
				System.out.println("If no orders appear, there are no orders by that ID.");
				
			}
			else if(input==5) {
				//calculates inventory report by year, month, or day
				System.out.println("Press 1 to load by year. \nPress 2 to load by month. \nPress 3 to load by ProductID.");
				int i3 = scan.nextInt();
				ArrayList<Order> list = new ArrayList<Order>();
				if(i3==1) {
					System.out.println("Enter the year.");
					int y = scan.nextInt();
					
					for(int i = 0; i < test.getSize();i++) {
						if(test.orderSystem.get(i).getOrderDate().getYear()==y) {
							list.add(test.orderSystem.get(i));
						}
					}
					
				}
				else if(i3==2) {
					System.out.println("Enter the month.");
						int m = scan.nextInt();
					
						for(int i = 0; i < test.getSize();i++) {
							if(test.orderSystem.get(i).getOrderDate().getMonth()==m) {
								list.add(test.orderSystem.get(i));
							}
						}
						
					}
				else if(i3==3) {
					System.out.println("Enter the productID.");
					String p = scan.nextLine();
					
					for(int i = 0; i < test.getSize();i++) {
						if(test.orderSystem.get(i).getProductID().contentEquals(p)) {
							list.add(test.orderSystem.get(i));
						}
					}
					
				}
				for(int i = 0; i < list.size();i++) {
					System.out.println(list.get(i).toString());
				}
				System.out.println("If no orders appear, there are no orders that exist with that information.");
			}
			System.out.println("");
			System.out.println("Press 1 to load file."+ "\nPress 2 to add order or repeated order." + "\nPress 3 to delete an order by ID."+"\nPress 4 to display list of orders."+"\nPress 5 to calculate inventory report."+"\nPress 6 to exit." + "\nWhat would you like to do?");
			input = scan.nextInt();
			
		}
		System.out.println("Have a nice day?");
	}
}
