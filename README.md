###Author and Contributor List
####Alex Shabanov
==============================
###List of Folders & Java Files
```
1. .settings
2. bin/com/bst
3. lib
4. properties
5. src/com/bst
6. .classpath
7. .project
```
==============================
###About this code.
```
This project is a simplified version of a generic Binary Search Tree data structure. More specifically this
code is an implementation of a binary tree that contais only the essential methods need to construct and operate
on the tree. Binary Search Tree consists of nodes that have a key value pair and two pointers that point
to the left subtree and to the right subtree. The property of a binary tree is that the nodes having smaller
key are stored to the left side and the nodes with higher key in comparison to the parent node are stored
to the right subtree. Insertion, removal and get methods are all done iteratively. This implementation of Binary
Search Tree also includes iterator and freatures preorder, inorder, postorder, and level order traversals that
are also done iteratively.
```
=======================
###Methods implemented.
1.  getRoot()
2.  size()
3.  height()
4.  findHeight(Node node)
5.  printBST()
6.  iterator()
7.  traverse(Node node)
8.  isLeafNode(Node node)
9.  put(Key key, Value value)
10. add(Key key, Value value)
11. findMinKey(Node prevNode, Node nextNode)
12. remove(Key key)
13. get(Key key)
14. contains(Key key)
15. levelOrderTraversal()
16. preOrderTraversal(Node node)
17. inOrderTraversal(Node node)
18. postOrderTraversal(Node node)

=======================
###Running Instructions
```
Application can be launched using Eclipse IDE.
git fork https://github.com/alex-shabanov/BinaryTreeDataStructure.git  
to create a copy of the project on GitHub account from where it can be pulled to local machine, or
git clone https://github.com/alex-shabanov/BinaryTreeDataStructure.git 
to create a copy of the porject on local machine where it can then be pushed to GitHub account
Add the BinaryTreeDataStructure folder to your Eclipse IDE project workspace.
```
=========================
###Additional Classes and Files Included.
```
1. JUnit Tests
2. Log4j (Apache Commons)
```