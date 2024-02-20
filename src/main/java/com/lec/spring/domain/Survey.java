package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;


@Data
// ↑ Lombok 어노테이션으로, 클래스에 대한 모든 기본 메서드들을 생성합니다. (게터, 세터, toString, equals 등)
@AllArgsConstructor
// ↑ Lombok 어노테이션으로, 모든 필드를 매개변수로 받는 생성자를 생성합니다.
// 객체의 불변성(Immutability)을 유지하고자 함과 생성자 호출 시 초기화를 간편하게 하기 위해서입니다.
// 불변성을 유지하면 객체의 상태가 변경되지 않기 때문에 코드의 안정성이 향상되며, 초기화 과정이 간소화되어 코드의 가독성이 향상됩니다.
// @AllArgsConstructor은 모든 필드를 초기화하는 생성자를 생성하므로, 객체를 생성하면서 모든 필드를 한 번에 초기화할 수 있습니다.
@NoArgsConstructor
// ↑ Lombok 어노테이션으로, 파라미터 없는 기본 생성자를 생성합니다.
// JPA는 객체를 데이터베이스로 매핑하고 역으로 매핑된 데이터를 객체로 복원할 때, 기본 생성자를 사용합니다.
// 따라서 @NoArgsConstructor를 사용하면 JPA에서 자동으로 필요한 초기화를 수행할 수 있습니다.
// @NoArgsConstructor는 파라미터가 없는 생성자를 생성하여 객체를 먼저 생성하고, 필요한 필드를 나중에 초기화할 수 있습니다.
// ★ @AllArgsConstructor과 @NoArgsConstructor을 같이 사용하는 경우, Lombok은 해당 클래스에 대해 매개변수가 있는 생성자와 매개변수가 없는 기본 생성자를 모두 생성합니다.
// 이는 다양한 상황에서 편의성을 제공하고 유연성을 높입니다.
// JPA는 엔터티 클래스에 기본 생성자와 매개변수가 있는 생성자를 필요로 합니다. @AllArgsConstructor과 @NoArgsConstructor를 함께 사용하면 JPA에서 엔터티 클래스를 효과적으로 다룰 수 있습니다.
@Entity(name = "R_Survey")
// ↑ JPA에서 엔터티 클래스임을 나타내는 어노테이션으로, 데이터베이스 테이블과 매핑됩니다. name 속성은 엔터티의 이름을 지정합니다.
public class Survey {

    @Id
    // ↑ JPA에서 기본 키(primary key)를 나타내는 어노테이션입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // ↑ JPA에서 기본 키의 자동 생성 전략을 지정하는 어노테이션으로, 여기서는 IDENTITY 전략을 사용하여 데이터베이스가 자동으로 기본 키를 생성하도록 합니다.
    private Long id;

    @NonNull
    // ↑ Lombok 어노테이션으로, 해당 필드는 null이 아니어야 함을 나타냅니다.
    private String name;

    private int age;

    // enum타입 클래스의 필드를 String타입으로 사용한다. 기본값은 String이 아니고 Ordinal(순서, 0부터 시작)이다.
    @Enumerated(value = EnumType.STRING)
    // ↑ JPA에서 Enum 타입의 필드를 매핑하는데 사용되는 어노테이션으로, 여기서는 문자열로 매핑합니다.
    @ColumnDefault(value = "MALE")
    // ↑ Hibernate 어노테이션으로, 해당 필드의 기본값을 설정합니다. 여기서는 Gender 열거형의 기본값을 "MALE"로 설정합니다.
    private Gender gender;

    private String area;

    private String favorite;

    private String createdAt;

}
