import java.util.*;

public class EncryptionDecryption {
    // A - Z = 65 - 90, a - z = 97 - 122
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        String EncryptOrDecrypt = scanner.nextLine();
        String strOriginalText = scanner.nextLine();
        String strKey = scanner.nextLine();
        int key = Integer.parseInt(strKey);
        char [] arrToEncrypt = strOriginalText.toCharArray();

        switch (EncryptOrDecrypt)
        {
        case "enc":
            Encrypt(arrToEncrypt, key);
            break;

        case "dec":
            Decrypt(arrToEncrypt, key);
            break;

        }

    }
    static void Encrypt(char [] arrToEncrypt, int key)
    {
        int res = 0;
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
            System.out.print(cypherText);

    }

    static void Decrypt(char [] arrToDecrypt, int key)
    {
        int res = 0;
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
        for (char decypherText : arrToDecrypt)
            System.out.print(decypherText);
    }

}
