import java.util.*;
import java.io.*;
import java.nio.file.*;

public class EncryptionDecryption {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String EncryptOrDecrypt = "enc";
        String strOriginalText = null;
        String strKey = null;
        String inputFilePath = null;
        String outputFilePath = null;
        String algoritm = null;
        char [] arrToEncrypt = null;
        String inputFileString;
        boolean data = false;

        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("-mode"))
                EncryptOrDecrypt = args[i + 1];

            else if (args[i].equals("-key"))
                strKey = args[i + 1];

            else if (args[i].equals("-data"))
            {
                strOriginalText = args[i + 1];
                data = true;
            }

            else if (args[i].equals("-in"))
                inputFilePath = args[i + 1];

            else if (args[i].equals("-out"))
                outputFilePath = args[i + 1];

            else if (args[i].equals("-alg"))
                algoritm = args[i + 1];
        }


        int key = Integer.parseInt(strKey);

        if (data == true)
        arrToEncrypt = strOriginalText.toCharArray();

        else
        {
            try
            {
            inputFileString = readFileAsString(inputFilePath);
            arrToEncrypt = inputFileString.toCharArray();
            }
            catch (IOException e)
            {
                System.out.println("Cannot read file: " + e);
            }
        }

        switch (EncryptOrDecrypt)
        {
        case "enc":
            if (algoritm.equals("unicode"))
            Encrypt(arrToEncrypt, key, data, outputFilePath);
            else if (algoritm.equals("shift"))
            EncryptShift(arrToEncrypt, key, data, outputFilePath);
            break;

        case "dec":
            if (algoritm.equals("unicode"))
            Decrypt(arrToEncrypt, key, data, outputFilePath);
            else if (algoritm.equals("shift"))
            DecryptShift (arrToEncrypt, key, data, outputFilePath);
            break;
        }


    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    static void EncryptShift (char [] arrToEncrypt, int key, boolean data, String outputFilePath)
    {
        // A - Z = 65 - 90, a - z = 97 - 122
        int res = 0;
        String strCypherText = "";

        for (int i = 0; i < arrToEncrypt.length; i++)
        {
            if (arrToEncrypt[i] >= 'A' && arrToEncrypt[i] <= 'Z')
            {

                if (arrToEncrypt[i] + key > 90)
                {
                    res = arrToEncrypt[i] + key - 90 + 64;
                    arrToEncrypt[i] = (char)res;
                }
                else
                {
                    res = arrToEncrypt[i] + key;
                    arrToEncrypt[i] = (char)res;
                }
            }

            else if (arrToEncrypt[i] >= 'a' && arrToEncrypt[i] <= 'z')
            {
                if (arrToEncrypt[i] + key > 122)
                {
                    res = arrToEncrypt[i] + key - 122 + 96;
                    arrToEncrypt[i] = (char)res;
                }
                else
                {
                    res = arrToEncrypt[i] + key;
                    arrToEncrypt[i] = (char)res;
                }

            }
        }

        for (char cypherText : arrToEncrypt)
        {
            strCypherText = strCypherText + cypherText;
        }

        if (data == true)
            System.out.print(strCypherText);

        else
        {

            File file = new File(outputFilePath);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.print(strCypherText);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }
    static void Encrypt(char [] arrToEncrypt, int key, boolean data, String outputFilePath)
    {
        int res = 0;
        String strCypherText = "";
        for (int i = 0; i < arrToEncrypt.length; i++)
        {
                if (arrToEncrypt[i] + key > 127)
                {
                    res = arrToEncrypt[i] + key - 127;
                    arrToEncrypt[i] = (char)res;
                }
                else
                {
                    res = arrToEncrypt[i] + key;
                    arrToEncrypt[i] = (char)res;
                }
        }
        for (char cypherText : arrToEncrypt)
        {
            strCypherText = strCypherText + cypherText;
        }

            if (data == true)
            System.out.print(strCypherText);

            else
            {

                File file = new File(outputFilePath);
                try (PrintWriter printWriter = new PrintWriter(file)) {
                    printWriter.print(strCypherText);
                } catch (IOException e) {
                    System.out.printf("An exception occurs %s", e.getMessage());
                }
            }

    }
    static void DecryptShift(char [] arrToDecrypt, int key, boolean data, String outputFilePath)
    {
        int res = 0;
        String strDecypherText = "";
        for (int i = 0; i < arrToDecrypt.length; i++)
        {
            if ( arrToDecrypt[i] >= 'A' && arrToDecrypt[i] <= 'Z' )
            {

                if (arrToDecrypt[i] - key < 65)
                {
                    res = arrToDecrypt[i] - key - 64 + 90 ;
                    arrToDecrypt[i] = (char)res;
                }
                else
                {
                    res = arrToDecrypt[i] - key;
                    arrToDecrypt[i] = (char)res;
                }
            }

            else if (arrToDecrypt[i] >= 'a' && arrToDecrypt[i] <= 'z')
            {
                if (arrToDecrypt[i] - key < 97)
                {
                    res = arrToDecrypt[i] - key + 122 - 96;
                    arrToDecrypt[i] = (char)res;
                }
                else
                {
                    res = arrToDecrypt[i] - key;
                    arrToDecrypt[i] = (char)res;
                }

            }
        }
        for (char cypherText : arrToDecrypt)
        {
            strDecypherText = strDecypherText + cypherText;
        }

        if (data == true)
            System.out.print(strDecypherText);

        else
        {

            File file = new File(outputFilePath);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.print(strDecypherText);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }
    static void Decrypt(char [] arrToDecrypt, int key, boolean data, String outputFilePath)
    {
        int res = 0;
        String strDecypherText = "";
        for (int i = 0; i < arrToDecrypt.length; i++)
        {
                if (arrToDecrypt[i] - key < 0)
                {
                    res = arrToDecrypt[i] - key + 127 ;
                    arrToDecrypt[i] = (char)res;
                }
                else
                {
                    res = arrToDecrypt[i] - key;
                    arrToDecrypt[i] = (char)res;
                }
        }
        for (char cypherText : arrToDecrypt)
        {
            strDecypherText = strDecypherText + cypherText;
        }

        if (data == true)
            System.out.print(strDecypherText);

        else
        {

            File file = new File(outputFilePath);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                printWriter.print(strDecypherText);
            } catch (IOException e) {
                System.out.printf("An exception occurs %s", e.getMessage());
            }
        }
    }

}
