package net.renfei.service.system;

import net.renfei.ApplicationTests;
import net.renfei.services.SysService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author renfei
 */
public class SysServiceTests extends ApplicationTests {
    @Autowired
    private SysService sysService;

    @Test
    public void execCmdTest() throws IOException {
        String[] cmd = new String[]{
                "ls", "~/"
        };
        sysService.execCmd(cmd);
        sysService.execCmd("ls ~/");
    }
}
