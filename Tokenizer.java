import java.util.ArrayList;

public class Tokenizer {

	String[] s;
	ArrayList<Token> tokenData;

	public Tokenizer(String str) {
		this.s = str.split("\\n+");
		this.tokenData = new ArrayList<Token>();
	}

	// Returns 'true' if the character is a DELIMITER.
	public boolean isDelimiter(char ch) {
		if (ch=='\"'|| ch == '\f' || ch == '\r' || ch == '\t' || ch == ' ' || ch == '+' || ch == '-' || ch == '*'
				|| ch == '/' || ch == ',' || ch == ';' || ch == '>' || ch == '<' || ch == '=' || ch == '(' || ch == ')'
				|| ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == '%' || ch == '!' || ch == ':')
			return true;
		return false;
	}

	// delimiter except 'Blank'
	public boolean isSeparator(char ch) {
		if ( ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ',' || ch == ';' || ch == '>' || ch == '<'
				|| ch == '=' || ch == '(' || ch == ')' || ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == '%'
				|| ch == '!' || ch == ':')
			return true;
		return false;
	}

	// Returns 'true' if the character is an OPERATOR.
	public boolean isOperator(char ch, char nxch) {
		if (ch == '+' && (nxch == '+' || nxch == '='))
			return true;
		if (ch == '-' && (nxch == '-' || nxch == '='))
			return true;
		if (ch == '*' && nxch == '=')
			return true;
		if (ch == '/' && nxch == '=')
			return true;
		if (ch == '%' && nxch == '=')
			return true;
		return false;
	}

	public boolean isOperator(char ch) {
		if ((ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%'))
			return true;
		return false;
	}

	// boolean function for comparator
	public boolean isComparator(char ch, char nxch) {
		if (ch == '>' && nxch == '=')
			return true;
		if (ch == '<' && nxch == '=')
			return true;
		if (ch == '=' && nxch == '=')
			return true;
		if (ch == '!' && nxch == '=')
			return true;
		return false;
	}

	public boolean isComparator(char ch) {
		if (ch == '>' || ch == '<')
			return true;
		return false;
	}

	// Returns 'true' if the string is a VALID IDENTIFIER.
	public boolean validIdentifier(String s) {
		char[] str = s.toCharArray();
		if (str[0] == '0' || str[0] == '1' || str[0] == '2' || str[0] == '3' || str[0] == '4' || str[0] == '5'
				|| str[0] == '6' || str[0] == '7' || str[0] == '8' || str[0] == '9' || isDelimiter(str[0]) == true)
			return (false);
		return (true);
	}

	// Returns 'true' if the string is a BASICTYPE.
	public boolean isBasicType(String str) {
		String[] keyword = { "int", "float", "char", "boolean" };
		for (String s : keyword)
			if (s.equals(str))
				return true;
		return false;
	}

	// Returns 'true' if the string is a KEYWORD.
	public boolean isKeyword(String str) {
		String[] keyword = { "chuongtrinh", "ketthuc", "hang", "kieu", "bien", "ham", "call", "batdau", "neu",
				"khi" };
		for (String s : keyword)
			if (s.equals(str))
				return true;
		return false;
	}
 
	// Returns 'true' if the string is an INTEGER.
	public boolean isInteger(String s) {
		char[] str = s.toCharArray();
		int i, len = str.length;

		if (len == 0)
			return (false);
		for (i = 0; i < len; i++) {
			if (str[i] != '0' && str[i] != '1' && str[i] != '2' && str[i] != '3' && str[i] != '4' && str[i] != '5'
					&& str[i] != '6' && str[i] != '7' && str[i] != '8' && str[i] != '9' || (str[i] == '-' && i > 0))
				return (false);
		}
		return (true);
	}

	// Returns 'true' if the string is a REAL NUMBER.
	public boolean isRealNumber(String s) {
		char[] str = s.toCharArray();
		int i, len = str.length;
		boolean pointused = false;

		if (len == 0)
			return false;
		for (i = 0; i < len; i++) {
			if (str[i] != '0' && str[i] != '1' && str[i] != '2' && str[i] != '3' && str[i] != '4' && str[i] != '5'
					&& str[i] != '6' && str[i] != '7' && str[i] != '8' && str[i] != '9' && str[i] != '.'
					|| (str[i] == '-' && i > 0))
				return false;
			if (str[i] == '.')
				if(!pointused) pointused = true;
				else return false;
				
		}
		return true;
	}

	public boolean isStringLiteral(String str) {
		char[] arr = str.toCharArray();
		for (char c : arr) {
			if(c=='\\' || c=='"') return false;
		}
		return true;
	}
	
	// Parsing the input STRING.
	public void parse() {

		int i = 1;
		for (String sub : s) {
			// String[] subarr = sub.split("\\+");

			// foreach substrings in input "s"
			// for (String str : subarr) {
			sub = sub.trim();
			System.out.println(sub);
			char[] c = sub.toCharArray();
			int len = c.length;
			int left = 0, right = 0;

			while (right <= len) {
				if (right<len && isDelimiter(c[right]) && left == right) {
					//System.out.println("check");
					if(c[right] == '\"') {
						right++;
						//System.out.println("check2");
						while(right<len) {
							if(c[right] != '\"') right++;
							else {
								String str = sub.substring(left+1,right);
								if(isStringLiteral(str)) {
									tokenData.add(new Token(str,TokenType.STRING_LITERAL, i));
									left=right;
									break;
								}
								else {
									tokenData.add(new Token(str,TokenType.STRING_LITERAL_ERROR, i));
									//System.out.println("al");
									left=right;
									break;
								}
							}
						}
						if(right == len)
							{
							tokenData.add(new Token(sub.substring(left,right-1),TokenType.STRING_LITERAL_ERROR, i));
							//System.out.println("hello");
							}
					}
					
					else if (right + 1 < len && isSeparator(c[right + 1])) {
						//System.out.print(c[right] + "as " + i);
						if (isOperator(c[right], c[right + 1])) {
							tokenData.add(new Token(String.valueOf(c[right]) + String.valueOf(c[right + 1]),
									TokenType.TOANTU, i));
							right++;
						}

						else if (isComparator(c[right], c[right + 1])) {
							//System.out.println(c[right] + "as " + right);
							tokenData.add(new Token(String.valueOf(c[right]) + String.valueOf(c[right + 1]),
									TokenType.SOSANH, i));
							right++;
						}
						else if (isOperator(c[right]))
							tokenData.add(new Token(String.valueOf(c[right]), TokenType.TOANTU, i));
						
						else if (isComparator(c[right]))
							tokenData.add(new Token(String.valueOf(c[right]), TokenType.SOSANH, i));
						
						else if (isSeparator(c[right])) 
							tokenData.add(new Token(String.valueOf(c[right]), TokenType.SEPARATOR, i));

					} 
					else if (isOperator(c[right]))
						tokenData.add(new Token(String.valueOf(c[right]), TokenType.TOANTU, i));
					
					else if (isComparator(c[right]))
						tokenData.add(new Token(String.valueOf(c[right]), TokenType.SOSANH, i));
					
					else if (isSeparator(c[right]))
						tokenData.add(new Token(String.valueOf(c[right]), TokenType.SEPARATOR, i));

					right++;
					if (right == len)
						break;
					left = right;
				}

				else if (right < len && isDelimiter(c[right]) && left != right || (right == len && left != right)) {
					String subStr = sub.substring(left, right);
					if(right == len) right ++;
					System.out.println("sub "+subStr);
					if (isKeyword(subStr))
						tokenData.add(new Token(subStr, TokenType.TUKHOA, i));
					// System.out.println(subStr+" is a keyword");

					else if (isBasicType(subStr))
						tokenData.add(new Token(subStr, TokenType.BASICTYPE, i));

					else if (isInteger(subStr))
						tokenData.add(new Token(subStr, TokenType.NUMB, i));
					// System.out.println(subStr + " is a integer");

					else if (isRealNumber(subStr))
						tokenData.add(new Token(subStr, TokenType.NUMB, i));
					// System.out.println(subStr + " is a real number");

					else if (validIdentifier(subStr))
						tokenData.add(new Token(subStr, TokenType.ID, i));
					// System.out.println(subStr + " is a valid identifier");

					// System.out.println(subStr+" is not a valid identifier");
					else if (!validIdentifier(subStr))
						tokenData.add(new Token(subStr, TokenType.TOKEN_ERROR, i));

					// taking to the next part <part><delimiter><part>
					left = right;

				}
				
				if (right<len && isDelimiter(c[right]) == false) {
					right++;
				}
			}
			// }
			i++;
		}
	}

	public ArrayList<Token> getTokenData() {
		return tokenData;
	}

	// 2 function bellow just for bloody testing
	public void display() {
		for (Token t : tokenData) {
			System.out.println(t.getToken() + " | " + t.getType() + " | " + t.getLine());
		}
	}

//	public static void main(String args[]) {
//		String input = 
//				"				a = 5\r\n" + 
//				".5	5.5";
//		System.out.println(input +"\n");
//		Tokenizer tokenizer = new Tokenizer(input);
//		tokenizer.parse();
//		tokenizer.display();
//	}
}
