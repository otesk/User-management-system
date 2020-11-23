package com.otesk.ums.repositories;

import com.otesk.ums.domain.Role;
import com.otesk.ums.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository of {@link UserAccount}
 */
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);

    Page<UserAccount> findAll(Pageable pageable);

    List<UserAccount> findAllByRole(Role role);
}
