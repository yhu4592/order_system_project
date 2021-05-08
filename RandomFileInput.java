package Ka;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;

public class RandomFileInput {
	public static void main(String args[]) throws DateInvalidException {
		
		ArrayList<String> list = new ArrayList<String>();
		String[] letters = {"A", "B", "C", "D", "E", "F"};
		String[] nums = {"1", "2", "3","4","5"};
		int[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
		int[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		int[] monthDay = {31,29,31,30,31,30,31,31,30,31,30,31};
		Scanner names = new Scanner(System.in);
		try {
			System.out.println("Enter name of file of company names.");
			String nameList = names.nextLine();
			Scanner scan = new Scanner(new File(nameList));
			while(scan.hasNextLine()) {
				String company = scan.nextLine();
				list.add(company);
			}
			File file = new File("randomFileInput.txt");
			//if(!file.exists()) {
				//file.createNewFile();
			//}
			PrintWriter pw = new PrintWriter(file);
			System.out.println("How many random orders do you want to create?");
			int counter = 0;
			int num=names.nextInt();
			while(counter < num) {
				//loops to create number of orders
				counter++;
				int random = (int)(Math.random()*2+1);
				if(random==1) {
					String input = "O,";
					input += list.get((int)(Math.random()*list.size())) + ",";
					input += letters[(int)(Math.random()*3)] + nums[(int)(Math.random()*3)] + ",";
					Date in = new Date(months[(int)(Math.random()*12)],days[(int)(Math.random()*31)],2019);
					input+=in.toString() + ","+(int)(Math.random()*99+1);
					pw.println(input);
				}
				else {
					String input2 = "R,";
	
					input2 += list.get((int)(Math.random()*list.size())) + ",";
					input2 += letters[(int)(Math.random()*3)] + nums[(int)(Math.random()*3)] + ",";
					Date in = new Date(months[(int)(Math.random()*12)],days[(int)(Math.random()*31)],2019);
					input2+=in.toString() +","+ (int)(Math.random()*99+1)+",";
					int period = (int)(Math.random()*30+1);
					input2+=period+",";
					if(in.getDay()+period>monthDay[in.getMonth()-1]) {
						in.setDay(in.getDay()+period-monthDay[in.getMonth()-1]);
						in.setMonth(in.getMonth()+1);
						//calculates end date month and day with repeated orders
					}
					else {
						in.setDay(in.getDay()+period);
					}
					input2 += in.toString();
					pw.println(input2);
				}
			}
			pw.close();
			System.out.println("Orders created.");
		}
		catch(FileNotFoundException e) {
			System.out.println("File could not be found.");
			
		}
		
	}
}
