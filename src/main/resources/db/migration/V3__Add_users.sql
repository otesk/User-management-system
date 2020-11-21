insert into user_account (username, password, first_name, last_name, created_at, role, status)
values ('user', '$2a$12$7K1nbG0HsTrKEwhAfCjehOgT7tOv6Fbtaghk9NNDuKcexvyN6VoHa', 'Ivan', 'Ivanov', '2020-11-09', 'USER', 'ACTIVE'),
       ('lockeduser', '$2a$12$w/9.O9agG6z6q5XSwdJD7.KoC72F0tm5o8OA3GKT/YcxfRUcaAyBS', 'Roman', 'Petrov', '2020-11-10', 'USER', 'INACTIVE');