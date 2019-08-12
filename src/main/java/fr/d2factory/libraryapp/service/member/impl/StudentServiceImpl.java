package fr.d2factory.libraryapp.service.member.impl;

import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.dto.StudentDto;
import fr.d2factory.libraryapp.service.member.MemberService;

public class StudentServiceImpl extends AbstractMemberCalculator implements MemberService {

	private static final int FREE_PERIOD_FOR_FIRST_YEAR_STUDENT = 15;
	private static final int NORMAL_PRICE = 10;
	private static final int RAISED_PRICE = 15;

	@Override
	public int getKeepingPeriodBeforeMarkedLate() {
		return 30;
	}

	@Override
	protected long getNormalPricePeriod(Long borrowingPeriodByDays, MemberDto member) {
		StudentDto student = (StudentDto) member;
		return Math.min(
				student.getYear() == 1 ? borrowingPeriodByDays - FREE_PERIOD_FOR_FIRST_YEAR_STUDENT
						: borrowingPeriodByDays,
				student.getYear() == 1 ? getKeepingPeriodBeforeMarkedLate() - FREE_PERIOD_FOR_FIRST_YEAR_STUDENT
						: getKeepingPeriodBeforeMarkedLate());
	}

	@Override
	protected int getRaisedPrice() {
		return RAISED_PRICE;
	}

	@Override
	protected int getNormalPrice() {
		return NORMAL_PRICE;
	}

}
