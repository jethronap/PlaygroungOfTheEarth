package designPatterns.singleton;

/**
 * an impl of singleton where
 * a finite number of instances
 * is needed
 *
 * Multiton: key-value pair with lazy loading
 */
public class Multiton {

    public static void main(String[] args) {
        // create a printer
        Printer main = Printer.get(Subsystem.PRIMARY);
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        // the output will give the already created printer in the HashMap
        Printer aux2 = Printer.get(Subsystem.AUXILIARY);
    }

}
