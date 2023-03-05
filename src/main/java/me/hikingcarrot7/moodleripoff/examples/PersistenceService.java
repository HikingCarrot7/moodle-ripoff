package me.hikingcarrot7.moodleripoff.examples;

import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
//@Startup
//@DataSourceDefinition(name = "java:global/datasources/JakartaJumpstart",
//		className = "com.mysql.cj.jdbc.MysqlDataSource",
//		portNumber = 3306,
//		serverName = "127.0.0.1",
//		databaseName= "jbank",
//		user = "trump",
//		password = "wHouse")
public class PersistenceService {
  @PersistenceContext
  private EntityManager entityManager;

  public HelloEntity save(final HelloEntity helloEntity) {
    entityManager.persist(helloEntity);
    return helloEntity;
  }

  public HelloEntity find(final Long id) {
    return entityManager.find(HelloEntity.class, id);
  }

}
