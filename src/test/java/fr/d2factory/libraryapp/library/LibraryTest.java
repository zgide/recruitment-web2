package fr.d2factory.libraryapp.library;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.dto.ResidentDto;
import fr.d2factory.libraryapp.dto.StudentDto;
import fr.d2factory.libraryapp.exceptions.BookNotFoundException;
import fr.d2factory.libraryapp.exceptions.HasLateBooksException;
import fr.d2factory.libraryapp.member.MemberRepository;

public class LibraryTest {
	private Library library;
	private BookRepository bookRepository;
	private MemberRepository memberRepository;

	@Before
	public void setup() {
		bookRepository = BookRepository.getInstance();
		List<BookDto> books = getBooks();
		library = new LibraryImpl();
		memberRepository = MemberRepository.getInstance();
		bookRepository.addBooks(books);
		addMembers(books);
	

	}

	private List<BookDto> getBooks() {
		BookDto book1 = new BookDto();
		book1.setAuthor("J.K. Rowling");
		book1.setTitle("Harry Potter");
		book1.setIsbnCode(46578964513L);

		BookDto book2 = new BookDto();
		book2.setAuthor("Jules Verne");
		book2.setTitle("Around the world in 80 days");
		book2.setIsbnCode(3326456467846L);
		List<BookDto> books = new ArrayList<>(2);
		books.add(book1);
		books.add(book2);
		return books;
	}

	@Test(expected = Test.None.class)
	public void member_can_borrow_a_book_if_book_is_available() throws HasLateBooksException {
		MemberDto member = new ResidentDto();
		member.setId(1L);
		library.borrowBook(46578964513L, 1L, LocalDate.now());
	}

	@Test
	public void borrowed_book_is_no_longer_available() throws HasLateBooksException {
		MemberDto member = new ResidentDto();
		member.setId(1L);
		library.borrowBook(46578964513L, 1L, LocalDate.now());
		Assert.assertFalse(library.isBookAvailable(46578964513L));
	}

	@Test
	public void residents_are_taxed_10cents_for_each_day_they_keep_a_book() throws HasLateBooksException {
		library.borrowBook(46578964513L, 2L, LocalDate.now().minusDays(10L));
		library.returnBook(46578964513L, 2L);
		assertEquals(0L, memberRepository.getMember(2L).getWallet());
	}

	@Test
	public void students_pay_10_cents_the_first_30days() throws HasLateBooksException {
		library.borrowBook(46578964513L, 1L, LocalDate.now().minusDays(30L));
		library.returnBook(46578964513L, 1L);
		assertEquals( -300L, memberRepository.getMember(1L).getWallet());
	}

	@Test
	public void students_in_1st_year_are_not_taxed_for_the_first_15days() throws HasLateBooksException {
		library.borrowBook(46578964513L, 3L, LocalDate.now().minusDays(15));
		library.returnBook(46578964513L, 3L);
		assertEquals(0, memberRepository.getMember(3L).getWallet());
	}

	@Test
	public void students_pay_15cents_for_each_day_they_keep_a_book_after_the_initial_30days() throws HasLateBooksException, BookNotFoundException {
		library.borrowBook(46578964513L, 1L, LocalDate.now().minusDays(31L));
		library.returnBook(46578964513L, 1L);
		assertEquals( -315L, memberRepository.getMember(1L).getWallet());
	}

	@Test
	public void residents_pay_20cents_for_each_day_they_keep_a_book_after_the_initial_60days() throws HasLateBooksException, BookNotFoundException {
		library.borrowBook(46578964513L, 2L, LocalDate.now().minusDays(61L));
		library.returnBook(46578964513L, 2L);
		assertEquals(-520L, memberRepository.getMember(2L).getWallet());
	}

	@Test(expected = HasLateBooksException.class)
	public void members_cannot_borrow_book_if_they_have_late_books() throws HasLateBooksException, BookNotFoundException {
		library.borrowBook(3326456467846L, 4L, LocalDate.now());
	}
	
	private void addMembers(List<BookDto> books) {
		StudentDto member1 = new StudentDto();
		member1.setFirstName("test_name1");
		member1.setLastName("test_name1");
		member1.setId(1L);
		member1.setWallet(0);

		MemberDto member2 = new ResidentDto();
		member2.setFirstName("test_name2");
		member2.setLastName("test_name2");
		member2.setId(2L);
		member2.setWallet(100);
		
		StudentDto member3 = new StudentDto();
		member3.setFirstName("test_name1");
		member3.setLastName("test_name1");
		member3.setId(3L);
		member3.setWallet(0);
		member3.setYear(1);
		
		StudentDto member4 = new StudentDto();
		member4.setFirstName("test_name1");
		member4.setLastName("test_name1");
		member4.setId(4L);
		member4.setWallet(0);
		member4.getBorrowebBooks().add(books.get(0));
		bookRepository.saveBookBorrow(books.get(0).getIsbnCode(), LocalDate.now().minusDays(70L));
		memberRepository.register(member1);
		memberRepository.register(member2);
		memberRepository.register(member3);
		memberRepository.register(member4);
	}
}
