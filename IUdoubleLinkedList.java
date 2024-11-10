import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUDoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head, tail;
	private int size;
	private int modCount;

    public IUDoubleLinkedList(){
        head= null;
        tail = null;
        size = 0;
        modCount = 0;
    }

    @Override
    public void addToFront(T element) {
       
        Node<T> newNode = new Node<T>(element);
        newNode.setNext(head);
        if(isEmpty()){
            tail = newNode;
        }else{
            //head.setPrevios(newNode);
        }
        
        head = newNode;
        size++;
        modCount++;
    }

    @Override
    public void addToRear(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addToRear'");
    
    }

    @Override
    public void add(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    
    }

    @Override
    public void addAfter(T element, T target) {
        
       Node<T> targetNode = head;
       while(targetNode != null&&!targetNode.getElement().equals(target)){
        targetNode = targetNode.getNext();
       }
       if(targetNode==null){
        throw new NoSuchElementException();
       }
        Node<T> newNode = new Node<T>(element);
        newNode.setNext(targetNode.getNext());
        newNode.setPrevios(targetNode);
        targetNode.setNext(newNode);
        if(newNode.getNext()!=null){
            newNode.getNext().setPrevios(newNode);
        }else{
            tail = newNode;
        }
        
        
        targetNode.getNext().setPrevios(newNode);
     
        size++;
        modCount++;
    }

    @Override
    public void add(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    
    }

    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    
    }

    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
   
    }

    @Override
    public T remove(T element) {
       
       if(isEmpty()){
        throw new NoSuchElementException();
       }
       Node<T> targetNode = head;
       while(targetNode.getElement()!=element){
        targetNode = targetNode.getNext();
       }
       if(targetNode == head){
       head= head.getNext();
       }else{
         targetNode.getprevious().setNext(targetNode.getNext());
       }
       if(targetNode==tail){
         tail= tail.getprevious();
       }else{
         targetNode.getNext().setPrevios(targetNode.getprevious());
       }
       if(targetNode.getElement() == null){
        throw new NoSuchElementException();
       }
       size--;
       modCount++;
    return targetNode.getElement();
    }


    @Override
    public T remove(int index) {
       
       if(index>=size||index<0){
        throw new IndexOutOfBoundsException();
       }
       Node<T> currNode = head;
       for(int i =0 ;i<index ;i++){
        currNode = currNode.getNext();
       }
      if(index==0){
        head = currNode.getNext();
      }else{
         currNode.getprevious().setNext(currNode.getNext());
      }
      if(currNode == tail){
        tail= currNode.getprevious();
      }else{
          currNode.getNext().setPrevios(currNode.getprevious());
      }
      
       size--;
       modCount++;
       return currNode.getElement();
    }

    @Override
    public void set(int index, T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    
    }

    @Override
    public T get(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    
    }

    @Override
    public int indexOf(T element) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    
    }

    @Override
    public T first() {
        return head.getElement();
   
    }

    @Override
    public T last() {
       return tail.getElement();
   
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    
    }

    @Override
    public boolean isEmpty() {
        if(head==tail){
            return true;
        }else{
            return false;
        }
    
    }

    @Override
    public int size() {
        return size;
        
    
    }

    @Override
    public Iterator<T> iterator() {
        
        return new DLLIterator();
    
    }

    @Override
    public ListIterator<T> listIterator() {
       
        return new DLLIterator();
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
      
        return new DLLIterator(startingIndex);
    
    }
    private class DLLIterator implements ListIterator<T>{
        private Node<T> nextNode;
        private int nextIndex;
        private int iterModCount;
        //private boolean canRemove;
        private Node<T> returnedNode;
       
        public DLLIterator(){
        nextNode = head;
        nextIndex = 0;
        iterModCount = modCount;
        returnedNode=null;
       }


        public DLLIterator(int startingIndex){
        if(startingIndex<0||startingIndex>size){
            throw new IndexOutOfBoundsException();
        }
        nextNode=head;
        for(int i =0; i<startingIndex;i++){
            nextNode = nextNode.getNext();
        }
        nextIndex = startingIndex;
        iterModCount=modCount;
        returnedNode = null;
       }
       
        @Override
        public boolean hasNext() {
            
           if(iterModCount != modCount){
            throw new ConcurrentModificationException();
           }
           return nextNode != null;
        }

        @Override
        public T next() {
           
           if(!hasNext()){
            throw new NoSuchElementException();
           }
           T retVal= nextNode.getElement();
           returnedNode=nextNode;
           nextNode = nextNode.getNext();
           nextIndex++;
          
           return retVal;
        }

        @Override
        public boolean hasPrevious() {
          
            if(iterModCount != modCount){
                throw new ConcurrentModificationException();
            }
            return nextNode!= head;
        }

        @Override
        public T previous() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'previous'");
        }

        @Override
        public int nextIndex() {
           
            
        return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex-1;
        }

        @Override
        public void remove() {
            
            if(iterModCount != modCount){
                throw new ConcurrentModificationException();
            }
            if(returnedNode == null){
                throw new IllegalStateException();
            }
           if(returnedNode != head){
            returnedNode.getprevious().setNext(returnedNode.getNext());
           }else{
            head = head.getNext();
           }
           if(returnedNode !=tail){
            returnedNode.getNext().setPrevios(returnedNode.getprevious());
           }else{
            tail =tail.getprevious();
           }
           if(returnedNode != nextNode){
            nextIndex--;
           }else{
            nextNode = nextNode.getNext();
           }
            
            
            returnedNode = null;
            size --;
            modCount++;
            iterModCount++;
            
            
        }

        @Override
        public void set(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

        @Override
        public void add(T e) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }

    }
}
