/**
  Represent a list, implemented in a chain of nodes
 */

public class List_inChainOfNodes{
    private Node headReference;

    /**
      Construct an empty list
     */
     public List_inChainOfNodes(){
       headReference = null;
     }


    /**
      @return the number of elements in this list
     */
    public int size() {
      if (headReference == null) return 0;
      else {
        List_inChainOfNodes shorterList = new List_inChainOfNodes();
        shorterList.headReference = headReference.getReferenceToNextNode();
        return shorterList.size() + 1;
      }
    }


     /**
       @return a string representation of this list,
       format:
           # elements [element0,element1,element2,]
      */
    public String toString() {
      if (headReference == null) return "0 elements []";
      else {
        String output = size() + " elements [";
        List_inChainOfNodes iterator = new List_inChainOfNodes();
        iterator.headReference = headReference;
        output += iterator.headReference.getCargoReference() + ", ";
        for (int i = 0; i < size() - 1; i++){
          iterator.headReference = iterator.headReference.getReferenceToNextNode();
          output += iterator.headReference.getCargoReference() + ", ";
        }
        return output + "]";
      }
    }

    public Object get( int index ) {
      if (index == 0) return headReference.getCargoReference();
      else {
        List_inChainOfNodes recursor = new List_inChainOfNodes();
        recursor.headReference = headReference.getReferenceToNextNode();
        return recursor.get(index - 1);
      }
    }

    public Object set( int index, Object val) {
      if (index == 0) {
        Object old = headReference.getCargoReference();
        headReference.setCargoReference(val);
        return old;
      }
      else {
        List_inChainOfNodes recursor = new List_inChainOfNodes();
        recursor.headReference = headReference.getReferenceToNextNode();
        return recursor.set(index - 1, val);
      }
    }

    public void add( int index, Object val) {
      if (index == 0) addAsHead(val);
      else if (index == 1) headReference.setReferenceToNextNode(new Node(val, headReference.getReferenceToNextNode()));
      else {
        Node iterator = headReference;
        for(int i = 0; i < index - 1; i++){
          iterator = iterator.getReferenceToNextNode();
        }
        Node addend = new Node(val, iterator.getReferenceToNextNode());
        iterator.setReferenceToNextNode(addend);
      }
    }

    public Object remove( int index ) {
      if(index == 0) {
        Object old = headReference.getCargoReference();
        headReference = headReference.getReferenceToNextNode();
        return old;
      }
      else if(index == 1) {
        Object old = headReference.getReferenceToNextNode().getCargoReference();
        headReference.setReferenceToNextNode(headReference.getReferenceToNextNode().getReferenceToNextNode());
        return old;
      }
      else {
        List_inChainOfNodes recursor = new List_inChainOfNodes();
        recursor.headReference = headReference.getReferenceToNextNode();
        return recursor.remove(index - 1);
      }
    }

    /**
      Append @value to the head of this list.

      @return true, in keeping with conventions yet to be discussed
     */
    public boolean addAsHead( Object val) {
      Node addend = new Node(val, headReference);
      headReference = addend;
      return true;
    }
}
