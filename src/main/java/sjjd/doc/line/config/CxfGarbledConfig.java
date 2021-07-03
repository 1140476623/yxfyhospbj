package sjjd.doc.line.config;

import org.apache.cxf.Bus;
import org.apache.cxf.common.util.Compiler;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class CxfGarbledConfig extends DynamicClientFactory {
    protected CxfGarbledConfig(Bus bus) {
        super(bus);
    }

    @Override
    protected boolean compileJavaSrc(String classPath, List<File> srcList, String dest) {
        Compiler javaCompiler = new Compiler();
        javaCompiler.setClassPath(classPath);
        javaCompiler.setOutputDir(dest);
        javaCompiler.setEncoding("UTF-8");
        if (System.getProperty("java.version").startsWith("9")) {
            javaCompiler.setTarget("9");
        } else {
            javaCompiler.setTarget("1.8");
        }

        return javaCompiler.compileFiles(srcList);
    }


}
