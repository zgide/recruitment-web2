package fr.d2factory.libraryapp.member;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.mappers.MemberMapper;

public class MemberRepository {

	private MemberMapper mapper;

	private Map<Long, Member> allMembers;

	private static MemberRepository instance = new MemberRepository();

	private MemberRepository() {
		allMembers = new HashMap<>();
		mapper = new MemberMapper();
	}

	public static MemberRepository getInstance() {
		return instance;
	}

	public void register(MemberDto member) {
		allMembers.put(member.getId(), mapper.inverseMap(member));
	}

	public MemberDto getMember(long memberId) {
		return mapper.map(allMembers.get(memberId));

	}

	public void returnBook(long isbnCode, long memberId) {
		Member member = allMembers.get(memberId);
		Optional<Book> borrowedBook = member.getBorrowebBooks().stream().filter(book->book.getIsbnCode()==isbnCode).findAny();
		if(borrowedBook.isPresent()) {
			member.getBorrowebBooks().remove(borrowedBook.get());
		}
	}

	public void update(MemberDto member) {
		Objects.requireNonNull(member);
		Member existingMember = allMembers.get(member.getId());
		mapper.update(existingMember, member);
	}

}
