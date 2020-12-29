package basic.tools.breeder;

public class DogBreeder {
    public static void main(String arggs[]) {
    	// Dog (String birthDate (yyyy-MM-dd), int age, Boolean female, String finalDate (yyyy-MM-dd) )
    	String motherDateOfBirth = "2017-12-28";
    	int motherAge = 3;
    	boolean isFemale = true;
    	String endDateOfBreadingProgram = "2018-12-28";
    	String name = Register.rootName;
    	
    	new Dog(motherDateOfBirth,motherAge,isFemale,endDateOfBreadingProgram,name);


    	System.out.println("==================================================================================");
    	System.out.println("==================================================================================");
    	System.out.println(" List of all dogs produced by the breading program ");
    	System.out.println("==================================================================================");
    	System.out.println("==================================================================================");
    	Register.Birth_Register.forEach(dog->System.out.println(dog.toString()));

    	System.out.println("==================================================================================");
    	System.out.println("==================================================================================");
    
    }
}
