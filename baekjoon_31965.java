import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int houseCount = Integer.parseInt(st.nextToken());
        int queryCount = Integer.parseInt(st.nextToken());

        long[] positions = new long[houseCount];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < houseCount; i++) {
            positions[i] = Long.parseLong(st.nextToken());
        }

        long[] prefixSum = new long[houseCount + 1];
        for (int i = 1; i <= houseCount; i++) {
            prefixSum[i] = prefixSum[i - 1] + positions[i - 1];
        }

        StringBuilder output = new StringBuilder();

        for (int q = 0; q < queryCount; q++) {
            st = new StringTokenizer(br.readLine());
            long leftValue = Long.parseLong(st.nextToken());
            long rightValue = Long.parseLong(st.nextToken());

            int leftIdx = lowerBound(positions, leftValue);
            int rightIdx = upperBound(positions, rightValue) - 1;

            if (leftIdx > rightIdx || leftIdx == rightIdx) {
                output.append("0\n");
                continue;
            }

            long costLeft = calculateCost(leftIdx, leftIdx, rightIdx, positions, prefixSum);
            long costRight = calculateCost(rightIdx, leftIdx, rightIdx, positions, prefixSum);
            long maxCost = Math.max(costLeft, costRight);

            int medianIdx = (leftIdx + rightIdx) / 2;
            long minCost = calculateCost(medianIdx, leftIdx, rightIdx, positions, prefixSum);

            output.append(maxCost - minCost).append('\n');
        }

        System.out.print(output);
    }

    static int lowerBound(long[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    static int upperBound(long[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    static long calculateCost(int centerIdx, int leftIdx, int rightIdx, long[] pos, long[] prefixSum) {
        long leftCount = centerIdx - leftIdx;
        long leftSum = prefixSum[centerIdx] - prefixSum[leftIdx];
        long leftCost = leftCount * pos[centerIdx] - leftSum;

        long rightCount = rightIdx - centerIdx;
        long rightSum = prefixSum[rightIdx + 1] - prefixSum[centerIdx + 1];
        long rightCost = rightSum - rightCount * pos[centerIdx];

        return leftCost + rightCost;
    }
}
