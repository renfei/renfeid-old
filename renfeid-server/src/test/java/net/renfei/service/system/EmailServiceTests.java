package net.renfei.service.system;

import net.renfei.ApplicationTests;
import net.renfei.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class EmailServiceTests extends ApplicationTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendTest() {
        String to = "unittesting@163.com",
                name = "UnitTesting",
                subject = "Email Unit Testing";
        List<String> contents = new ArrayList<String>() {{
            this.add("Email Unit Testing");
            this.add("Content");
        }};
        assert emailService.send(to, name, subject, contents);
        assert emailService.send(to, name, subject, "Email Unit Testing Content");
    }
}
