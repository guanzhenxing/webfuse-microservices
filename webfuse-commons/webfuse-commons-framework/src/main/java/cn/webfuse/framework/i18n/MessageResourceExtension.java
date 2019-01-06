package cn.webfuse.framework.i18n;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * 代码来源：https://github.com/zzzzbw/Spring-Boot-I18n-Pro
 */
@Component("messageSource")
public class MessageResourceExtension extends ResourceBundleMessageSource {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageResourceExtension.class);

    /**
     * 指定的国际化文件目录
     */
    @Value(value = "${spring.messages.base-folder:i18n}")
    private String baseFolder;

    /**
     * 父MessageSource指定的国际化文件
     */
    @Value(value = "${spring.messages.basename:messages}")
    private String basename;

    @PostConstruct
    public void init() {
        LOGGER.info("init MessageResourceExtension...");
        if (!StringUtils.isEmpty(baseFolder)) {
            try {
                this.setBasenames(getAllBaseNames(baseFolder));
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        //设置父MessageSource
        ResourceBundleMessageSource parent = new ResourceBundleMessageSource();
        parent.setBasename(basename);
        this.setParentMessageSource(parent);
    }

    /**
     * 获取文件夹下所有的国际化文件名
     *
     * @param folderName 文件名
     * @return
     * @throws IOException
     */
    private String[] getAllBaseNames(final String folderName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader()
                .getResource(folderName);
        if (null == url) {
            throw new RuntimeException("无法获取资源文件路径");
        }

        List<String> baseNames = new ArrayList<>();
        if ("file".equalsIgnoreCase(url.getProtocol())) {
            // 文件夹形式,用File获取资源路径
            File file = new File(url.getFile());
            if (file.exists() && file.isDirectory()) {
                baseNames = Files.walk(file.toPath())
                        .filter(path -> path.toFile().isFile())
                        .map(Path::toString)
                        .map(path -> path.substring(path.indexOf(folderName)))
                        .map(this::getI18FileName)
                        .distinct()
                        .collect(Collectors.toList());
            } else {
                LOGGER.error("指定的baseFile不存在或者不是文件夹");
            }
        } else if ("jar".equalsIgnoreCase(url.getProtocol())) {
            // jar包形式，用JarEntry获取资源路径
            String jarPath = url.getFile().substring(url.getFile().indexOf(":") + 2, url.getFile().indexOf("!"));
            JarFile jarFile = new JarFile(new File(jarPath));
            List<String> baseJars = jarFile.stream()
                    .map(ZipEntry::toString)
                    .filter(jar -> jar.endsWith(folderName + "/")).collect(Collectors.toList());
            if (baseJars.isEmpty()) {
                LOGGER.info("不存在{}资源文件夹", folderName);
                return new String[0];
            }

            baseNames = jarFile.stream().map(ZipEntry::toString)
                    .filter(jar -> baseJars.stream().anyMatch(jar::startsWith))
                    .filter(jar -> jar.endsWith(".properties"))
                    .map(jar -> jar.substring(jar.indexOf(folderName)))
                    .map(this::getI18FileName)
                    .distinct()
                    .collect(Collectors.toList());

        }
        return baseNames.toArray(new String[0]);
    }

    /**
     * 把普通文件名转换成国际化文件名
     *
     * @param filename
     * @return
     */
    private String getI18FileName(String filename) {
        filename = filename.replace(".properties", "");
        for (int i = 0; i < 2; i++) {
            int index = filename.lastIndexOf("_");
            if (index != -1) {
                filename = filename.substring(0, index);
            }
        }
        return filename.replace("\\", "/");
    }

    /**
     * 获得message
     *
     * @param code message的key
     * @return message的值
     */
    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return this.getMessage(code, null, locale);
    }

}

