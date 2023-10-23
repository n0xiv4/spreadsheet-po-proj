package xxl.core;

import java.util.Collection;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The {@code User} class represents a user in the spreadsheet application.
 * Each user is identified by a unique name and may have associated spreadsheets.
 * This class implements the {@link Serializable} interface for serialization purposes.
 * 
 * @Serial 202310091820L
 */
public class User implements Serializable {

	private String _name;
	
	/** The collection of spreadsheets associated with the user. */
	private Collection<Spreadsheet> _spreadsheets;

	/** The serial version UID for ensuring version compatibility during serialization. */
	@Serial
	private static final long serialVersionUID = 202310091820L;

	/**
	 * Constructs a new {@code User} object with the specified name.
	 *
	 * @param name The name of the user.
	 */
	User(String name) {
		_name = name;
		_spreadsheets = new ArrayList<Spreadsheet>();
	}

	/**
	 * Computes the hash code for the user based on their name.
	 *
	 * @return The hash code value for this user.
	 */
	public int hashCode() {
		return _name.hashCode();
	}

	/**
	 * Compares this user object with the specified object for equality.
	 * Two user objects are considered equal if their hash codes are equal.
	 *
	 * @param obj The object to compare with this user.
	 * @return {@code true} if the specified object is equal to this user, {@code false} otherwise.
	 */
	public boolean equals(Object obj) {
		return hashCode() == obj.hashCode();
	}

	/**
	 * Links a spreadsheet to the user, associating it with their account.
	 *
	 * @param spreadsheet The {@link Spreadsheet} to link to the user's account.
	 */
	void linkSpreadsheet(Spreadsheet spreadsheet) {
		_spreadsheets.add(spreadsheet);
	}
}