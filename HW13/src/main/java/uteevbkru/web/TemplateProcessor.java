package uteevbkru.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


class TemplateProcessor {
    private static TemplateProcessor instance = new TemplateProcessor();
    private static final Configuration configuration = new Configuration();

    static {
        try {
            configuration.setDirectoryForTemplateLoading(
                    new File(TemplateProcessor.class.getClassLoader().getResource("tml").getFile())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TemplateProcessor() { }


    static TemplateProcessor instance() {
        return instance;
    }

    String getPage(String filename, Map<String, Object> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
            return "";
        }
    }
}
