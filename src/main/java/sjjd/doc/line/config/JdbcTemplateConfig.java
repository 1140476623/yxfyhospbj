package sjjd.doc.line.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @program: line
 * @description: 数据库连接JdcbTemplate NamedJdbcTeamplate
 * @author: yellowsun
 * @create: 2019-12-24 09:37
 */
@Configuration
public class JdbcTemplateConfig {

    /**
     * @Description: JdcbTemplate 模板 数据源
     * @Param: []
     * @return: javax.sql.DataSource
     * @Author: yellowsun
     * @date: 15:22
     */
    @Bean(name = "jdbcTemplateDataSource")
    public DataSource dataSource() {
        Properties properties = new Properties();
        try {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("db.properties");
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverclassname"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setUrl(properties.getProperty("connecturl"));
        return dataSource;
    }

    /**
     * @Description: 简单jdbcTemplate模板
     * @Param: [dataSource]
     * @return: org.springframework.jdbc.core.JdbcTemplate
     * @Author: yellowsun
     * @date: 15:24
     */
    @Bean(name = "hisJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("jdbcTemplateDataSource")
                                             DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    /**
     * @Description: 参数名绑定 NamedParameterJdbcTemplate 模板(可在sql中动态传参)
     * @Param: [dataSource]
     * @return: org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
     * @Author: yellowsun
     * @date: 15:23
     */
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("jdbcTemplateDataSource") DataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return namedParameterJdbcTemplate;
    }

}
