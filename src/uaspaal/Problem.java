/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uaspaal;

/**
 *
 * @author NOOR-MAULIDA
 */
public class Problem {
    
    private final int INF = 1234567;
    private int MAX;
    private int C[][];
    private int F[][];
    private int M[][];
    private int u;
    private int v;
    private int excess[];
    private int height[];
    private int seen[];
    private int list[];

    public Problem(int max) {
        this.MAX = max;
        C = new int[MAX+1][MAX+1];
        F = new int[MAX+1][MAX+1];
        M = new int[MAX+1][MAX+1];
        excess = new int[MAX+1];
        height = new int[MAX+1]; 
        seen = new int[MAX+1]; 
        list = new int[MAX-1]; 
    
        for(int a=0; a<MAX; a++) {
            for(int b=0; b<MAX; b++) {
                C[a][b] = 0;
                F[a][b] = 0;
                M[a][b] = 0;
            }
        }
        
        for(int d=0; d<MAX; d++) {
            excess[d] = 0;
            height[d] = 0;
            seen[d] = 0;
        }

        for(int e=0; e<MAX-2; e++) {
            list[e] = 0;
        }        
    }
    
    public int min(int a, int b) {
        if(a<b) {
            return a;
        }
        else {
            return b;
        }
    }
    
    public void push(int C[][], int F[][], int excess[], int u, int v) {
        /*
        System.out.println("C[u][v]: " + (C[u][v]));
        System.out.println("F[u][v]: " + (F[u][v]));
        
        System.out.println("C[u][v] - F[u][v]: " + (C[u][v] - F[u][v]));
        System.out.println("excess[u]: " + excess[u]);
        
        for(int k=0; k<6; k++) {
            for(int l=0; l<6; l++) {
                System.out.print(C[k][l] + " ");
            }
            System.out.println();
        }*/
        int flow_min = min(excess[u], C[u][v] - F[u][v]);
        System.out.println("send: " + flow_min);
        excess[u] -= flow_min;
        excess[v] += flow_min;

        F[u][v] += flow_min;
        F[v][u] -= flow_min;
        
    }
    
    public void relabel(int C[][], int F[][], int height[], int u) {
        int w;
        int min_height = INF;
        for(w=0; w<MAX; w++) {
            if(C[u][w] - F[u][w] > 0) {
                min_height = min(min_height, height[w]);
                height[u] = min_height +1;
            }
        }
    }
    
    public void discharge(int C1[][], int F[][], int excess[], int height[], int seen[], int u) {

        while(excess[u] > 0) {
            if(seen[u] < MAX) {
                int w = seen[u];
                if((C1[u][w] - F[u][w] > 0) && (height[u] > height[w])) {
                    push(C1, F, excess, u, w);
                }
                else {
                    //relabel(C1, F, height, u);
                    seen[u] += 1;
                }
            }
            
            else {
                relabel(C1, F, height, u);
                seen[u]=0;
            }
        }
    }
       
    public void moveToFront(int i, int A[]) {
            int temp = A[i];
            int n;
            for(n=i; n>0; n--) {
                A[n] = A[n-1];
            }
            A[0] = temp;
    
    }
    
    public int pushRelabel(int C[][], int F[][], int awal, int tujuan) {
        int i, p;
        for(i=0,p=0; i<MAX; i++) {
        
            if((i!=awal) && (i!=tujuan)) {
                list[p]=i;
                p++;
            }
        }
    
        height[awal] = MAX;
        excess[awal] = INF;
        
        for(i=0; i<MAX; i++) {
        
            //System.out.println("Ini i ke-" + i);
            push(C, F, excess, awal, i);
        }
        
        p=0;
        while(p<MAX) {
            //System.out.println("Tes4");
            int z = list[p];
            int height_lama = height[z];
            discharge(C, F, excess, height, seen, z);
            if(height[z] > height_lama) {
                moveToFront(p, list);
                p=0;
            }
            else {
                p++;
            }
        }
        
        int maxflow =0;
        for(i=0; i<MAX; i++) {
            //System.out.println("F[awal][i]" + F[awal][i]);
            maxflow += F[awal][i];
        }
        //System.out.println("MaxFlow: " + maxflow);*/
        return maxflow;
    }

    public void printMatrix(int M[][]) {
        int i,j;
        for(i=0; i<MAX; i++) {
            for(j=0; j<MAX; j++) {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }
    }
}