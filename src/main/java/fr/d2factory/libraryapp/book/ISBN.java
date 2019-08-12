package fr.d2factory.libraryapp.book;

public class ISBN {
	private long isbnCode;

	public ISBN(long isbnCode) {
		this.isbnCode = isbnCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getIsbnCode() ^ (getIsbnCode() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ISBN other = (ISBN) obj;
		if (getIsbnCode() != other.getIsbnCode())
			return false;
		return true;
	}

	public long getIsbnCode() {
		return isbnCode;
	}
}
