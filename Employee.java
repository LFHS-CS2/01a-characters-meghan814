class Employee {
  private String firstName;   // the first name of the employee
  private char middleInitial; // the middle initial
  private String lastName;    // the last name of the employee
  
  public Employee(String firstName, char middleInitial, String lastName)
  {
    this.firstName = firstName;
    this.middleInitial = middleInitial;
    this.lastName = lastName;
  }
  
  public String getFirstName() { return firstName; }
  public char getMiddleInitial() { return middleInitial;}
  public String getLastName() { return lastName; }
  
 
}
