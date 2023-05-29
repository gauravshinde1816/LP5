import mpi.MPI;
import mpi.*;

public class ArrSum {
    public static void main(String[] args) {
        MPI.Init(args);

        // get the rank and size

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // unitsize , buffer size , buffer array

        int unitsize = 5, root = 0;
        int[] send_buffer = new int[size * unitsize];
        int[] receive_buffer = new int[unitsize];
        int[] new_receive_buffer = new int[size];

        // set data for distribution
        
        if (rank == root){
            int total_size = unitsize * size;
            System.out.println("Total number of element :" + total_size);
            for(int i=0; i<total_size ; i++){
                System.out.println("Element " + i + " " + (i + 1));
                send_buffer[i] = (i+1);
            }
        }

        // scatter the data to process
        MPI.COMM_WORLD.Scatter(
            send_buffer ,
            0 , 
            unitsize , 
            MPI.INT ,
            receive_buffer , 
            0 , 
            unitsize , 
            MPI.INT,
            root
        );

        // calculate the non root element sum and store at first index
        for(int i=0; i< unitsize ; i++){
            receive_buffer[0] = receive_buffer[0] + receive_buffer[i];        
        }


        System.out.println("Intermediate process sum " + receive_buffer[0]);

        // gather the data
        MPI.COMM_WORLD.Gather(
            receive_buffer , 
            0 , 
            1 , 
            MPI.INT, 
            new_receive_buffer , 
            0 , 
            1 , 
            MPI.INT, 
            root
        );
        // aggregate data to calculate the final sum
        if(rank == root){
            int total_sum  = 0;
            for(int i=0 ; i<size ; i++){
                total_sum = total_sum + new_receive_buffer[i];
            }
            System.out.println("Final Sum : "  +  total_sum);
        }

        MPI.Finalize();
    }

}