package sample_test;

import java.util.HashMap;
import java.util.Scanner;

public class thoughtworks {
	
	public static void day_diff() {
		
		int day_counter = 0;
		String[] start_date = null;
		String[] end_date = null;
		
		System.out.println("Enter current date in dd-mm-yyyy format : ");
		Scanner sc = new Scanner(System.in);
		String current_date = sc.nextLine();
		System.out.println("Enter date to check difference in dd-mm-yyyy format : ");
		String check_date = sc.nextLine();
		
		System.out.println("Current date = "+current_date);
		System.out.println("Check date = "+check_date);
		
		
		if(date_check(current_date) != null && date_check(check_date) != null) {
			if(Integer.parseInt(transform_date(current_date)) < Integer.parseInt(transform_date(check_date))){
				start_date = current_date.split("-");
				end_date = check_date.split("-");
			}
			else {
				start_date = check_date.split("-");
				end_date = current_date.split("-");
			}
			day_counter = traverse_to_date(start_date, end_date);
			System.out.println("The difference between "+current_date+" and "+check_date+" is "+day_counter+" days");
		}
		sc.close();	
	}
	
	
	public static HashMap<Integer, Integer> get_year_map(String[] date){
		HashMap<Integer, Integer> date_limit = new HashMap<Integer, Integer>();
		
		//map specifies the month of the year and its corresponding date limit
		date_limit.put(1, 31);
		date_limit.put(3, 31);
		date_limit.put(4, 30);
		date_limit.put(5, 31);
		date_limit.put(6, 30);
		date_limit.put(7, 31);
		date_limit.put(8, 31);
		date_limit.put(9, 30);
		date_limit.put(10, 31);
		date_limit.put(11, 30);
		date_limit.put(12, 31);
		
		int year = Integer.parseInt(date[2]);
		
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				date_limit.put(2, 29);
			}
			else {
				date_limit.put(2, 28);
			}
		return date_limit;
	}
	
	public static HashMap<Integer, Integer> get_year_map(int year){
		HashMap<Integer, Integer> date_limit = new HashMap<Integer, Integer>();
		
		//map specifies the month of the year and its corresponding date limit
		date_limit.put(1, 31);
		date_limit.put(3, 31);
		date_limit.put(4, 30);
		date_limit.put(5, 31);
		date_limit.put(6, 30);
		date_limit.put(7, 31);
		date_limit.put(8, 31);
		date_limit.put(9, 30);
		date_limit.put(10, 31);
		date_limit.put(11, 30);
		date_limit.put(12, 31);
		
		
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				date_limit.put(2, 29);
			}
			else {
				date_limit.put(2, 28);
			}
		return date_limit;
	}
	
	
	//date check will check whether the date given is in proper format or not
	public static String[] date_check(String date) {
		
		String[] checked_date = null;
		
		if(date.contains("-") && date.split("-").length == 3) {
			
			String[] split_date = date.split("-");
			
			String day = split_date[0];
			String month = split_date[1];
			String year = split_date[2];
			
			HashMap<Integer, Integer> map_for_date_check = get_year_map(split_date);
			
			if((day.length() <= 2) && (month.length() <= 2) && (year.length() == 4) 
					&& map_for_date_check.containsKey(Integer.parseInt(month))
					&& Integer.parseInt(day) <= map_for_date_check.get(Integer.parseInt(month)))  {
				
				checked_date = split_date;
			}
			else {
				System.out.println("Invalid date, please enter correct date");
			}
		}
		else {
			System.out.println("Invalid date, please enter correct date");
		}
		return checked_date;
	}
	
	
	//transform_date will change date values to yyyymmdd format
	public static String transform_date(String date) {
		String output_date = null;
		String[] split_date_values = date_check(date);
		output_date = split_date_values[2] + split_date_values[1] + split_date_values[0];
		return output_date;
	}
	
	
	public static int traverse_to_date(String[] date_start, String[] date_end) {
		int day_counter = 0;
		HashMap<Integer, Integer> date_map = get_year_map(date_start);
		
		int start_date_day = Integer.parseInt(date_start[0]);
		int end_date_day = Integer.parseInt(date_end[0]);
		int start_date_month = Integer.parseInt(date_start[1]);
		int end_date_month = Integer.parseInt(date_end[1]);
		int start_date_year = Integer.parseInt(date_start[2]);
		int end_date_year = Integer.parseInt(date_end[2]);
		int loop_counter = 0;
		
		
		//matching the day 
		if(start_date_day > end_date_day) {
			day_counter = date_map.get(start_date_month) - start_date_day + end_date_day;
		}
		else if(start_date_day < end_date_day) {
			day_counter = end_date_day - start_date_day;
		}
		
		start_date_day = start_date_day + day_counter;
		
		if(start_date_day > date_map.get(start_date_month)) {
			
			start_date_day = start_date_day - date_map.get(start_date_month);
			start_date_month = start_date_month + 1;
		}
			
		if(start_date_month > end_date_month) {
			loop_counter = (12 - start_date_month + end_date_month);
			for(int i = 0; i < loop_counter; i++) {
				day_counter = day_counter + date_map.get(start_date_month);
				start_date_month = start_date_month + 1;
				
				if(start_date_month > 12) {
					start_date_year = start_date_year + 1;
					start_date_month = start_date_month - 12;
				}
			}
		}
		else if(start_date_month < end_date_month){
			while(start_date_month < end_date_month) {
				day_counter = day_counter + date_map.get(start_date_month);
				start_date_month = start_date_month + 1;
				
				if(start_date_month > 12) {
					start_date_year = start_date_year + 1;
					date_map = get_year_map(start_date_year);
					start_date_month = start_date_month - 12;
				}
			}
		}
		
		if(start_date_year < end_date_year) {
			loop_counter = end_date_year - start_date_year;
			for(int i = 0; i < loop_counter; i++) {
				if(date_map.get(2) == 29) {
					day_counter = day_counter + 366;
				}
				else if(date_map.get(2) == 28) {
					day_counter = day_counter + 365;
				}
				start_date_year = start_date_year + 1;
				date_map = get_year_map(start_date_year);
			}
			
		}
		return day_counter;
	}
	
	
	
	
	
	
	
	
	
	
	public static void prev_and_next_date() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("1_january", 31);
		map.put("3_march", 31);
		map.put("4_april", 30);
		map.put("5_may", 31);
		map.put("6_june", 30);
		map.put("7_july", 31);
		map.put("8_august", 31);
		map.put("9_september", 30);
		map.put("10_october", 31);
		map.put("11_november", 30);
		map.put("12_december", 31);
		System.out.println("Enter a date in dd month yyyy format : ");
		
		Scanner sc = new Scanner(System.in);
		String input_date;
		
		input_date = sc.nextLine();
		
		sc.close();
		
		
		String[] split_date = input_date.split(" ");
		if(input_date.contains(" ") == false || split_date.length != 3 || split_date[0].matches(".*[a-zA-Z].*") || split_date[2].matches(".*[a-zA-Z].*") || split_date[1].matches(".*[0-9].*")) {
			System.out.println("Invalid date, please provide a correct date");
		}	
		else {
			
			if (((Integer.parseInt(split_date[2]) % 4 == 0) && ((Integer.parseInt(split_date[2]) % 100 != 0))) || ((Integer.parseInt(split_date[2]) % 400 == 0))){
				map.put("2_february", 29);
			}
			else {
				map.put("2_february", 28);
			}
			
			get_future_date(split_date, map);
			get_past_date(split_date, map);
		}
	}


	
	public static void get_future_date(String[] d, HashMap<String, Integer> month_map) {
		
		
		d[1] = d[1].toLowerCase();
		int year = Integer.parseInt(d[2]);
		int date = Integer.parseInt(d[0]);
	
		
		
		int temp_year = year;
		int temp_date = 0;
		int date_limit = 0;
		int month = 0;
		boolean month_flag = false;
		int ycounter = 0;
		String future_date = null;
		
		for(String s : month_map.keySet()) {
			if(s.contains(d[1])) {
				date_limit = month_map.get(s);
				month = Integer.parseInt((s.split("_"))[0]);
				month_flag = true;
				break;
			}
		}
			
		while(temp_year > 0) {
			temp_year = temp_year/10;
			ycounter++;
		}
			
		if(month_flag == false || date == 0 || ycounter != 4 || year == 0 || date > date_limit) {
			System.out.println("Invalid date, please provide a correct date");
		}
		else {
			temp_date = date + 1;
			if(temp_date > date_limit) {
				temp_date = temp_date - date_limit;
				month = month + 1;
				if(month > 12) {
					year = year + 1;
					month = 1;
				} 
			}
				
			for(String s : month_map.keySet()) {
				if(Integer.parseInt(s.split("_")[0]) == month) {
					future_date = String.valueOf(temp_date) + " " + (s.split("_"))[1] + " " + String.valueOf(year);
				}
			}
			
			System.out.println("Future date : "+future_date);
		}
	}
	
	public static void get_past_date(String[] d, HashMap<String, Integer> month_map) {
		
		d[1] = d[1].toLowerCase();
		int year = Integer.parseInt(d[2]);
		int date = Integer.parseInt(d[0]);
		
		
		int temp_year = year;
		int temp_date = 0;
		int date_limit = 0;
		int month = 0;
		boolean month_flag = false;
		int ycounter = 0;
		String past_date = null;
		
		for(String s : month_map.keySet()) {
			if(s.contains(d[1])) {
				date_limit = month_map.get(s);
				month = Integer.parseInt((s.split("_"))[0]);
				month_flag = true;
				break;
			}
		}
			
		while(temp_year > 0) {
			temp_year = temp_year/10;
			ycounter++;
		}
			
		if(month_flag == false || date == 0 || ycounter != 4 || year == 0 || date > date_limit) {
			System.out.println("Invalid date, please provide a correct date");
		}
		else {
			
			temp_date = date - 1;
			if(temp_date <= 0) {
				month = month - 1;
				if(month <= 0) {
					year = year - 1;
					month = 12;
				}
				for(String s : month_map.keySet()) {
					if(Integer.parseInt(s.split("_")[0]) == month) {
						date_limit = month_map.get(s);
					}
				}
				temp_date = date_limit - temp_date;
			}
			for(String s : month_map.keySet()) {
				if(Integer.parseInt(s.split("_")[0]) == month) {
					past_date = String.valueOf(temp_date) + " " + (s.split("_"))[1] + " " + String.valueOf(year);
				}
			}
			System.out.println("Past date : "+past_date);
		}
	}
		
		

	public static void main(String[] args) {
		
		//prev_and_next_date();
		day_diff();
	}

}
