/**
 *
 * @author Michelle
 */
//package Hw01b;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class Hw01b 
{
    static Scanner console = new Scanner(System.in);
    static String userChoice;
    static String stockFile = "stocks.txt";
    static String stockTickerInput, stockTickerInFile;
    static Double stockPriceInput,stockPriceInFile;
    static Scanner infile;
    static DecimalFormat df = new DecimalFormat("#.00");
    
    public static void main(String[] args) throws FileNotFoundException
    {
        do 
        {
            System.out.println("Enter '1' to get max, min and avg of a stock");
            System.out.println("Enter '2' to get stock ticker with highest price");
            System.out.println("Enter '3' to get stock ticker with lowest price");
            System.out.println("Enter 'c' to change the stockfile name");
            System.out.println("Enter 'q' to quit");

            userChoice = console.next();
            System.out.println();

            switch (userChoice.toLowerCase())
            {
                case "1": 
                    PrintOutTheChoice(userChoice," ");
                    System.out.print("Enter a stock ticker: ");
                    stockTickerInput = console.next();
                    System.out.println();
                    GetStockStats();
                  break; 
                case "2":
                case "3":
                    PrintOutTheChoice(userChoice," ");
                    GetStockHighLow(); 
                    break;
                 
                case "c":
                    PrintOutTheChoice(userChoice," ");
                    System.out.print("Enter a stock filename: ");
                    stockFile = console.next();
                    break;
                    
                case "q":
                    PrintOutTheChoice(userChoice, "Goodbye");
                    break;

                default: 
                    PrintOutTheChoice(userChoice, "Unrecognized menu option");
                    break;
            }
        }   while (userChoice != "q"); 
    }
    
    private static void PrintOutTheChoice ( String userChoice, String otherText)
    {
        System.out.println("Your choice: " + userChoice);
        System.out.println(otherText);
    }
    
    private static void ReadFromInFileTicker ()
    {
        stockTickerInFile = infile.next();
        stockPriceInFile = infile.nextDouble();
    }

    private static void GetStockStats()throws FileNotFoundException
    {   
        infile = new Scanner(new FileReader(stockFile));
        Double min = 0.0, max = 0.0, avg = 0.0;
        Double sum = 0.0;
        boolean found = false;
        Integer count = 0;
        
        while (infile.hasNext())
        {
            ReadFromInFileTicker();
            if (stockTickerInput.equalsIgnoreCase(stockTickerInFile))
            { 
                found = true;
                count++;
                sum += stockPriceInFile;
                
                if (count.equals(1))
                {
                    min = stockPriceInFile;
                    max = stockPriceInFile;
                }
                else 
                {
                    if (stockPriceInFile < min)
                        min = stockPriceInFile;
                    if (stockPriceInFile > max)
                        max = stockPriceInFile;
                }
                
            }
        }
        
        infile.close();
        if (found)
            System.out.println(String.format("%1s min: %2s max: %3s avg: %4s", 
                    stockTickerInput, 
                    df.format(min), 
                    df.format(max), 
                    df.format(sum/count)));
        else 
            System.out.println(String.format("Stock Ticker %1s not found in file", stockTickerInput));
        
    }
    
    private static void GetStockHighLow()throws FileNotFoundException
    {  
        infile = new Scanner(new FileReader(stockFile)); 
        Double min = 0.0, max = 0.0;
        String stockTickerMin = "", stockTickerMax = "";
        Integer count = 0;
        while (infile.hasNext())
        {
            ReadFromInFileTicker();
            count++;

            if (count.equals(1))
            {
               min = stockPriceInFile;
               max = stockPriceInFile;
               stockTickerMin = stockTickerInFile;
               stockTickerMax = stockTickerInFile;  
            }
            else 
            {
                if (stockPriceInFile < min)
                {
                    min = stockPriceInFile;
                    stockTickerMin = stockTickerInFile;
                }
                if (stockPriceInFile > max)
                {
                    max = stockPriceInFile;
                    stockTickerMax = stockTickerInFile;
                }
            }

        }
        infile.close();
            System.out.println(
                    String.format("%1s has %2s price of %3s", 
                            userChoice.equals("3") ? stockTickerMin : stockTickerMax, 
                            userChoice.equals("3") ? "lowest" : "highest", 
                            userChoice.equals("3") ? df.format(min) : df.format(max)));
    }
    
        
}
