 class Node {

    String data;
    boolean isRed;         // color red or black true for red false for black
    Node parent,l,r;   //parent , left child ,and right child nodes

     Node(String data,boolean color)
     {
         this.data = data;
         parent=l=r=nil;
         this.isRed = color;
     }

static Node nil=new Node("nil",false);



 }
