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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 主数据源配置
 *
 * @author renfei
 */
@Configuration
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan(basePackages = "net.renfei.*.repositories", sqlSessionTemplateRef = "primarySessionTemplate", annotationClass = Mapper.class)
public class PrimaryDataSourceConfig {
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 由于此处的配置几乎不怎么修改，所以直接在代码里硬编码了，懒得写入配置文件中
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(60000);
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
        properties.setProperty("druid.stat.mergeSql","true");
        properties.setProperty("druid.stat.slowSqlMillis","5000");
        druidDataSource.setConnectProperties(properties);
        return druidDataSource;
    }

    @Value("${mybatis.mapper-locations}")
    public String locationPattern;

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
}
