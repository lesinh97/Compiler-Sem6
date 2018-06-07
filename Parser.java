
public class Parser {

	static Token token;
	static Tokenizer tokenizer;
	static int count;
	static boolean cuPhap = true;
	static String tmpString ="";
	public Parser(String input) {
		tokenizer = new Tokenizer(input);
		tokenizer.parse();
		count = 0;
	}
	
	public static void nextToken() {
		if(count < tokenizer.tokenData.size()) {
			token = tokenizer.tokenData.get(count);
			//System.out.println(token.getToken());
			count++;
		}
	}
	
	public static boolean accept(String s) {
		if(token.getToken().equals(s)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void baoLoi(String s) {
		System.out.println("Co loi tai dong "+ token.getLine() +" ; " + s + " ; "+token.getToken());
		String tmp = "Co loi tai dong"+" " + token.getLine() + ": " +s;
		tmpString+= tmp+"\n";
		cuPhap = false;
		
	}
	public static boolean isID() {
		if(token.getType().equals(TokenType.ID)) return true;
		else  {
//			System.out.println(token.getType());
//			System.out.println(token.getType().equals(TokenType.ID));
			return false;
		}
	}
	
	public static boolean isNumb() {
		if(token.getType().equals(TokenType.NUMB)) return true;
		else {
			return false;
		}
	}
	public static boolean isBasicType() {
		if(token.getType().equals(TokenType.BASICTYPE)) return true;
		else {
			return false;
		}
	}
	
	public static boolean isSoSanh() {
		if(token.getType().equals(TokenType.SOSANH)) return true;
		else {
			return false;
		}
	}
	public static boolean isStringLiteral() {
		if(token.getType().equals(TokenType.STRING_LITERAL)) return true;
		else {
			return false;
		}
	}
	public static void chuongtrinh() {
		if(accept("chuongtrinh")) {
			nextToken();
//			System.out.println("working?");
		}
		else {
			baoLoi("Thieu chuongtrinh");
		}
		if(isID()) {
			nextToken();
		}
		else baoLoi("Thieu id cho chuongtrinh");
		if(accept(";")) {
			nextToken();
		}
		else baoLoi("Thieu ;");
		block();
		if(accept("}")) {
		}
		else baoLoi("Thieu ki hieu }");
	}
	
	public static void block() {
		kbhang();
		kbkieu();
		kbbien();
		kbham();
		if(accept("batdau")) {
			nextToken();
		}
		else baoLoi("thieu batdau");
		lenhs();
		if(accept("ketthuc")) {
			nextToken();
		}
		else baoLoi("thieu ketthuc");
	}
	public static void kbhang() {
		if(accept("hang")) {
			nextToken();
			cac_hang();
		}
	}
	public static void cac_hang() {
		if(isID()) {
			nextToken();
		}
		else baoLoi("Thieu id cho hang");
		if(accept("=")) {
			nextToken();
		}
		else baoLoi("Thieu dau =");
		if(isNumb()) {
			nextToken();
		}
		else baoLoi("Yeu cau nhap so");
		if(accept(";")) {
			nextToken();
		}
		else baoLoi("Thieu ;");
		them_hang();
	}
	
	public static void them_hang() {
		if(isID()) {
			cac_hang();
		}
	}
	
	public static void kbkieu() {
		if(accept("kieu")) {
			nextToken();
			cac_kieu();
		}
		
	}
	
	public static void cac_kieu() {
		if(isID()) {
			nextToken();
		}
		else baoLoi("Thieu Id chi kieu");
		if(accept("=")) {
			nextToken();
		}
		else baoLoi("Thieu =");
		kieu();
		if(accept(";")) {
			nextToken();
		}
		else baoLoi("Thieu ;");
		them_kieu();
	}
	
	public static void them_kieu() {
		if(isID()) {
			cac_kieu();
		}
	}
	
	public static void kieu() {
		if(isID()) {
			nextToken();
		}
		else if(isBasicType()) {
			nextToken();
		}
		else baoLoi("Can kieu");
	}
	
	public static void kbbien() {
		if(accept("bien")) {
			nextToken();
			cac_bien();
		}
	}
	
	public static void cac_bien() {
		if(isID()) {
			nextToken();
		}
		else baoLoi("Thieu id cho bien");
		if(accept(":")) {
			nextToken();
		}
		else baoLoi("Thieu :");
		kieu();
		if(accept(";")) {
			nextToken();
		}
		else baoLoi("Thieu ;");
		them_bien();
	}
	
	public static void them_bien() {
		if(isID()) {
			cac_bien();
		}
	}
	
	public static void kbham() {
		if(accept("ham")) {
			nextToken();
			if(isID()) {
				nextToken();
			}
			else baoLoi("Thieu ID");
			if(accept("(")) {
				nextToken();
			}
			else baoLoi("Thieu (");
			ds_thamso();
			if(accept(")")) {
				nextToken();
			}
			else baoLoi("Thieu )");
			if(accept(";")) {
				nextToken();
			}
			else baoLoi("Thieu ;");
			if(isBasicType()) {
				nextToken();
			}
			else baoLoi("Thieu type tra ve");
			if(accept(";")) {
				nextToken();
			}
			else baoLoi("Thieu ;");
			block();
			if(accept(";")) {
				nextToken();
			}
			else baoLoi("Thieu ;");
			kbham();
		}
	}
	
	public static void ds_thamso() {
		if(isID()) {
			nextToken();
			if(accept(":")) {
				nextToken();
			}
			else baoLoi("Thieu :");
			if(isBasicType()) {
				nextToken();
			}
			else baoLoi("Thieu kieu");
			op_thamso();
		}
	}
	
	public static void op_thamso() {
		if(accept(",")) {
			nextToken();
			if(isID()) {
				nextToken();
			}
			else baoLoi("Thieu id");
			if(accept(":")) {
				nextToken();
			}
			else baoLoi("Thieu :");
			if(isBasicType()) {
				nextToken();
			}
			else baoLoi("Thieu kieu");
			op_thamso();
		}
	}
	
	public static void lenhs() {
		if(isID() || accept("call") || accept("batdau") || accept("neu") || accept("khi")) {
			lenh();
			op_lenh();
		}
	}
	
	public static void lenh() {
		if(isID()) {
			nextToken();
			if(accept("=")) {
				nextToken();
			}
			else baoLoi("Thieu =");
			value();
		}
		else if(accept("call")) {
			nextToken();
			if(isID()) {
				nextToken();
			}
			else baoLoi("Thieu id ten ham");
			if(accept("(")) {
				nextToken();
			}
			else baoLoi("Thieu (");
			exprs();
			if(accept(")")) {
				nextToken();
			}
			else baoLoi("Thieu )");
		}
		else if(accept("batdau")) {
			nextToken();
			lenhs();
			if(accept("ketthuc")) {
				nextToken();
			}
			else baoLoi("Thieu kethuc");
		}
		else if(accept("neu")) {
			nextToken();
			if(accept("(")) {
				nextToken();
			}
			else baoLoi("Thieu (");
			dieukien();
			if(accept(")")) {
				nextToken();
			}
			else baoLoi("Thieu )");
			lenhs();
			else_stmt();
			if(accept("ketthuc")) {
				nextToken();
			}
			else baoLoi("Thieu ketthuc");
		}
		else if(accept("khi")) {
			nextToken();
			if(accept("(")) {
				nextToken();
			}
			dieukien();
			if(accept(")")) {
				nextToken();
			}
			else baoLoi("Thieu )");
			lenhs();
			if(accept("ketthuc")) {
				nextToken();
			}
			else baoLoi("Thieu ketthuc");
		}
		else baoLoi("unexpected symbol");
	}
	
	public static void else_stmt() {
		if(accept("else")) {
			nextToken();
			lenhs();
		}
	}
	
	public static void op_lenh() {
		if(accept(";")) {
			nextToken();
			lenhs();
		}
	}
	public static void value() {
		if(isStringLiteral()) {
			nextToken();
		}
		//First cua <expr> la (, numb, id
		else if(accept("(") || accept("-") || isNumb() || isID()) {
			expr();
		}
		else baoLoi("Thieu gia tri cho bien");
	}
	
	public static void dieukien() {
		expr();
		if(isSoSanh()) {
			nextToken();
		}
		else baoLoi("Thieu phep so sanh");
		expr();
	}
	
	public static void exprs() {
		if(accept("(") || accept("-") || isNumb() || isID()) {
			expr();
			m_exprs();
		}
	}
	public static void m_exprs() {
		if(accept(",")) {
			nextToken();
			expr();
			m_exprs();
		}
	}
	public static void expr() {
		term();
		factor_term();
	}
	public static void factor_term() {
		if(accept("*") || accept("/")) {
			nextToken();
			expr();
		}
	}
	
	public static void term() {
		factor();
		factor_factor();
	}
	
	public static void factor_factor() {
		if(accept("+") || accept("-")) {
			nextToken();
			term();
		}
	}
	public static void factor() {
		if(accept("-")) {
			nextToken();
			factor();
		}
		else if(accept("(")) {
			nextToken();
			expr();
			if(accept(")")) {
				nextToken();
			}
			else baoLoi("Thieu )");
		}
		else if(isID()) {
			nextToken();
		}
		else if(isNumb()) {
			nextToken();
		}
		else baoLoi("Thieu factor");
	}
//	public static void main(String[] args) {
//		String input = "chuongtrinh firsttest \n batdau neu (a == 6.00) a = \"buison\" ketthuc ketthuc }" ;
//		new Parser(input);
//		Parser.tokenizer.display();
//		nextToken();
//		chuongtrinh();
//		if(Parser.cuPhap == true) {
//			System.out.println("Dung cu phap");
//		}
//	}

}
