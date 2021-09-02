package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();
    // 시작할때 static영역인것에 new 가있다면 생성해서 가지고있는다.

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){ }
    // 이렇게 생성자를 private로 만들어놓으면
    // 외부에서는 생성자를 접근하지 못함.
    // 즉 외부에서는 생성불가 -> 처음에 생성한 객체하나만 존제

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
