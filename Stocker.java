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

public class Trial1 {

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
                    int netProfitComparison = 0;
                    if (o1.getValue().NETPROFITINCREASE != null && o2.getValue().NETPROFITINCREASE != null) {
                        netProfitComparison = Long.compare(
                                Long.parseLong(o2.getValue().NETPROFITINCREASE),
                                Long.parseLong(o1.getValue().NETPROFITINCREASE));
                    }

                    if (netProfitComparison == 0) {
                        return Long.compare(
                                Long.parseLong(o2.getValue().INCOMEINCREASE),
                                Long.parseLong(o1.getValue().INCOMEINCREASE));
                    }

                    return netProfitComparison;

                } catch (NumberFormatException e) {
                    return 0; // handle the exception
                }
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
