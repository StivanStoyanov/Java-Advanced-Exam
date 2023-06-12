package TheExam;

import java.util.*;

public class P01_StackQueueMap {
    public static final int PATCH = 30;
    public static final int BANDAGE = 40;
    public static final int MEDKIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arrayTextiles = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] arrayMedicaments = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> textiles = new ArrayDeque<>();
        ArrayDeque<Integer> medicaments = new ArrayDeque<>();

        for (Integer textile : arrayTextiles) {

            textiles.offer(textile);
        }
        for (Integer medicament : arrayMedicaments) {
            medicaments.push(medicament);
        }
        Map<String, Integer> meds = new TreeMap<>();

        int patchCounter = 0;
        int bandageCounter = 0;
        int medkitCounter = 0;


        while (!textiles.isEmpty() && !medicaments.isEmpty()){
            int sum = 0;
            int textile = textiles.peek();
            int medicament = medicaments.peek();
            sum = textile + medicament;

            if (sum == PATCH){
                patchCounter ++;
                meds.put("Patch", patchCounter);
                textiles.poll();
                medicaments.pop();

            } else if (sum == BANDAGE){
                bandageCounter++;
                meds.put("Bandage", bandageCounter);
                textiles.poll();
                medicaments.pop();

            } else if (sum == MEDKIT){
                medkitCounter ++;
                meds.put("MedKit", medkitCounter);
                textiles.poll();
                medicaments.pop();

            }else if (sum > MEDKIT){
                medkitCounter ++;
                meds.put("MedKit", medkitCounter);
                textiles.poll();
                medicaments.pop();
                int leftSum = sum - 100;
                int nextMedicament = medicaments.pop();
                medicaments.push(nextMedicament + leftSum);

            }else {
                textiles.poll();
                if (!medicaments.isEmpty()) {
                    int nextMedicament = medicaments.pop();
                    medicaments.push(nextMedicament + 10);
                }else {
                    medicaments.push(medicament + 10);
                }

            }
        }
        if (textiles.isEmpty() && !medicaments.isEmpty()){
            System.out.println("Textiles are empty.");
        } else if (!textiles.isEmpty() && medicaments.isEmpty()){
            System.out.println("Medicaments are empty.");
        }else if (textiles.isEmpty() && medicaments.isEmpty()){
            System.out.println("Textiles and medicaments are both empty.");
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        meds.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.printf("%s - %d%n", entry.getKey(), entry.getValue());
        }

        if (!textiles.isEmpty()){
            int leftTextiles = textiles.size();
            StringBuilder sb = new StringBuilder();
            sb.append("Textiles left: ");
            for (int i = 0; i < leftTextiles; i++) {

                int currentTextile = textiles.pop();

                if (i < leftTextiles - 1) {
                    sb.append(currentTextile).append(", ");

                } else if (i == leftTextiles - 1){
                    sb.append(currentTextile);
                }
            }
            System.out.println(sb.toString());
        }
        if (!medicaments.isEmpty()){
            int leftMedicaments = medicaments.size();
            StringBuilder sb = new StringBuilder();
            sb.append("Medicaments left: ");
            for (int i = 0; i < leftMedicaments; i++) {

                int currentMedicament = medicaments.pop();

                if (i < leftMedicaments - 1) {
                    sb.append(currentMedicament).append(", ");

                } else if (i == leftMedicaments - 1){
                    sb.append(currentMedicament);
                }
            }
            System.out.println(sb.toString());
        }

    }
}
