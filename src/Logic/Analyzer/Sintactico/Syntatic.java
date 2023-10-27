package Logic.Analyzer.Sintactico;
import java.util.List;
import java.util.concurrent.CancellationException;

import Logic.Analyzer.Lexico.Token.Token;

public class Syntatic {
    private List<Token> tokens;
    private int index;
    private String stateMessage;
    private Exp.VarDecl Declaraciones = new Exp.VarDecl();
    private String type;

    public String getStateMessage() {
        return stateMessage;
    }


    private void coincidir(int token) {
        System.out.println("coincidir" + token);
        if((tokens.get(index).getId().getId() != token)){
            System.out.println("error");
            throw new CancellationException("El numero que se esperaba de token '" + token + "' no el token '" + tokens.get(index).getId().getDescription() + "'");  
        }
        System.out.println("si coincidio: " + tokens.get(index).getLexema().getSubString() + " -- " + index + " de: " + (tokens.size()-1));
        if ((index == tokens.size() - 1 )) {
            return;
        }
        if ((index < tokens.size() - 1 )) {
            index++;
            System.out.println("index aumento " + index);
        }
    }

    private void programa() {
        System.out.println("programa");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 1:
                coincidir(1);
                coincidir(3);
                coincidir('('); // numero de "("
                coincidir(')'); // numero de ")"
                coincidir('{');
                declaraciones();
                ordenes();
                coincidir(11);
                coincidir('e');
                coincidir(';');
                coincidir('}');
                break;
            default:
                break;
        }
    }

    private void declaraciones() {
        System.out.println("declaraciones");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 1:
                coincidir(1);
                this.type = token.getLexema().getSubString();
                lista_variables();
                coincidir(';');
                declaraciones();
                break;
            case 2:
                coincidir(2);
                this.type = token.getLexema().getSubString();
                lista_variables();
                coincidir(';');
                declaraciones();
                break;
            default:
                break;
                
        }
        System.out.println("declaraciones fin");
    }
    private void lista_variables(){
        System.out.println("lista_variables");

        this.Declaraciones.setValue(type, this.tokens.get(index).getLexema().getSubString());
        System.out.println(this.Declaraciones.getVariable(type, this.tokens.get(index).getLexema().getSubString()));

        coincidir('i');
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case ',':
                coincidir(',');
                lista_variables();
            default:
                break;
        } 
        System.out.println("lista_variables fin");
    }

    private void ordenes() {
     System.out.println("ordenes");
        orden();
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 9:case 10:case 'i':
                ordenes();
                break;
            default:
                break;
        }
      System.out.println("ordenes fin");
    }
    private void orden(){
        System.out.println("orden");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 9:
               condicion(); 
                break;
            case 10:
                bucle_while();
                break;
            case 'i':
                asignaciones();
                break;
            default:
                break;
        }
        System.out.println("orden fin");
    }
 // asignaciones
    private void asignaciones() {
        System.out.println("asignaciones");
        coincidir('i');
        coincidir('=');
        expresion_arit();
        coincidir(';');  
        System.out.println("asignaciones fin");       
    }
    
    private void expresion_arit() {
        System.out.println("expresion_arit");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case '(':
                coincidir('(');
                expresion_arit();
                coincidir(')');
                exp_arit();
            break;
            case 'e': // numero de "entero"
                coincidir('e'); // numero de "entero"
                exp_arit();
                break;
            case 'f': // numero de "real"
                coincidir('f'); // numero de "real"
                exp_arit();
                break;
            case 'i':
                coincidir('i'); // numero de "identificador"
                exp_arit();
                break;
            default:
                throw new CancellationException("no es un operador valido");
        }
        System.out.println("expresion_arit fin");
    }

    private void exp_arit(){
        System.out.println("exp_arit");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case '+':case '-':case '*':case '/': 
                operador_arit();
                expresion_arit();
                exp_arit();
            default:
                break;
        }
        System.out.println("exp_arit fin");
    }

    private void operador_arit() {
        System.out.println("operador_arit");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case '+': // +
                coincidir('+'); // +
                break;
            case '*': // *
                coincidir('*'); // *
                break;
            case '-': // -
                coincidir('-'); // -
                break;
            case '/': // /
                coincidir('/'); // /
                break;
            default:
                throw new CancellationException("no es un operador valido");
        }
        System.out.println("operador_arit fin");
    }
//----------------------------------------------------------------------------------

// condicion --------------------------------------------------------------------
    private void condicion (){
        coincidir(9);
        coincidir('(');
        comparacion();
        coincidir(')');
        coincidir('{');
        ordenes();
        sig_condicion();
    } 
    private void sig_condicion(){
        System.out.println("sig_condicion");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case '}': //cierra llave
                coincidir('}'); //cierra llave
                if(this.tokens.get(index).getId().getId() == 12){// else
                    coincidir(12); // else
                    coincidir('{');
                    ordenes();
                    coincidir('}');
                } 
            break;
            default:
                throw new CancellationException("no cerro correctamente la condicion");
        }
        System.out.println("sig_condicion fin");
    }
//----------------------------------------------------------------------------------------
// comparacion ---------------------------------------------------------------------------------- 
    private void comparacion(){
        operador();
        condicion_op();
        operador();
    }

    private void operador(){
        System.out.println("operador");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 'e': // numero de "entero"
                coincidir('e'); // numero de "entero"
                break;
            case 'f': // numero de "real"
                coincidir('f'); // numero de "real"
                break;
            case 'i':
                coincidir('i'); // numero de "identificador"
                break;
            default:
                throw new CancellationException("no es un operador valido");
        }
        System.out.println("operador fin");
    }

    private void condicion_op() {
        /// usare if, y mi funcion isThere aqui
        System.out.println("condicion_op");
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
            case 17: // =
                coincidir(17); // =
                break;
            case 18: // <=
                coincidir(18); // <=
                break;
            case 19: // >=
                coincidir(19); // >=
                break;
            case '<': // <
                coincidir('<'); // <
                break;
            case '>': // >
                coincidir('>'); // >
                break;
            default:
                throw new CancellationException("no es un operador relacional valido");
        }
    }
//------------------------------------------------------------------------------------
// while ----------------------------------------------------------------------------------
    private void bucle_while (){
        coincidir(10);
        coincidir('(');
        comparacion();
        coincidir(')');
        coincidir('{');
        ordenes();
        coincidir('}');
    } 
//----------------------------------------------------------------------------------------
/* private void tipoDato() {
        Token token = this.tokens.get(index);
        switch (token.getId().getId()) {
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
        this.Declaraciones.clear();
        this.type = "";
    
        try {
            if (this.tokens.isEmpty()) {
                throw new CancellationException("La lista de tokens no se ha inicializado");
            }
            // Agregar una verificaciÃ³n para imprimir el tamaÃ±o de la lista
            System.out.println("TamaÃ±o de tokens en isValid: " + this.tokens.size());
            programa();
            if ( index < this.tokens.size() - 1){
                throw new CancellationException("El token '" + tokens.get(index).getLexema().getSubString() + "' no es valido");
            }
        } catch (CancellationException expected) {
            this.stateMessage = expected.getMessage();
            return false;
        }
        this.stateMessage = "Todo bien";
        return true;
    }
}