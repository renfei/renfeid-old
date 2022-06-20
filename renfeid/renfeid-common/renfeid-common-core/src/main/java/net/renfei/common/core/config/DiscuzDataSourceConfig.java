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
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Discuz论坛数据源配置
 *
 * @author renfei
 */
@Configuration
@MapperScan(basePackages = "net.renfei.proprietary.discuz.repositories", sqlSessionTemplateRef = "discuzSessionTemplate", annotationClass = Mapper.class)
public class DiscuzDataSourceConfig {
    @Bean(name = "discuzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.discuz")
    public DataSource discuzDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        // 由于此处的配置几乎不怎么修改，所以直接在代码里硬编码了，懒得写入配置文件中
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(10);
        druidDataSource.setMaxWait(3000);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setValidationQuery("SELECT 1");
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        druidDataSource.setPoolPreparedStatements(true);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        druidDataSource.setUseGlobalDataSourceStat(true);
        // Properties
        Properties properties = new Properties();
        properties.setProperty("druid.stat.mergeSql","true");
        properties.setProperty("druid.stat.slowSqlMillis","5000");
        druidDataSource.setConnectProperties(properties);
        return druidDataSource;
    }

    @Bean(name = "discuzSessionFactory")
    public SqlSessionFactory discuzSessionFactory(@Qualifier("discuzDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/discuz/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "discuzTransactionManager")
    public DataSourceTransactionManager discuzTransactionManager(@Qualifier("discuzDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "discuzSessionTemplate")
    public SqlSessionTemplate discuzSessionTemplate(@Qualifier("discuzSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
