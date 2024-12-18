package hamming;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] codeword = null;

        System.out.println("\u001B[34m********** Декодер Хэмминга **********\u001B[0m");

        while (codeword == null) {
            try {
                System.out.print("Введите кодовое слово: ");
                String input = scanner.nextLine();
                codeword = BitUtils.stringToBitArray(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        if(scanner != null) scanner.close();
        
        HammingCode hammingCode = new HammingCode(new HammingErrorDetector());
        int[] dataBits = hammingCode.decode(codeword);

        System.out.println("Информационные биты:           " + BitUtils.bitArrayToString(dataBits));
        System.out.println("\u001B[34m**************************************\u001B[0m");
    }
}
