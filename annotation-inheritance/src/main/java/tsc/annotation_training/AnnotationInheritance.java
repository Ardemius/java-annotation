package tsc.annotation_training;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationInheritance {

   public static void main(String[] args) {
      TestSubclass test = new TestSubclass();

      for (Annotation annotation : test.getClass().getAnnotations()) {
         System.out.println("Class getAnnotations: " + annotation);
      }

      for (Annotation annotation : test.getClass().getDeclaredAnnotations()) {
         System.out.println("Class getDeclaredAnnotations: " + annotation);
      }

      for (Field field : test.getClass().getFields()) {
         for (Annotation annotation : field.getAnnotations()) {
            System.out.println("Field getAnnotations: " + annotation);
         }

         for (Annotation annotation : field.getDeclaredAnnotations()) {
            System.out.println("Field getDeclaredAnnotations: " + annotation);
         }
      }

      for (Method method : test.getClass().getMethods()) {
         for (Annotation annotation : method.getAnnotations()) {
            System.out.println("Method getAnnotations: " + annotation);
         }

         for (Annotation annotation : method.getDeclaredAnnotations()) {
            System.out.println("Method getDeclaredAnnotations: " + annotation);
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