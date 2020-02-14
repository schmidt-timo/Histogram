/*
 * @author Timo Schmidt, Sophia Stölzle
 * @version 2019-10-27
 */

import java.io.*;

public class Histogram {

    private static File file;
    private static FileReader fr;
    private static BufferedWriter writer;

    public static void main(String[] args) throws IOException {

        String jabberwocky = "src/Jabberwocky.txt";
        String emptyFile = "src/empty.txt";
        String test = "src/test.txt";

        Histogram hist = new Histogram();       // Create new instance of class

        // Exercise 1
        hist.readCharFromFile(jabberwocky);
        separationLine();
        hist.readCharFromFile2(jabberwocky);
        separationLine();
        hist.readCharFromFile(emptyFile);
        separationLine();
        hist.readCharFromFile2(emptyFile);
        separationLine();

        // Exercise 2
        hist.writeStringToFile("This text overwrites the existing text.", test);
        hist.writeStringToFile("This text overwrites the existing text.", test, true);
        hist.writeStringToFile(" This text gets added to the end of the file", test, true);
        hist.writeIntToFile(1234567, test, true);
        hist.createFile("src/", "newFile.txt");

        // Exercise 3 + 4
        hist.countCharsInFile(jabberwocky);

    }

    /**
     * Exercise 1 (First version)
     * @param filePath of the txt-file
     * @return next character
     */
    private char readCharFromFile(String filePath) throws IOException {

        char ch = '\0';

        file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        if (!(fis.available() > 0)) {
            System.out.println("There are no characters in this file!");
        }

        while (fis.available() > 0) {
            ch = (char) fis.read();
            System.out.print(ch);
        }

        return ch;
    }

    /**
     * Exercise #1 (Second version)
     * This method also removes all the carriage returns and line feeds.
     * @param fileName of the txt-file
     * @return next character
     */
    private char readCharFromFile2(String fileName) throws IOException {

        file = new File(fileName);
        fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        char c = '\0';
        int charNum;

        if((charNum = br.read()) == -1)
            System.out.println("There are no characters in this file!");

        while((charNum = br.read()) != -1)
        {
            c = (char) charNum;
            if (c == '\n' || c == '\r')
                continue;
            else
                System.out.print(c);
        }

        return c;
    }

    /**
     * Exercise 2 - First version (overwrites file)
     * @param str which gets printed to file
     * @param filePath of which file the String gets printed to
     */
    private void writeStringToFile(String str, String filePath) throws IOException {
        file = new File(filePath);
        writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }

    /**
     * Exercise 2 - Write String to File
     * @param str which gets printed to file
     * @param filePath of which file the String gets printed to
     * @param appendText: true = String gets printed to end of file, false = String overwrites file
     */
    private void writeStringToFile(String str, String filePath, Boolean appendText) throws IOException {

        file = new File(filePath);
        writer = new BufferedWriter(new FileWriter(file, appendText));
        writer.write(str);
        writer.close();

    }

    /**
     * Exercise 2 - Write int to File
     * @param number: int which gets printed to file
     * @param filePath of which file the String gets printed to
     * @param appendText: true = String gets printed to end of file, false = String overwrites file
     */
    private void writeIntToFile(int number, String filePath, Boolean appendText) throws IOException {

        String str = Integer.toString(number);
        file = new File(filePath);

        writer = new BufferedWriter(new FileWriter(file, appendText));
        writer.write(str);
        writer.close();

    }

    /**
     * Exercise 2 - Create new file
     * @param filePath: directory in which the file should be created
     * @param fileName: name of the newly created file
     */
    private void createFile(String filePath, String fileName) throws IOException {

        file = new File(filePath + fileName);
        file.createNewFile();
    }

    /**
     * Exercise 3 + 4
     * Check frequency of characters in a file
     * @param filePath to the file you want to check
     * @throws IOException
     */
    private void countCharsInFile(String filePath) throws IOException {

        file = new File(filePath);
        fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        int[] chars = new int[256];

        // initialize all characters with zero
        for(int i = 0; i < chars.length; i++) {
            chars[i] = 0;
        }

        String str = "";
        String line = br.readLine();

        // add whole text to a String
        while(line != null) {
            str += line;
            line = br.readLine();
        }

        br.close();

        // iterate through string and add up
        for (int j = 0; j < str.length(); j++) {
            char c = str.charAt(j);
            chars[c] += 1;
        }

        // go through Array and change index to corresponding char
        for (int k = 0; k < chars.length; k++) {
            char b = (char) k;

            if(chars[k] != 0) {
                writeStringToFile(b + "  :  " + chars[k] + "\n", "frequency.txt", true);
                System.out.println(b + "   :   " + "*".repeat(chars[k]));
            }
        }

    }

    /**
     * Static method for creating a separation line for the console output
     */
    public static void separationLine() {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

}
