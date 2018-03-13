package ru.otus.HW05;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by mix on 07.03.2018.
 */
public class TestPackageRunner {
    private Package aPackage;

    public TestPackageRunner(Package aPackage) {
        this.aPackage = aPackage;
    }

    public TestPackageRunner(String packageName) {
        this.aPackage = Package.getPackage(packageName);
    }

    public TestPackageResult run() throws Throwable {
        ArrayList<Class> classes = getClasses();
        TestPackageResult testPackageResult = new TestPackageResult(aPackage.getName());
        for(Class c : classes) {
            try {
                TestClassRunner runner = new TestClassRunner(c);
                testPackageResult.add(runner.run());
            } catch (NoTestMethodsFoundException e) {
            }
        }
        return testPackageResult;
    }

    private ArrayList<Class> getClasses() throws IOException, ClassNotFoundException {
        List<File> dirs = getDirectories();

        ArrayList<Class> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, aPackage.getName()));
        }

        return classes;
    }

    private List<File> getDirectories() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String path = aPackage.getName().replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        return dirs;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
