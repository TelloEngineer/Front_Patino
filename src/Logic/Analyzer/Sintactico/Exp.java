package Logic.Analyzer.Sintactico;

import java.util.concurrent.CancellationException;

import lombok.Data;

import java.util.List;

public interface Exp {
   public String toString();

   public String semanticAnalize();

   @Data
   class Num implements Exp {
      public String type;
      public String value;

      public Num(String t, String v) {
         value = v;
         type = t;
      }

      public String toString() {
         return " <" + type + "," + value + "> ";
      }

      public String semanticAnalize() {
         return type;
      }
   }

   @Data
   class Variable implements Exp {
      public String type;
      public String name;

      public Variable(String t, String n) {
         name = n;
         type = t;
      }

      public String toString() {
         return " <" + type + "," + name + "> ";
      }

      public String semanticAnalize() {
         return type;
      }
   }

   class Binary implements Exp {
      public String operator;
      public Exp left;
      public Exp right;

      public Binary(String o, Exp l, Exp r) {
         operator = o;
         left = l;
         right = r;
      }

      public String toString() {
         return " ( " + operator + " " + left.toString() + "," + right.toString() + ") ";
      }

      @Override
      public String semanticAnalize() {
         String typeLeft = left.semanticAnalize(), typeRight = right.semanticAnalize();
         if (!typeLeft.equals(typeRight)) {
            throw new CancellationException("error en la operacion: " + operator);
         }
         return typeLeft;
      }
   }

   class Unary implements Exp {
      public String operator;
      public Exp operand;

      public Unary(String o, Exp e) {
         operator = o;
         operand = e;
      }

      public String toString() {
         return operator + " " + operand.toString();
      }

      @Override
      public String semanticAnalize() {
         return operand.semanticAnalize();
      }
   }

   class Call implements Exp {
      public String name;
      public List<Exp> arguments;

      public Call(String nm, List<Exp> s) {
         name = nm;
         arguments = s;
      }

      public String toString() {
         String value = "0";
         for (Exp s : arguments) {
            value = s.toString() + '\n';
         }
         return name + " " + value;
      }

      @Override
      public String semanticAnalize() {
         return " ";
      }

   }

   class Projection implements Exp {
      public Exp record;
      public String attribute;

      public Projection(Exp v, String a) {
         record = v;
         attribute = a;
      }

      public String toString() {
         return record.toString() + " " + attribute;
      }

      @Override
      public String semanticAnalize() {
         return record.semanticAnalize();
      }
   }

   class Record implements Exp {
      public List<RecordElement> elements;

      public Record(List<RecordElement> el) {
         elements = el;
      }

      public String toString() {
         String value = "0";
         for (RecordElement s : elements) {
            value = s.toString() + '\n';
         }
         return value;
      }

      @Override
      public String semanticAnalize() {
         return " ";
      }

   }

   class RecordElement {
      public String attribute;
      public Exp value;

      public RecordElement(String a, Exp v) {
         attribute = a;
         value = v;
      }

      public String toString() {
         return attribute + " " + value.toString();
      }
   }
}
