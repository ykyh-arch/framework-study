package example.dcl;

public enum EnumTest {
    //常量，在加载的时候实例化一次，方法区
    A,B,C,D;

    public static void m1(){
        System.out.println("method");
    }

    public static void main(String[] args) {
        A.m1();
        B.m1();
        C.m1();
        D.m1();

        System.out.println(A.getClass().getName());//example.dcl.EnumTest
        System.out.println(B.getClass().getName());//example.dcl.EnumTest
        System.out.println(C.getClass().getName());
        System.out.println(D.getClass().getName());
    }
}
