import java.io.*;

enum TokenType { NUM, SOMA, MULT, APar, FPar, EOF }

class Token {
    String lexema;
    TokenType token;

    Token(String s, TokenType t) {
        lexema = s;
        token = t;
    }
}

class AnaliseLexica {

    BufferedReader arquivo;

    AnaliseLexica(String a) throws Exception {
        this.arquivo = new BufferedReader(new FileReader(a));
    }

    // Agora aceita números com mais de um dígito
    Token getNextToken() throws Exception {
        int eof = -1;
        char currchar;
        int currchar1;

        // Ignora espaços, tabs, quebras de linha e retornos de carro
        do {
            currchar1 = arquivo.read();
            if (currchar1 == eof) {
                arquivo.close();
                return new Token("EOF", TokenType.EOF);
            }
            currchar = (char) currchar1;
        } while (currchar == '\n' || currchar == ' ' || currchar == '\t' || currchar == '\r');

        if (currchar >= '0' && currchar <= '9') {
            StringBuilder numberBuilder = new StringBuilder();
            numberBuilder.append(currchar);

            arquivo.mark(1);
            while (true) {
                currchar1 = arquivo.read();
                if (currchar1 == -1) break;

                currchar = (char) currchar1;
                if (currchar >= '0' && currchar <= '9') {
                    numberBuilder.append(currchar);
                    arquivo.mark(1);
                } else {
                    arquivo.reset();
                    break;
                }
            }

            return new Token(numberBuilder.toString(), TokenType.NUM);
        } else {
            switch (currchar) {
                case '(':
                    return new Token(String.valueOf(currchar), TokenType.APar);
                case ')':
                    return new Token(String.valueOf(currchar), TokenType.FPar);
                case '+':
                    return new Token(String.valueOf(currchar), TokenType.SOMA);
                case '*':
                    return new Token(String.valueOf(currchar), TokenType.MULT);
                default:
                    throw new Exception("Caractere inválido: " + ((int) currchar));
            }
        }
    }
}
