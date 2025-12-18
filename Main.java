// Main.java — Students version
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    pubblic int[][][] profitData=new int[12][28][5];
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};
    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for (int i=0;i<months.length;i++){
            String filename="Data_Files/"+months[i]+".txt";
            File file=new File(filename);
            if (file.exists()){
                try {
                    Scanner sc = new Scanner(file);
                    if (sc.hasNextLine()) {
                        sc.nextLine();
                    }
                    while (sc.hasNextLine()) {
                        String[] parts = sc.nextLine().split(",");
                        for (int a = 0; a < parts.length; a++) {
                            if (parts.length == 3) {
                                int day = Integer.parseInt(parts[0].trim());
                                String commodityName = parts[1].trim();
                                int profit = Integer.parseInt(parts[2].trim());

                                int commodityIndex = -1;
                                for (int k = 0; k < commodities.length; k++) {
                                    if (commodities[k].equals(commodityName)) {
                                        commodityIndex = k;
                                        break;
                                    }
                                }
                                if (commodityIndex != -1 && day >= 1 && day <= 28) {
                                    profitData[i][day - 1][commodityIndex] = profit;
                                }
                            }

                        }
                    }
                    sc.close();
                }
                catch (FileNotFoundException e){
                    System.out.println("File couldn't find!"+filename);
                    e.printStackTrace();
                }
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if (month<12 || month>=0) {
            String y = null;
            int[] tot = new int[5];
            int max = 0;
            for (int j = 0; j < DAYS; j++) {
                for (int k = 0; k < commodities.length; k++) {
                    switch (k) {
                        case 0:
                            tot[k] += profitData[month][j][k];
                            break;
                        case 1:
                            tot[k] += profitData[month][j][k];
                            break;
                        case 2:
                            tot[k] += profitData[month][j][k];
                            break;
                        case 3:
                            tot[k] += profitData[month][j][k];
                            break;
                        case 4:
                            tot[k] += profitData[month][j][k];
                            break;
                    }
                }
                for (int l = 0; l < commodities.length; l++) {
                    if (tot[l] > max) {
                        max = tot[l];
                        y = commodities[l];
                    }
                }
            }
            return "Commodity totalProfit " +y+" "+max;
        }
        else {
            return "INVALID_MONTH";
        }
    }

    public static int totalProfitOnDay(int month, int day) {
        if(month<12 || month>=0){
            if (day==0){
                return -99999;
            }
            else if (day-1<DAYS || day-1>=0){
                int total=0;
                for (int i=0;i<commodities.length;i++){
                    total+=profitData[month][day-1][i];
                }
                return total;
            }
            else {
                return -99999;
            }
        }
        else{
            return -99999;
        }
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
    }

    public static int bestDayOfMonth(int month) {
        int dy=0;
        if (month<12 || month>=0){
            int[] tot=new int[DAYS];
            int max=0;
            for (int i=0;i<DAYS;i++){
                for(int j=0;j<commodities.length;j++){
                    tot[i]+=profitData[month][i][j];
                }
                if (max<=tot[i]){
                    max=tot[i];
                    dy=i;
                }
            }
            return dy+1;
        }
        else {
            return dy;
        }
    }
    
    public static String bestMonthForCommodity(String comm) { 
        return "DUMMY"; 
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}