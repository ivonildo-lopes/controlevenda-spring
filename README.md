# controlevenda-spring

caso dê algum erro no pom (obs.: aconteceu quando baixei o projeto no eclipse)
realizar o seguinte passo
Menu Help -> new software -> colar esse link http://repo1.maven.org/maven2/.m2e/connectors/m2eclipse-mavenarchiver/0.17.2/N/LATEST/ -> 
realizar a instalação normalmente e quando da o ok quando pedir para reiniciar o eclipse -> dar um update project no projeto e estará funcionando


#Para abrir o swagger

localhost:8080/swagger-ui.html

#Para abrir o banco de dados h2 #

localhost:8080/h2-console

------------------------------------------------------------------------------------------------
#Configurando o Swagger
-----------------------------------------------------------------------------

no pom.xml

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.7.0</version>
		</dependency>

		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.7.0</version>
			<scope>compile</scope>
		</dependency>
		
depois criar uma class de configuração

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.tags(new Tag("Address Entity", "Repository for Address entities"))
				.apiInfo(new ApiInfo("Documentação API", "REST API of the Customer Service", "v0.0.1", null, null, null,
						null, Lists.newArrayList()));
	}
}	


na classe principal anotar com o @EnableSwagger2

@SpringBootApplication
@EnableSwagger2
public class ControlevendaApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new ControlevendaApplication().configure(new SpringApplicationBuilder(ControlevendaApplication.class)).run(args);
	}
}	

----------------------------------------------------------------------------------------
#Configurando o Mybatis
-----------------------------------------------------------------------
no pom.xml

	<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>

		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<version>1.3.1</version>
		</dependency>
		
no application.properties:

mybatis.mapperLocations=classpath*:**/mappers/*.xml
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.default-fetch-size=100
		
