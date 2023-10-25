package Logic.Analyzer.Sintactico;

import java.util.List;
interface Exp {
   public String toString();
}
class NumExp implements Exp {
   public String type;
   public String value;
   public NumExp ( String t , String v) { value=v; type=t; }
   public String toString(){
      return "<" + type + "," + value + ">";
   }
}
class VariableExp implements Exp {
   public String type;
   public String name;
   public VariableExp ( String t, String n) { name=n; type=t;}
   public String toString(){
      return "<" + type + "," + name + ">";
   }
}
class BinaryExp implements Exp {
   public String operator;
   public Exp left;
   public Exp right;
   public BinaryExp ( String o, Exp l, Exp r ) { operator=o; left=l; right=r; }
   public String toString(){
      return operator + " (" + left.toString() + " " + right.toString() + ") ";
   }
}
class UnaryExp implements Exp {
   public String operator;
   public Exp operand;
   public UnaryExp ( String o, Exp e ) { operator=o; operand=e; }
   public String toString(){
      return operator + " " + operand.toString();
   }
}
class CallExp implements Exp {
   public String name;
   public List<Exp> arguments;
   public CallExp ( String nm, List<Exp> s ) { name=nm; arguments=s; }
   public String toString(){
      String value = "0";
      for (Exp s : arguments){
         value = s.toString() + '\n';
      }
      return name + " " + value;
   }
}
class ProjectionExp implements Exp {
   public Exp record;
   public String attribute;
   public ProjectionExp ( Exp v, String a ) { record=v; attribute=a; }
   public String toString(){
      return record.toString() + " " + attribute;
   }
}
class RecordElement {
   public String attribute;
   public Exp value;
   public RecordElement ( String a, Exp v ) { attribute=a; value=v; }
   public String toString(){
      return attribute + " " + value.toString();
   }
}
class RecordExp implements Exp {
    public List<RecordElement> elements;
    public RecordExp ( List<RecordElement> el ) { elements=el; }
    public String toString(){
      String value = "0";
      for (RecordElement s : elements){
         value = s.toString() + '\n';
      }
      return value;
   }
}