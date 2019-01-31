import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * javac AnnotationProcessor.java
 * javac -processor AnnotationProcessor DataClass.java
 */

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        "ClassAnnotation"
})
public class AnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println(roundEnv.toString());
        System.out.println("Annotations size: " + annotations.size());
        final Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(ClassAnnotation.class);
        System.out.println("Total elements annotated with " + ClassAnnotation.class.getCanonicalName() + ": " + elements.size());
        for (Element element : elements) {
            System.out.println(element.getSimpleName().toString());
        }
        return false;
    }
}
