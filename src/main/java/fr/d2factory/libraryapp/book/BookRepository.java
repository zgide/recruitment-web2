package fr.d2factory.libraryapp.book;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.mappers.BookMapper;

/**
 * The book repository emulates a database via 2 HashMaps
 */
public class BookRepository {
	private Map<Long, Book> allBooks;
	private Map<Long, LocalDate> borrowedBooks;
	private BookMapper mapper;
	private static BookRepository instance = new BookRepository();

	private BookRepository() {
		allBooks = new ConcurrentHashMap<>();
		borrowedBooks = new ConcurrentHashMap<>();
		mapper = new BookMapper();
	}

	public static BookRepository getInstance() {
		return instance;
	}

	public void addBooks(List<BookDto> books) {
		books.forEach(book -> allBooks.put(book.getIsbnCode(), mapper.inverseMap(book)));
	}

	public BookDto findBook(long isbnCode) {
		return mapper.map(allBooks.get(isbnCode));
	}

	public void saveBookBorrow(long isbnCode, LocalDate borrowedAt) {
		borrowedBooks.put(isbnCode, borrowedAt);
	}

	public LocalDate findBorrowedBookDate(long isbnCode) {
		return borrowedBooks.get(isbnCode);
	}
	
	public void returnBook(long isbnCode) {
		borrowedBooks.remove(isbnCode);
	}
}
