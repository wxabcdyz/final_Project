import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Locale;

class Transaction {

    private String transactionType;

    private double amount;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.CANADA);
    private long accountNum;


    private Date date;

    Scanner sc = new Scanner(System.in);

    public Transaction() {

    }

    public void transaction(long accountNum, long accountNum1, String transactionType,double amount) {

        this.accountNum= accountNum;

        this.transactionType = transactionType;

        this.amount = amount;

        date = new Date();

        operation();

    }

    private void operation() {

        if (transactionType.equalsIgnoreCase("Opening")) {

            try{



                FileWriter fstream = new FileWriter("MyFile.txt", true);

                BufferedWriter out =new BufferedWriter(fstream);

                int userId=(int) (findMaxId()+1);

                out.write(Integer.toString(userId)+"\n");

                out.write(amount+"\n");

                out.write(date+"\n");

                System.out.println("\tAccount created successfully.\n");

                out.close();

            }catch (IOException e) {

                System.err.println("Caught IOException: " + e.getMessage());

            }

        }



        else if (transactionType.equalsIgnoreCase("withdraw")) {

            Path path = Paths.get("MyFile.txt");

            if (Files.exists(path)) {

                findUpdate() ;

            }else{

                System.out.println("File not Found");

            }

        }



        else if (transactionType.equalsIgnoreCase("deposit")) {

            Path path = Paths.get("MyFile.txt");

            if (Files.exists(path)) {

                findUpdate() ;

            }else{

                System.out.println("File not Found");

            }

        }

        else if (transactionType.equalsIgnoreCase("transfer")) {

            Path path = Paths.get("MyFile.txt");

            if (Files.exists(path)) {

                findUpdate() ;

            }else{

                System.out.println("File not Found");

            }

        }

        else if (transactionType.equalsIgnoreCase("pay bill")) {

            Path path = Paths.get("MyFile.txt");

            if (Files.exists(path)) {

                findUpdate() ;

            }else{

                System.out.println("File not Found");

            }

        }



        else if (transactionType.equalsIgnoreCase("showInfo")) {

            Path path = Paths.get("MyFile.txt");

            if (Files.exists(path)) {

                findDisplay();

            }else{

                System.out.println("File not Found");

            }

        }



        else {

            System.out.println("Invalid option");

            return;

        }



    }

    private int findMaxId() {

        try{

            BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"));

            int count=0;

            while ((reader.readLine()) != null)

            {

                count=count+1;

            }

            reader.close();

// Logic for finding maximum Id

            return count/3;

        }

        catch (Exception e)

        {

            System.err.format("Exception occurred trying to read '%s'.", "MyFile.txt");

            e.printStackTrace();

        }

        return 0;

    }



    private void findDisplay() {

        try{

            BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"));

            String line;

            String trmpaccountNum=Long.toString(accountNum);

            int count=1;

            while ((line = reader.readLine()) != null)

            {



                if(count>1){

                    trmpaccountNum = line;

                }

                if((line.equals(trmpaccountNum))&&(count<4)){
                    System.out.println(line);

                    count=count+1;

                }

            }

            reader.close();

        }

        catch (Exception e)

        {

            System.err.format("Exception occurred trying to read '%s'.", "MyFile.txt");

            e.printStackTrace();

        }

    }

    private void findUpdate() {

        try{

            BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"));



            FileWriter fstream = new FileWriter("TempFile.txt", true);

            BufferedWriter out =new BufferedWriter(fstream);



            String line;

            String trmpaccountNum=Long.toString(accountNum);

            int count=1;

            while ((line = reader.readLine()) != null)

            {
                if(count>1){

                    trmpaccountNum =line;
                }

                if((line.equals(trmpaccountNum))&&(count<4)){

                    double temp_amount = 0;
                    //double temp_amount1 = 0;

                    if (count==1){

                        out.write(accountNum+"\n");

                    }

                    else if (count==2){

                        if(transactionType.equalsIgnoreCase("deposit")){

                            temp_amount=amount+Double.parseDouble(line);

                        }else if(transactionType.equalsIgnoreCase("withdraw")){

                            temp_amount=Double.parseDouble(line)-amount;

                        }else if(transactionType.equalsIgnoreCase("transfer")){

                            temp_amount=amount+Double.parseDouble(line); //value transfer to this account
                        }
                        else if(transactionType.equalsIgnoreCase("pay bill")){

                            temp_amount=Double.parseDouble(line)-amount;

                        }

                        if(temp_amount<0){

                            System.out.println("Could not update invalid value\n");

                            out.write(Double.toString(amount)+"\n");

                        }else{
                            if (transactionType.equalsIgnoreCase("deposit")||transactionType.equalsIgnoreCase("withdraw")||
                                    transactionType.equalsIgnoreCase("pay bill")) {
                                out.write(Double.toString(temp_amount) + "\n");

                                System.out.println(formatter.format(temp_amount) + " is updated balance in account after above transaction.\n");
                            }
                            else if (transactionType.equalsIgnoreCase("transfer")){
                                System.out.println(Double.toString(temp_amount) + " is updated value of TO A/C\n");
                                //System.out.println(Double.toString(temp_amount1) + " is updated value of FROM A/C\n");
                                out.write(Double.toString(temp_amount) + "\n");
                                //out.write(Double.toString(temp_amount1) + "\n");
                            }
                        }

                    }

                    else if(count==3){

                        out.write(date+"\n");

                    }

                    count=count+1;

                }else{

                    out.write(line+"\n");//System.out.println("Account not found in file");

                }

            }

            out.close();

            reader.close();



            File f1 = new File("MyFile.txt");

            f1.delete();

            File f2 = new File("TempFile.txt");

            boolean b = f2.renameTo(f1);

            if(b){

            }else{

                System.out.println("Updated has Error");

            }



        }

        catch (Exception e)

        {

            System.err.format("Exception occurred trying to read '%s'.", "MyFile.txt");

            e.printStackTrace();

        }

    }

}