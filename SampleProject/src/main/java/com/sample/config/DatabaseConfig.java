package com.sample.config;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.json.JsonSimpleJsonParser;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import argo.jdom.JdomParser;
import argo.jdom.JsonNode;
import argo.jdom.JsonRootNode;

/**
 * Database & Reposiory Config
 * 
 * @author eltriny
 */
@Configuration
public class DatabaseConfig {

	private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

	/*
	 * +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+= 
	 * Primary Datasource 정의 : T-Fabric
	 * +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
	 */
/*
	public DataSource dataSource() {
		
		String vcap_services = System.getenv("VCAP_SERVICES");
        
        System.out.println( "VCAP_SERVICES : " + vcap_services );

        DataSource db = null;

        if (vcap_services != null && vcap_services.length() > 0) {
        	
            try {

                // Use a JSON parser to get the info we need from  the
                // VCAP_SERVICES environment variable. This variable contains
                // credentials for all services bound to the application.
                // In this case, MySQL is the only bound service.
                JsonRootNode root = new JdomParser().parse(vcap_services);
                
                JsonNode oracleNode = root.getNode( "mysql_service" );
                JsonNode credentials = oracleNode.getNode(0).getNode( "credentials" );
                
                System.out.println("Credentials : " + credentials);
                
                // Grab login info for MySQL from the credentials node
                String hostname = credentials.getStringValue("host");
                System.out.println("host " + hostname);

                String name = credentials.getStringValue("database");
                System.out.println("database " + name);
                
                String user = credentials.getStringValue("username");
                System.out.println("username " + user);
                
                String password = credentials.getStringValue("password");
                System.out.println("password " + password);
                
                String jdbcUrl = credentials.getStringValue("jdbc_url");
                System.out.println("jdbc_url " + jdbcUrl);
                
                String port = "3306";

                String dbUrl = "jdbc:mysql://" + hostname + ":"  + port + "/" + name;
                System.out.println("result dbUrl : " + dbUrl);

                // Connect to Oracle
                System.out.println("Connecting to Mysql...");
                
                db = DataSourceBuilder.create()
	            	.driverClassName( "com.mysql.jdbc.Driver" )
	            	.url( dbUrl )
	            	.username( user )
	            	.password( password )
	            	.build();
            } 
            catch ( Exception ex ) {
            	System.out.println("Caught error: " + ex);
                ex.printStackTrace();
            }
        }
        
        return db;         
	}
*/		

	@Bean(destroyMethod = "close")
	@Primary
	@ConfigurationProperties( prefix = "spring.datasource" )
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	} // Method - datasource


	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

		Map<String, String> propertiesHashMap = new HashMap<String, String>();
		propertiesHashMap.put( "hibernate.ejb.naming_strategy", DEFAULT_NAMING_STRATEGY );

		return builder
				.dataSource( dataSource() )
				.packages( "com.sample.repository" )
				.properties( propertiesHashMap )
				.build();
		
	} // Method - entityManagerFactory

	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager( entityManagerFactory(builder).getObject() );
	} // Method - transactionManager

	@Configuration
	@EnableJpaRepositories( basePackages = "com.sample.repository" )
	static class jpaRepositoriesConfig {}

}