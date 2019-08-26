package com.gojek.parkinglot;
//An Iterative Java program to do DFS traversal from 
//a given source vertex. DFS(int s) traverses vertices 
//reachable from s. 

import java.util.*; 

public class GFG 
{ 
	// This class represents a directed graph using adjacency 
	// list representation 
	static class Graph 
	{ 
		int V; //Number of Vertices 
		
		LinkedList<Integer>[] adj; // adjacency lists 
		
		//Constructor 
		Graph(int V) 
		{ 
			this.V = V; 
			adj = new LinkedList[V]; 
			
			for (int i = 0; i < adj.length; i++) 
				adj[i] = new LinkedList<Integer>(); 
			
		} 
		
		//To add an edge to graph 
		void addEdge(int v, Integer w) 
		{ if(w!=null)
			adj[v].add(w); // Add w to v’s list. 
		} 
		
		// prints all not yet visited vertices reachable from s 
		void DFS(int s) 
		{ 
			// Initially mark all vertices as not visited 
			Vector<Boolean> visited = new Vector<Boolean>(V); 
			for (int i = 0; i < V; i++) 
				visited.add(false); 
	
			// Create a stack for DFS 
			Stack<Integer> stack = new Stack<>(); 
			
			// Push the current source node 
			stack.push(s); 
			
			while(stack.empty() == false) 
			{ 
				// Pop a vertex from stack and print it 
				s = stack.peek(); 
				stack.pop(); 
				
				// Stack may contain same vertex twice. So 
				// we need to print the popped item only 
				// if it is not visited. 
				if(visited.get(s) == false) 
				{ 
					System.out.print(s + " "); 
					visited.set(s, true); 
				} 
				
				// Get all adjacent vertices of the popped vertex s 
				// If a adjacent has not been visited, then puah it 
				// to the stack. 
				Iterator<Integer> itr = adj[s].iterator(); 
				
				while (itr.hasNext()) 
				{ 
					int v = itr.next(); 
					if(!visited.get(v)) 
						stack.push(v); 
				} 
				
			} 
			for(int i=0;i<visited.size();i++ ) {
				if(!visited.get(i)) {
					System.out.print(i);
				}
				
			}
		} 
	} 
	
	// Driver program to test methods of graph class 
	public static void main(String[] args) 
	{ 
		// Total 5 vertices in graph 
		Graph g = new Graph(6); 
		
		g.addEdge(0, null); 
		g.addEdge(1, null); 
		g.addEdge(2, 3); 
		g.addEdge(3, 1); 
		g.addEdge(4, 0); 
		g.addEdge(4, 1); 
		g.addEdge(5, 2); 
		g.addEdge(5, 0); 
		System.out.println("Following is the Depth First Traversal"); 
		g.DFS(5); 
	} 
} 
