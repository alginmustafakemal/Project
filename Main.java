// Main.java — Students version
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static int[][][] profitData=new int[12][28][5];
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};
    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for (int i = 0; i < months.length; i++) {
            Path path = Paths.get("Data_Files", months[i] + ".txt");

            if (Files.exists(path)) {
                try (Scanner sc = new Scanner(path)) {
                    if (sc.hasNextLine()) {
                        sc.nextLine();
                    }
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] parts = line.split(",");

                        if (parts.length == 3) {
                            int day = Integer.parseInt(parts[0].trim());
                            String commodityName = parts[1].trim();
                            int profit = Integer.parseInt(parts[2].trim());

                            int commodityIndex = -1;
                            for (int k = 0; k < commodities.length; k++) {
                                if (commodities[k].equalsIgnoreCase(commodityName)) {
                                    commodityIndex = k;
                                    break;
                                }
                            }

                            if (commodityIndex != -1 && day >= 1 && day <= 28) {
                                profitData[i][day - 1][commodityIndex] = profit;
                            }
                        }
                    }
                } catch (IOException e) {
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
        if(from<=0 || to>=28){
            return -99999;
        }
        for (int a=0;a<commodities.length;a++){
            if (commodity.equals(commodities[a])){
                if (from<=to){
                    int tot=0;
                    for (int i=0;i<months.length;i++){
                        for (int j=from-1;j<=to;j++){
                            tot+=profitData[i][j][a];
                        }
                    }
                    return tot;
                }
                else{
                    return -99999;
                }
            }
        }
        return -99999;
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
        for (int a=0;a<commodities.length;a++){
            if(comm.equals(commodities[a] )){
                int[] tot=new int[months.length];
                int bestMnth=profitData[0][0][a];
                int mnth=0;
                for (int i=0;i<months.length;i++){
                    for (int j=0;j<DAYS;j++){
                        tot[i]+=profitData[i][j][a];
                    }
                    if (bestMnth<tot[i]){
                        bestMnth=tot[i];
                        mnth=i;
                    }
                }
                return months[mnth];
            }
        }
        return "INVALID_COMMODITY";
    }

    public static int consecutiveLossDays(String comm) {
        for (int a=0;a<commodities.length;a++){
            if (comm.equals(commodities[a])){
                int bestStreak=0;
                int[] streak=new int[months.length];
                for (int y=0;y<streak.length;y++){
                    streak[y]=0;
                }
                for (int i=0;i<months.length;i++){
                    for (int j=0;j<DAYS;j++){
                        if (profitData[i][j][a]<0){
                            streak[i]++;

                        }
                        else if(profitData[i][j][a]>=0){
                            streak[i]=0;
                        }
                    }
                    if (streak[i]>bestStreak){
                        bestStreak=streak[i];
                    }
                }
                return bestStreak;
            }
        }
        return -1;
    }
    
    public static int daysAboveThreshold(String comm, int threshold) {
        for (int a=0;a<commodities.length;a++){
            if (comm.equals(commodities[a])){
                int totalDay=0;
                for (int i=0;i<months.length;i++){
                    for (int j=0;j<DAYS;j++){
                        if (profitData[i][j][a]>threshold){
                            totalDay++;
                        }
                    }
                }
                return totalDay;
            }
        }
        return -1;
    }

    public static int biggestDailySwing(int month) {
        if(month<12 && month>=0) {
            int max = 0;
            int min = 0;

            for (int i = 0; i < DAYS; i++) {
                int totday = 0;
                for (int j = 0; j < commodities.length; j++) {
                    totday += profitData[month][i][j];
                }
                if (totday > max) {
                    max = totday;
                }
                if (totday < min) {
                    min = totday;
                }
            }
            return max-min;
        }
        return -99999;
    }
    
    public static String compareTwoCommodities(String c1, String c2) {
        int totc1=0;
        int totc2=0;
        String c1s="";
        String c2s="";
        for (int a=0;a<commodities.length;a++){
            if(c1.equals(commodities[a])){
                c1s=commodities[a];
                for (int i=0;i<months.length;i++){
                    for (int j=0;j<DAYS;j++){
                        totc1+=profitData[i][j][a];
                    }
                }
                break;
            }
        }
        for (int b=0;b<commodities.length;b++){
            if(c2.equals(commodities[b])){
                c2s=commodities[b];
                for (int z=0;z<months.length;z++){
                    for (int k=0;k<DAYS;k++){
                        totc2+=profitData[z][k][b];
                    }
                }
                break;
            }
        }
        if(totc1>totc2){
            return c1s+" is better by "+(totc1-totc2);
        }
        else if(totc1==totc2){
            return  "Equal";
        }
        else {
            return c2s+" is better by "+(totc2-totc1);
        }
    }
    
    public static String bestWeekOfMonth(int month) {
        if(month<12 && month>=0) {
            int[] totweek = new int[4];
            int bestweek = 0;
            int wk=0;
            for (int i = 0; i < DAYS; i++) {
                if (i < 7){
                    for(int k=0;k<commodities.length;k++) {
                        totweek[0] += profitData[month][i][k];
                    }
                }
                else if (i < 14){
                    for(int k=0;k<commodities.length;k++) {
                        totweek[1] += profitData[month][i][k];
                    }
                }
                else if (i < 21){
                    for(int k=0;k<commodities.length;k++) {
                        totweek[2] += profitData[month][i][k];
                    }
                }
                else if (i < 28){
                    for(int k=0;k<commodities.length;k++) {
                        totweek[3] += profitData[month][i][k];
                    }
                }
            }
            for (int j = 0; j < totweek.length; j++) {
                if (totweek[j] > bestweek) {
                    bestweek = totweek[j];
                    wk=j+1;
                }
            }
            return "Week " + wk;
        }
        return  "INVALID_MONTH";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}