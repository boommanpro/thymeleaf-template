package [(${referencePackage})];

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author boommanpro
 * @description generator by boommanpro
 */
@Configuration
public class [(${core})]DataSourceConfiguration {

    //配置数据库

    @Bean(value = [(${core})]DataSourceConfig.DB_[(${upperCore})],destroyMethod = "")
    @Primary
    @ConfigurationProperties([(${core})]DataSourceConfig.DB_[(${upperCore})]_PREFIX)
    public DataSource [(${humpCore})]DataSource() {
        DataSourceBuilder<HikariDataSource> factory = DataSourceBuilder.create().type(HikariDataSource.class);
        HikariDataSource gameDataSource = factory.build();
        gameDataSource.setConnectionInitSql("SET NAMES utf8mb4");
        return gameDataSource;
    }

    // TODO: 2018/9/21 正式线需要重新配置插件

    @Bean([(${core})]DataSourceConfig.DB_[(${upperCore})]_SQL_SESSION_FACTORY)
    @Primary
    public SqlSessionFactoryBean [(${humpCore})]SqlSessionFactory(@Qualifier([(${core})]DataSourceConfig.DB_[(${upperCore})]) DataSource dataSource) {
        SqlSessionFactoryBean mybatisSqlSessionFactoryBean = new SqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setSqlSessionFactoryBuilder(new SqlSessionFactoryBuilder());
        return mybatisSqlSessionFactoryBean;
    }




    @Bean([(${core})]DataSourceConfig.TX_[(${upperCore})])
    @Primary
    public DataSourceTransactionManager [(${humpCore})]Tx(@Qualifier([(${core})]DataSourceConfig.DB_[(${upperCore})]) DataSource dataSource) {
        DataSourceTransactionManager basicTx = new DataSourceTransactionManager();
        basicTx.setDataSource(dataSource);
        return basicTx;
    }
}
