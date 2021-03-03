"""

Created on Wed Dec  2 14:11:03 2020

@author: Charlie Johnson

Algorithm to take a network (a number of vertices V and edges E connecting some 
of the vertices) and identify all closed paths on the network of length at most 
N. This can be done by finding all non-self-intersecting paths (loop-less paths) 
with a pre-existing algorithm, identifying where they overlap, and then using 
this to add the loops back in. 

The networks are assumed to be simple and undirected.
"""

import networkx as nx 

# Identify all cycles on the network
# adj_mat: nested list, adjaceny matrix of the network
# max_len: int, maximum cycle length to be looked for
# returns a list of all the cycles of length n for n up to max_len 
  
def Cycle_Finder(adj_mat,max_len): 
    
    # Increase by one due to being used to only close non-inclusive ranges
    max_len += 1
    
    # Initialise a directed networkx graph - direction is needed to run simple_cycles but 
    # undirected graphs can be mimicked by adding both the bonds (i,j) and (j,i)
    graph = nx.DiGraph()
    graph.add_nodes_from(range(len(adj_mat)))
    
    for i in range(len(adj_mat)):
        for j in range(i+1,len(adj_mat)):
            if adj_mat[i][j] == 1:
                graph.add_edges_from([(i,j),(j,i)])
                    
    # Identify the simple cycles. Cycles stored as a list of visited vertices. 
    simp_cyc = nx.simple_cycles(graph)
    
    # Store cycles in length order
    cycles = [[] for n in range(max_len-2)]
    for i in simp_cyc:
        cycles[len(i)-2].append(Sort_Cycle_Start(i))
        
    # Combine simple cycles into the non-simple cycles.     
    for n in range(4,max_len):
        for i in range(2,n/2+1):
                        
            for c1 in cycles[i-2]:
                for c2 in cycles[n-i-2]:
                    
                    # Cycles can be added if they share a vertex
                    if bool((set(c1) & set(c2))):
                        
                        # Get all combinations of c1 and c2
                        added_cyc = Add_Cycles(c1,c2)
                        
                        # Add any new cycles to memory
                        for cyc in added_cyc:
                            if not cyc in cycles[n-2]:
                                cycles[n-2].append(cyc)                       
        
    return cycles

# Find all ways two cycles can be added together
# c1: list, cycle 1
# c2: list, cycle 2
# returns list of possible cycles    

def Add_Cycles(c1,c2):
    # Find all overlaps in cycle elements
    intersect = list(set(c1) & set(c2))
    
    output = []
    
    # For each overlap combine two cycles at this point
    for i in intersect:
        loc_c1 = [j for j, x in enumerate(c1) if x==i]
        loc_c2 = [j for j, x in enumerate(c2) if x==i]
        
        for l1 in loc_c1:
            for l2 in loc_c2:
                
                output.append(c1[:l1]+c2[l2:]+c2[:l2]+c1[l1:])
                
    return list(map(Sort_Cycle_Start,output))

# Give a standardised starting point to a cycle by choosing alphabetically 
# smallest cycle found by shifting start index
# Allows repeated cycles to be recognised
# cyc: list, cycle as list of vertices
# returns sorted cycle

def Sort_Cycle_Start(cyc):
    min_vertex = min(cyc)
    return min(list(map(lambda i : cyc[i:]+cyc[:i], [j for j, x in enumerate(cyc) if x==min_vertex])))

