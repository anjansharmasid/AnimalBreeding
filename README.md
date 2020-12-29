AnimalBreeding
Simple application to estimate number of animals at the end of a breeding program.Compile and run DogBreeder.java to see the output on the console.

Configuration: All configuration parameters are in Register.java. Change these values accordingly for your estimations.

This is the name of the first mother. 

public static String rootName = "root";

We are assuming that in one breeding iteration there will be one male and two female childs. Change this accordingle to your estimation. 

public static int numberOfMale = 1; 

public static int numberOfFemale = 2;

Once the animal reaches breedingStartAge age the animal breeds. 

Change this value accordingly. public static int breedingStartAge = 3;

Once the animal reaches breedingEndAge age the animal stop breed. 

Change this value accordingly. public static int breedingStopAge = 10;

Date format used, change to other valid format as needed. 

public static String pattern = "yyyy-MM-dd";



To run the application :

String motherDateOfBirth = "2017-12-28"; 

int motherAge = 3; 

boolean isFemale = true; 

String endDateOfBreedingProgram = "2018-12-28"; 

String name = Register.rootName; new 

Dog(motherDateOfBirth,motherAge,isFemale,endDateOfBreedingProgram,name);

Output: 

==================================== 

List of all dogs produced by the breeding program ==================================== 

Dog [serialNumber=1, name=root-1, age=5, female=true, birthDate=2017-12-28] 

Dog [serialNumber=2, name=root-1-2, age=2, female=false, birthDate=2018-12-28] 

Dog [serialNumber=3, name=root-1-3, age=2, female=true, birthDate=2018-12-28] 

Dog [serialNumber=4, name=root-1-4, age=2, female=true, birthDate=2018-12-28] 

Dog [serialNumber=5, name=root-1-5, age=1, female=false, birthDate=2019-12-28] 

Dog [serialNumber=6, name=root-1-6, age=1, female=true, birthDate=2019-12-28] 

Dog [serialNumber=7, name=root-1-7, age=1, female=true, birthDate=2019-12-28]
