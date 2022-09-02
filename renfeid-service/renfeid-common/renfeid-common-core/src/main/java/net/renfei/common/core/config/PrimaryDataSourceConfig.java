/*
 *   Copyright 2022 RenFei(i@renfei.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.renfei.common.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.servlet.Servlet;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 主数据源配置
 *
 * @author renfei
 */
@Configuration
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = "net.renfei.**.repositories", sqlSessionTemplateRef = "primarySessionTemplate", annotationClass = Mapper.class)
public class PrimaryDataSourceConfig {
    private final static Logger logger = LoggerFactory.getLogger(PrimaryDataSourceConfig.class);

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 由于此处的配置几乎不怎么修改，所以直接在代码里硬编码了，懒得写入配置文件中
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(3000);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setValidationQuery("SELECT 1");
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setUseGlobalDataSourceStat(true);
        // Properties
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql", "true");
        properties.setProperty("druid.stat.slowSqlMillis", "5000");
        druidDataSource.setConnectProperties(properties);
        return druidDataSource;
    }

    @Value("${mybatis.mapper-locations}")
    public String locationPattern;

    @Value("${spring.datasource.druid.stat-view-servlet.login-username}")
    public String loginUsername;

    @Value("${spring.datasource.druid.stat-view-servlet.login-password}")
    public String loginPassword;

    @Primary
    @Bean(name = "primarySessionFactory")
    public SqlSessionFactory primarySessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 由于mybatis多数据源的xml扫描需要配置在数据源的配置类里即可，否则会扫描不到。
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(locationPattern));
        return bean.getObject();
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "primarySessionTemplate")
    public SqlSessionTemplate primarySessionTemplate(@Qualifier("primarySessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 配置druid管理页面的访问控制
     * 访问网址: http://127.0.0.1:9595/druid
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean<Servlet> druidServlet() {
        logger.info("init Druid Servlet Configuration");
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>();
        //配置一个拦截器
        servletRegistrationBean.setServlet(new StatViewServlet());
        //指定拦截器只拦截druid管理页面的请求
        servletRegistrationBean.addUrlMappings("/druid/*");
        HashMap<String, String> initParam = new HashMap<>();
        //登录druid管理页面的用户名
        initParam.put("loginUsername", loginUsername);
        //登录druid管理页面的密码
        initParam.put("loginPassword", loginPassword);
        //是否允许重置druid的统计信息
        initParam.put("resetEnable", "true");
        //ip白名单，如果没有设置或为空，则表示允许所有访问
        initParam.put("allow", "");
        servletRegistrationBean.setInitParameters(initParam);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {
        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
