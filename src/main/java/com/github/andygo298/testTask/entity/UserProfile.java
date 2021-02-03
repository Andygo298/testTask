package com.github.andygo298.testTask.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "USER_PROFILE")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;
    @Column(name = "nick_name")
    String nickName;
    @Column(name = "telegram_nick_name")
    String telegramNickName;
    @Column(name = "chat_id")
    String chatId;
}
