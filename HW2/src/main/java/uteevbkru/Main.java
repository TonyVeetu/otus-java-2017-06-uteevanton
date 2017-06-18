package uteevbkru;

import java.lang.management.ManagementFactory;

/**
 * VM options -Xmx512m -Xms512m
 * <p>
 * Runtime runtime = Runtime.getRuntime();
 * long mem = runtime.totalMemory() - runtime.freeMemory();
 * <p>
 * System.gc()
 * <p>
 * jconsole, connect to pid
 */
@SuppressWarnings({"RedundantStringConstructorCall", "InfiniteLoopStatement"})
public class Main {
    public static void main(String... args) throws InterruptedException {
        System.out.println("pid: " + ManagementFactory.getRuntimeMXBean().getName());

        Desk desk = new Desk();
        desk.prepare();

        desk.measure(Object::new, "Object");
        desk.clean();
        desk.measure(Object::new, "Object");
        desk.clean();
        desk.measure(Object::new, "Object");
        desk.clean();

        desk.measure(String::new, "String with pool");
        desk.clean();
        desk.measure(String::new, "String with pool");
        desk.clean();
        desk.measure(String::new, "String with pool");
        desk.clean();


        desk.measure(() -> new String(new char[0]), "String");
        desk.clean();
        desk.measure(() -> new String(new char[0]), "String");
        desk.clean();
        desk.measure(() -> new String(new char[0]), "String");
        desk.clean();

        desk.measure(() -> new Desk(1), "Desk(1)");
        desk.clean();
        desk.measure(() -> new Desk(5), "Desk(5)");
        desk.clean();
        desk.measure(() -> new Desk(10), "Desk(10)");
        desk.clean();

    }
}
