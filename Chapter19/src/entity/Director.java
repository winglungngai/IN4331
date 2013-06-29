package entity;
import java.util.ArrayList;

public class Director {
	
	private String firstName;
	private String lastName;

	public Director() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString()
	{
		return firstName + " " + lastName + "\t"; 
	}
	
}
