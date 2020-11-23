delete from user_account;

insert into user_account (id, username, password, first_name, last_name, created_at, role, status)
values (1, 'admin', '$2y$12$NMhKqGIlC8y52.Wog0uMGe5h42DyClZsWx2LbTWk.iEGlSlQke5Qy', 'Aleksey', 'Dvornichenko', '2020-11-09', 'ADMIN', 'ACTIVE'),
       (2, 'user', '$2a$12$7K1nbG0HsTrKEwhAfCjehOgT7tOv6Fbtaghk9NNDuKcexvyN6VoHa', 'Ivan', 'Ivanov', '2020-11-09', 'USER', 'ACTIVE'),
       (3, 'lockeduser', '$2a$12$w/9.O9agG6z6q5XSwdJD7.KoC72F0tm5o8OA3GKT/YcxfRUcaAyBS', 'Roman', 'Petrov', '2020-11-10', 'USER', 'INACTIVE');