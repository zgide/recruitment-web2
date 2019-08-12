package fr.d2factory.libraryapp.factory;

import java.util.HashMap;
import java.util.Map;

import fr.d2factory.libraryapp.dto.MemberTypeEnum;
import fr.d2factory.libraryapp.service.member.MemberService;
import fr.d2factory.libraryapp.service.member.impl.ResidentServiceImpl;
import fr.d2factory.libraryapp.service.member.impl.StudentServiceImpl;

public class MemberServiceFactory {

	private static Map<MemberTypeEnum, MemberService> memberServices;

	static {
		memberServices = new HashMap<>();
		memberServices.put(MemberTypeEnum.STUDENT, new StudentServiceImpl());
		memberServices.put(MemberTypeEnum.RESIDENT, new ResidentServiceImpl());
	}

	private MemberServiceFactory() {
		
	}
	public static MemberService getMemberService(MemberTypeEnum type) {
		return memberServices.get(type);
	}
}
