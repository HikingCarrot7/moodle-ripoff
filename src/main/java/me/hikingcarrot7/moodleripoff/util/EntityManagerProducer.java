package me.hikingcarrot7.moodleripoff.util;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

import static java.util.Objects.isNull;

@ApplicationScoped
public class EntityManagerProducer {
  @PersistenceUnit
  private EntityManagerFactory emf;

  @PostConstruct
  public void init() {
    if (isNull(emf)) {
      emf = Persistence.createEntityManagerFactory("moodle-ripoff-pu");
    }
  }

  @Produces
  @Default
  public EntityManagerFactory createEntityManagerFactory() {
    return emf;
  }

  @Produces
  @Default
  @Singleton
  public EntityManager createEntityManager() {
    return this.emf.createEntityManager();
  }

  public void close(@Disposes EntityManager em) {
    if (em.isOpen()) {
      em.close();
    }
  }

}
