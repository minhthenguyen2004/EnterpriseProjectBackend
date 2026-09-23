package com.example.EnterpriseProject.config;

import com.example.EnterpriseProject.entity.Role;
import com.example.EnterpriseProject.entity.User;
import com.example.EnterpriseProject.repository.RoleRepository;
import com.example.EnterpriseProject.repository.UserRepository;
import com.example.EnterpriseProject.state.RoleName;
import com.example.EnterpriseProject.state.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Initialize four rules
            List<Role> defaultRoles = List.of(
                    new Role(RoleName.ROLE_ADMIN, "Quản trị viên hệ thống (Toàn quyền quản trị)"),
                    new Role(RoleName.ROLE_MANAGER, "Quản lý phòng ban / bộ phận"),
                    new Role(RoleName.ROLE_EMPLOYEE, "Nhân viên doanh nghiệp"),
                    new Role(RoleName.ROLE_USER, "Người dùng hệ thống")
            );

            for (Role role : defaultRoles) {
                if (!roleRepository.existsByRoleName(role.getRoleName())) {
                    roleRepository.save(role);
                    logger.info("Create role: {}", role.getRoleName());
                }
            }

            Role adminRole = roleRepository.findByRoleName(RoleName.ROLE_ADMIN).orElse(null);
            Role managerRole = roleRepository.findByRoleName(RoleName.ROLE_MANAGER).orElse(null);
            Role employeeRole = roleRepository.findByRoleName(RoleName.ROLE_EMPLOYEE).orElse(null);
            Role userRole = roleRepository.findByRoleName(RoleName.ROLE_USER).orElse(null);


            // Initialize 4 test account with full respective rules
            createTestUser(userRepository, passwordEncoder,
                    "CARD001", "admin", "12345678", "Nguyễn Quản Trị", "admin@example.com", "Ban Quản Trị",
                    adminRole != null ? Set.of(adminRole) : Set.of());

            createTestUser(userRepository, passwordEncoder,
                    "CARD002", "manager", "12345678", "Trần Quản Lý", "manager@example.com", "Phòng Kỹ Thuật",
                    managerRole != null ? Set.of(managerRole) : Set.of());

            createTestUser(userRepository, passwordEncoder,
                    "CARD003", "employee", "12345678", "Lê Nhân Viên", "employee@example.com", "Phòng Kinh Doanh",
                    employeeRole != null ? Set.of(employeeRole) : Set.of());

            createTestUser(userRepository, passwordEncoder,
                    "CARD004", "user", "12345678", "Phạm Người Dùng", "user@example.com", "Khách Hàng",
                    userRole != null ? Set.of(userRole) : Set.of());
        };
    }

    private void createTestUser(UserRepository userRepository,
                                PasswordEncoder passwordEncoder,
                                String cardId,
                                String username,
                                String rawPassword,
                                String fullName,
                                String email,
                                String department,
                                Set<Role> roles) {
        if (!userRepository.existsByCardId(cardId) && !userRepository.existsByUsername(username)) {
            User user = new User();
            user.setCardId(cardId);
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setFullName(fullName);
            user.setEmail(email);
            user.setDepartment(department);
            user.setStatus(UserStatus.ACTIVE);
            user.setRoles(new HashSet<>(roles));

            userRepository.save(user);
            logger.info(">>> Create test accounts: cardId='{}', username='{}', password='{}'", cardId, username, rawPassword);
        }
    }
}
