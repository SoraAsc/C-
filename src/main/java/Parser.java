import java.util.Iterator;
import java.util.List;

public class Parser {
    private Iterator<Token> tokenIterator;
    private Token currentToken;

    public Parser(List<Token> tokens) {
        this.tokenIterator = tokens.iterator();
    }

    private  void next() {
        if(tokenIterator.hasNext()) currentToken = tokenIterator.next();
        else currentToken = null;
    }

    private void match(TokenType type) throws Exception {
        if(currentToken != null && currentToken.getType() == type) next();
        else throw new Exception("Erro de sintaxe: esperava " + type + " mas encontrou " +
                (currentToken != null ? currentToken.getType() : "EOF"));
    }

    public void parse() throws Exception {
        programa();
    }

    private void programa() throws Exception {
        declaracoesLista();
    }

    private void declaracoesLista() throws Exception {
        // <declarações lista> → <declarações lista> <declarações> | <declarações>
        declaracoes();
        while (currentToken != null && isDeclaracaoToken(currentToken)) {
            declaracoes();
        }
    }

    private void declaracoes() throws Exception {
        // <declarações> → <declaração var> | <declaração func>
        if(isTipoToken(currentToken)) {
            next(); // Consome o tipo
            match(TokenType.IDENT); // Consome o identificador (nome da variável ou função)
            if(currentToken.getType() == TokenType.PARABERTO) {
                // <declaração func> → <tipo> ident ( <par formais> ) <decl composto>
                match(TokenType.PARABERTO);
                parFormais();
            }
        }
    }

    private void parFormais() throws Exception {
        // <par formais> → <lista par formais> | ε
        if(isTipoToken(currentToken)) {
            listaParFormais();
        } // ε: não faz nada se não houver parâmetros
    }

    private void listaParFormais() throws Exception {
        // <lista par formais> → <parametro> , <lista par formais> | <parametro>
        parametro();
        while(currentToken != null && currentToken.getType() == TokenType.VIRGULA) {
            match(TokenType.VIRGULA);
            parametro();
        }
    }

    private void parametro() throws Exception {
        // <parametro> → <tipo> ident | <tipo> ident [ ]
        if(isTipoToken(currentToken)) {
            next(); // Consome o tipo
            match(TokenType.IDENT); // Consome o identificador
            if(currentToken.getType() == TokenType.COLCHEABERTO) {
                match(TokenType.COLCHEABERTO);
                match(TokenType.COLCHEFECHADO);
            }
        } else throw new Exception("Erro de sintaxe: Esperava tipo de parâmetro.");
    }

    private void declComposto() throws Exception {
        // <decl composto> → { <declarações locais> <lista comandos> }
        match(TokenType.CHAVEABERTO);
        declaracoesLocais();
        listaComandos();
        match(TokenType.CHAVEFECHADO);
    }

    private void declaracoesLocais() throws Exception {
        // <declarações locais> → <declarações locais> <declaração var> | ε
        while (isTipoToken(currentToken)) {
            declaracoes(); // Trata variáveis locais
        }
    }

    private void listaComandos() throws Exception {
        // <lista de comandos> → <comando> <lista de comandos> | ε
        while (isComandoToken(currentToken)) {
            comando();
        }
    }

    private void comando() throws Exception {
        // <comando> → <comando expressão> | <comando composto> | <comando seleção> | <comando iteração> | <comando retorno>
        if(currentToken.getType() == TokenType.IDENT) comandoExpressao();
        else if(currentToken.getType() == TokenType.CHAVEABERTO) comandoComposto();
        else if (currentToken.getType() == TokenType.IF) comandoSelecao();
//        else if (currentToken.getType() == TokenType.WHILE) comandoIteracao();
//        else if (currentToken.getType() == TokenType.RETURN) comandoRetorno();
        else throw new Exception("Erro de sintaxe: Comando inválido.");
    }

    private void comandoExpressao() throws Exception {
        // <comando expressão> → <expressão> ;
        expressao();
        match(TokenType.PONTOVIRGULA); // O comando deve terminar com ;
    }

    private void comandoComposto() throws Exception {
        // <comando composto> → { <lista de comandos> }
        match(TokenType.CHAVEABERTO);
        listaComandos();
        match(TokenType.CHAVEFECHADO);
    }

    private void comandoSelecao() throws Exception {
        // <comando seleção> → if (<expressão>) <comando> |
        //                     if (<expressão>) <comando> else <comando>
        match(TokenType.IF);
        match(TokenType.PARABERTO);
        expressao(); // Avalia a expressão condicional
        match(TokenType.PARFECHADO);
        comando();

        if (currentToken.getType() == TokenType.ELSE) {
            match(TokenType.ELSE);
            comando();
        }
    }

    private void expressao() {

    }

    private boolean isComandoToken(Token token) {
        // Verifica se o ‘token’ atual pode iniciar um comando.
        return token.getType() == TokenType.IF ||
                token.getType() == TokenType.WHILE ||
                token.getType() == TokenType.RETURN ||
                token.getType() == TokenType.IDENT ||
                token.getType() == TokenType.CHAVEABERTO;
    }

    private boolean isDeclaracaoToken(Token token) {
        // Verifica se o ‘token’ atual pode iniciar uma declaração.
        return isTipoToken(token);
    }

    private boolean isTipoToken(Token token) {
        // Verifica se o ‘token’ atual é um tipo (int ou void).
        return token.getType() == TokenType.INTTIPO || token.getType() == TokenType.VOIDTIPO;
    }



}
