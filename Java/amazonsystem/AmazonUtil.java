package amazonsystem;

import java.util.ArrayList;
import java.util.Scanner;

public class AmazonUtil {
	public static float convertStrToFloat (String in) {
		String out = "" + in;
		
		for(int i = 0; i < out.length(); i++) {
			if(out.charAt(i) == 44) {
				out = out.substring(0, i) + out.substring(i+1);
			}
		}
		
		try {
			return Float.parseFloat(out);	
		}
		catch(Exception noValidFloat){
			return 0;
		}
	}
	
	public static String[] lineReader(String line) {
		ArrayList<String> processed = new ArrayList<String>();
		
		Scanner in = new Scanner(line);
		
		in.useDelimiter(",");
		
		while(in.hasNext()) {
			String value = in.next();
			
			if(value.contains("\"")) {
				if(value.charAt(0) == 34) {
					value = value.substring(1);
				}
				if(value.charAt(value.length()-1) == 34) {
					value = value.substring(0, value.length()-1);
				}
			}
			
			if(value.charAt(0) == 9 || value.charAt(value.length()-1) == 9) {
				if(value.charAt(0) == 9) {
					value = value.substring(1);
				}
				if(value.charAt(value.length()-1) == 9) {
					value = value.substring(0, value.length()-1);
				}
			}
			
			
			while(value.contains("  ")) {
				value = value.substring(0, value.indexOf("  ")) + value.substring(value.indexOf("  ") + 1);
			}
			while(value.contains("\t")) {
				value = value.substring(0, value.indexOf("\t")) + value.substring(value.indexOf("\t") + 1);
			}
			
			if(value.contains(" ")) {
				if(value.charAt(0) == 32) {
					value = value.substring(1);
				}
				if(value.charAt(value.length()-1) == 32) {
					value = value.substring(0, value.length()-1);
				}
			}
			processed.add(value);
		}
		
		String[] out = new String[processed.size()];
		
		for(int i = 0; i < processed.size(); i++) {
			out[i] = processed.get(i);
		}
		
		in.close();
		return out;
	}
	
	public static boolean isValidFloat(String in) {
		try {
			if(Float.parseFloat(in) >= 0) {
				return true;
			}
		} catch (NumberFormatException e) {
			
		}
		return false;
	}
	public static boolean isValidInt(String in) {
		try {
			if(Integer.parseInt(in) >= 0) {
				return true;
			}
		} catch (NumberFormatException e) {
			
		}
		return false;
	}
	public static boolean isValidInt(String in, int i, int j) {
		try {
			if(Integer.parseInt(in) >= 0) {
				return true;
			}
		} catch (NumberFormatException e) {
			
		}
		return false;
	}
	public static boolean isValidString(String in) {
		if(!in.equals(null)){
			return true;
		}
		return false;
	}
}
