package fr.d2factory.libraryapp.service.member.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import fr.d2factory.libraryapp.book.BookRepository;
import fr.d2factory.libraryapp.dto.MemberDto;

public abstract class AbstractMemberCalculator {

	// class for template pattern
	public long calculateSumtoPay(Long isbnCode, MemberDto member) {
		long result;
		LocalDate borrowedBookDate = BookRepository.getInstance().findBorrowedBookDate(isbnCode);
		long borrowingPeriodByDays = ChronoUnit.DAYS.between(borrowedBookDate,LocalDate.now());
		long min = getNormalPricePeriod(borrowingPeriodByDays, member);
		result = min * getNormalPrice();
		long majoredBorrowingPeriod = borrowingPeriodByDays - getKeepingPeriodBeforeMarkedLate();
		if (majoredBorrowingPeriod > 0) {
			result += majoredBorrowingPeriod * getRaisedPrice();
		}
		return result;
	}

	protected abstract long getNormalPricePeriod(Long borrowingPeriodByDays, MemberDto member);

	protected abstract int getKeepingPeriodBeforeMarkedLate();

	protected abstract int getRaisedPrice();

	protected abstract int getNormalPrice();
}
