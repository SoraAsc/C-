import java.io.InputStream;
import java.io.IOException;

public class Scanner
{
    InputStream in;
    boolean back;
    char currentChar;
    boolean error;
    StringBuffer tokenString;

    public Scanner (InputStream i)
    {
        this.in = i;
        back = false;
    }

    private boolean nextChar() throws IOException
    {
        if (!back) currentChar = (char) in.read();
        tokenString.append(currentChar);
        back = false;
        return true;
    }

    public void cleartokenString()
    {
        tokenString.deleteCharAt(tokenString.length() - 1);
    }

    public void setBack()
    {
        back = true;
        cleartokenString();
    }

    public Token lex()
    {
        int state;

        tokenString = new StringBuffer();
        error = false;
        state = 0;

        try
        {
            while(!error)
            {
                switch (state) // Todos os estados após o 10 (11..n) eu adicionei, os outros só corrigi
                {
                    case 0: nextChar();
                        if (Character.isLetter(currentChar)) state = 1;
                        else if (Character.isDigit(currentChar)) state = 3;
                        else if (currentChar == '>') state = 5;
                        else if (currentChar == '<') state = 8;
                        else if (currentChar == '=') state = 11;
                        else if (currentChar == '!') state = 12;
                        else if (currentChar == '(')
                            return new Token(TokenType.PARABERTO, tokenString.toString());
                        else if (currentChar == ')')
                            return new Token(TokenType.PARFECHADO, tokenString.toString());
                        else if (currentChar == '[')
                            return new Token(TokenType.COLCHEABERTO, tokenString.toString());
                        else if (currentChar == ']')
                            return new Token(TokenType.COLCHEFECHADO, tokenString.toString());
                        else if (currentChar == '/')
                            return new Token(TokenType.BARRA, tokenString.toString());
                        else if (currentChar == '*')
                            return new Token(TokenType.ASTERISCO, tokenString.toString());
                        else if ( (currentChar == ' '  ||
                                currentChar == '\n' ||
                                currentChar == '\t' ||
                                currentChar == '\r') )
                            cleartokenString();
                        else
                        if(currentChar == '.') return new Token(TokenType.PONTO, tokenString.toString());
                        else error = true;
                        break;
                    case 1: nextChar();
                        if(tokenString.toString().equals("int"))
                            return new Token(TokenType.INTTIPO, tokenString.toString());
                        else if(tokenString.toString().equals("void"))
                            return new Token(TokenType.VOIDTIPO, tokenString.toString());
                        else if(!Character.isLetterOrDigit(currentChar)) state = 2;
                        break;
                    case 2: setBack();
                        return new Token(TokenType.IDENT, tokenString.toString());
                    case 3: nextChar();
                        if (!Character.isDigit(currentChar)) state = 4;
                        break;
                    case 4: setBack();
                        return new Token(TokenType.INTCONST, tokenString.toString());
                    case 5: nextChar();
                        if (currentChar == '=') state = 6;
                        else state = 7;
                        break;
                    case 6: return new Token(TokenType.MAIORIGUAL, tokenString.toString());
                    case 7: setBack();
                        return new Token(TokenType.MAIOR, tokenString.toString());
                    case 8: nextChar();
                        if (currentChar == '=' ) state = 9;
                        else state = 10;
                        break;
                    case 9: return new Token(TokenType.MENORIGUAL, tokenString.toString());
                    case 10: setBack();
                        return new Token(TokenType.MENOR, tokenString.toString());
                    case 11: nextChar();
                        if (currentChar == '=') return new Token(TokenType.IGUALDADE, tokenString.toString());
                    case 12: nextChar();
                        if (currentChar == '=') return new Token(TokenType.DIFERENTEIGUAL, tokenString.toString());
                    default : error = true;
                }
            }
            return null;
        } catch (IOException e) { return null; }
    }
}