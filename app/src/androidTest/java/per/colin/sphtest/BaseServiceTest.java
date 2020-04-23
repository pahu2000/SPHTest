package per.colin.sphtest;

import androidx.test.runner.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public abstract class BaseServiceTest extends TestCase {

    @BeforeClass
    public static void init() {

    }

    @AfterClass
    public static void close() {
//        System.exit(0);
    }

    protected void waitThread() {
        try {
            Thread.sleep(500000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
