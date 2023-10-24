package Logic.Analyzer.Sintactico;
import java.util.List;
import java.util.concurrent.CancellationException;

import Logic.Analyzer.Lexico.Token.Token;

public class ejemplo_sintactico {
    private List<Token> tokens;
    private int index;
    private String stateMessage;

    public String getStateMessage() {
        return stateMessage;
    }


    private void coincidir(int token) {
        System.out.println("coincidir");
        if((tokens.get(index).getId().getId() != token)){
            System.out.println("error");
            this.stateMessage = "El numero que se esperaba de token '" + token + "' en el token " + index;
            throw new CancellationException();  
        }
        System.out.println("si coincidio: " + index + " de: " + (tokens.size()-1));
        if ((index == tokens.size() - 1 )) {
            return;
        }
        if ((index < tokens.size() - 1 )) {
            index++;
            System.out.println("index aumento " + index);
        }
    }


    private void factor() {
        System.out.println("factor");
        switch (this.tokens.get(index).getId().getId()) {
            case '(': // "("
                coincidir('('); // numero de "("
                expr();
                coincidir(')'); // numero de ")"
                break;
            default:
                digito();
        }
        System.out.println("factor fin");
    }

    private void digito() {
        System.out.println("digito");
        switch (this.tokens.get(index).getId().getId()) {
            case 'e': // numero de "entero"
                coincidir('e'); // numero de "entero"
                break;
            case 'f': //numero de "real"
                coincidir('f'); // numero de "real"
                break;
            default:
                coincidir('i'); // numero de "identificador"
        }
    }
    
    private void divMul() {
        System.out.println("divMul");
        switch (this.tokens.get(index).getId().getId()) {
            case '*': /// cambiar numero "*"
                this.coincidir('*');
                factor();
                divMul();
            break;
            case '/': /// cambiar numero "/"
                this.coincidir('/'); 
                factor();
                divMul();
            break;
            default:
            break;
        }
        System.out.println("divMul fin");
    }

    private void subAdd(){
        System.out.println("subAdd");
        switch (this.tokens.get(index).getId().getId()) {
            case '+': 
                this.coincidir('+'); /// poner numero "+"
                term();
                subAdd();
            break;
            case '-':
                this.coincidir('-'); /// poner numero "-"
                term();
                subAdd();
            break;
            default:
            break;
        }
        System.out.println("subAdd fin");
    }

    private void term(){
        System.out.println("term");
        factor();
        divMul();
        System.out.println("term fin");
    }

    private void expr(){
        System.out.println("expr");
        term();
        subAdd();
        System.out.println("expr fin");
    }

    
   /* private void tipoDato() {
        switch (this.tokens.get(index).getId().getId()) {
            case 4: // Palabra reservada "int"
                coincidir(4);
                tipoDato();
                break;
            case 3: // Palabra reservada "float"
                coincidir(3);
                break;
            case 2: // Palabra reservada "void"
                coincidir(2);
                break;
            default:
                throw new CancellationException("Se esperaba palabra reservada 'int', 'float' o 'void' en el token " + index);
        }
    }*/

    public boolean isValid(List<Token> listaTokens) {
        listaTokens.add(new Token());
        this.tokens = listaTokens;
        this.index = 0;
    
        try {
            if (this.tokens.isEmpty()) {
                this.stateMessage = "La lista de tokens no se ha inicializado";
                throw new CancellationException();
            }
            // Agregar una verificaciÃ³n para imprimir el tamaÃ±o de la lista
            System.out.println("TamaÃ±o de tokens en isValid: " + this.tokens.size());
            expr();
            if ( index < this.tokens.size() - 1){
                this.stateMessage = "El token '" + tokens.get(index).getLexema().getSubString() + "' no es valido";
                throw new CancellationException();
            }
        } catch (CancellationException expected) {
            return false;
        }
        this.stateMessage = "Todo bien";
        return true;
    }
}