import entity.User;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test02 {


    @Test
    public void test1(){

    }

    @Test
    public void test01(){
        List<User> list = Arrays.asList(
                new User("张三",101,25,4.442),
                new User("李四",102,22,424242.4),
                new User("王五",103,53,74542.74),
                new User("赵六",104,33,2333.333)
        );

        list.stream().map(User::getName).forEach(System.out::println);

    }


    @Test
    public void test02(){
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
    }
    
    /*
    *
     *功能描述 
     * @author dnslin
     * @date 对象引用：：实例方法
     * @param  
     * @return 
     */
    @Test
    public void test03(){
        User user = new User("张三",101,25,4.442);
//        Supplier sup = () -> user.getAge();
//        System.out.println(sup.get());
        //  新的写法  方法引用
        Supplier sup = user::getName;
        System.out.println(sup.get());
    }
    /*
    *
     *功能描述 
     * @author dnslin
     * @date 类名：：静态方法
     * @param  static int compare(int x, int y)
     *         int compare(T o1, T o2);
     * @return 
     */

    @Test
    public void test04(){
        Comparator<Integer> con = (x, y) -> Integer.compare(x,y);

        System.out.println("------------------------------------");
        
        Comparator<Integer> com = Integer::compareTo;
    }
    
    /*
    *
     *功能描述 
     * @author dnslin
     * @date 类名：：实例方法
     * @param  
     * @return 
     */
    public void test05(){
        BiPredicate<String,String> bp =(s, s2) -> s.equals(s2);


        BiPredicate<String,String> bq2 = String::equals;
    }


    @Test
    public void tye04(){
//        User user = new User("张三",101,25,4.442);
//        Supplier supplier = () -> new User();
        Supplier supplier = User::new;
        System.out.println(supplier.get());
    }
}
