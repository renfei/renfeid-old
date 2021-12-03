package net.renfei.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.renfei.ApplicationTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JacksonUtilTests extends ApplicationTests {

    @Test
    public void test() {
        User user1 = new User();
        user1.setId(1);
        user1.setEmail("chenhaifei@163.com");
        String userJsonstr = JacksonUtil.obj2String(user1);
        String userJsonPretty = JacksonUtil.obj2StringPretty(user1);
        log.info("userJson: {}", userJsonPretty);

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

    @Data
    public static class User {
        private Integer id;
        private String email;
    }
}
