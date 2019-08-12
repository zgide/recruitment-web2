package fr.d2factory.libraryapp.mappers;

import fr.d2factory.libraryapp.book.Book;
import fr.d2factory.libraryapp.book.ISBN;
import fr.d2factory.libraryapp.dto.BookDto;

public class BookMapper implements Mapper<Book, BookDto> {

	@Override
	public BookDto map(Book source) {
		BookDto target = new BookDto();
		target.setAuthor(source.getAuthor());
		target.setTitle(source.getTitle());
		target.setIsbnCode(source.getIsbnCode());
		return target;
	}

	@Override
	public Book inverseMap(BookDto target) {
		Book source = new Book();
		source.setAuthor(target.getAuthor());
		source.setTitle(target.getTitle());
		source.setIsbn(new ISBN(target.getIsbnCode()));
		return source;
	}

	@Override
	public Book update(Book source, BookDto target) {
		throw new UnsupportedOperationException();
	}

}
