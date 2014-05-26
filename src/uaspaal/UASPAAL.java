/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uaspaal;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 *
 * @author NOOR-MAULIDA
 */
public class UASPAAL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        
        /*
        Format Input
        //jumlah vertex:
        6
        //jumlah edge:
        8
        //edge yg terhubung:
        0 1 2
        0 2 9
        1 2 1
        1 3 0
        1 4 0
        2 4 7
        3 5 7
        4 5 4
        //source
        0
        //sink
        5
        */
        

        int flow[][];
        int capacities[][];
        int i;
        Scanner scan = new Scanner(new BufferedInputStream(System.in));
        System.out.println("Masukkan jumlah vertex: ");
        int save = scan.nextInt();
        Problem prob = new Problem(save);
        
        System.out.println("Masukkan jumlah edge: ");
        int edge = scan.nextInt();
        flow = new int[edge+1][edge+1];
        
        capacities = new int[edge+1][edge+1];
        
        int s, t, maxflow;
        System.out.println("Masukkan edge yang terhubung");
        System.out.println("s <spasi> t <spasi> maxflow");
        for(int m = 0; m < edge; m++) {
            s=scan.nextInt();
            t=scan.nextInt();
            maxflow=scan.nextInt();
            
            capacities[s][t]=maxflow;
            
        }
        
        System.out.println("Source: ");
        int source = scan.nextInt();
        System.out.println("Sink: ");
        int sink = scan.nextInt();
        
        
        System.out.println("Capacities:");
        prob.printMatrix(capacities);
        
        System.out.println();
        System.out.println("MaxFlow:");
        System.out.println(prob.pushRelabel(capacities, flow, source, sink));

        System.out.println();
        prob.printMatrix(flow);
    }
}
