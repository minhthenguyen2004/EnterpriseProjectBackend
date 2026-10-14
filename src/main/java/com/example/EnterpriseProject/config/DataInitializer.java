package com.example.EnterpriseProject.config;

import com.example.EnterpriseProject.entity.*;
import com.example.EnterpriseProject.repository.RoleRepository;
import com.example.EnterpriseProject.repository.UserRepository;
import com.example.EnterpriseProject.enums.RoleName;
import com.example.EnterpriseProject.enums.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private static final String[] FIRST_NAMES = {
            "Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ",
            "Võ", "Đặng", "Bùi", "Đỗ", "Hồ", "Ngô", "Dương", "Lý"
    };

    private static final String[] MIDDLE_NAMES = {
            "Văn", "Thị", "Đức", "Minh", "Quang", "Hải", "Thanh", "Ngọc",
            "Xuân", "Thành", "Hữu", "Gia", "Bảo", "Tuấn", "Hồng", "Khánh"
    };

    private static final String[] LAST_NAMES = {
            "An", "Bình", "Cường", "Dũng", "Dương", "Giang", "Hà", "Hải",
            "Hiếu", "Hoàng", "Hùng", "Huy", "Khoa", "Khánh", "Linh", "Long",
            "Mai", "Minh", "Nam", "Nghĩa", "Phong", "Phúc", "Quân", "Quang",
            "Sơn", "Tâm", "Thắng", "Thảo", "Thịnh", "Trang", "Trung", "Tuấn",
            "Tùng", "Vinh", "Vy", "Yến"
    };

    private static final String[] DEPARTMENTS = {
            "Phòng Kỹ Thuật", "Phòng Kinh Doanh", "Phòng Kế Toán",
            "Phòng Nhân Sự", "Phòng Marketing", "Phòng Chăm Sóc Khách Hàng"
    };

    private static final String[] STEPS = {
            "STEP01", "STEP02", "STEP03", "STEP04", "STEP05",
            "STEP06", "STEP07", "STEP08", "STEP09", "STEP10"
    };

    private static final String[] GROUP_NAMES = {
            "SCANNER_BARCODE", "ASSY_STIFFENER", "AUTO_SCREW_01", "AUTO_SCREW_02", "PRESS_FIT",
            "ASSY_COOLING", "DISPENSING_TIM", "AOI_INSPECTION", "EOL_TEST", "UNLOAD_PACKING"
    };

    private static final double[] CYCLE_TIMES = {
            8.5, 14.0, 12.5, 12.0, 11.0, 15.0, 16.5, 9.0, 18.0, 10.0
    };

    @Bean
    public CommandLineRunner initData(RoleRepository roleRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder passwordEncoder
                                      ) {
        return args -> {
            // 1. Initialize 4 default roles
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

            String defaultEncodedPassword = passwordEncoder.encode("12345678");

            // 2. Initialize core test accounts
            createTestUser(userRepository, defaultEncodedPassword,
                    "CARD001", "admin", "Nguyễn Quản Trị", "admin@example.com", "Ban Quản Trị",
                    adminRole != null ? Set.of(adminRole) : Set.of());

            createTestUser(userRepository, defaultEncodedPassword,
                    "CARD002", "manager", "Trần Quản Lý", "manager@example.com", "Phòng Kỹ Thuật",
                    managerRole != null ? Set.of(managerRole) : Set.of());

            createTestUser(userRepository, defaultEncodedPassword,
                    "CARD003", "employee", "Lê Nhân Viên", "employee@example.com", "Phòng Kinh Doanh",
                    employeeRole != null ? Set.of(employeeRole) : Set.of());

            createTestUser(userRepository, defaultEncodedPassword,
                    "CARD004", "user", "Phạm Người Dùng", "user@example.com", "Khách Hàng",
                    userRole != null ? Set.of(userRole) : Set.of());

            // 3. Initialize 100 mock user accounts
            generate100MockUsers(userRepository, defaultEncodedPassword, adminRole, managerRole, employeeRole, userRole);

        };
    }


    private void generate100MockUsers(UserRepository userRepository,
                                      String defaultEncodedPassword,
                                      Role adminRole,
                                      Role managerRole,
                                      Role employeeRole,
                                      Role userRole) {
        List<User> mockUsers = new ArrayList<>();
        int userIndex = 1;

        // 5 Admin accounts (admin01 -> admin05, CARD101 -> CARD105)
        for (int i = 1; i <= 5; i++, userIndex++) {
            String cardId = String.format("CARD%03d", 100 + userIndex);
            String username = String.format("admin%02d", i);
            mockUsers.add(buildMockUser(userIndex, cardId, username, "Ban Giám Đốc",
                    adminRole != null ? Set.of(adminRole) : Set.of(), defaultEncodedPassword));
        }

        // 15 Manager accounts (manager01 -> manager15, CARD106 -> CARD120)
        for (int i = 1; i <= 15; i++, userIndex++) {
            String cardId = String.format("CARD%03d", 100 + userIndex);
            String username = String.format("manager%02d", i);
            String department = DEPARTMENTS[(i - 1) % DEPARTMENTS.length];
            mockUsers.add(buildMockUser(userIndex, cardId, username, department,
                    managerRole != null ? Set.of(managerRole) : Set.of(), defaultEncodedPassword));
        }

        // 50 Employee accounts (emp01 -> emp50, CARD121 -> CARD170)
        for (int i = 1; i <= 50; i++, userIndex++) {
            String cardId = String.format("CARD%03d", 100 + userIndex);
            String username = String.format("emp%02d", i);
            String department = DEPARTMENTS[(i - 1) % DEPARTMENTS.length];
            mockUsers.add(buildMockUser(userIndex, cardId, username, department,
                    employeeRole != null ? Set.of(employeeRole) : Set.of(), defaultEncodedPassword));
        }

        // 30 User accounts (user01 -> user30, CARD171 -> CARD200)
        for (int i = 1; i <= 30; i++, userIndex++) {
            String cardId = String.format("CARD%03d", 100 + userIndex);
            String username = String.format("user%02d", i);
            mockUsers.add(buildMockUser(userIndex, cardId, username, "Khách Hàng",
                    userRole != null ? Set.of(userRole) : Set.of(), defaultEncodedPassword));
        }

        List<User> toSave = mockUsers.stream()
                .filter(u -> !userRepository.existsByUsername(u.getUsername()) && !userRepository.existsByCardId(u.getCardId()))
                .toList();

        if (!toSave.isEmpty()) {
            userRepository.saveAll(toSave);
            logger.info(">>> Successfully generated and saved {} mock accounts (CARD101 -> CARD200)", toSave.size());
        }
    }

    private User buildMockUser(int index,
                               String cardId,
                               String username,
                               String department,
                               Set<Role> roles,
                               String encodedPassword) {
        String firstName = FIRST_NAMES[(index * 7) % FIRST_NAMES.length];
        String middleName = MIDDLE_NAMES[(index * 3) % MIDDLE_NAMES.length];
        String lastName = LAST_NAMES[(index * 11) % LAST_NAMES.length];
        String fullName = firstName + " " + middleName + " " + lastName;

        String email = username + "@example.com";
        UserStatus status = (index % 20 == 0) ? UserStatus.INACTIVE : UserStatus.ACTIVE;

        User user = new User();
        user.setCardId(cardId);
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setDepartment(department);
        user.setStatus(status);
        user.setRoles(new HashSet<>(roles));

        return user;
    }

    private void createTestUser(UserRepository userRepository,
                                String encodedPassword,
                                String cardId,
                                String username,
                                String fullName,
                                String email,
                                String department,
                                Set<Role> roles) {
        if (!userRepository.existsByCardId(cardId) && !userRepository.existsByUsername(username)) {
            User user = new User();
            user.setCardId(cardId);
            user.setUsername(username);
            user.setPassword(encodedPassword);
            user.setFullName(fullName);
            user.setEmail(email);
            user.setDepartment(department);
            user.setStatus(UserStatus.ACTIVE);
            user.setRoles(new HashSet<>(roles));
            userRepository.save(user);
        }
    }
}
