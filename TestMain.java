import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.*;

/**
 *
 * @author  Aronson
 */
public class TestMain
{
    String className = "Employee";

    private  boolean failed = false;

    private  Class<?> c;
    private Constructor hConstructor;

    public static void main(String args[]) {
        TestMain test = new TestMain();
        test.testThirdLetterOfAlphabet();
        test.testRememberQuoting();
        test.testEmployee();
    }


    private  void failure(String str)
    {
        System.out.println("*** Failed: " + str);
        failed = true;
    }

    public Object callMethod(Object obj, String method)
    {
        try {
            Method m = c.getDeclaredMethod(method);
            Object temp = m.invoke(obj);
            return temp;
        } catch (Exception e) {
            failure(e.toString());

        }
        return null;
    }

    public void callSet(Object obj, String method, Object param, Class<?> cls)
    {
        try {
            Method m = c.getDeclaredMethod(method, cls);
            Object temp = m.invoke(obj, param);

        } catch (Exception e) {
            failure(e.toString());

        }

    }

    @Test
    public void testThirdLetterOfAlphabet() 
    {
        assertEquals('c', Main.getThirdLetterOfAlphabet());
    }

    @Test
    public void testRememberQuoting() 
    {
        assertEquals("I remember \"quoting\" from last year!", Main.rememberQuoting());
    }

    @Test
    public void testEmployee() {
        try
        {
            c = Class.forName(className);
            hConstructor = c.getConstructor(new Class[] {String.class, char.class, String.class});
            Object[] cArgs1 = {"Pat", 'D', "Uzamaki"};
            Object e1 = hConstructor.newInstance(cArgs1);

            Object temp = callMethod(e1, "getLastInitial");

            assertEquals("Pat Uzamaki has last initial U", 'U', temp);

            temp = callMethod(e1, "hasDoubleName");
            assertTrue("Pat Uzamaki is not a double name", !(Boolean)temp);
            Object[] cArgs2 = {"Pat", 'D', "Pat"};
            Object e2 = hConstructor.newInstance(cArgs2);
            temp = callMethod(e2, "hasDoubleName");
            assertTrue("Pat Pat is a double name", (Boolean)temp);
            temp = callMethod(e1, "getNameLength");
            assertEquals(11, temp);
            temp = callMethod(e1, "getFullName");

            assertEquals("PAT D. UZAMAKI", temp);
            temp = callMethod(e1, "getFirstName");
            assertEquals("Should not change name", "Pat", temp);
            temp = callMethod(e1, "getMonogram");
            assertEquals("pdu", temp);
            temp = callMethod(e1, "getLastName");
            assertEquals("Should not change last name's case", "Uzamaki", temp);
            temp = callMethod(e1, "getMiddleInitial");
            assertEquals("Should not change middle initial's case", 'D', temp);

            callSet(e1, "setMiddleInitial", 'Y', char.class);
            temp = callMethod(e1, "getMiddleInitial");
            assertEquals('Y', temp);

            callSet(e1, "setMiddleInitial", 'z', char.class);
            temp = callMethod(e1, "getMiddleInitial");
            assertEquals('Z', temp);

            callSet(e1, "setMiddleInitial", '3', char.class);
            temp = callMethod(e1, "getMiddleInitial");
            assertEquals("Should not change middle initial if not a letter", 'Z', temp);

        }
        catch (NoClassDefFoundError e)
        {
            failure("Epic Failure: missing " + className + " class");
        }
        catch (ClassNotFoundException e)
        {
            failure("Epic Failure: missing " + className + " class");
        }
        catch (NoSuchMethodException e)
        {
            failure("missing method :" + e.toString());
        }
        catch (Exception e)
        {
            failure(e.toString());
        }

    }
}
