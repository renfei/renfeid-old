package net.renfei.config;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author renfei
 */
public class RenFeiBanner implements Banner {
    private static final String[] BANNER = new String[]{"    ____                   ____          _                     __ ", "   / __ \\  ___    ____    / __/  ___    (_)      ____   ___   / /_", "  / /_/ / / _ \\  / __ \\  / /_   / _ \\  / /      / __ \\ / _ \\ / __/", " / _, _/ /  __/ / / / / / __/  /  __/ / /   _  / / / //  __// /_  ", "/_/ |_|  \\___/ /_/ /_/ /_/     \\___/ /_/   (_)/_/ /_/ \\___/ \\__/  "};

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion(), url = "== https://www.renfei.net ==";
        version = version != null ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder(), paddingUrl = new StringBuilder();
        while (padding.length() < 64 - (version.length() + " :: Spring Boot :: ".length())) {
            padding.append(" ");
        }
        while (paddingUrl.length() < 64 - url.length()) {
            paddingUrl.append("=");
        }
        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, " :: Spring Boot :: ", AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println(url + paddingUrl);
        printStream.println();
    }
}
