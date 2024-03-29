import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface HashInterface<Key, Value> {
    Value get(Key key);
    void put(Key key, Value value);
    int getCollisions();
    void printTable(PrintWriter fout);
}

class LinearProbingHash<Key, Value> implements HashInterface<Key, Value> {

    private class Record {
        Key key;
        Value value;

        public Record(Key key1, Value value1) {
            key = key1;
            value = value1;
        }
    }

    private List<Record> table;
    private int collisions;

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % table.size();
    }

    public LinearProbingHash(int initialSize) {
        table = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            table.add(null);
        }
        collisions = 0;
    }

    @Override
    public Value get(Key key) {
        int index = lookUp(key);
        if (index >= table.size()) return null;
        Record p = table.get(index);
        return p != null ? p.value : null;
    }

    @Override
    public void put(Key key, Value value) {
        int index = lookUp(key);
        if (index >= table.size()) {
            throw new RuntimeException("Table is full");
        }
        Record p = table.get(index);
        if (p == null) {
            table.set(index, new Record(key, value));
        } else {
            p.value = value;
        }
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
public void printTable(PrintWriter fout) {
    fout.println("*** Linear probing Start ***");
    fout.println();
    fout.println("print table.size()=" + table.size());
    for (int i = 0; i < table.size(); i++) {
        Record p = table.get(i);
        if (p != null) {
            fout.println("index=" + i + " key=" + p.key + " value=" + p.value);
        }
    }
    fout.println();
    fout.println("Linear probing " + getCollisions() + " collisions");
    fout.println();
    fout.println("*** Linear probing End ***");
    fout.println();
}



    private int lookUp(Key key) {
        int index = hash(key);
        while (true) {
            Record p = table.get(index);
            if (p == null || p.key.equals(key)) {
                return index;
            }
            collisions++;
            index = (index + 1) % table.size();
        }
    }
}

class QuadraticProbingHash<Key, Value> implements HashInterface<Key, Value> {

    private class Record {
        Key key;
        Value value;

        public Record(Key key1, Value value1) {
            key = key1;
            value = value1;
        }
    }

    private List<Record> table;
    private int collisions;
    private static final int doubleFactor = 181;

    public QuadraticProbingHash(int initialSize) {
        table = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            table.add(null);
        }
        collisions = 0;
    }

    @Override
    public Value get(Key key) {
        int index = lookUp(key);
        if (index >= table.size()) return null;
        Record p = table.get(index);
        return p != null ? p.value : null;
    }

    @Override
    public void put(Key key, Value value) {
        int index = lookUp(key);
        if (index >= table.size()) {
            throw new RuntimeException("Table is full");
        }
        Record p = table.get(index);
        if (p == null) {
            table.set(index, new Record(key, value));
        } else {
            p.value = value;
        }
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
public void printTable(PrintWriter fout) {
    fout.println("*** Quadratic probing Start ***");
    fout.println();
    fout.println("print table.size()=" + table.size());
    for (int i = 0; i < table.size(); i++) {
        Record p = table.get(i);
        if (p != null) {
            fout.println("index=" + i + " key=" + p.key + " value=" + p.value);
        }
    }
    fout.println();
    fout.println("Quadratic probing " + getCollisions() + " collisions");
    fout.println();
    fout.println("*** Quadratic probing End ***");
    fout.println();
}



    private int hash2(Key key) {
        return doubleFactor - (key.hashCode() & 0x7fffffff) % doubleFactor;
    }

    private int lookUp(Key key) {
        int index = hash2(key);
        int i = 0;
        while (true) {
            Record p = table.get(index);
            if (p == null || p.key.equals(key)) {
                return index;
            }
            collisions++;
            i++;
            index = (index + f(i, key)) % table.size();
        }
    }

    private int f(int i, Key key) {
        return i * i;
    }   
}

class DoubleHashingProbing<Key, Value> implements HashInterface<Key, Value> {

    private class Record {
        Key key;
        Value value;

        public Record(Key key1, Value value1) {
            key = key1;
            value = value1;
        }
    }

    private List<Record> table;
    private int collisions;
    private static final int doubleFactor = 181;

    public DoubleHashingProbing(int initialSize) {
        table = new ArrayList<>(initialSize);
        for (int i = 0; i < initialSize; i++) {
            table.add(null);
        }
        collisions = 0;
    }

    @Override
    public Value get(Key key) {
        int index = lookUp(key);
        if (index >= table.size()) return null;
        Record p = table.get(index);
        return p != null ? p.value : null;
    }

    @Override
    public void put(Key key, Value value) {
        int index = lookUp(key);
        if (index >= table.size()) {
            throw new RuntimeException("Table is full");
        }
        Record p = table.get(index);
        if (p == null) {
            table.set(index, new Record(key, value));
        } else {
            p.value = value;
        }
    }

    @Override
    public int getCollisions() {
        return collisions;
    }

    @Override
public void printTable(PrintWriter fout) {
    fout.println("*** Double Hashing probing Start ***");
    fout.println();
    fout.println("print table_.size()=" + table.size());
    for (int i = 0; i < table.size(); i++) {
        Record p = table.get(i);
        if (p != null) {
            fout.println("index=" + i + " key=" + p.key + " value=" + p.value);
        }
    }
    fout.println();
    fout.println("Double Hashing probing " + getCollisions() + " collisions");
    fout.println();
    fout.println("*** Double Hashing probing End ***");
    fout.println();
}



    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % table.size();
    }

    private int hash2(Key key) {
        return doubleFactor - (key.hashCode() & 0x7fffffff) % doubleFactor;
    }

    private int lookUp(Key key) {
        int index = hash(key);
        int step = hash2(key);

        while (true) {
            Record p = table.get(index);
            if (p == null || p.key.equals(key)) {
                return index;
            }
            collisions++;
            index = (index + step) % table.size();
        }
    }
}

public class Driver {
    private static final int TABLE_SIZE = 191;

    public static void main(String[] args) {
        String[] inputFiles = {"in150.txt", "in160.txt", "in170.txt", "in180.txt"};
    
        for (String inputFile : inputFiles) {
            try {
                String outputFilePrefix = "out" + inputFile.substring(2, 5);
    
                LinearProbingHash<Integer, Integer> linearProbingHash = new LinearProbingHash<>(TABLE_SIZE);
                testFile(inputFile, outputFilePrefix + "_collisions_linear.txt", outputFilePrefix + "_tables_linear.txt", linearProbingHash);
    
                // DoubleHashingProbing<Integer, Integer> doubleHashingProbing = new DoubleHashingProbing<>(TABLE_SIZE);
                // testFile(inputFile, outputFilePrefix + "_collisions_double.txt", outputFilePrefix + "_tables_double.txt", doubleHashingProbing);
    
                QuadraticProbingHash<Integer, Integer> quadraticProbingHash = new QuadraticProbingHash<>(TABLE_SIZE);
                testFile(inputFile, outputFilePrefix + "_collisions_quadratic.txt", outputFilePrefix + "_tables_quadratic.txt", quadraticProbingHash);
    
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
    

    private static void testFile(String inputFilename, String outputFilename1, String outputFilename2, HashInterface<Integer, Integer> hashTable) {
        List<Integer> data = readData(inputFilename);
    
        try (PrintWriter fout = new PrintWriter(new FileWriter(outputFilename1));
     PrintWriter fout2 = new PrintWriter(new FileWriter(outputFilename2))) {

    fout.println("*** Random Order Start ***\n");
    testData(data, hashTable, fout, fout2);
    
            Collections.sort(data);
            fout.println("*** Ascending Order Start ***\n");
            testData(data, hashTable, fout, fout2);
    
            Collections.reverse(data);
            fout.println("*** Descending Order Start ***\n");
            testData(data, hashTable, fout, fout2);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<Integer> readData(String inputFile) {
        List<Integer> data = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = fin.readLine()) != null) {
                String[] tokens = line.split("\\s+");
                for (String token : tokens) {
                    try {
                        int key = Integer.parseInt(token.trim());
                        data.add(key);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing integer: " + token);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    

    private static void testData(List<Integer> data, HashInterface<Integer, Integer> hashTable, PrintWriter fout, PrintWriter fout2) {
        HashInterface<Integer, Integer> hashtable = new QuadraticProbingHash<>(TABLE_SIZE);

        for (Integer key : data) {
            testInputKey(key, hashTable, fout, fout2);
        }
        
        fout.println("\nQuadratic " + hashTable.getCollisions() + " collisions");
        hashTable.printTable(fout2);

        fout.println("\n*** End ***\n");
    }

    private static void testInputKey(Integer key, HashInterface<Integer, Integer> qph,
                                     PrintWriter fout, PrintWriter fout2) {
        int previousCollisions = qph.getCollisions();

        qph.put(key, key * 2);

        Integer retrievedValue = qph.get(key);
        String retrievedText = retrievedValue != null ? String.valueOf(retrievedValue) : "null";

        fout.println(key + " : " + key * 2 + " -> " + retrievedText +
                ", collisions " + (qph.getCollisions() - previousCollisions));

        if (retrievedValue == null || !retrievedValue.equals(key * 2)) {
            fout.println("Retrieved value " + retrievedText + " does not match stored value " + key * 2 +
                    " for key " + key + "\n");
            throw new RuntimeException("value mismatch");
        }
    }
}