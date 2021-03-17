# number-of-swap-to-make-all-nodes-good-node


       Given a directed Graph with each node having exactly one outgoing edge. Good Node:
          - A node which is marked as good
          - A node which has a path to a good node
      One node in the graph is marked good, 
      you can swap an edge(can both change its start node and end node), find the minimum number of swaps to make all nodes good nodes
       (Swap: n1 -> n2 => n1 -> n3)，
        if (1. bad) -> (2.bad) -> (3.bad) <- (4.good)
            need to swap:
               3,4 
         explaination:
              if we swap 3 and 4, the original graph will be:
              (1. bad) -> (2.bad) -> (4.good) <- (3.bad)
              since 3 now point to a good node, 3 will be good, 
              and since 2 and 1 point to a good node, 1 and 2 will be good too 
         
       idea:
           find the number of bad nodes that points to no other nodes. once we swap this bad node with a good node, 
           then all nodes connecting to this node will be good 
