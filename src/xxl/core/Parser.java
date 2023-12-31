package xxl.core;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import xxl.app.exception.UnknownFunctionException;
import xxl.core.content.Content;
import xxl.core.content.Reference;
import xxl.core.content.function.AddFunction;
import xxl.core.content.function.AverageFunction;
import xxl.core.content.function.CoalesceFunction;
import xxl.core.content.function.ConcatFunction;
import xxl.core.content.function.DivFunction;
import xxl.core.content.function.Function;
import xxl.core.content.function.MulFunction;
import xxl.core.content.function.ProductFunction;
import xxl.core.content.function.SubFunction;
import xxl.core.content.literal.Literal;
import xxl.core.content.literal.LiteralInteger;
import xxl.core.content.literal.LiteralString;
import xxl.core.exception.InvalidCellIntervalException;
import xxl.core.exception.InvalidFunctionException;
import xxl.core.exception.UnrecognizedEntryException;

/**
 * The {@code Parser} class is responsible for parsing and interpreting input data
 * to construct and manipulate spreadsheet content.
 * It includes methods for parsing dimensions, lines, and content expressions.
 * Additionally, it handles the interpretation of functions and literals.
 */
public class Parser {

	/** The spreadsheet to parse to. */
	private Spreadsheet _spreadsheet;
	
	/**
	 * Constructs a new Parser instance with no associated Spreadsheet.
	 */
	public Parser() {
	}

	/**
	 * Constructs a new Parser instance with an associated Spreadsheet.
	 *
	 * @param spreadsheet The Spreadsheet object to associate with this Parser.
	 */
	public Parser(Spreadsheet spreadsheet) {
		_spreadsheet = spreadsheet;
	}

	/**
	 * Parses data from a file and populates the associated Spreadsheet object.
	 *
	 * @param filename The name of the file to be parsed.
	 * @return The populated Spreadsheet object.
	 * @throws IOException If an I/O error occurs while reading the file.
	 * @throws UnrecognizedEntryException if an unrecognized entry is encountered during parsing.
	 */
	public Spreadsheet parseFile(String filename) throws IOException, UnrecognizedEntryException /* More Exceptions? */ {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			parseDimensions(reader);
			String line;
			while ((line = reader.readLine()) != null) {
				parseLine(line);
			}
		}
		return _spreadsheet;
	}

	/**
	 * Parses the user-provided content specification and returns the corresponding Content object.
	 *
	 * @param contentSpecification The content specification provided by the user.
	 * @return The parsed Content object.
	 * @throws UnrecognizedEntryException if the contentSpecification cannot be recognized.
	 * @throws InvalidFunctionException if the contentSpecification contains an invalid function.
	 */
	public Content parseContentInput(String contentSpecification) throws UnrecognizedEntryException, InvalidFunctionException {
		return parseContent(contentSpecification);
	}

	/**
	 * Parses a literal specification provided as a string to create a Literal object.
	 *
	 * @param literalSpecification The string representation of the literal to be parsed.
	 * @return The parsed Literal object.
	 * @throws UnrecognizedEntryException If the provided literal specification is not recognized.
	 */
	public Literal parseLiteralInput(String literalSpecification) throws UnrecognizedEntryException {
		return parseLiteral(literalSpecification);
	}

	/**
	 * Parses and extracts the dimensions (number of rows and columns) of a spreadsheet
	 * from the given input {@link BufferedReader}.
	 *
	 * @param reader The {@link BufferedReader} used to read the input lines.
	 * @throws IOException If an I/O error occurs while reading from the reader.
	 * @throws UnrecognizedEntryException If the dimensions cannot be recognized or contain invalid values.
	 */
	private void parseDimensions(BufferedReader reader) throws IOException, UnrecognizedEntryException {
		int rows = -1;
		int columns = -1;
		
		for (int i = 0; i < 2; i++) {
			String[] dimension = reader.readLine().split("=");
			if ("linhas".equals(dimension[0])) {
				rows = Integer.parseInt(dimension[1]);
			}
			else if ("colunas".equals(dimension[0])) {
				columns = Integer.parseInt(dimension[1]);
			}
			else {
				throw new UnrecognizedEntryException(reader.readLine());
			}
		}

		if (rows <= 0 || columns <= 0)
			throw new UnrecognizedEntryException("Dimensões inválidas para a folha");

		_spreadsheet = new Spreadsheet(rows, columns);
	}

	/**
	 * Parses a line of input and inserts content into the spreadsheet based on the provided line.
	 *
	 * @param line The input line to parse and process.
	 * @throws UnrecognizedEntryException If the content cannot be recognized or contain invalid syntax.
	 */
	private void parseLine(String line) throws UnrecognizedEntryException {
		String[] components = line.split("\\|");

		if (components.length == 1) { // do nothing
			return;
		}
		
		if (components.length == 2) {
			String[] address = components[0].split(";");
			Content content = parseContent(components[1]);
			Position cellPosition = new Position(Integer.parseInt(address[0]), Integer.parseInt(address[1]));
			_spreadsheet.insertContent(cellPosition, content);
		}
		else {
			throw new UnrecognizedEntryException("Wrong format in line: " + line);
		}
	}

	/**
	 * Parses the beginning of a content expression and returns the corresponding {@link Content}.
	 *
	 * @param contentSpecification The content specification string to parse, which may start with an '=' character.
	 * @return The parsed {@link Content} object.
	 * @throws UnrecognizedEntryException If the arguments cannot be recognized or contain invalid syntax.
	 * @throws InvalidFunctionException If the function cannot be recognized.
	 */
	Content parseContent(String contentSpecification) throws UnrecognizedEntryException, InvalidFunctionException {
		char c = contentSpecification.charAt(0);

		if (c == '=') {
			return parseContentExpression(contentSpecification.substring(1));
		}
		else {
			return parseLiteral(contentSpecification);
		}
	}

	/**
	 * Parses a literal expression represented as a string and returns the corresponding {@link Literal}.
	 *
	 * @param literalExpression The string representation of the literal expression to parse.
	 * @return The parsed {@link Literal} object.
	 * @throws UnrecognizedEntryException If the literal expression cannot be recognized or contains invalid syntax.
	 */
	private Literal parseLiteral(String literalExpression) throws UnrecognizedEntryException {
		if (literalExpression.charAt(0) == '\'')
			// removes the quote
			return new LiteralString(literalExpression.substring(1));
		else {
			try {
				int val = Integer.parseInt(literalExpression);
				return new LiteralInteger(val);
			} 
			catch (NumberFormatException nfe) {
				throw new UnrecognizedEntryException("Número inválido: " + literalExpression, nfe);
			}
		}
	}

	/**
	 * Parses a content expression specified as a string and returns the corresponding {@link Content}.
	 * If it contains a "(", it must be a {@link Function}. Otherwise, it's a {@link Reference}.
	 *
	 * @param contentSpecification The string representation of the content expression to parse.
	 *                             (This is what comes after '=' in the input.)
	 * @return The parsed {@link Content} object.
	 * @throws UnrecognizedEntryException If the arguments cannot be recognized or contain invalid syntax.
	 * @throws InvalidFunctionException If the function cannot be recognized.
	 */
	private Content parseContentExpression(String contentSpecification) throws UnrecognizedEntryException, InvalidFunctionException {
		if (contentSpecification.contains("(")) {
			return parseFunction(contentSpecification);
		}
		String[] address = contentSpecification.split(";");
		/* Creates a {@link Position} which represents the position given. Then creates a reference to said position. */
		Position referencedPosition = new Position(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
		return new Reference(referencedPosition, _spreadsheet);
	}

	/**
	 * Parses a function expression represented as a string and returns the corresponding {@link Content}.
	 *
	 * @param functionSpecification The string representation of the function expression to parse.
	 * @return The parsed {@link Content} object.
	 * @throws UnrecognizedEntryException If the arguments cannot be recognized or contain invalid syntax.
	 * @throws InvalidFunctionException If the function cannot be recognized.
	 */
	private Content parseFunction(String functionSpecification) throws UnrecognizedEntryException, InvalidFunctionException {
		String[] components = functionSpecification.split("[()]");
		if (components[1].contains(",")) {
			return parseBinaryFunction(components[0], components[1]);
		}
		return parseIntervalFunction(components[0], components[1]);
	}

	/**
	 * Parses a binary function expression and returns the corresponding {@link Content}.
	 *
	 * @param functionName The name of the binary function (e.g., "ADD", "SUB").
	 * @param args         The arguments of the binary function as a string.
	 * @return The parsed {@link Content} object representing the binary function.
	 * @throws UnrecognizedEntryException If the arguments cannot be recognized or contain invalid syntax.
	 * @throws InvalidFunctionException If the function cannot be recognized.
	 */
	private Content parseBinaryFunction(String functionName, String args) throws UnrecognizedEntryException, InvalidFunctionException {
		String[] arguments = args.split(",");
		Content arg0 = parseArgumentExpression(arguments[0]);
		Content arg1 = parseArgumentExpression(arguments[1]);
		
		return switch (functionName) {
			case "ADD" -> new AddFunction(arg0, arg1);
			case "SUB" -> new SubFunction(arg0, arg1);
			case "MUL" -> new MulFunction(arg0, arg1);
			case "DIV" -> new DivFunction(arg0, arg1);
			default -> throw new InvalidFunctionException(functionName);
		};
	}

	/**
	 * Parses an argument expression represented as a string and returns the corresponding {@link Content}.
	 *
	 * @param argExpression The string representation of the argument expression to parse.
	 * @return The parsed {@link Content} object.
	 * @throws UnrecognizedEntryException If the argument expression cannot be recognized or contains invalid syntax.
	 */
	private Content parseArgumentExpression(String argExpression) throws UnrecognizedEntryException {
		if (argExpression.contains(";")  && argExpression.charAt(0) != '\'') {
			String[] address = argExpression.split(";");
			/* Creates a {@link Position} which represents the position given. Then creates a reference to said position. */
			Position referencedPosition = new Position(Integer.parseInt(address[0].trim()), Integer.parseInt(address[1]));
			return new Reference(referencedPosition, _spreadsheet);
		} else
			return parseLiteral(argExpression);
	}

	/**
	 * Parses an interval function and its range description to create the corresponding Content object.
	 *
	 * @param functionName The name of the interval function to be parsed.
	 * @param rangeDescription The description of the range or interval for the function.
	 * @return The parsed Content object representing the interval function.
	 * @throws InvalidFunctionException if the function cannot be parsed or is invalid.
	 */
	private Content parseIntervalFunction(String functionName, String rangeDescription) throws InvalidFunctionException {
		try {
			Interval interval = new Interval(rangeDescription, _spreadsheet);
			return switch (functionName) {
				case "CONCAT" -> new ConcatFunction(interval);
				case "COALESCE" -> new CoalesceFunction(interval);
				case "PRODUCT" -> new ProductFunction(interval);
				case "AVERAGE" -> new AverageFunction(interval);
				default -> throw new UnknownFunctionException(functionName);
			};
		}
		catch (InvalidCellIntervalException | UnknownFunctionException e) {
			throw new InvalidFunctionException(functionName);
		}
	}
}