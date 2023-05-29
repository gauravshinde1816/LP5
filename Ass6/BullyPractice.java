import java.util.Scanner;

public class BullyPractice {

    boolean[] process_state;
    int coordinator, n;

    public BullyPractice(int n) {
        this.n = n;
        this.coordinator = n - 1;

        process_state = new boolean[n];

        for (int i = 0; i < n; i++) {
            process_state[i] = true;
        }
    }

    // ping the coordinator
    public void ping_coordinator(int id) {

        if (this.process_state[id - 1] == false) {
            System.out.println("Process is dead");
            return;
        }

        if (!this.process_state[this.coordinator]) {
            System.out.println("Coordinator is not active");
            System.out.println("Performing election");
            election(id);
        }

        if (id == this.coordinator) {
            if (this.process_state[id - 1]) {
                System.out.println("Coordinator is active");
            } else {
                System.out.println("Coordinator is not active");
            }
        }
        System.out.println("Process " + id + " Sending message to " + (this.coordinator + 1));

    }

    // election
    public void election(int id) {
        int current_coordinator = id - 1;
        for (int i = id; i < this.n; i++) {
            if (this.process_state[i] && i > current_coordinator) {
                this.coordinator = i;
            }
        }
        System.out.println("Current Coordinator : " + this.coordinator + 1);
    }

    // deactivate process
    public void decativate(int id) {
        if (id < 0 || id > this.n) {
            System.out.println("Enter the correct ID");
            return;
        }

        if (this.process_state[id - 1] == false) {
            System.out.println("The process is already dead");
            return;
        } else {
            this.process_state[id - 1] = false;
            System.out.println("Process " + id + " deactivated");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of process");
        int n = sc.nextInt();
        BullyPractice ring = new BullyPractice(n);
        int choice = 0;

        while (choice != 5) {

            System.out.println("***********Menu***********");
            System.out.println("1. Deactivate a process");
            System.out.println("2. Ping coordinator");
            System.out.println("3. View Ring");
            System.out.println("4. Election");
            System.out.println("5. Exit");
            System.out.println("**************************");
            System.out.println("Enter Choice : ");

            System.out.println("Enter the choice");
            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the id to deactive process");
                    int id = sc.nextInt();
                    ring.decativate(id);
                    ;
                    break;
                }
                case 2: {
                    System.out.println("Enter the id to ping coordinator");
                    int id = sc.nextInt();
                    ring.ping_coordinator(id);
                    break;
                }
                case 3: {
                    System.out.println("Enter the id to start election");
                    int id = sc.nextInt();
                    ring.election(id);
                    break;

                }
                case 5: {
                    System.out.println("");
                    sc.close();
                }
            }

        }

    }

}
