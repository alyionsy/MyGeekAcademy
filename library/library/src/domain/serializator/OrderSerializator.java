package domain.serializator;

import domain.Order;

import java.io.*;

public class OrderSerializator {
    public boolean serialization(Order order, String fileName) {
        boolean flag = false;
        File f = new File(fileName);
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            if (fos != null) {
                ostream = new ObjectOutputStream(fos);
                ostream.writeObject(order); // serialization
                flag = true;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File could not be created: "+ e);
        } catch (NotSerializableException e) {
            System.err.println("Class does not support serialization: " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("Stream close error");
            }
        }
        return flag;
    }

    public Order deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis); // deserialization
            Order order = (Order) istream.readObject();
            return order;
        } catch (ClassNotFoundException ce) { System.err.println("Class does not exist: " + ce);
        } catch (FileNotFoundException e) {
            System.err.println("Deserialization file does not exist: "+ e);
        } catch (InvalidClassException ioe) {
            System.err.println("Class version mismatch: " + ioe);
        } catch (IOException ioe) {
            System.err.println("Common I/O error: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("Stream close error");
            }
        }
        throw new InvalidObjectException("Object not restored");
    }
}
