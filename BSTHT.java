public class BSTHT {

    public static void main(String[] args) {
       //Sequence 1
        System.out.println("Binary Search Tree (Sequence 1):");
        BST<Integer,String> bst = new BST<>();
        long s = System.nanoTime();       
         bst.put(5,"B1");
         System.out.println("Put 5-B1");
         bst.put(10,"B2");
         System.out.println("Put 10-B2");
         bst.put(5,"B3");
         System.out.println("Put 3-B3");
         System.out.println(bst.get(10));
         System.out.println(bst.get(5));

         long e = System.nanoTime();
         System.out.println("Sequence 1 Time: " + (e-s) + " ns\n");
         
         s = System.nanoTime();
         System.out.println("Hash Table (Sequence 1):");
         LinearProbingHashST<Integer, String> hash= new LinearProbingHashST<>();
         hash.put(5, "H1");
         System.out.println("Put 5-H1");
         hash.put(10, "H2");
         System.out.println("Put 10-H2");
         hash.put(5, "H3");
         System.out.println("Put 3-H3");
         System.out.println(hash.get(10));
         System.out.println(hash.get(5));
        
         e = System.nanoTime();
         System.out.println("Sequence 1 Time: " + (e-s) + " ns\n");
          
         //Sequence 2 
         System.out.println("Binary Search Tree (Sequence 2):");
         BST<Integer,String> bst2 = new BST<>();
        s = System.nanoTime();       
          bst2.put(2,"B1");
          System.out.println("Put 2-B1");
          bst2.put(4,"B2");
          System.out.println("Put 4-B2");
          bst2.put(1,"B3");
          System.out.println("Put 1-B3");
          System.out.println(bst2.get(3));
          System.out.println(bst2.get(4));
          bst2.put(6,"B4");
          System.out.println("Put 6-B4");
          System.out.println(bst2.get(6));
           e = System.nanoTime();
          System.out.println("Sequence 2 Time: " + (e-s) + " ns\n");
          
          System.out.println("HashTable (Sequence 2):");
          BST<Integer,String> hash2 = new BST<>();
         s = System.nanoTime();       
           hash2.put(2,"H1");
           System.out.println("Put 2-H1");
           hash2.put(4,"H2");
           System.out.println("Put 4-H2");
           hash2.put(1,"H3");
           System.out.println("Put 1-H3");
           System.out.println(hash2.get(3));
           System.out.println(hash2.get(4));
           hash2.put(6,"H4");
           System.out.println("Put 6-H4");
           System.out.println(hash2.get(6));
            e = System.nanoTime();
           System.out.println("Sequence 2 Time: " + (e-s) + " ns\n");
          
          
    }
}

 class  LinearProbingHashST<Key, Value> 
{
   private int N;         //number of key value
   private int M = 16;    //probing table size
   private Key[] keys;                                         
   private Value[] vals;                 
public LinearProbingHashST()
   {
      keys = (Key[])new Object[M];
      vals = (Value[]) new Object[M];
   }
  
public LinearProbingHashST(int cap)
   {
      keys = (Key[]) new Object[M];
      vals = (Value[]) new Object[M];
      this.M=cap;
   }
   public int hash(Key key)
   {  return (key.hashCode() & 0x7fffffff) % M; }
   public void resize(int cap) 
   {
       LinearProbingHashST<Key, Value> t;
       t = new LinearProbingHashST<Key, Value>(cap);
       for (int i = 0; i < M; i++)
          if (keys[i] != null)
              t.put(keys[i], vals[i]);
       keys = t.keys;
       vals = t.vals;
       M    = t.M; 
   }
   public void put(Key key, Value val)
   {
      if (N >= M/2) resize(2*M); 
      int i;
      for (i = hash(key); keys[i] != null; i = (i +1)% M)
         if (keys[i].equals(key)) { vals[i] = val; return; }
      keys[i] = key;
      vals[i] = val;
       N++;
   }
   public Value get(Key key)
   {
      for (int i = hash(key);keys[i] !=null;i= (i +1) %M)
         if (keys[i].equals(key))
             return vals[i];
      return null;
   } 
}
 class  BST<Key extends Comparable<Key>, Value> 
{
   private Item root;               

    class  Item
   {
      private Key key;              
      private Value val;            
      private Item left, right;     
      private int N;                

      public Item(Key key,Value val,int N)
      {  this.key =key; this.val =val;this.N = N; }
   }
   public int size()
   {  return size(root);  }
   public int size(Item a)
   {
	   
      if (a == null) return 0;
      else           return a.N;
   }
   public  Value get(Key key) {  
	   return get(root, key);  }
    public Value get(Item a, Key key){  
       //return the value with the associated key
      if (a == null) return null;
      int b = key.compareTo(a.key);
      if      (b < 0) return get(a.left, key);
      else if (b> 0) return get(a.right, key);
      else return a.val; 
   }
    
    
    public void put(Key key, Value val){   
      root = put(root, key, val); 
      //search for key
   }
    public Item put(Item a, Key key, Value val){
    	   //if key is subtree rooted to a then change to val
    	   // else add new ıtem to tree
      if (a == null) return new Item(key, val, 1);
      int b = key.compareTo(a.key);
      if (b < 0) a.left  = put(a.left,  key, val);
      else if (b> 0) a.right = put(a.right, key, val);
      else a.val = val;
      a.N = size(a.left) + size(a.right) + 1;
      return a; 
   }
}

