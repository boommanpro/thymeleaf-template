package [(${referencePackage})];



import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author boommanpro
 * @description generator by boommanpro
 */
@Configuration
@ConditionalOnBean(SqlSessionFactoryBean.class)
@MapperScan(basePackages = [(${core})]DataSourceConfig.[(${upperCore})]_DAO_PACKAGE,sqlSessionFactoryRef = [(${core})]DataSourceConfig.DB_[(${upperCore})]_SQL_SESSION_FACTORY)
public class [(${core})]MybatisConfiguration {
}
