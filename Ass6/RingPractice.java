import java.util.Scanner;

public class RingPractice {
    int n, inactice_process, coordinator;
    boolean[] process_state;

    public RingPractice(int n) {
        this.n = n;
        this.inactice_process = 0;
        this.coordinator = n - 1;
        process_state = new boolean[n];
        for (int i = 0; i < n; i++) {
            process_state[i] = true;
        }
    }

    public void deactivate_process(int id) {
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
            this.inactice_process += 1;
        }
    }

    public void election(int id) {
        if (this.inactice_process == this.n) {
            System.out.println("All the processes are deactive");
            return;
        }

        id = id - 1;
        int current_coordinator = id;
        System.out.println("Process initiating election " + (id + 1));
        int token = (id + 1) % n;

        while (token != id) {
            if (this.process_state[token] && token > current_coordinator) {
                current_coordinator = token;
            }
            token = (token + 1) % n;
        }
        this.coordinator = current_coordinator;
        System.out.println("Current coordinator : " + (coordinator + 1));
    }

    public void view_ring() {
        if (this.inactice_process == this.n) {
            System.out.println("No active Ring found");
            return;
        }
        System.out.println("Active Ring Members");
        for (int i = 0; i < this.n; i++) {
            if (this.process_state[i])
                System.out.println("process " + (i + 1));
        }
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of process");
        int n = sc.nextInt();
        RingPractice ring = new RingPractice(n);
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
                    ring.deactivate_process(id);
                    break;
                }
                case 2: {
                    System.out.println("Enter the id to ping coordinator");
                    int id = sc.nextInt();
                    ring.ping_coordinator(id);
                    break;
                }

                case 3: {
                    ring.view_ring();
                    break;
                }
                case 4: {
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
