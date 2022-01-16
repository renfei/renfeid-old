package net.renfei.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class JacksonUtilTests extends ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(JacksonUtilTests.class);

    @Test
    public void test() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("chenhaifei@163.com");
        String userJsonstr = JacksonUtil.obj2String(user1);
        String userJsonPretty = JacksonUtil.obj2StringPretty(user1);
        logger.info("userJson: {}", userJsonPretty);

        User user2 = JacksonUtil.string2Obj(userJsonstr, User.class);
        user2.setId(2);
        user2.setEmail("tianxiaorui@126.com");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        String userListJson = JacksonUtil.obj2String(userList);
        List<User> userListBean = JacksonUtil.string2Obj(userListJson, new TypeReference<List<User>>() {
        });
        if (userListBean != null) {
            userListBean.forEach(user -> {
                System.out.println(user.getId() + " : " + user.getEmail());
            });
        }
        List<User> userListBean2 = JacksonUtil.string2Obj(userListJson, List.class, User.class);
        if (userListBean2 != null) {
            userListBean2.forEach(user -> {
                System.out.println(user.getId() + " : " + user.getEmail());
            });
        }
    }

    public static class User {
        private Integer id;
        private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
