import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BlockImplementation {

    public static void main(String[] args) throws Exception {
        boolean keepRunning = true;
        List<Block> blockchain = new ArrayList<>();

        Scanner keyboard = new Scanner(System.in);

        while (keepRunning) {
            System.out.println( "1) Tokenize\n2) Detokenize\n3) Exit");
            System.out.print( "Please choose an operation: ");

            int option = keyboard.nextInt();

            if (option == 1) {
                System.out.print("Please enter value to tokenize: ");
                String data = keyboard.next();  // local variable

                // use local variables in constructor call

                //initialize with previous hash
                if (blockchain.isEmpty()) {
                    Block block = new Block(data, "0d65d9aea14ac119173995894fee7a3d", new Date().getTime());
                    int prefix = 4;
                    block.mineBlock(prefix);
                    blockchain.add(block);
                } else {
                    Block block = new Block(data, blockchain.get(blockchain.size() - 1).getHash(), new Date().getTime());
                    int prefix = 4;
                    block.mineBlock(prefix);
                    blockchain.add(block);
                }

                System.out.println("*****************************************");
                System.out.println("Success!");
                System.out.println("Your tokenized value is: " + blockchain.get(blockchain.size() - 1).getData());
                System.out.println("*****************************************");
                System.out.println("" + keyboard.nextLine());
            } else if (option == 2) {
                System.out.print("Please enter a tokenized value: ");
                String data = keyboard.next();  // local variable

                String value = TokenizeUtils.detokenizeData(data);

                if (value.isEmpty()) {
                    System.out.println("Please enter a valid token!");
                }

                System.out.println("*****************************************");
                System.out.println("Success!");
                System.out.println("Your value is: " + value);
                System.out.println("*****************************************");
                System.out.println("" + keyboard.nextLine());
            } else if (option == 3) {
                keepRunning = false;
            }
        }
        System.out.println("" + keyboard.nextLine());
        System.out.println("Thank you!");
        keyboard.close();
    }
}
