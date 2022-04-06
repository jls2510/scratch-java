package com.ping23.scratch.reflect;

import java.lang.reflect.Field;

public class ExamineWithReflection {

	public static void examine(Object objectToExamine) {

		if (objectToExamine == null) {
			return;
		}

		Field[] fields = objectToExamine.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); // get access to private fields

			System.out.print("field Name: " + field.getName());
//			System.out.print("; field Type: " + field.getType());
//			System.out.print("; field Modifiers: " + field.getModifiers());
//			System.out.print("; field Annotated Type: " + field.getAnnotatedType());
//			System.out.print("; field Declaring Class: " + field.getDeclaringClass());
//			System.out.print("; field Generic Type: " + field.getGenericType());

//			if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {

			Object fieldValue;
			try {
				fieldValue = field.get(objectToExamine);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				continue;
			}

			if (fieldValue != null) {
				System.out.print("; fieldValue: " + fieldValue);
			}
			
			System.out.print("\n");
		}
	}
}
