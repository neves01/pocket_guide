package org.acme.repoitory;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.entity.CustomerEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<CustomerEntity> {
}
