package basic.tools.breeder;

import java.util.ArrayList;
import java.util.List;

public final class Register {
  static long birthCount = 0;
  static List<Dog>Birth_Register = new ArrayList<Dog>();
  
public static synchronized long registerBirth() {
	birthCount = birthCount + 1;
	return birthCount;
}
public static void registerDogBirth(Dog puppy) {
	Birth_Register.add(puppy);
}

public static String rootName = "root";

public static int numberOfMale = 1;
public static int numberOfFemale = 2;
public static int breedingStartAge = 3;
public static int breedingStopAge = 10;
public static String pattern = "yyyy-MM-dd";
}
