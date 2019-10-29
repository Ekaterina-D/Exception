import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class PhoneBook {

    private ArrayList<String> phoneBook = new ArrayList<>();
    private String fileLocation = "PhoneBook.txt";
    private static PhoneBook instance = null;

    public static void main(String[] args) throws WhatAreYouDoingException {
        PhoneBook phoneBook = PhoneBook.getInstance();
        phoneBook.saveWord("Иванов И.И. 89106943498");
        phoneBook.saveWord("Иванов И.И. 89106943497");
    }

    private PhoneBook() {
        readWordsFromFile();
    }

    public static PhoneBook getInstance() {
        if (instance == null) {
            instance = new PhoneBook();
        }
        return instance;
    }

    public void saveWord(String record) throws WhatAreYouDoingException {
        if (!phoneBook.contains(record)) {
            phoneBook.add(record);
            writeWordToFile(record);
        } else {
            throw new WhatAreYouDoingException("Повтор записи");
        }
    }

    private void writeWordToFile(String record) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(
                    fileLocation), true));
            bufferedWriter.write(record);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readWordsFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(
                    fileLocation)));
            String record;
            while ((record = bufferedReader.readLine()) != null) {
                if (!phoneBook.contains(record)) {
                    phoneBook.add(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WhatAreYouDoingException extends Exception {

    public WhatAreYouDoingException(String message) {
        super(message);
    }

}