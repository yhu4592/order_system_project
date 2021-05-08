package Ka;
import java.util.InputMismatchException;

public class Date{
	  private int month;
	  private int day;
	  private int year;

	  /** default constructor
	  *  sets month to 1, day to 1 and year to 2000
	 * @throws DateInvalidException 
	  */
	  public Date( ) throws DateInvalidException   {
	    setDate( 1, 1, 2000 );
	  }

	  /** overloaded constructor
	  *  @param mm    initial value for month
	  *  @param dd    initial value for day
	  *  @param yyyy  initial value for year
	  *
	  *  passes parameters to setDate method
	 * @throws DateInvalidException 
	  */
	  public Date( int mm, int dd, int yyyy ) throws DateInvalidException   {
	    setDate( mm, dd, yyyy );
	  }

	  /* accessor methods */
	  int getMonth( ) { return month; }
	  int getDay( )   { return day; }
	  int getYear( )  { return year; }

	  /** mutator method */
	  /** setDate
	  *  @param mm    new value for month
	  *  @param dd    new value for day
	  *  @param yyyy  new value for year
	  *  passes parameters to setMonth, setDay, and setYear
	 * @throws DateInvalidException 
	  */
	  public void setDate( int mm, int dd, int yyyy ) throws DateInvalidException   {
	    setYear(yyyy);
	    setMonth( mm );
	    setDay( dd );
	  }
	  
	  /** helper methods */  
	  /** setDay
	  *  @param dd new value for day
	  *  if dd is legal day for current month, sets day to dd
	  *  otherwise, sets day to 1
	 * @throws DateInvalidException 
	  */
	  public boolean greaterOrEqual(Date d) {
		  if (year > d.year) {
			  return true;
		  }
		  else if (year == d.year) {
			  if (month > d.month) {
				  return true;
			  }
			  else if (month == d.month) {
				  if (day >= d.day) {
					  return true;
				  }
				  return false;
			  }
			  return false;
		  }
		  return false;
	  }
	  
	  public boolean lessOrEqual(Date d) {
		  if(month <= d.month && day <=d.day && year <= d.year){
			  return true;
		  }
		  else {
			  return false;
		  }
	  }
	  
	  public void setDay( int dd ) throws DateInvalidException   {
	   try {
		   if(dd<=0) {
				 
				 throw new DateInvalidException("You cannot enter a negative day!");
			  	}
		   int [] validDays = { 0,  31, 29, 31, 30,
	                         31, 30, 31, 31, 30,
	                         31, 30, 31 };
		   day = ( dd >= 1 && dd <= validDays[month] ? dd : 1 );
	   }
	   catch(InputMismatchException e) {
		   System.out.println("This is not a valid input!");
	   }
	   catch(DateInvalidException e) {
	    	System.out.println("You cannot enter a negative day!");
	    	day = 1;
	    }
	   
	  } 
	  /** setMonth
	  *  @param mm new value for month
	  *  if mm is between 1 and 12, sets month to mm
	  *  otherwise, sets month to 1
	 * @throws DateInvalidException 
	  */
	  public void setMonth( int mm ) throws DateInvalidException {
		 try{
			if(mm<=0) {
				throw new DateInvalidException("You cannot enter a negative month!");
			}
		  	if(mm>=1&&mm<=12) {
			  month = mm;
		  }
		  else {
	    		month = 1;
	    	}
	  	}
		 catch(InputMismatchException e) {
			 System.out.println("This is not a valid input!");
		 }
		 catch(DateInvalidException e) {
		    	System.out.println("You cannot enter a negative month!");
		    	month = 1;
		    }
	  }
	  /** setYear
	  *  @param yyyy new value for year
	  *  sets year to yyyy
	 * @throws DateInvalidException 
	  */
	  public void setYear( int yyyy ) throws DateInvalidException {
	    try{
	    	
	    	
	    	if(yyyy<=0) {
	    		throw new DateInvalidException("You cannot enter a negative year!");
	    		
	    	}
	    	year = yyyy;
	    }
	    catch(InputMismatchException e) {
	    	System.out.println("This is not a valid input!");
	    	year = 2000;
	    }
	    catch(DateInvalidException e) {
	    	System.out.println("You cannot enter a negative year!");
	    	year = 2000;
	    }
	  }
		
	  /** toString
	  *  @return String
	  *  returns date in mm/dd/yyyy format
	  */
	  public String toString( ) {
	    return month + "/" + day + "/" + year;
	  }

	  /** equals
	  *  @param   d  Date object to compare to this
	  *  @return  true if d is equal to this
	  *           false, otherwise
	  */
	  public boolean equals( Date d ) {
	    if ( month == d.month
	         && day == d.day
	         && year == d.year )
	      return true;
	    else
	      return false;
	 }
	  public boolean leapYear() {
		  if(year%4==0) {
			  if(year % 100==0) {
				  if(year%400==0) {
					  return true;
				  }
			      else {
					  return false;
				  }
			  }
			  else {
				  return true;
			  }
		  }
		  else {
			  return false;
		  }
	  }
	  public static int thrower(int num) throws DateInvalidException{
		  if(num<=0) {
			  throw new DateInvalidException("You cannot enter a negative number!");
		  }
		  else {
			  return num;
		  }
	  	}
	 }
