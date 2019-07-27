package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
