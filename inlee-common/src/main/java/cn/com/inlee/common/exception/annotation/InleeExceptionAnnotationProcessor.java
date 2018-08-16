package cn.com.inlee.common.exception.annotation;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;



@SupportedAnnotationTypes({"cn.com.inlee.common.exception.annotation.InleeExceptionAnnotation"}) 
public class InleeExceptionAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("Test log in MyProcessor.process");
		return false;
	}

}
