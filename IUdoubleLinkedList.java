import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class IUdoubleLinkedList<T> implements IndexedUnsortedList<T> {
    private Node<T> head, tail;
	private int size;
	private int modCount;

    public IUdoubleLinkedList(){
        head= null;
        tail = null;
        size = 0;
        modCount = 0;
    }
    @Override
    public void addToFront(T element) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
       Node<T> targetNode = head;
       while(targetNode != null&&!targetNode.getElement().equals(target)){
        targetNode = targetNode.getNext();
       }
       if(targetNode==null){
        throw new NoSuchElementException();
       }
        Node<T> newNode = new Node<T>(element);
        newNode.setNext(targetNode.getNext());
        //newNode.setPrevios(targetNode);
        targetNode.setNext(newNode);
        if(newNode.getNext()!=null){
           // newNode.getNext().setPrevios(newNode);
        }else{
            tail = newNode;
        }
        
        
        //targetNode.getNext().setPrevios(newNode);
     
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
        // TODO Auto-generated method stub
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
        // targetNode.getprevious().setNext(targetNode.getNext());
       }
       if(targetNode==tail){
        // tail= tail.getprevious();
       }else{
        // targetNode.getNext().setPrevios(targetNode.getprevious());
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
        // TODO Auto-generated method stub
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
        // currNode.getprevious().setNext(currNode.getNext());
      }
      if(currNode == tail){
        //tail= currNode.getprevious();
      }else{
         // currNode.getNext().setPrevios(currNode.getprevious());
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'first'");
   
    }

    @Override
    public T last() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'last'");
   
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    
    }

    @Override
    public ListIterator<T> listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    
    }

    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    
    }
    
}
