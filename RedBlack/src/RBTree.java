import java.util.*;
import java.io.*;
import java.lang.*;

public class RBTree {

    private Node root;
    private Node nil=Node.nil;

    RBTree() { root = nil ; }

    Node getRoot(){ return root;}


   void insert(String newdata){
        Node n=new Node(newdata,true);
        root=insertRec(root,n);
        RedBlackFix(n); }


    private Node insertRec(Node root,Node n){
      if (root == nil) {
          return n;  }

      if (n.data.compareToIgnoreCase(root.data)<0){
          root.l = insertRec(root.l, n);
          root.l.parent=root;  // setting parent node
          return root; }
      else if (n.data.compareToIgnoreCase(root.data)>0){
          root.r = insertRec(root.r, n);
          root.r.parent=root;  //setting parent node
          return root; }
      System.out.println("Error , \" "+ n.data +" \"  already exists");
      return root;
 }


/*brief summary for the steps of fixing a red black tree to keep it balanced
* 1-new node is always red
* 2-if node is root make it black and end
* 3-if parent is red check uncle if also red make them black and change grandaprent color and do a redblack fix on the grandarent
* 4-if uncle is black we now we have to check for left right and right left cases (triangle cases) (check to see if the new node is a left child and its parent is a right child or opposite)
* 5-if we have a triangle case then rotate parent accordingly (if left-right rotate left ,and if right-left rotate right) then rotate as left left and right right cases
* 6=left-left and right-right cases rotate grandarent accordingly (right for left-left and left for right-right)
* 7-recolor for the parent and grandparent
* */

 private void RedBlackFix(Node n){
        boolean rotateFlag;
        //rotateFlag is set when finding the uncle ,and is used to determine which way to rotate as the uncle position changes the cases
        if(n==root){root.isRed=false;return;}
        while (n.parent.isRed) {
            Node uncle = nil;
            if (n.parent == n.parent.parent.l) {uncle = n.parent.parent.r; rotateFlag=false;}
            else { uncle = n.parent.parent.l; rotateFlag=true;} //finding the uncle
            if (uncle != nil && uncle.isRed) { //since uncle is red and parent is red make them black
                    n.parent.isRed=false;
                    uncle.isRed=false;
                    n.parent.parent.isRed=true;//change grandparent color and set him as n to fix further violations that might occur as a resault
                    n = n.parent.parent;
                    continue;
                }
                if ((n == n.parent.r)&&!rotateFlag){n=n.parent;rotate(n,false);}//left rotation for left-right case
                else if ((n == n.parent.l)&&rotateFlag){n=n.parent;rotate(n,true);}//right rotation right-left case
                n.parent.isRed=false;
                n.parent.parent.isRed=true;
                rotate(n.parent.parent,!rotateFlag);//rotation for left-left and right-right cases
            }
        root.isRed=false;// if root color got changed at any point returns to black
    }


private  void rotate(Node n , boolean flag){
        Node temp;
        if(flag) {// if boolean flag is true rotate right

            temp = n.l;                      //hold left node in temp
            n.l = temp.r;                    //left is now the right child of the previous left
            if (n.l != nil) n.l.parent = n;  //if the new left child exist set its parent to n
            temp.parent = n.parent;          //the temp(previous left) takes n's parent as its parent
            if (n.parent == nil) root = temp;// if the parent is nil that means temp is the new root
            else if (n == n.parent.l)
                n.parent.l = temp;           //if n was a left child temp is now the new left child of its parent
            else n.parent.r = temp;          // else its n's parent right child
            temp.r = n;                      // set n as temps right child
            n.parent = temp;                 //and temp as its parent

        }else {// rotate left if the boolean flag is false

            temp = n.r;
            n.r = temp.l;
            if (n.r != nil) n.r.parent = n;
            temp.parent = n.parent;
            if (n.parent == nil) root = temp;
            else if (n == n.parent.r) n.parent.r = temp;
            else n.parent.l = temp;
            temp.l = n;
            n.parent = temp;
        }
}







    void search(String target){searchRec(root,target);}

    private void searchRec(Node root,String target) { //recursive fn to search for the required string
     if(root==nil){ System.out.println("NO, \" "+target+" \" is not found"); return;}
     else if(target.compareToIgnoreCase(root.data)==0){ System.out.println("YES, \" "+target+" \" is found");return; }
     else if(target.compareToIgnoreCase(root.data)<0){ searchRec(root.l,target); }
     else if(target.compareToIgnoreCase(root.data)>0){ searchRec(root.r,target); }
     }


    int size(){ return calcSize(root); }

    private int calcSize(Node root) { // calcualte size recursively
        if (root == nil) return 0;
        else{
            return 1+calcSize(root.l)+calcSize(root.r);
        } }

    int height(){ return calcHeight(root); }

    private int calcHeight(Node root) { // calcualte height recursively
        if (root == nil) return 0;
        else{
            int left=1+calcHeight(root.l);
            int right=1+calcHeight(root.r);
            return Math.max(left,right);
        } }



/* ===================================================================
  *printing functions
 *===================================================================*/

        void inorder(){inorderRec(root);}
    private void inorderRec(Node root) { // an inoder traversal function to test code
        if (root != nil) {
            inorderRec(root.l);
            System.out.print(root.data +"(" +( root.isRed ? "R" : "b")+  ")  ");
            inorderRec(root.r);
        }
    }

    void preorder(){preorderRec(root);}
    private void preorderRec(Node root) { // a preoder traversal function to test code
        if (root != nil) {
            System.out.print(root.data +"(" +( root.isRed ? "R" : "b")+  ")  ");
            preorderRec(root.l);
            preorderRec(root.r);
        }
    }




    void levelOrder() {
        int h = height();
        for( int i = 1; i <= h; i++){
            printLevel(root, i);
        System.out.println();}
    }
    private void printLevel(Node root, int level)
    {
        if (root == nil) return;
        if (level == 1)
            System.out.print(root.data +"(" +( root.isRed ? "R" : "b")+  ")  ");
        else if (level > 1)
        {
            printLevel(root.l, level-1);
            printLevel(root.r, level-1);
        }

    }


}
