package hamming;

import java.util.ArrayList;
import java.util.List;

public class HammingCode {
    private final ErrorDetection errorDetector;

    public HammingCode(ErrorDetection errorDetector) {
        this.errorDetector = errorDetector;
    }

    public int[] decode(int[] codeword) {
        System.out.println("Исходное кодовое слово:        " + BitUtils.bitArrayToString(codeword));

        int errorPosition = errorDetector.detectError(codeword);

        if (errorPosition != 0) {
            System.out.println("Ошибка обнаружена в бите:      " + errorPosition);
            if (errorPosition - 1 < codeword.length) {
                codeword[errorPosition - 1] ^= 1;
                System.out.println("Исправленное кодовое слово:    " + BitUtils.bitArrayToStringWithHighlight(codeword, errorPosition));
            } else {
                System.out.println("Ошибка вне диапазона кодового слова.");
            }
        } else {
            System.out.println("Ошибок не обнаружено.");
        }

        System.out.println("Статус:                        " + errorDetector.getStatus().getMessage());

        return extractDataBits(codeword);
    }

    private int[] extractDataBits(int[] codeword) {
        List<Integer> dataBits = new ArrayList<>();
        for (int i = 0; i < codeword.length; i++) {
            if (!isPowerOfTwo(i + 1)) {
                dataBits.add(codeword[i]);
            }
        }
        int[] result = new int[dataBits.size()];
        for (int i = 0; i < dataBits.size(); i++) {
            result[i] = dataBits.get(i);
        }
        return result;
    }

    private boolean isPowerOfTwo(int x) {
        return (x & (x - 1)) == 0;
    }
}
