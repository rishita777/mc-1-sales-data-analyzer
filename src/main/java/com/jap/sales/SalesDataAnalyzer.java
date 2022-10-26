package com.jap.sales;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SalesDataAnalyzer {
    // Read the data from the file and store in a List
    public List<SalesRecord> readFile(String fileName)
    {
        int count = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ="";
            bufferedReader.readLine();
            while((line=bufferedReader.readLine())!=null)
            {
                count++;
            }
            System.out.println(count);
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        List<SalesRecord> list=new ArrayList<>();
        SalesRecord salesData = null;
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line ="";
            bufferedReader.readLine();
            while((line=bufferedReader.readLine())!=null){
                String []data = line.split(",");
                salesData = new SalesRecord(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]),data[3],Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),Integer.parseInt(data[6]));
                // index++;

                list.add(salesData);



            }

            // System.out.println(list);


        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        return list;
    }

    // Sort the customers based on purchase amount
    public List<SalesRecord> getAllCustomersSortedByPurchaseAmount(List<SalesRecord> salesData, AmountComparator amountComparator){
        amountComparator = new AmountComparator();
        Collections.sort(salesData,amountComparator);
        return salesData;
    }

    // Find the top customer who spent the maximum time on the site
    public SalesRecord getTopCustomerWhoSpentMaxTimeOnSite(List<SalesRecord> salesData,TimeOnSiteComparator timeOnSiteComparator){
        timeOnSiteComparator = new TimeOnSiteComparator();
        Collections.sort(salesData,timeOnSiteComparator);
        SalesRecord maxTimeSpent = salesData.get(0);
        return maxTimeSpent;
    }

    public static void main(String[] args) {
        SalesDataAnalyzer salesDataAnalyzer = new SalesDataAnalyzer();
        List<SalesRecord> list1 = new ArrayList<>();
        AmountComparator amountComparator=new AmountComparator();
        TimeOnSiteComparator timeOnSiteComparator=new TimeOnSiteComparator();


        list1 = salesDataAnalyzer.readFile("D:\\NIIT JAVA\\COURSE 5\\SPRINT 3\\mc-1-sales-data-analyzer\\src\\main\\resources\\purchase_details.csv");
        for (SalesRecord salesRecord : list1) {
            System.out.println(salesRecord);
        }
        System.out.println("---------------------------------------------------------------------------------------");

        List<SalesRecord> list2=new ArrayList<>();
        list2=salesDataAnalyzer.getAllCustomersSortedByPurchaseAmount(list1,amountComparator);
        for (SalesRecord salesRecord:list2)
            System.out.println(salesRecord);
        System.out.println("---------------------------------------------------------------------------------------");
        SalesRecord timeOnSite=salesDataAnalyzer.getTopCustomerWhoSpentMaxTimeOnSite(list1,timeOnSiteComparator);
        System.out.println(timeOnSite);
    }
}