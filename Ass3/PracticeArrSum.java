import mpi.MPI;
import mpi.*;


public class PracticeArrSum {

    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();


        int unitsize = 5 ,  root = 0;

        int[] send_buffer = new int[unitsize * size];
        int[] receive_buffer = new int[unitsize];
        int[] new_receive_buffer = new int[size];


        if(root == rank){
            int totalElements = unitsize * size;

            for (int j = 0; j < totalElements; j++) {
                send_buffer[j] = j+1;
                System.out.println("Element "  + j + " : " +  (j + 1));
            }
        }


        MPI.COMM_WORLD.Scatter(
            send_buffer, 
            0, 
            unitsize,
            MPI.INT,
            receive_buffer,
            0,
            unitsize, 
            MPI.INT,
            root
        );
        

        for(int i=0; i<unitsize ; i++){
            receive_buffer[0]+= receive_buffer[i];
        }

        System.out.println("Intermediate Sum at process " + rank + " " +  receive_buffer[0]);


        MPI.COMM_WORLD.Gather(
            receive_buffer, 
            0,
            1,
            MPI.INT,
            new_receive_buffer , 
            0,
            1,
            MPI.INT,
            root
        );

        if(root == rank){
            int sum = 0;

            for(int i=0 ;i<size ;i++){
                sum += new_receive_buffer[i];
            }

            System.out.println("Total Sum : " +  sum);
        }
    }

}
