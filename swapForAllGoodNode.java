import java.util.*;

// for a node, 1 is good, 0 is bad
class Node {
    int val;
    int good;
    public Node(int val, int good) {
        this.val = val;
        this.good = good;
    }
}
/*
    
       找最小 swap 使所有节点都有 path 到起始 good node
        Directed Graph with each node having exactly one outgoing edge Good Node:
        - A node which is marked as good
        - A node which has a path to good node
        One node in the graph is marked good
        Swap: n1 -> n2 => n1 -> n3，可以改变任何一个 edge 的 start 和 end node. Minimum
          
        number of swaps to make all nodes good

        if (1. bad) -> (2.bad) -> (3.bad) <- (4.good)
            need to swap:
               3,4 -> then 2,3 -> then 1,2

    */

class swapForAllGoodNode {
   public static int minSwapV2(Node[][]connection) {
	// used to store all nodes in the graph
        Set<Node>set = new HashSet<>();
        Map<Node, List<Node>>graph = new HashMap<>();
        for(Node[] node: connection) {
            List<Node>list = new ArrayList<>();
            graph.put(node[0], list);
        }
        for(Node[] node: connection) {
            graph.get(node[0]).add(node[1]);
            set.add(node[0]);
            set.add(node[1]);
        }
        // another step to find the bad node that points to no one
        List<Node>endBad = new ArrayList<>();
        for(Node n: set) {
            if(n.good == 0 && !graph.keySet().contains(n)) {
                endBad.add(n);
            }
        }
        //System.out.println("size="+endBad.size());
        if(endBad.size()!= 0) {
            return endBad.size();
        }
        int numOfSwap = 0;
        // get all edges where good -> bad
        for(Node[] n: connection) {
            if(n[0].good == 1 && n[1].good == 0) {
                numOfSwap++;
                n[0].good = 0;
                n[1].good = 1;
                dfs(graph, n[0]);
            }
        }
        return numOfSwap;
    }
    /* dfs: 
        1. find the node that is good downthere starting from cur
        2. update all bad ones on this path that end with a good node 
    */
        public static void dfs(Map<Node, List<Node>>graph, Node cur) {
            //System.out.println(cur.val+","+prev.val);
            if(cur.good == 1) {
                return;
            }
            List<Node>neighbors = graph.get(cur);
            if(neighbors != null ) {
                for(Node n: neighbors) {
                    dfs(graph, n);
                }
                cur.good = 1;
            }
            
        }
    public static void main(String[] arg) {
        /*
           (0,0)->(1,0)->(2,0)<-(3,1)
           1 swap for 3rd and 4th node
           (0,0)->(1,0)->(2,1)<-(3,0)
           then all bad nodes has a path to a goof node, then all will be good           
        */
        Node n1 = new Node(0, 0);
        Node n2 = new Node(1, 0);
        Node n3 = new Node(2, 0);
        Node n4 = new Node(3, 1);
        Node[][]graph = new Node[3][2];
        graph[0][0] = n1;
        graph[0][1] = n2;
        graph[1][0] = n2;
        graph[1][1] = n3;
        graph[2][0] = n4;
        graph[2][1] = n3;
        int swaps = minSwap(graph);
        System.out.println("swap for graph 1: "+swaps);
        /*
            
                   
         (0,0) ->(2,0) ->(3,0) <- (4,1)
                   |      
            (1,0)->
             swap (4,1) and (3,0), then all will be good node 
        */ 
        Node n6 = new Node(0,0);
        Node n7 = new Node(1,0);
        Node n8 = new Node(2,0);
        Node n9 = new Node(3,0);
        Node n10 = new Node(4,1);
        Node[][]graph_1 = new Node[4][2];
        graph_1[0][0] = n6;
        graph_1[0][1] = n8;
        graph_1[1][0] = n7;
        graph_1[1][1] = n8;
        graph_1[2][0] = n8;
        graph_1[2][1] = n9;
        graph_1[3][0] = n10;
        graph_1[3][1] = n9;
        int swaps_1 = minSwap(graph_1);
        System.out.println("swap for graph 2: "+swaps_1);
        /*
            (0,0)->(1,0)->(2,0)->(3,1)
            no need to swap, all nodes already point to a good node 
        */
        Node n_1 = new Node(0,0);
        Node n_2 = new Node(1,0);
        Node n_3 = new Node(2,0);
        Node n_4 = new Node(3,1);
        Node[][]graph_2 = new Node[3][2];
        graph_2[0][0] = n_1;
        graph_2[0][1] = n_2;
        graph_2[1][0] = n_2;
        graph_2[1][1] = n_3;
        graph_2[2][0] = n_3;
        graph_2[2][1] = n_4;
        int swaps_2 = minSwap(graph_2);
        System.out.println("swap for graph 3: "+swaps_2);
    }
}
