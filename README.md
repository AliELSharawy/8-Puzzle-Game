# ***8-Puzzle-Game :jigsaw:***

- This project was generated with JAVAFX to solve 8-puzzle to goal state 012345678.


## Authors:
> [Habiba Osama zaky](https://github.com/habibaosama)
>
> [Mai Ahmed Hussien](https://github.com/MaiAhmedHussein)
>
> [Basel Ahmed Awad](https://github.com/Basel-byte)
>
> [Ali Hassan Alsharawy](https://github.com/AliELSharawy)  

## UI
![image](https://github.com/MaiAhmedHussein/8-Puzzle-Game/blob/main/readme-images/puzzle.png)

## Table of Contents

- [Search Algorithms](#Search-Algorithms)
    - [BFS](#BFS)
    - [DFS](#DFS)
    - [A*](#A*)
- [Features and User Guide](#Features-and-User-Guide)
    - [Features](#Features)
    - [User Guide](#User-Guide)
    - [UI Samples](#UI-Samples)

## Search-Algorithms

### BFS
- Start by checking the shallowest node first,serch level by level and check if it was the goal when dequeueing from queue.
### DFS
- Start by checking the deepest node first, and check if it was the goal when poping from stack.
### A*
- Searching according to the min(Cost(x) + heuristic(x)) using Priority Queue as frontier.The heuristic is calculated using 2 different methods **Manhattan**  and **Euclidean**

## Features and User Guide
   
### Features   
- user can see the path to the goal by clicking on view path button
 ![image](https://github.com/MaiAhmedHussein/8-Puzzle-Game/blob/main/readme-images/view%20path.png)

- user can see each step by clicking on next to view next step or back to return one step 
![image](https://github.com/MaiAhmedHussein/8-Puzzle-Game/blob/main/readme-images/next.png)

### User Guide
- first user enters the initial case in that form 125340678 and then click **Enter Puzzle :)**. The puzzle will be viewed as shown

- Choose the search method from the drop down menu . The default is BFS.then click **solve :)**
![image](https://github.com/MaiAhmedHussein/8-Puzzle-Game/blob/main/readme-images/guide1.png)

- After clicking on solve the results of depth ,time and cost will appear (number4).

- the information wanted for this search. 

- you can press next to know the next move of the puzzle and keep moving till reach our **goal state 012345678**.

- View path will open a new window that shows the whole path as in the following photo, also the path is printed in “path.txt” file
![image](https://github.com/MaiAhmedHussein/8-Puzzle-Game/blob/main/readme-images/guide2.png)









