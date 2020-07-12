import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {


        RBTree theTree= new RBTree();

        try {
            Scanner scanner = new Scanner(new File("dictionary.txt"));//name of the file to read
            while(scanner.hasNextLine()){
                theTree.insert( scanner.nextLine() ); }

        } catch (FileNotFoundException ex){
            System.out.println("ERROR CANT READ FILE"); }


        Scanner s = new Scanner(System.in);
char sel;
String word;
boolean loop=true;
while(loop) {
    System.out.println("\nTREE SIZE ="+theTree.size()+" HEIGHT IS " +theTree.height()+" ROOT IS  \"" +theTree.getRoot().data+"\""+
            "\n1-Print inorder" +
            "\n2-Print preorder" +
            "\n3-Print level order (Beadth first) " +
            "\n4-SEARCH word"+
            "\n5-INSERT word"+
            "\nOtherWise- Exit");
sel=s.next().charAt(0);
    s.nextLine();
switch(sel){
    case '1':
        System.out.println("printing Inorder : \n");
        theTree.inorder();
      break;
    case '2':
        System.out.println("printing Preorder : \n");
        theTree.preorder();
    break;
    case '3': ;
        System.out.println("printing level order (Beadth first) : \n");
        theTree.levelOrder();
    break;
    case '4':
        System.out.println("Enter the word u wish to SEARCH for");
        word=s.nextLine();
         theTree.search(word);
         break;
    case '5':
        System.out.println("Enter the word u wish to INSERT");
        word=s.nextLine();
        theTree.insert(word);
        break;
    default:
        loop=false;
        break;}



}
    }
}
