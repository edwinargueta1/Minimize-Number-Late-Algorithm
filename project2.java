import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class project2{
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Hello Sir Noga");

        //Reading from the input file and adding the data into variables.
        File input = new File("input.txt");
        Scanner pointer = new Scanner(input);
        int numberOfJobs = pointer.nextInt();
        int jobs[][] = new int[numberOfJobs][2]; 
        for(int i = 0; i < numberOfJobs; i++){
            jobs[i][0] = pointer.nextInt();
            jobs[i][1] = pointer.nextInt();
        }
        pointer.close();

        printJobs(jobs, numberOfJobs);
        edf(jobs,numberOfJobs);
        sjf(jobs, numberOfJobs);
        lsf(jobs, numberOfJobs);
    }

    //method for printing the 2d array
    static void printJobs(int table[][], int numberOfJobs){
        for(int i = 0; i < numberOfJobs; i++){
            System.out.println("(" + table[i][0] + ", " + table[i][1]+")");
        }
    }

    //This algorithm runs the Earliest Deadline First
    static void edf(int table[][], int numberOfJobs){
        int tmp [][] = new int[numberOfJobs][2];
        tmp = table;
        int tmpInt[] = new int[2];

        //Rearranging the order of the points with the deadline
        for(int i = 0; i < numberOfJobs; i++){
            for(int j = i + 1; j < numberOfJobs; j++){
                if(tmp[i][1] > tmp[j][1]){

                    tmpInt[0] = tmp[i][0];
                    tmpInt[1] = tmp[i][1];
                    
                    tmp[i][0] = tmp[j][0];
                    tmp[i][1] = tmp[j][1];

                    tmp[j][0] = tmpInt[0];
                    tmp[j][1] = tmpInt[1];
                }
            }
        }
        System.out.println("EDF: " + compute(tmp, numberOfJobs));
    }

    //This algorithm runs the Shortest Job First
    static void sjf(int table[][], int numberOfJobs){
        int tmp [][] = new int[numberOfJobs][2];
        tmp = table;
        int tmpInt[] = new int[2];

        //Rearranging the order of the points with the shortest processing time.
        for(int i = 0; i < numberOfJobs; i++){
            for(int j = i + 1; j < numberOfJobs; j++){
                if(tmp[i][0] > tmp[j][0]){

                    tmpInt[0] = tmp[i][0];
                    tmpInt[1] = tmp[i][1];
                    
                    tmp[i][0] = tmp[j][0];
                    tmp[i][1] = tmp[j][1];

                    tmp[j][0] = tmpInt[0];
                    tmp[j][1] = tmpInt[1];
                }
            }
        }
        System.out.println("SJF: " + compute(tmp, numberOfJobs));
    }
    //This algorithm runs the Least Slack First
    static void lsf(int table[][], int numberOfJobs){
        int tmp [][] = new int[numberOfJobs][2];
        tmp = table;
        int tmpInt[] = new int[2];

        //Rearranging the order of the points with the d - p
        for(int i = 0; i < numberOfJobs; i++){
            for(int j = i + 1; j < numberOfJobs; j++){
                if(tmp[i][0] - tmp[i][1] < tmp[j][0] - tmp[j][1]){

                    tmpInt[0] = tmp[i][0];
                    tmpInt[1] = tmp[i][1];
                    
                    tmp[i][0] = tmp[j][0];
                    tmp[i][1] = tmp[j][1];

                    tmp[j][0] = tmpInt[0];
                    tmp[j][1] = tmpInt[1];
                }
            }
        }
        System.out.println("LSF: " + compute(tmp, numberOfJobs));
    }

    //Processes the lateness of a current configured job list.
    static int compute(int table[][], int numberOfJobs){
        int processTime = 0;
        int j = 0;
        int numberOfLate = 0;
        
        for(int i = 0; i < numberOfJobs; i++){
                processTime += table[i][0];
                if(processTime > table[j][1]){
                    numberOfLate += 1;
                }
                j += 1; 
        }
        return numberOfLate;
    }
}