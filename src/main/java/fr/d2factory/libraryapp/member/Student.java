package fr.d2factory.libraryapp.member;

public class Student extends Member{

	private int year;
	
	public void setYear(int year) {
		this.year = year;
	}

	public Student() {
	}

	@Override
	public void payBook(int numberOfDays) {
		// TODO Auto-generated method stub
		
	}

	public int getYear() {
		return year;
	}

}
