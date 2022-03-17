package com.example.application.views.list;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

@PageTitle("Тест Java")
@Route(value = "")
public class MainView extends VerticalLayout {

  public MainView() {
    setSpacing(false);

    MultiFileMemoryBuffer multiFileMemoryBuffer = new MultiFileMemoryBuffer();
    Upload multiUpload = new Upload(multiFileMemoryBuffer);

    multiUpload.addSucceededListener(event -> {
      File root = new File("/java");
      File sourceFile = new File(root,
          parsePackage(multiFileMemoryBuffer.getInputStream(event.getFileName())) + "/" + event.getFileName());
      sourceFile.getParentFile().mkdirs();
      try {
        Files.write(sourceFile.toPath(),
            multiFileMemoryBuffer.getOutputBuffer(event.getFileName()).toString().getBytes(StandardCharsets.UTF_8));

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()});

        Class<?> cls = Class.forName(new Scanner(multiFileMemoryBuffer.getInputStream(event.getFileName())).nextLine()
            .replaceAll("package ", "")
            .replaceAll(";", "") + "." +
            event.getFileName().replaceAll(".java", ""), true, classLoader);

        Object instance = cls.getConstructor().newInstance();

        TextArea textArea = new TextArea("Java Code");
        textArea.setValue(multiFileMemoryBuffer.getOutputBuffer(event.getFileName()).toString(StandardCharsets.UTF_8));
        textArea.setWidth("1000px");
        textArea.setMaxHeight("700px");
        textArea.setReadOnly(true);

        add(textArea);

        Arrays.stream(cls.getDeclaredMethods())
            .filter(method -> !Modifier.isNative(method.getModifiers()))
            .filter(method -> Modifier.isPublic(method.getModifiers()))
            .forEach(method -> {
              long before = System.currentTimeMillis();
              try {
                method.invoke(instance);
              } catch (Exception e) {
                e.printStackTrace();
              } finally {
                double milliSecs = System.currentTimeMillis() - before;
                Span span = new Span("Done:" + milliSecs / 1000 + "s.");
                if (milliSecs < 1000d) {
                  span.getElement().getThemeList().add("badge success");
                  add(span);
                  return;
                }
                span.getElement().getThemeList().add("badge error");
                add(span);
              }
            });
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    add(new H2("Test you java code."));
    add(multiUpload);

    setSizeFull();
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    getStyle().set("text-align", "center");
  }

  private String parsePackage(InputStream source) {
    Scanner scanner = new Scanner(source);
    scanner.next();
    return scanner.next().replaceAll("\\.", "\\/").replaceAll(";", "");

  }

}