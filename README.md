# number-of-swap-to-make-all-nodes-good-node


       Given a directed Graph with each node having exactly one outgoing edge. Good Node:
          - A node which is marked as good
          - A node which has a path to good node
      One node in the graph is marked good, 
      you can swap an edge(can both change its start node and end node), find the minimum number of swaps to make all nodes good nodes
       (Swap: n1 -> n2 => n1 -> n3)，
        if (1. bad) -> (2.bad) -> (3.bad) <- (4.good)
            need to swap:
               3,4 
         
    */
