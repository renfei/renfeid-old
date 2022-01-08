package net.renfei.config;

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

/**
 * Discuz论坛数据源配置
 *
 * @author renfei
 */
@Configuration
@MapperScan(basePackages = "net.renfei.discuz.repositories", sqlSessionTemplateRef = "discuzSessionTemplate", annotationClass = Mapper.class)
public class DiscuzDataSourceConfig {
    @Bean(name = "discuzDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.discuz")
    public DataSource discuzDataSource() {
        return new DruidDataSource();
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
