package fr.d2factory.libraryapp.member;

import java.util.ArrayList;
import java.util.List;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.library.Library;

/**
 * A member is a person who can borrow and return books to a {@link Library} A
 * member can be either a student or a resident
 */
public abstract class Member {
	/**
	 * An initial sum of money per cent the member has
	 */
	private long wallet;

	/**
	 * Member ID
	 */
	private long id;

	private String firstName;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private String lastName;

	private List<Book> borrowebBooks = new ArrayList<>();

	public void setId(long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setBorrowebBooks(List<Book> borrowebBooks) {
		this.borrowebBooks = borrowebBooks;
	}

	/**
	 * The member should pay their books when they are returned to the library
	 *
	 * @param numberOfDays the number of days they kept the book
	 */
	public abstract void payBook(int numberOfDays);

	public long getWallet() {
		return wallet;
	}

	public void setWallet(long wallet) {
		this.wallet = wallet;
	}

	public List<Book> getBorrowebBooks() {
		return borrowebBooks;
	}

	public Long getId() {
		return this.id;
	}
}
