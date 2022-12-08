public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(2);
        list.add(1);
        list.add(5);
        list.add(10);

        list.remove(5);

        System.out.println(list);
    }
}