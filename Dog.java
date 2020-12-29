package basic.tools.breeder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Dog {
	
	int age = 0;
	Boolean female;
    String birthDate;
    String finalDate;
    int dogNumber = 0;
    String name = "";
    String tName = Register.rootName + "-1";
    int yearRemaining = 0;
	List<Dog> children = new ArrayList<Dog>();
	private long serialNumber;
	int generation = 1;
	
	/*
	 * Date of birth of the dog String birthDate
     * Age of the femalee dog which was participating in the Breeding program: int age 
     * Sex of the dog,  Boolean female
     * Final date of the Breeding program when results will be calculated : String finalDate
     */
	
	public Dog (String birthDate, int age, Boolean female, String finalDate, String name ){
		this.birthDate = birthDate;
		this.age = age;
	 	this.finalDate = finalDate; 
	 	this.female = female;
	 	String sex = female ? "female dog" : "male dog";
	 	// Register is a global Birth registry to keep track of birth. 
	 	this.serialNumber = Register.registerBirth();
	 	this.name = name + "-" + this.serialNumber;
	 	Register.registerDogBirth(this);
	 	tName = Register.rootName + "-1";
	 	String message =  this.name.equals(tName) ? " is added to the Breeding program, at age "+ this.age + ", date of enrolement ": " puppy is born at ";
	 	System.out.println("Dog serialNumber " + this.serialNumber + ", a "+ sex+message + birthDate);
	 	this.yearRemaining = yearRemaining();
	 	//The startThisDog will generate  recursive call to this object.  
	 	startThisDog();
	}

	
	/*
	 *  When a dogs age is increased its children's age should be increased too.
	 */
	private void increaseChildAge() {
		children.forEach(d->d.age=d.age+1);
		System.out.println("All children's age of dog " + this.name + ", increased by 1 year.");
	}
	
	
	/*
	 *  This needs to be incremented once every year.
	 */
	public boolean increaseAge() {
		if(this.isBreedingEventActive()){
			System.out.println("Dog " + serialNumber + ", current age " + this.age);
			if(this.age <10) {
				this.BreedDog();
				this.age = this.age + 1;
				increaseChildAge();
				System.out.println("Dog " + serialNumber + ", age increased by 1 to " + this.age);
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	private int yearRemaining() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Register.pattern);
		LocalDate current = LocalDate.parse(this.birthDate, formatter);
		LocalDate future = LocalDate.parse(this.finalDate, formatter);
		return  Period.between(current, future).getYears();
	}
	
	/*
	 *  We do not want to continue counting after the Breeding program ends for this dog. 
	 *  Only dog age >= 3 and <= 10 are allowed to take part in the program.
	 *  The very first dog ("root-1"), can immediately participate in the program.
	 *  Other dogs has to wait till they are 3 year old. 
	 *  this.generation indicates how many years has elapsed since the program has started. 
	 */
	private boolean isBreedingEventActive() {
		if (this.age >=Register.breedingStartAge && this.age <= Register.breedingStopAge) {
			if(this.name.equals(tName)) {
				if(this.yearRemaining >=0) {
					System.out.println("Qualified for BreedingEventActive. Dog "+this.serialNumber+ " age "+ this.age + " yearRemaining " + this.yearRemaining);
					return true;
				}else {
					System.out.println("Breeding program is over. Dog "+ this.serialNumber + " age "+this.age + " yearRemaining " + this.yearRemaining);
					return true;
				}
			}else {
				if(this.yearRemaining - this.generation >=0) {
					System.out.println("Qualified for BreedingEventActive. Dog "+this.serialNumber+ " age "+ this.age + " yearRemaining " + this.yearRemaining);
					return true;
				}else {
					System.out.println("Breeding program is over. Dog "+ this.serialNumber + " age "+this.age + " yearRemaining " + this.yearRemaining);
					return false;
				}
			}
		}else {
			System.out.println("Exclude from program,  age disqualification, serialNumber "+ this.serialNumber + ", age "+this.age);
			return false;
		}
	}
	
	
	/*
	 *  Give birth from the age of 3 till the age of 10.
	 *  Dog give birth of two femalee and one male poppy
	 *  BreedDog will not continue when final Breeding date( finalDate ) is arrived.
	 */
	private void BreedDog() {
		
		if(this.female  && this.age >= Register.breedingStartAge  && this.age <= Register.breedingStopAge ) {
			System.out.println("Dog "+this.serialNumber + " is in Breeding. Dog  "+ " age "+ this.age);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Register.pattern);
			LocalDate tmpDateOfBirth = LocalDate.parse(birthDate, formatter);
			LocalDate puppyDateOfBirth = null;
			puppyDateOfBirth = tmpDateOfBirth.plusYears(generation);
			String puppyDateOfBirthString =  puppyDateOfBirth.toString();
			
			for(int i =0 ; i < Register.numberOfMale; i++) {
				Dog puppy = new Dog(puppyDateOfBirthString,0,false,finalDate,this.name);	
				this.children.add(puppy);
			}
			
			for(int i =0 ; i < Register.numberOfFemale; i++) {
				Dog puppy = new Dog(puppyDateOfBirthString,0,true,finalDate,this.name);	
				this.children.add(puppy);
			}
			this.generation = this.generation+1; 
		}
		System.out.println("Breeding complete for dog " + this.serialNumber + " name "+this.name + ". Decrease remaining years by one once.");
		
		// The yearRemaining must be reduced by one.
		this.yearRemaining = this.yearRemaining -1;
		
		// Recursive call to startThisDog() for each child dog. 
		this.children.forEach(d->d.startThisDog());		
	}

	// This method controls the flow of execution.
	public void startThisDog() {
		while(this.increaseAge() && this.yearRemaining >=0 ){ }
	}

	@Override
	public String toString() {
		return "Dog [serialNumber=" + this.serialNumber + ", name=" + this.name + ", age=" + this.age + ", female=" + this.female
				+ ", birthDate=" + this.birthDate + "]";
	}	
}
