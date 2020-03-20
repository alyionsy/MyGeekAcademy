package domain.serializator;

import domain.BookOwner;

import java.io.*;

public class BookOwnerSerializator {
    public boolean serialization(BookOwner owner, String fileName) {
        boolean flag = false;
        File f = new File(fileName);
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            if (fos != null) {
                ostream = new ObjectOutputStream(fos);
                ostream.writeObject(owner); // serialization
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

    public BookOwner deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis); // deserialization
            BookOwner owner = (BookOwner) istream.readObject();
            return owner;
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
