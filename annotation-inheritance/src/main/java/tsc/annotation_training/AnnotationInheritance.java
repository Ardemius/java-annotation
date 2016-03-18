package tsc.annotation_training;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationInheritance {

   public static void main(String[] args) {

      TestInterface test = new TestSubclass();
      testAnnotationInheritance(test.getClass());

      testAnnotationInheritance(TestSubclass.class);
      testAnnotationInheritance(TestSuperclass.class);
      testAnnotationInheritance(TestInterface.class);
   }

   private static void testAnnotationInheritance(Class<?> clazz) {

      System.out.println("Class under test = " + clazz);

      // Class level
      for (Annotation annotation : clazz.getAnnotations()) {
         System.out.println("    - Class getAnnotations: " + annotation);
      }

      for (Annotation annotation : clazz.getDeclaredAnnotations()) {
         System.out.println("    - Class getDeclaredAnnotations: " + annotation);
      }

      // Method level
      for (Method method : clazz.getMethods()) {
         for (Annotation annotation : method.getAnnotations()) {
            System.out.println("    - Method getAnnotations: " + annotation);
         }

         for (Annotation annotation : method.getDeclaredAnnotations()) {
            System.out.println("    - Method getDeclaredAnnotations: " + annotation);
         }
      }

      // Field level
      for (Field field : clazz.getFields()) {
         for (Annotation annotation : field.getAnnotations()) {
            System.out.println("    - Field getAnnotations: " + annotation);
         }

         for (Annotation annotation : field.getDeclaredAnnotations()) {
            System.out.println("    - Field getDeclaredAnnotations: " + annotation);
         }
      }
   }
}

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface CustomAnnotation {
   String value();
}

interface TestInterface {
   @CustomAnnotation("Method")
   void testMethod();
}

@CustomAnnotation("Class")
class TestSuperclass {

   @CustomAnnotation("Field")
   public String testField;

}

class TestSubclass extends TestSuperclass implements TestInterface {

   // The CustomAnnotation defined in TestInterface.testMethod() is NOT inherited by this TestSubclass.testMethod()
   @Override
   public void testMethod() {}

}