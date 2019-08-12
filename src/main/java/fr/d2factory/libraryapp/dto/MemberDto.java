package fr.d2factory.libraryapp.dto;

import java.util.ArrayList;
import java.util.List;

public abstract class MemberDto {

	private long id;

	private long wallet;

	private String firstName;

	private String lastName;

	private MemberTypeEnum type;

	private List<BookDto> borrowebBooks = new ArrayList<>();

	public MemberDto() {

	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the wallet
	 */
	public long getWallet() {
		return wallet;
	}

	/**
	 * @param wallet the wallet to set
	 */
	public void setWallet(long wallet) {
		this.wallet = wallet;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public MemberTypeEnum getType() {
		return type;
	}

	public void setType(MemberTypeEnum type) {
		this.type = type;
	}

	public List<BookDto> getBorrowebBooks() {
		return borrowebBooks;
	}

	public void setBorrowebBooks(List<BookDto> borrowebBooks) {
		this.borrowebBooks = borrowebBooks;
	}

}
