package kr.co.cbnu.hive.configurations;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@MapperScan("kr.co.cbnu.hive.repositories")
public class MyBatisConfiguration {

	@Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        
        //TODO: TO SOVLE WITH DON'T KNOW THE PROPERTY MAPPING WITH NULL VALUE
        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject();
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sessionFactory;
    }

    @Bean("datasource")
    @Profile("pgsql")
    public DataSource dataSource() {

        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("org.postgresql.Driver");
        managerDataSource.setUrl("jdbc:postgresql://localhost:5432/import_from_hadoop_db");
        managerDataSource.setUsername("postgres");
        managerDataSource.setPassword("root");

        return managerDataSource;
    }
}
