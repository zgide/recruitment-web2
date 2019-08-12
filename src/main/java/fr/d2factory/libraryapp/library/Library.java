package fr.d2factory.libraryapp.library;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.exceptions.BookNotFoundException;
import fr.d2factory.libraryapp.exceptions.HasLateBooksException;
import fr.d2factory.libraryapp.member.Member;

/**
 * The library class is in charge of stocking the books and managing the return
 * delays and members
 *
 * The books are available via the
 * {@link fr.d2factory.libraryapp.book.BookRepository}
 */
public interface Library {

	/**
	 * A member is borrowing a book from our library.
	 *
	 * @param isbnCode   the isbn code of the book
	 * @param member     the member who is borrowing the book
	 * @param borrowedAt the date when the book was borrowed
	 *
	 * @return the book the member wishes to obtain if found
	 * @throws HasLateBooksException in case the member has books that are late
	 * @throws BookNotFoundException in case the book not exist
	 *
	 * @see fr.d2factory.libraryapp.book.ISBN
	 * @see Member
	 */
	BookDto borrowBook(long isbnCode, long memberId, LocalDate borrowedAt)
			throws HasLateBooksException;

	/**
	 * A member returns a book to the library. We should calculate the tarif and
	 * probably charge the member
	 *
	 * @param book   the {@link Book} they return
	 * @param member the {@link Member} who is returning the book
	 *
	 * @see Member#payBook(int)
	 */
	void returnBook(long isbnCode, long memberId);
	
	/**
	 * Api method to look for book availabily 
	 * @param isbnCode the code of the book
	 */
	boolean isBookAvailable(Long isbnCode);
}
