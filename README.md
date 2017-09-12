# AlgorithmVisualizer
Algorithm Visualizer for Android

<a href="https://play.google.com/store/apps/details?id=com.naman14.algovisualizer&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png" height=50px/></a>

## Screenshots

<img src="https://raw.githubusercontent.com/naman14/AlgorithmVisualizer-Android/master/screenshots/screen1.png" width="360" height="640">
<img src="https://raw.githubusercontent.com/naman14/AlgorithmVisualizer-Android/master/screenshots/screen3.png" width="360" height="640">
<img src="https://raw.githubusercontent.com/naman14/AlgorithmVisualizer-Android/master/screenshots/screen5.png" width="360" height="640">
<img src="https://raw.githubusercontent.com/naman14/AlgorithmVisualizer-Android/master/screenshots/screen4.png" width="360" height="640">
<img src="https://raw.githubusercontent.com/naman14/AlgorithmVisualizer-Android/master/screenshots/screen2.png" width="360" height="640">

## Visualizations

The following visualizations are available currently -

* Binary search
* Binary Search Tree (Search and insertion)
* Linked List and Stack
* Bubble sort and Insertion sort
* BFS and DFS traversal
* Dijkstara and Bellman Ford graph search

## Contributing

Contributions are welcome to be able to provide visualizations for more algorithms. The existing API for visualizers are given below. These existing 
visualizers can be used directly for most algorithms.

Algorithm | Implementation | Visualizer | 
------------ | -------------|-------------
Binary Search | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/search/BinarySearch.java'>BinarySearch</a> | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/BinarySearchVisualizer.java'>BinarySearchVisualizer</a>
Binary Search Tree | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/tree/bst/BinarySearchTree.java'>BSTAlgorithm</a> | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/BSTVisualizer.java'>BSTVisualizer</a>
Linked List | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/list/LinkedList.java'>LinkedList</a> |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/LinkedListVisualizer.java'>LinkedListVisualizer</a>
Stack | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/list/Stack.java'>Stack</a> | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/StackVisualizer.java'>StackVisualizer</a>
Breadth first search | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/graph/GraphTraversalAlgorithm.java'>GraphTraversal</a>|  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/graph/DirectedGraphVisualizer.java'>DirectedGraphVisualizer</a>
Depth first search | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/graph/GraphTraversalAlgorithm.java'>GraphTraversal</a>|  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/graph/DirectedGraphVisualizer.java'>DirectedGraphVisualizer</a>
Dijkstara | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/graph/DijkstraAgorithm.java'>DijkstraAlgorithm</a> | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/graph/WeightedGraphVisualizer2.java'>WeightedGraphVisualizer</a>
Bellman Ford | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/graph/BellmanFordAlgorithm.java'>BellmanFord</a> | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/graph/WeightedGraphVisualizer.java'>WeightedGraphVisualizer</a>
Bubble Sort | <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/sorting/BubbleSort.java'>BubbleSort</a> |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/SortingVisualizer.java'>SortingVisualizer</a>
Insertion Sort |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/sorting/InsertionSort.java'>InsertionSort</a> |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/SortingVisualizer.java'>SortingVisualizer</a>
Selection Sort |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/sorting/SelectionSort.java'>SelectionSort</a> |  <a href='https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/visualizer/SortingVisualizer.java'>SortingVisualizer</a>
 
 All algorithm implementations must extend [Algorithm](https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/Algorithm.java) and implement the [DataHandler](https://github.com/naman14/AlgorithmVisualizer-Android/blob/master/app/src/main/java/com/naman14/algovisualizer/algorithm/DataHandler.java) interface. The visualization happens on the main thread while
 the algorithms run in a seperate thread to be able to pause/resume execution. All data transfer must take place through the DataHandler interface.
 
## License

>(c) 2016 Naman Dwivedi 

>This is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. 

>This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. 

>You should have received a copy of the GNU General Public License along with this app. If not, see <https://www.gnu.org/licenses/>.




