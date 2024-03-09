public class Main {
    public static void main(String[] args) {

        Heap<Integer> heap = new Heap(4);
        heap.insert(10);
        heap.insert(12);
        heap.insert(11);
        heap.insert(120);
        heap.insert(175);
        heap.insert(140);
        heap.insert(123);

        while(!heap.isEmpty()){
            System.out.println(heap.maxOut());
        }


    }
}