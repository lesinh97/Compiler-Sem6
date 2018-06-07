
public enum TokenType {

	/** BASICTYPE INTERGER, CHAR, BOOLEAN --- DONE*/
	BASICTYPE,
	
	/** Chữ trước, tiếp theo là số và chữ --- DONE*/
	ID,
	
	/** keyword */
	TUKHOA,
	
	/** Khác vd &^%$ các kiểu NOT YET**/
	TOKEN_ERROR,
	
	/** Các từ tố so sánh > | >= | < | <= | == --- DONE*/
	SOSANH,
	
	/** . , ; ( ) : DONE định nghĩa  --- DONE**/
	SEPARATOR,
	
	/** Toán tử +, -, *, / --- NOT YET*/
	TOANTU,
	
	/** Các String với bắt đầu bằng \" \" "Hello" "1" --- DONE*/
	STRING_LITERAL,
	
	/** String literal error */
	STRING_LITERAL_ERROR,
	
	/** Số --- DONE**/
	NUMB,
	
	/** Absolutely nothing ! DONE định nghĩa --- DONE **/
	EMPTY,
}
