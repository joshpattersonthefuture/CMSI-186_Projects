public class CountTheDays{
	public static void main(String[] args){
		if (args.length != 6){
			System.out.println("Not enough args");
		}else{
		    long month1 = Long.parseLong(args[0]);
		    long day1 = Long.parseLong(args[1]);
		    long year1 = Long.parseLong(args[2]);
		    long month2 = Long.parseLong(args[3]);
		    long day2 = Long.parseLong(args[4]);
		    long year2 = Long.parseLong(args[5]);
			if (CalendarStuff.isValidDate(month1,day1,year1)==true){
			    if (CalendarStuff.isValidDate(month2,day2,year2)==true){
				    long dayCount = CalendarStuff.daysBetween(month1,day1,year1,month2,day2,year2);
					System.out.println("dayCount = " + dayCount);
			    }else{
					System.out.println("date not valid");
				}
		    }else{
				System.out.println("date not valid");
			}
		}
	}
}
