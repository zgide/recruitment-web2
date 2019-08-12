package fr.d2factory.libraryapp.mappers;

public interface Mapper<S, T> {

	T map(S source);

	S inverseMap(T target);
	
	S update(S source, T target);

}
