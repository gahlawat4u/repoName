package test.com.gms.xms.cache;

import com.gms.xms.cache.LocalizationCache;
import test.com.gms.xms.TestBase;

public class TestLanguageCache extends TestBase {
    public static void main(String[] args) {
        // TestLanguageCache testLanguageCache = new TestLanguageCache();
        // testLanguageCache.getApplicationContext().start();
        System.out.println(LocalizationCache.getInstance().toString());
    }
}
