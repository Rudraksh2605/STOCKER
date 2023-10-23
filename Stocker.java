package STOCKER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Stocker {

    static class CompanyInfo {
        String name;
        String INCOMEINCREASE;
        String NETPROFITINCREASE;

        CompanyInfo(String name, String INCOMEINCREASE, String NETPROFITINCREASE) {
            this.name = name;
            this.INCOMEINCREASE = INCOMEINCREASE;
            this.NETPROFITINCREASE = NETPROFITINCREASE;
        }
    }

    static Map<String, CompanyInfo> map = new HashMap<>();

    public static void main(String[] args) {

        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(
                new FileReader("D:\\VSforJAVA\\Assignment\\src\\STOCKER\\STOCKS-TRAIL 2.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);
                map.put(fields[0], new CompanyInfo(fields[0], fields[1], fields[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Map.Entry<String, CompanyInfo>> list = new LinkedList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, CompanyInfo>>() {
            public int compare(Map.Entry<String, CompanyInfo> o1, Map.Entry<String, CompanyInfo> o2) {
                try {
                    if (o1.getValue().NETPROFITINCREASE != null && o2.getValue().NETPROFITINCREASE != null) {
                        double netProfit1 = Double.parseDouble(o1.getValue().NETPROFITINCREASE);
                        double netProfit2 = Double.parseDouble(o2.getValue().NETPROFITINCREASE);

                        // Compare the net profit values
                        return Double.compare(netProfit2, netProfit1);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                // Default comparison result when there's an issue or null values
                return 0;
            }
        });

        for (Map.Entry<String, CompanyInfo> entry : list) {
            String COMPANY = entry.getKey();
            CompanyInfo info = entry.getValue();
            System.out.println("NAME OF THE COMPANY [COMPANY = " + COMPANY + " , INCOME INCREASE (%) = "
                    + info.INCOMEINCREASE + " , NET PROFIT INCREASE = "
                    + info.NETPROFITINCREASE + "]");
        }
    }
}
