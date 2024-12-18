package hamming;

public class HammingErrorDetector implements ErrorDetection {
    private HammingStatus status = HammingStatus.NO_ERROR;

    @Override
    public int detectError(int[] codeword) {
        int m = 0;
        while (Math.pow(2, m) < codeword.length + 1) {
            m++;
        }

        int[] syndrome = new int[m];

        for (int i = 0; i < m; i++) {
            int p = 1 << i;
            int parity = 0;
            for (int j = 1; j <= codeword.length; j++) {
                if ((j & p) != 0) {
                    parity ^= codeword[j - 1];
                }
            }
            syndrome[i] = parity;
        }

        int errorPosition = 0;
        for (int i = 0; i < m; i++) {
            errorPosition += syndrome[i] << i;
        }

        status = errorPosition != 0 ? HammingStatus.ERROR : HammingStatus.NO_ERROR;

        return errorPosition;
    }

    @Override
    public HammingStatus getStatus() {
        return status;
    }
}
