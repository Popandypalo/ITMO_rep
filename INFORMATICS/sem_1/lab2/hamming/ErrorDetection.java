package hamming;
public interface ErrorDetection {
    int detectError(int[] codeword);
    HammingStatus getStatus();
}
