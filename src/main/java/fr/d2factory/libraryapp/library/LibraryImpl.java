package fr.d2factory.libraryapp.library;

import java.time.LocalDate;

import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.exceptions.BookNotFoundException;
import fr.d2factory.libraryapp.exceptions.HasLateBooksException;
import fr.d2factory.libraryapp.factory.MemberServiceFactory;
import fr.d2factory.libraryapp.member.MemberRepository;
import fr.d2factory.libraryapp.service.member.MemberService;

public class LibraryImpl implements Library {

	@Override
	public synchronized BookDto borrowBook(long isbnCode, long memberId, LocalDate borrowedAt) throws HasLateBooksException{
		MemberDto member = getMemberById(memberId);
		MemberService memberService = MemberServiceFactory.getMemberService(member.getType());
		if(memberService.isLate(member)) {
			throw new HasLateBooksException();
		}
		BookDto book = BookRepository.getInstance().findBook(isbnCode);
		if(book==null) {
			throw new BookNotFoundException();
		}
		BookRepository.getInstance().saveBookBorrow(isbnCode, borrowedAt);
		return book;
	}

	@Override
	public void returnBook(long isbnCode, long memberId) {
		MemberDto member = getMemberById(memberId);
		MemberService memberService = MemberServiceFactory.getMemberService(member.getType());
		long sumToPay = memberService.calculateSumtoPay(isbnCode, member);
		BookRepository.getInstance().returnBook(isbnCode);
		MemberRepository.getInstance().returnBook(isbnCode, memberId);
		member.setWallet(member.getWallet()-sumToPay);
		MemberRepository.getInstance().update(member);

	}

	@Override
	public boolean isBookAvailable(Long isbnCode) {
		return BookRepository.getInstance().findBook(isbnCode) != null && BookRepository.getInstance().findBorrowedBookDate(isbnCode) == null;
	}
	
	private MemberDto getMemberById(long memberId) {
		MemberDto member = MemberRepository.getInstance().getMember(memberId);
		return member;
	}

}
