package fr.d2factory.libraryapp.mappers;

import java.util.ArrayList;
import java.util.List;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.dto.BookDto;
import fr.d2factory.libraryapp.dto.MemberDto;
import fr.d2factory.libraryapp.dto.MemberTypeEnum;
import fr.d2factory.libraryapp.dto.ResidentDto;
import fr.d2factory.libraryapp.dto.StudentDto;
import fr.d2factory.libraryapp.member.Member;
import fr.d2factory.libraryapp.member.Resident;
import fr.d2factory.libraryapp.member.Student;

public class MemberMapper implements Mapper<Member, MemberDto> {

	@Override
	public MemberDto map(Member source) {
		if (source == null) {
			return null;
		}
		MemberDto target = mapSpecific(source);
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setWallet(source.getWallet());
		List<BookDto> borrowedBooks = new ArrayList<>(source.getBorrowebBooks().size());
		BookMapper bookMapper = new BookMapper();
		source.getBorrowebBooks().forEach(book -> borrowedBooks.add(bookMapper.map(book)));
		target.setBorrowebBooks(borrowedBooks);
		target.setId(source.getId());
		return target;
	}

	@Override
	public Member inverseMap(MemberDto target) {
		if (target == null) {
			return null;
		}
		Member source = mapSpecific(target);
		source.setFirstName(target.getFirstName());
		source.setLastName(target.getLastName());
		source.setWallet(target.getWallet());
		source.setId(target.getId());
		List<Book> borrowedBooks = new ArrayList<>(target.getBorrowebBooks().size());
		BookMapper bookMapper = new BookMapper();
		target.getBorrowebBooks().forEach(book -> borrowedBooks.add(bookMapper.inverseMap(book)));
		source.setBorrowebBooks(borrowedBooks);
		return source;
	}

	@Override
	public Member update(Member source, MemberDto target) {
		source.setWallet(target.getWallet());
		source.setFirstName(target.getFirstName());
		source.setLastName(target.getLastName());
		return source;
	}
	private Member mapSpecific(MemberDto target) {
		if (target instanceof StudentDto) {
			Student source = new Student();
			source.setYear(((StudentDto) target).getYear());
			return source;
		} else {
			Resident source = new Resident();
			return source;
		}
	}
	
	private MemberDto mapSpecific(Member source) {
		if (source instanceof Student) {
			StudentDto target = new StudentDto();
			target.setYear(((Student) source).getYear());
			target.setType(MemberTypeEnum.STUDENT);
			return target;
		} else {
			ResidentDto target = new ResidentDto();
			target.setType(MemberTypeEnum.RESIDENT);
			return target;
		}
	}
}
