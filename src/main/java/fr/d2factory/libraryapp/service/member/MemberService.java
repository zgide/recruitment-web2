package fr.d2factory.libraryapp.service.member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.dto.MemberDto;

public interface MemberService {

	default boolean isLate(MemberDto member) {

		for (BookDto book : member.getBorrowebBooks()) {
			LocalDate borrowedBookDate = BookRepository.getInstance().findBorrowedBookDate(book.getIsbnCode());
			long borrowingPeriodByDays = ChronoUnit.DAYS.between(borrowedBookDate, LocalDate.now());
			if (borrowingPeriodByDays > getKeepingPeriodBeforeMarkedLate()) {
				return true;
			}
		}
		return false;

	}

	int getKeepingPeriodBeforeMarkedLate();

	long calculateSumtoPay(Long isbnCode, MemberDto member);
}
