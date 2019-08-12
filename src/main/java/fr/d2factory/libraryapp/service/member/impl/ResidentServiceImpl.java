package fr.d2factory.libraryapp.service.member.impl;

import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.service.member.MemberService;

public class ResidentServiceImpl extends AbstractMemberCalculator implements MemberService {

	private static final int NORMAL_PRICE = 10;
	private static final int RAISED_PRICE = 20;

	@Override
	public int getKeepingPeriodBeforeMarkedLate() {
		return 60;
	}

	@Override
	protected long getNormalPricePeriod(Long borrowingPeriodByDays, MemberDto member) {
		return Math.min(borrowingPeriodByDays, getKeepingPeriodBeforeMarkedLate());
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
