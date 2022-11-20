import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start{
    int count=0;
    public void StartBank(){

        Scanner input = new Scanner(System.in);
        char mainYesOrNo = 'Y';
        while (mainYesOrNo =='Y'){
            System.out.print("\t\t ================================\n");
            System.out.print("\t\t Welcome To Banking System (GroupA)\n");
            if(count<1){
                count=count+1;
            }else{
                System.out.print("\t\t Again \n");
            }
            System.out.print("\t\t ================================\n");

            System.out.print("\tPlease select one option\n\n");
            if(count<2){
                System.out.print("\t\t1 : Enter into banking system\n\n");
                count=count+1;
            }else{
                System.out.print("\t\t1 : Enter Again\n\n");
            }
            System.out.print("\t\t2 : Exit\n\n");
            System.out.println("\t\t\tEnter your choice: ");
            int switchChoice = input.nextInt();
            switch (switchChoice)
            {

                case 1: {
                    start();
                    break;
                }
                case 2: {
                    return;
                }
                default: {
                    System.out.println("Invalid Selection");
                    error();
                    break;
                }
            }
            System.out.println("\n\tDo you want to run your Program Again \n\t\t\tY = yes\n\t\t\tN = No\n");
            System.out.println("Enter your choice: ");

            mainYesOrNo =(input.next()).charAt(0);
            if(Character.isLowerCase(mainYesOrNo )){
                mainYesOrNo =Character.toUpperCase(mainYesOrNo );
            }
        }
    }
    private List<String> readFile(String filename) {
        List<String> records = new ArrayList<String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
    private void start() {
        String choice, ch, operation;
        Transaction transac = new Transaction();
        Scanner sc = new Scanner(System.in);
        double amount;
        long accountNo=0;
        long accountNo1=0;

        do {

            System.out.print("\tQ:What do you want to do next?\n\n");
            System.out.print("\t\t1 : New account\n\n");
            System.out.print("\t\t2 : Transaction\n\n");
            System.out.print("\t\t3 : View Account Information\n\n");
            System.out.print("\t\tq : Exit\n\n");
            System.out.println("Your choice: ");
            choice = sc.next();
            switch (choice) {
                case "1":
                    double openingBalance;
                    System.out.println("Enter the opening balance :");
                    openingBalance = sc.nextDouble();
                    transac.transaction(accountNo, accountNo1,"Opening", openingBalance);
                    accountNo=accountNo+1;
                    break;
                case "2":
                    System.out.print("\tQ:What type of transaction you want to do today?\n\n");
                    System.out.print("\t\ta : Deposit\n\n");
                    System.out.print("\t\tb : Withdraw\n\n");
                    System.out.print("\t\tc : Transfer\n\n");
                    System.out.print("\t\td : Pay bill\n\n");
                    ch = sc.next();
                    if (ch.equalsIgnoreCase("a"))
                    {
                        operation = "Deposit";
                        System.out.println("Account Number:");
                        accountNo = sc.nextLong();
                        System.out.println("Amount:");
                        amount = sc.nextDouble();
                        transac.transaction(accountNo, accountNo1, operation, amount);
                    }
                    else if (ch.equalsIgnoreCase("b"))
                    {
                        operation = "Withdraw";
                        System.out.println("Account Number:");
                        accountNo = sc.nextLong();
                        System.out.println("Amount:");
                        amount = sc.nextDouble();
                        transac.transaction(accountNo, accountNo1,operation, amount);
                    }
                    else if (ch.equalsIgnoreCase("c"))
                    {
                        operation = "Transfer";
                        System.out.println("From Account Number:");
                        accountNo = sc.nextLong();
                        System.out.println("To Account Number:");
                        accountNo1 = sc.nextLong();
                        System.out.println("Amount:");
                        amount = sc.nextDouble();
                        transac.transaction(accountNo, accountNo1,operation, amount);
                    }
                    else if (ch.equalsIgnoreCase("d"))
                    {
                        operation = "Pay bill";
                        System.out.println("Account Number:");
                        accountNo = sc.nextLong();
                        System.out.println("Amount of bill:");
                        amount = sc.nextDouble();
                        transac.transaction(accountNo, accountNo1,operation, amount);
                    }
                    else
                    {
                        operation = "Invalid option";
                    }
                    break;
                case "3":

                    System.out.println("Account Number:");
                    accountNo = sc.nextLong();
                    operation = "showInfo";
                    transac.transaction(accountNo, accountNo1, operation, 0);
                    break;
                case "q":
                    System.out.println("Thank you for using our system!");
                    return;
                default:
                    error();
            }
        } while (choice != "q");
        sc.close();
    }
    public static void error() {
        System.out.print("\t\t *****************************\n");
        System.out.print("\t\t |-----------> ERROR <--------| \n");
        System.out.print("\t\t *****************************\n");
        System.out.print("\t\t You Select something wrong\n");
        System.out.print("\t\t OR\n");
        System.out.print("\t\t There may be some other Problem\n");
        System.out.print("\t\t It is better for you to try again...!\n");
        System.out.print("\t\t *****************************\n");
    }
}